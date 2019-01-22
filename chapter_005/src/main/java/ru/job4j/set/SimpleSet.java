package ru.job4j.set;

import ru.job4j.list.DynamicContainer;

public class SimpleSet<E> {

    private DynamicContainer<E> container = new DynamicContainer<>();

    public void add(E value) {
        if (!contains(value)) {
           container.add(value);
        }
    }

    public void remove(E value) {
        if (container.length() == 0 || !contains(value)) {
            throw new AssertionError();
        }
        container.remove(value);
    }

    public boolean contains(E value) {
        var result = false;
        for (int i = 0; i < container.length(); i++) {
            if (value.equals(container.get(i))) {
                result = true;
                break;
            }
        }
        return result;
    }

    public int size() {
        return container.length();
    }
}
