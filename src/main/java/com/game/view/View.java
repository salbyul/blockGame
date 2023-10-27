package com.game.view;

import com.game.board.Point;
import com.game.view.exception.InputException;
import com.game.view.translator.InputTranslator;

import java.io.*;

/**
 * 입출력을 담당하는 클래스
 */
public class View implements Closeable {

    private static final String GAME_START_MESSAGE = "GAME START!!";
    private static final String CHOOSE_FIRST_PLAYER_MESSAGE = "먼저 공격하시겠습니까?\n(1)예 - 선공 (2)아니오 - 후공";
    private static final String DELIMITER = "--------------------";
    private static final String OCCUPY_INPUT_MESSAGE = "숫자를 입력해주세요.";
    private static final String GAME_OVER_MESSAGE = "게임이 종료되었습니다.";

    private final GameWriter writer;
    private final BufferedReader reader;
    private final InputTranslator inputTranslator;

    public View(final InputStream inputStream, final OutputStream outputStream, final InputTranslator inputTranslator) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.writer = new GameWriter(outputStream);
        this.inputTranslator = inputTranslator;
    }

    /**
     * 선공을 입력받는 메서드
     * 입력을 받을 때 유저의 입력값으로 인해 나타난 모든 예외를 InputException 으로 변환하여 다시 예외를 발생시킨다.
     * InputException 예외가 발생할 경우 현재 메서드로 다시 진입한다.
     *
     * @return 선공일 경우 true를 반환
     * @throws IOException 입출력으로 인한 예외
     */
    public boolean receiveFirstPlayer() throws IOException {
        try {
            printDelimiter(); // 구분선 출력
            printGameStartMessage(); // 게임시작 메시지 출력
            String input = reader.readLine().trim(); // 유저의 입력을 받는다.
            return inputTranslator.translateInputFirstPlayer(input); // 선공일 경우 true 반환
        } catch (InputException e) {
            writer.println(e.getMessage()); //  예외 메시지 출력
            return receiveFirstPlayer(); // 현재 메서드로 재진입
        }
    }

    /**
     * 게임 시작 메시지를 출력하는 메서드
     *
     * @throws IOException 입출력으로 인한 예외
     */
    private void printGameStartMessage() throws IOException {
        writer.println(GAME_START_MESSAGE); // 게임 시작 메시지 출력
        writer.println(CHOOSE_FIRST_PLAYER_MESSAGE); // 선공 여부를 입력하라는 메시지를 출력
    }

    /**
     * 블럭판 출력 메서드
     *
     * @param board 출력할 블럭판
     * @throws IOException 입출력으로 인한 예외
     */
    public void printBoard(final boolean[][] board) throws IOException {
        printFirstLineOfBoard(board); // 첫째 줄 출력 ex 0 1 2 3 4
        for (int i = 0; i < board.length; i++) {
            writer.println(i + " [" + getLineForConsole(board[i]) + "]");
        }
        /* [ 위의 for 문 ]
         * 나머지 줄 출력 ex)
         * 1 [o o o o o]
         * 2 [o o o o o]
         * 3 [o o o o o]
         * 4 [o o o o o]
         */
    }

    /**
     * 블럭판의 첫째 줄 출력 메서드
     *
     * @param board 출력할 블럭판
     * @throws IOException 입출력으로 인한 예외
     */
    private void printFirstLineOfBoard(final boolean[][] board) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("   ");
        for (int i = 0; i < board.length; i++) { // 블럭판의 길이를 이용하여 '0, 1, 2...' 출력
            stringBuilder.append(i).append(" ");
        }
        writer.println(stringBuilder.toString());
    }

    /**
     * 해당 블럭판 줄의 점령 여부 반환 메서드
     *
     * @param line 점령 여부를 확인할 배열
     * @return 해당 줄의 점령 여부를 String으로 변환한 값
     */
    private String getLineForConsole(final boolean[] line) {
        StringBuilder stringBuilder = new StringBuilder();
        for (boolean b : line) {
            stringBuilder.append(b ? "x " : "o "); // 해당 지점이 점령되어 있을 경우 true 값이므로 true 값이면 'x' 아닐 경우 'o' 추가
        }
        return stringBuilder.toString().trim(); // String 으로 변환하여 반환
    }

    /**
     * 구분선 출력 메서드
     *
     * @throws IOException 입출력으로 인한 예외
     */
    public void printDelimiter() throws IOException {
        writer.println(DELIMITER);
    }

    /**
     * 해당 Point 출력 메서드
     *
     * @param point 출력할 Point 객체
     * @throws IOException 입출력으로 인한 예외
     */
    public void printBlock(final Point point) throws IOException {
        writer.println("[ " + point.getBlockShape().getBlockNumber() + " ][ " + point.getX() + " ][ " + point.getY() + " ]");
        // ex) [ 2 ][ 4 ][ 2 ]
    }

    /**
     * 유저가 점령할 지점의 입력을 받는 메서드
     * 유저의 입력값을 Point 객체로 변환하는 과정에서 InputException 이 발생할 경우 현재 메서드로 재진입한다.
     *
     * @return 유저의 입력값을 Point 객체로 변환하여 반환
     * @throws IOException 입출력으로 인한 예외
     */
    public Point receivePointUserInput() throws IOException {
        try {
            printGetUserInputMessage(); // 입력값 유도 메시지
            String input = reader.readLine(); // 유저에게 입력을 받는다.
            return inputTranslator.translateUserInput(input); // 유저의 입력값을 Point 객체로 변환하여 반환
        } catch (InputException e) {
            writer.println(e.getMessage()); // 예외 메시지 출력
            return receivePointUserInput(); // 현재 메시지로 재진입
        }
    }

    /**
     * 유저에게 블럭을 놓을 위치를 유도하는 메시지 출력 메서드
     *
     * @throws IOException 입출력으로 인한 예외
     */
    private void printGetUserInputMessage() throws IOException {
        writer.println(OCCUPY_INPUT_MESSAGE);
    }

    /**
     * 게임 종료 출력 메서드
     *
     * @throws IOException 입출력으로 인한 예외
     */
    public void printGameOver() throws IOException {
        writer.println(GAME_OVER_MESSAGE);
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
        this.writer.close();
    }
}
