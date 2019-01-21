package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NodeDynamicContainer<E> implements Iterable<E> {

    private SimpleArrayList simpleArrayList;
    private int modCount = 0;


    public NodeDynamicContainer() {
        this.simpleArrayList = new SimpleArrayList();
    }

    public void add(E value) {
        simpleArrayList.add(value);
        modCount++;
    }

    public E get(int index) {
        return (E) simpleArrayList.get(simpleArrayList.getSize() - index - 1);
    }

    public void delete() {
        if (simpleArrayList.getSize() == 1) {
            simpleArrayList = new SimpleArrayList();
        } else {
            simpleArrayList.delete();
        }
    }

    public int length() {
        return simpleArrayList.getSize();
    }

    @Override
    public Iterator<E> iterator() {
        Object[] obj = new Object[simpleArrayList.getSize()];
        for (int i = 0; i < simpleArrayList.getSize(); i++) {
            obj[i] = get(i);
        }
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int ind = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (ind < obj.length) {
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
}
