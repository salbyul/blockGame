package com.game.board;

import com.game.block.BlockShape;

public class Board {

    private final boolean[][] board;

    public Board(final boolean[][] board) {
        this.board = board;
    }

    public boolean[][] getBoard() {
        return board;
    }

    /**
     * 블럭판에 블럭을 놓는 메서드
     * TODO 주석 추가 예정
     *
     * @param point 점령할 블럭과 좌표가 담겨있는 객체
     */
    public void occupy(final Point point) {
        BlockShape block = point.getBlockShape();
        int x = point.getX();
        int y = point.getY();
        int[] xDirection = block.getXDirection();
        int[] yDirection = block.getYDirection();

        for (int i = 0; i < xDirection.length; i++) {
            int dx = xDirection[i] + x;
            int dy = yDirection[i] + y;
            board[dx][dy] = true;
        }
    }
}