package ru.job4j.check;

import java.util.HashMap;

public class Check {

    public boolean check(String firstWord, String secondWord) {

        var result = true;

        if (firstWord.length() != secondWord.length()) {
            result = false;
        } else {

            var firstMap = new HashMap<Character, Integer>();
            var secondMas = secondWord.toCharArray();

            for (char c : firstWord.toCharArray()) {
                firstMap.computeIfPresent(c, (x, z) -> z = firstMap.get(x) + 1);
                firstMap.putIfAbsent(c, 1);
            }

            for (char c : secondMas) {
                if (!firstMap.containsKey(c)) {
                    result = false;
                    break;
                } else {
                    firstMap.remove(c, 1);
                    firstMap.computeIfPresent(c, (x, z) -> z = firstMap.get(x) - 1);
                }
            }

            if (!firstMap.isEmpty()) {
                result = false;
            }
        }
        return result;
    }
}
