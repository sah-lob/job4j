package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();

        for (int[] i: array) {
            for (int num : i) {
                list.add(num);
            }
        }
        return list;
    }
}
