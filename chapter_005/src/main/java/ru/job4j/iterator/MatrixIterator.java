package ru.job4j.iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator {

    private final int[][] array;
    private int index = 0;
    private int indexX = 0;

    public MatrixIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        var result = false;
        if (indexX < array.length - 1) {
            result = true;
        } else if (index < array[indexX].length) {
            result = true;
        }
        return result;
    }

    @Override
    public Object next() {
        if (index > array[indexX].length - 1) {
            indexX++;
            index = 0;
        }
        return array[indexX][index++];
    }
}
