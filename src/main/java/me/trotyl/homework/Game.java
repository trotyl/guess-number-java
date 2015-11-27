package me.trotyl.homework;

public class Game {
    private Generator generator;
    private Comparer comparer;

    private String number;
    private int remaining;

    public Game(Generator generator, Comparer comparer) {
        this.generator = generator;
        this.comparer = comparer;
    }

    public String guess(String guessNumber) {
        if (remaining == 0) {
            return null;
        }
        remaining--;

        return comparer.compare(number, guessNumber);
    }

    public void start(int limit) {
        number = generator.generate();
        remaining = limit;
    }

    public int getRemaining() {
        return remaining;
    }
}
