package com.game.board;

import com.game.block.BlockShape;

public class Board {

    private final boolean[][] board; // 실질적인 블럭판, false 값은 해당 위치에 블럭이 없는 경우, true 값은 해당 위치에 블럭이 있는 경우

    public Board(final boolean[][] board) {
        this.board = board;
    }

    public boolean[][] getBoard() {
        return board;
    }

    /**
     * 블럭판에 블럭을 놓는 메서드
     *
     * @param point 놓을 블럭과 좌표가 담겨있는 객체
     */
    public void occupy(final Point point) {
        BlockShape block = point.getBlockShape(); // 블럭을 꺼낸다.
        int x = point.getX(); // 블럭을 놓을 좌표를 꺼낸다.
        int y = point.getY(); // 블럭을 놓을 좌표를 꺼낸다.
        int[] xDirection = block.getXDirection(); // 블럭을 놓을 방향 좌표를 꺼낸다.
        int[] yDirection = block.getYDirection(); // 블럭을 놓을 방향 좌표를 꺼낸다.

        for (int i = 0; i < xDirection.length; i++) {
            int dx = xDirection[i] + x; // 블럭을 놓을 좌표를 구한다.
            int dy = yDirection[i] + y; // 블럭을 놓을 좌표를 구한다.
            board[dx][dy] = true; // 해당 위치를 true 값으로 변경한다.
        }
    }
}