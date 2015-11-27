package me.trotyl.homework;

import java.util.Random;

public class Generator {
    private Random random;

    public Generator(Random random) {
        this.random = random;
    }

    public String generate() {
        String number;

        do {
            number = generateRandomNumber();
        } while ((int)number.chars().distinct().count() != number.length());

        return number;
    }

    private String generateRandomNumber() {
        return random.nextInt(9877) + "";
    }
}
