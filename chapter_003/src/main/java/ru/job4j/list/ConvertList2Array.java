package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {

        int cells = (int) Math.ceil((double) list.size() / (double) rows);
        int[][] array = new int[rows][cells];
        int n = 0;
        for (int i = 0; i < cells; i++) {
            for (int j = 0; j < rows; j++) {
                if (list.size() > n) {
                    array[i][j] = list.get(n);
                }
                n++;
            }
        }
        return array;
    }
}
