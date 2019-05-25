package ru.job4j.threadsafelist;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicContainer;

import java.util.Iterator;

@ThreadSafe
public class ThreadSafeList<E> extends DynamicContainerDecorator<E> {


    public ThreadSafeList() {
        super();
    }

    @Override
    public Iterator<E> iterator() {
        return copy(this.container).iterator();
    }

    private DynamicContainer<E> copy(DynamicContainer<E> component) {
        DynamicContainer<E> result = null;
        try {
            result = (DynamicContainer<E>) component.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
