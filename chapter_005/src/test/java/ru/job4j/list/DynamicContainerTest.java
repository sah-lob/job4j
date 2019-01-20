package ru.job4j.list;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DynamicContainerTest {


    DynamicContainer<Integer> dynamicContainer = new DynamicContainer<>();


    @Test
    public void addTwelveElements() {
        dynamicContainer.add(1);
        dynamicContainer.add(2);
        dynamicContainer.add(3);
        dynamicContainer.add(4);
        dynamicContainer.add(5);
        dynamicContainer.add(6);
        dynamicContainer.add(7);
        dynamicContainer.add(8);
        dynamicContainer.add(9);
        dynamicContainer.add(10);
        dynamicContainer.add(11);
        dynamicContainer.add(12);
        assertThat(dynamicContainer.length(), is(12));
        assertThat(dynamicContainer.get(6), is(7));
    }

    @Test
    public void iteratorTrueWork() {
        dynamicContainer.add(1);
        dynamicContainer.add(2);
        dynamicContainer.add(3);
        dynamicContainer.add(4);
        dynamicContainer.add(5);
        dynamicContainer.add(6);
        dynamicContainer.add(7);
        dynamicContainer.add(8);
        dynamicContainer.add(9);
        Iterator iterator = dynamicContainer.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorWorkWithException() {
        dynamicContainer.add(1);
        dynamicContainer.add(2);
        dynamicContainer.add(3);
        dynamicContainer.add(4);
        dynamicContainer.add(5);
        dynamicContainer.add(6);
        dynamicContainer.add(7);
        Iterator iterator = dynamicContainer.iterator();
        iterator.next();
        dynamicContainer.add(8);
        dynamicContainer.add(9);
        dynamicContainer.add(10);
        dynamicContainer.add(11);
        dynamicContainer.add(12);
        iterator.next();

    }
}