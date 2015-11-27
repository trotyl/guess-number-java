package me.trotyl.homework;

import org.javatuples.Pair;

import java.util.HashSet;

public class Comparer {

    public Comparer() {

    }

    public Pair<Integer, Integer> compare(String systemNumber, String userNumber) {
        int numberOfAs = 0;
        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < systemNumber.length(); i++) {
            if (systemNumber.charAt(i) == userNumber.charAt(i)) {
                numberOfAs++;
            }
            set.add(systemNumber.charAt(i));
            set.add(userNumber.charAt(i));
        }

        int numberOfAsAndBs = systemNumber.length() + userNumber.length() - set.size();

        return new Pair<>(numberOfAs, numberOfAsAndBs - numberOfAs);
    }
}
