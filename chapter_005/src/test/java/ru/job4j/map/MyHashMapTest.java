package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MyHashMapTest {

    @Test
    public void insertThreeElements() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.insert(3, "Alexander");
        myHashMap.insert(1, "Anna");
        myHashMap.insert(2, "Irina");
        assertThat(myHashMap.size(), is(3));
    }


    @Test
    public void getElementWithKey2() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.insert(3, "Alexander");
        myHashMap.insert(1, "Anna");
        myHashMap.insert(2, "Irina");
        assertThat(myHashMap.get(2), is("Irina"));
    }


    @Test
    public void insertThreeElementsThanDeleteTwo() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.insert(3, "Alexander");
        myHashMap.insert(1, "Anna");
        myHashMap.insert(2, "Irina");
        myHashMap.delete(2);
        myHashMap.delete(1);
        assertThat(myHashMap.size(), is(1));
    }

    @Test
    public void ifHasNextIsTrue() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.insert(3, "Alexander");
        myHashMap.insert(1, "Anna");
        myHashMap.insert(2, "Irina");
        Iterator iterator = myHashMap.iterator();
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void ifHasNextIsFalse() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.insert(3, "Alexander");
        myHashMap.insert(1, "Anna");
        myHashMap.insert(2, "Irina");
        Iterator iterator = myHashMap.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Anna"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Irina"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Alexander"));
        assertThat(iterator.hasNext(), is(false));
    }



}