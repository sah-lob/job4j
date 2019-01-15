package ru.job4j.iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator {

    private final int[][] array;
    private int cell = 0;
    private int row = 0;

    public MatrixIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return row < array.length - 1 ? true : cell < array[row].length ? true : false;
    }

    @Override
    public Object next() {
        if (cell > array[row].length - 1) {
            row++;
            cell = 0;
        }
        return array[row][cell++];
    }
}
