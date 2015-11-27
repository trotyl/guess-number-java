package me.trotyl.homework;

import org.javatuples.Pair;

public class Game {
    private Generator generator;
    private Comparer comparer;

    private String number;
    private int remain;

    public Game(Generator generator, Comparer comparer) {
        this.generator = generator;
        this.comparer = comparer;
    }

    public Pair<Integer, Integer> guess(String guessNumber) {
        if (remain == 0) {
            return new Pair<>(-1, -1);
        }
        remain--;

        return comparer.compare(number, guessNumber);
    }

    public void start(int limit) {
        number = generator.generate();
        remain = limit;
    }

    public int getRemain() {
        return remain;
    }
}
