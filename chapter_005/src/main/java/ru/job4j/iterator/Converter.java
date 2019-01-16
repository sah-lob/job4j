package ru.job4j.iterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<>() {

            Iterator iterator = it.next();

            @Override
            public boolean hasNext() {
                var result = false;
                while (it.hasNext() || iterator.hasNext()) {
                    if (iterator.hasNext()) {
                        result = true;
                        break;
                    }
                    iterator = it.next();
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (Integer) iterator.next();
            }
        };
    }
}