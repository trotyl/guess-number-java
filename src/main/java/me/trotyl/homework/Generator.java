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
            number = randomNumber();
        } while ((int)number.chars().distinct().count() != 4);

        return number;
    }

    private String randomNumber() {
        return random.nextInt(9877) + "";
    }
}
