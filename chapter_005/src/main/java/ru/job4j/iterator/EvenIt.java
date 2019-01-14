package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator {

    private final int[] numbers;
    private int index = 0;

    public EvenIt(final int[] numbers) {
        this.numbers = numbers;
    }


    @Override
    public boolean hasNext() {
        var result = false;
        for (int i = index; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        int result = 0;
        if (hasNext()) {
            for (int i = index; i < numbers.length; i++) {
                if (numbers[i] % 2 == 0) {
                    result = numbers[i];
                    index = i;
                    break;
                }
            }
        } else {
            throw new NoSuchElementException();
        }
        index++;
        return result;
    }
}
