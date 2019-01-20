package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NodeDynamicContainerTest {


    NodeDynamicContainer<Integer> nodeDynamicContainer = new NodeDynamicContainer<>();


    @Test
    public void addTwelveElements() {
        nodeDynamicContainer.add(1);
        nodeDynamicContainer.add(2);
        nodeDynamicContainer.add(3);
        nodeDynamicContainer.add(4);
        nodeDynamicContainer.add(5);
        nodeDynamicContainer.add(6);
        nodeDynamicContainer.add(7);
        nodeDynamicContainer.add(8);
        nodeDynamicContainer.add(9);
        nodeDynamicContainer.add(10);
        nodeDynamicContainer.add(11);
        nodeDynamicContainer.add(12);
        assertThat(nodeDynamicContainer.length(), is(12));
        assertThat(nodeDynamicContainer.get(6), is(7));
    }

    @Test
    public void iteratorTrueWork() {
        nodeDynamicContainer.add(1);
        nodeDynamicContainer.add(2);
        nodeDynamicContainer.add(3);
        nodeDynamicContainer.add(4);
        nodeDynamicContainer.add(5);
        nodeDynamicContainer.add(6);
        nodeDynamicContainer.add(7);
        nodeDynamicContainer.add(8);
        nodeDynamicContainer.add(9);
        Iterator iterator = nodeDynamicContainer.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorWorkWithException() {
        nodeDynamicContainer.add(1);
        nodeDynamicContainer.add(2);
        nodeDynamicContainer.add(3);
        nodeDynamicContainer.add(4);
        nodeDynamicContainer.add(5);
        nodeDynamicContainer.add(6);
        nodeDynamicContainer.add(7);
        Iterator iterator = nodeDynamicContainer.iterator();
        iterator.next();
        nodeDynamicContainer.add(8);
        nodeDynamicContainer.add(9);
        nodeDynamicContainer.add(10);
        nodeDynamicContainer.add(11);
        nodeDynamicContainer.add(12);
        iterator.next();
    }
}