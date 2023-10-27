package com.game;

import com.game.block.BlockShape;
import com.game.board.Board;
import com.game.board.OccupationCalculator;
import com.game.board.Point;
import com.game.board.Result;
import com.game.view.View;

import java.io.IOException;
import java.util.List;

/**
 * 게임을 총괄하는 클래스
 */
public class Game {

    private final List<BlockShape> blockShapeList;
    private final Board board;
    private final OccupationCalculator occupationCalculator;
    private final View view;
    private final RandomGenerator randomGenerator;

    public Game(final List<BlockShape> blockShapeList,
                final Board board,
                final OccupationCalculator occupationCalculator,
                final View view,
                final RandomGenerator randomGenerator) {
        this.blockShapeList = blockShapeList;
        this.board = board;
        this.occupationCalculator = occupationCalculator;
        this.view = view;
        this.randomGenerator = randomGenerator;
    }

    /**
     * 게임 시작 메서드
     * 선공일 경우 랜덤한 지점에 블럭 추가 후 입력을 받게된다.
     *
     * @throws IOException 입출력으로 인한 예외
     */
    public void start() throws IOException {
        Result result = occupationCalculator.calculatePointCanOccupy(this.blockShapeList, this.board.getBoard()); // 놓을 수 있는 모든 경우의 수 계산

        boolean isFirst = view.receiveFirstPlayer(); // 선공일 경우 true
        if (isFirst) { // 선공일 경우 랜덤한 지점에 블럭 추가
            occupyRandomly(result);
        }

        while (!isGameOver()) { // 더 이상 블럭을 놓을 수 없는 경우에 while 문 탈출
            Point pointUserInput = view.receivePointUserInput(); // 유저의 입력을 기다린다.
            if (!occupationCalculator.canOccupy(pointUserInput, this.blockShapeList, this.board.getBoard())) { // 유저가 입력한 지점에 블럭을 놓을 수 있는지
                view.printGameOver(); // 게임 종료 메시지 출력
                break;
            }
            board.occupy(pointUserInput); // 해당 지점에 블럭 추가
            printAfterOccupy(pointUserInput); // 블럭 추가 후 필요한 메시지 출력
            occupyRandomly(occupationCalculator.calculatePointCanOccupy(this.blockShapeList, this.board.getBoard())); // 랜덤한 지점에 블럭 추가
        }
    }

    /**
     * 랜덤한 지점에 블럭 추가 메서드
     *
     * @param result 블럭을 놓을 수 있는 모든 경우의 수
     * @throws IOException 입출력으로 인한 예외
     */
    private void occupyRandomly(final Result result) throws IOException { // 가장 상대방에게 방해가 될만한 포지션 로직 추가해야 함
        int randomNumber = randomGenerator.generateUpTo(result.getSize()); // 경우의 수 이하의 랜덤한 숫자 생성
        Point point = result.get(randomNumber); // 랜덤한 숫자를 인덱스로 이용하여 특정 경우의 수를 가져온다.
        board.occupy(point); // 해당 지점에 블럭 추가
        printAfterOccupy(point); // 블럭 추가 후 필요한 메시지 출력
    }

    /**
     * 블럭을 추가한 후 필요한 메시지 출력 메서드
     *
     * @param point 어디에 어떤 블럭을 추가하는지
     * @throws IOException 입출력으로 인한 예외
     */
    private void printAfterOccupy(final Point point) throws IOException {
        view.printDelimiter(); // 구분선 출력 ex) --------------
        view.printBoard(this.board.getBoard()); // 블럭판 출력
        view.printDelimiter(); // 구분선 출력
        view.printBlock(point); // 해당 지점에 대한 숫자 출력 ex) [ 2 ][ 4 ][ 2 ]
        view.printDelimiter(); // 구분선 출력
    }

    /**
     * 게임 종료를 확인하는 메서드
     * 블럭을 놓을 수 있는 모든 경우의 수를 계산하여 경우의 수가 없으면 true를 반환한다.
     *
     * @return 게임이 종료될 경우 true 아닐 경우 false
     */
    private boolean isGameOver() {
        Result result = occupationCalculator.calculatePointCanOccupy(this.blockShapeList, this.board.getBoard()); // 모든 경우의 수 계산
        return result.isEmpty(); // 경우의 수가 비어있을 경우 true
    }
}