package com.game.board;

import com.game.block.BlockShape;

import java.util.Objects;

public class Point {

    private final int x;
    private final int y;
    private final BlockShape blockShape;

    public Point(final int x, final int y, final BlockShape blockShape) {
        this.x = x;
        this.y = y;
        this.blockShape = blockShape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BlockShape getBlockShape() {
        return blockShape;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Point point = (Point) o;
        return getX() == point.getX() && getY() == point.getY() && Objects.equals(getBlockShape(), point.getBlockShape());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getBlockShape());
    }
}
