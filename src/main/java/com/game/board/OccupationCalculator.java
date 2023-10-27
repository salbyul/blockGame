package com.game.board;

import com.game.block.BlockShape;

import java.util.ArrayList;
import java.util.List;

public class OccupationCalculator {

    /**
     * 모든 경우의 수를 찾는 메서드
     *
     * @param blockShapeList 블럭 모양 리스트
     * @param board 블럭판
     * @return 모든 경우의 수를 담은 Result 객체
     */
    public Result calculatePointCanOccupy(final List<BlockShape> blockShapeList, final boolean[][] board) {
        List<Point> pointList = new ArrayList<>(); // 경우의 수를 담을 리스트 객체 생성
        for (BlockShape block : blockShapeList) { // 블럭 모양 리스트에서 블럭 모양을 하나씩 꺼낸다.

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) { // 블럭판의 모든 좌표를 이중 for 문으로 확인한다.
                    int[] xDirection = block.getXDirection(); // 특정 블럭의 좌표를 꺼낸다.
                    int[] yDirection = block.getYDirection(); // 특정 블럭의 좌표를 꺼낸다.

                    boolean canOccupy = true; // 블럭판의 특정 위치에 블럭을 놓을 수 있는지 확인하기 위한 boolean 값
                    for (int k = 0; k < xDirection.length; k++) {
                        int dx = xDirection[k] + i; // 블럭판의 특정 위치의 좌표
                        int dy = yDirection[k] + j; // 블럭판의 특정 위치의 좌표

                        if (!(dx >= 0 && dx < board[i].length && dy >= 0 && dy < board.length && !board[dx][dy])) {
                            // 좌표가 블럭판의 크기에 합당한지 확인 & 해당 위치에 블럭이 놓여있는지 확인한다.

                            canOccupy = false; // 위의 조건에 해당되지 않을 경우 블럭을 놓을 수 없으므로 false 로 변경한다.
                            break;
                        }
                    }
                    if (canOccupy) { // 블럭을 놓을 수 있을 경우
                        pointList.add(new Point(i, j, block)); // 경우의 수로 추가한다.
                    }
                }
            }
        }
        return new Result(pointList); // 모든 경우의 수를 찾아 반환
    }

    /**
     * 해당 위치에 블럭을 놓을 수 있는 확인하는 메서드
     *
     * @param point 위치와 블럭이 담긴 객체
     * @param blockShapeList 블럭 종류 리스트
     * @param board 블럭판
     * @return 블럭을 놓을 수 있으면 true 반환
     */
    public boolean canOccupy(final Point point, final List<BlockShape> blockShapeList, final boolean[][] board) {
        Result result = calculatePointCanOccupy(blockShapeList, board);
        return result.isContains(point); // 모든 경우의 수에 현재 위치에 블럭을 놓는 경우의 수가 있는지 확인 후 존재하면 true 반환
    }
}