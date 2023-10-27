package com.game.view.translator;

import com.game.block.BlockShape;
import com.game.board.Board;
import com.game.board.Point;
import com.game.view.exception.InputException;

import java.util.List;

/**
 * 유저의 입력을 게임 객체로 변환하는 클래스
 */
public class InputTranslator {

    private final Board board;
    private final List<BlockShape> blockShapeList;

    public InputTranslator(final Board board, final List<BlockShape> blockShapeList) {
        this.board = board;
        this.blockShapeList = blockShapeList;
    }

    /**
     * 유저의 선공여부 입력값을 boolean 값으로 변환하는 메서드
     *
     * @param input 유저의 선공여부 입력값
     * @return 선공일 경우 true 반환
     */
    public boolean translateInputFirstPlayer(final String input) {
        try {
            int parseInt = Integer.parseInt(input); // 유저의 입력을 int 값으로 변환
            if (parseInt != 1 && parseInt != 2) { // 1 혹은 2가 아닐경우 InputException 발생
                throw new InputException("숫자 1과 2만 입력이 가능합니다.");
            }
            return parseInt == 1; // 유저가 1을 입력할 경우 true 반환
        } catch (NumberFormatException e) { // 유저가 숫자를 입력하지 않았을 경우 발생하는 NumberFormatException 을 InputException 으로 다시 발생한다.
            throw new InputException("숫자만 입력 가능합니다.");
        }
    }

    /**
     *
     * @param input
     * @return
     */
    public Point translateUserInput(final String input) {
        try {
            int ySizeOfBoard = this.board.getBoard().length;
            int xSizeOfBoard = this.board.getBoard()[0].length;

            if (input.length() != 3) {
                throw new InputException("세자리의 숫자만 입력 가능합니다.");
            }

            String[] split = input.split("");
            int first = Integer.parseInt(split[0]);
            int second = Integer.parseInt(split[1]);
            int third = Integer.parseInt(split[2]);

            if (second < 0 || second > ySizeOfBoard - 1 || third < 0 || third > xSizeOfBoard) {
                throw new InputException("블록판에 존재하지 않는 숫자를 입력할 수 없습니다.");
            }

            BlockShape blockShape = this.blockShapeList.stream()
                    .filter(block -> block.getBlockNumber() == first)
                    .findAny()
                    .orElseThrow(() -> new InputException("해당 블록이 없습니다."));

            return new Point(second, third, blockShape);
        } catch (NumberFormatException e) {
            throw new InputException("세자리의 숫자만 입력 가능합니다.");
        }
    }
}