package ru.job4j.threadsafelist;

import ru.job4j.list.DynamicContainer;

import java.util.Iterator;

public class DynamicContainerDecorator<E> extends DynamicContainer<E> {

    protected DynamicContainer<E> container;

    public DynamicContainerDecorator() {
        this.container = new DynamicContainer<>();
    }

    @Override
    public Iterator<E> iterator() {
        return this.container.iterator();
    }

    @Override
    public void add(E value) {
        this.container.add(value);
    }

    @Override
    public E get(int index) {
        return this.container.get(index);
    }

    @Override
    public int length() {
        return this.container.length();
    }

    @Override
    public void remove(E value) {
        this.container.remove(value);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
