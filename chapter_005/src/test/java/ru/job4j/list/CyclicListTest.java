package ru.job4j.list;

import org.junit.Test;

public class CyclicListTest {

    @Test
    public void test() {
        CyclicList cyclicList = new CyclicList();
        CyclicList.Node first = new CyclicList.Node(1);
        CyclicList.Node second = new CyclicList.Node(2);
        CyclicList.Node third = new CyclicList.Node(3);
        CyclicList.Node forth = new CyclicList.Node(4);
        first.next = second;
        second.next = third;
        third.next = forth;
        forth.next = first;

        System.out.println(cyclicList.hasCycle(first));
    }

}