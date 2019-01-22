package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CyclicListTest {

    @Test
    public void whenCyclicListHasCycle() {
        CyclicList cyclicList = new CyclicList();
        CyclicList.Node first = new CyclicList.Node(1);
        CyclicList.Node second = new CyclicList.Node(2);
        CyclicList.Node third = new CyclicList.Node(3);
        CyclicList.Node forth = new CyclicList.Node(4);
        first.next = second;
        second.next = third;
        third.next = forth;
        forth.next = first;
        assertThat(cyclicList.hasCycle(first), is(true));
    }


    @Test
    public void whenCyclicListHasntCycle() {
        CyclicList cyclicList = new CyclicList();
        CyclicList.Node first = new CyclicList.Node(1);
        CyclicList.Node second = new CyclicList.Node(2);
        CyclicList.Node third = new CyclicList.Node(3);
        CyclicList.Node forth = new CyclicList.Node(4);
        first.next = second;
        second.next = third;
        third.next = forth;
        assertThat(cyclicList.hasCycle(first), is(false));
    }

}