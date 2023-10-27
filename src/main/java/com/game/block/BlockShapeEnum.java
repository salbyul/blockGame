package com.game.block;

public enum BlockShapeEnum implements BlockShape {
    FIRST_BLOCK(1, new int[]{0, 0, 0}, new int[]{0, 1, 2}),
    SECOND_BLOCK(2, new int[]{1, 0, 1}, new int[]{0, 1, 1}),
    THIRD_BLOCK(3, new int[]{-1, -1, 0}, new int[]{0, 1, 1}),
    FOURTH_BLOCK(4, new int[]{-1, 0, -1}, new int[]{-1, -1, 0}),
    FIFTH_BLOCK(5, new int[]{0, 1, 1}, new int[]{-1, -1, 0});

    private final int blockNumber;
    private final int[] xDirection;
    private final int[] yDirection;

    BlockShapeEnum(final int blockNumber, final int[] xDirection, final int[] yDirection) {
        this.blockNumber = blockNumber;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public int[] getXDirection() {
        return xDirection;
    }

    public int[] getYDirection() {
        return yDirection;
    }
}