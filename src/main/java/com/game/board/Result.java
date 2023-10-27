package com.game.board;

import java.util.List;

/**
 * OccupationCalculator 객체가 모든 경우의 수를 계산하여 반환할 객체
 * 모든 경우의 수를 담고있다.
 */
public class Result {

    private final List<Point> canOccupationList;

    public Result(final List<Point> canOccupationList) {
        this.canOccupationList = canOccupationList;
    }

    public int getSize() {
        return canOccupationList.size();
    }

    public Point get(final int index) {
        return canOccupationList.get(index);
    }

    public boolean isEmpty() {
        return canOccupationList.isEmpty();
    }

    public boolean isContains(final Point point) {
        return canOccupationList.contains(point);
    }
}
