package me.trotyl.homework;


import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Cli {
    public static Random random = new Random();
    public static int limit = 6;

    public static void main(String[] args) throws IOException {
        Game game = new Game(new Generator(random), new Comparer());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        game.start(limit);
        System.out.println("Welcome!");
        System.out.println();

        while (true) {
            System.out.printf("Please input your number(%d):", game.getRemain());
            String input = reader.readLine();

            if (input.chars().distinct().count() != input.length()) {
                System.out.println("Cannot input duplicate numbers!");
                System.out.println();
                continue;
            }

            Pair<Integer, Integer> pair = game.guess(input);

            if (pair.getValue0() == 4 && pair.getValue1() == 0) {
                System.out.println("Congratulations!");
                break;
            }

            if (game.getRemain() <= 0) {
                System.out.println("Game Over");
                break;
            }

            System.out.println(pair.getValue0() + "A" + pair.getValue1() + "B");
            System.out.println();
        }
    }
}
