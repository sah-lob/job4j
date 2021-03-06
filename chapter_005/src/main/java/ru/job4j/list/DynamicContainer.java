package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicContainer<E> implements Iterable<E> {

    public Object[] container;
    private int index = 0;
    private int modCount = 0;


    public DynamicContainer() {
        this.container = new Object[10];
    }

    public void add(E value) {
        if (container.length <= index) {
            Object[] objects = new Object[(index * 3) / 2];
            System.arraycopy(container, 0, objects, 0, container.length);
            container = objects;
        }
        modCount++;
        container[index++] = value;
    }

    public void remove(E value) {

        int index = -1;
        for (int i = 0; i < container.length; i++) {
            if (container[i] == value) {
                index = i;
            }
        }
        if (index >= 0) {
            System.arraycopy(container, index + 1, container, index, container.length - 1 - index);
            container = Arrays.copyOfRange(container, 0, container.length - 1);
            this.index--;
        }
    }
    public E get(int index) {
        return (E) container[index];
    }

    public int length() {
        return index;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            Object[] obj = container;
            int ind = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (ind < index) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) obj[ind++];
            }
        };
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
