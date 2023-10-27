package com.game.board;

import com.game.block.BlockShape;

import java.util.ArrayList;
import java.util.List;

public class OccupationCalculator {

    /**
     * 모든 경우의 수를 찾는 메서드
     * TODO 주석 추가 예정
     *
     * @param blockShapeList
     * @param board
     * @return
     */
    public Result calculatePointCanOccupy(final List<BlockShape> blockShapeList, final boolean[][] board) {
        List<Point> pointList = new ArrayList<>();
        for (BlockShape block : blockShapeList) {

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    int[] xDirection = block.getXDirection();
                    int[] yDirection = block.getYDirection();

                    boolean canOccupy = true;
                    for (int k = 0; k < xDirection.length; k++) {
                        int dx = xDirection[k] + i;
                        int dy = yDirection[k] + j;

                        if (!(dx >= 0 && dx < board[i].length && dy >= 0 && dy < board.length && !board[dx][dy])) {
                            canOccupy = false;
                            break;
                        }
                    }
                    if (canOccupy) {
                        System.out.println("추천: [" + i + ", " + j + "]");
                        pointList.add(new Point(i, j, block));
                    }
                }
            }
        }
        return new Result(pointList);
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