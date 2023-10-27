package com.game;

import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator {

    private final Random random;

    public RandomGeneratorImpl() {
        this.random = new Random();
    }

    /**
     * 0 이상 number 미만의 랜덤한 숫자 값 생성 메서드
     *
     * @param number 범위를 위한 숫자 값
     * @return 랜덤으로 생성한 값
     */
    @Override
    public int generateUpTo(final int number) {
        return random.nextInt(number);
    }
}
