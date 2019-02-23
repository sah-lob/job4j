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
                if (firstMap.get(c) != null) {
                    firstMap.put(c, firstMap.get(c) + 1);
                } else {
                    firstMap.put(c, 1);
                }
            }

            for (char c : secondMas) {
                if (firstMap.get(c) == null) {
                    result = false;
                    break;
                } else if (firstMap.get(c) == 1) {
                    firstMap.remove(c);
                } else {
                    firstMap.put(c, firstMap.get(c) - 1);
                }
            }

            if (!firstMap.isEmpty()) {
                result = false;
            }
        }
        return result;
    }
}
