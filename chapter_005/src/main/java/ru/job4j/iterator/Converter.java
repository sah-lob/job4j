package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (it.hasNext()) {
            var iterator = it.next();
            while (iterator.hasNext()) {
                arrayList.add(iterator.next());
            }
        }


        return new Iterator<>() {
            ArrayList<Integer> list = arrayList;
            int index = 0;

            @Override
            public boolean hasNext() {
                return arrayList.size() > index;
            }

            @Override
            public Integer next() {
                var result = 0;
                if (hasNext()) {
                    result = list.get(index++);
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }
}