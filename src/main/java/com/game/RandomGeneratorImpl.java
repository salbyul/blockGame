package com.game;

import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator {

    private final Random random;

    public RandomGeneratorImpl() {
        this.random = new Random();
    }

    @Override
    public int generateUpTo(final int number) {
        return random.nextInt(number);
    }
}
