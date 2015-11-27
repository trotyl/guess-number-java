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
            System.out.printf("Please input your number(%d):", game.getRemaining());
            String input = reader.readLine();

            if (input.chars().distinct().count() != input.length()) {
                System.out.println("Cannot input duplicate numbers!");
                System.out.println();
                continue;
            }

            Pair<Integer, Integer> resultPair = game.guess(input);
            String result = resultPair.getValue0() + "A" + resultPair.getValue1() + "B";
            if (result.equals("4A0B")) {
                System.out.println("Congratulations!");
                break;
            }

            if (game.getRemaining() <= 0) {
                System.out.println("Game Over");
                break;
            }

            System.out.println(result);
            System.out.println();
        }
    }
}
