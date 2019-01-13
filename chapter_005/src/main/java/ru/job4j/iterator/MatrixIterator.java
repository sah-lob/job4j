package ru.job4j.iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator {

    private final int[][] array;
    private int index = 0;
    private int indexX = 0;
    private int indexY = 0;

    public MatrixIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        var num = 0;
        for (var t : array) {
            num += t.length;
        }
        return num > index;
    }

    @Override
    public Object next() {
        if (indexY > array[indexX].length - 1) {
            indexY = 0;
            indexX++;
        }
        index++;
        return array[indexX][indexY++];
    }
}
