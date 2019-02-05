package ru.job4j.map;

import org.junit.Test;

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
        assertThat(myHashMap.hasNext(), is(true));
    }

    @Test
    public void ifHasNextIsFalse() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.insert(3, "Alexander");
        myHashMap.insert(1, "Anna");
        myHashMap.insert(2, "Irina");
        assertThat(myHashMap.hasNext(), is(true));
        assertThat(myHashMap.next(), is("Alexander"));
        assertThat(myHashMap.hasNext(), is(true));
        assertThat(myHashMap.next(), is("Irina"));
        assertThat(myHashMap.hasNext(), is(true));
        assertThat(myHashMap.next(), is("Anna"));
        assertThat(myHashMap.hasNext(), is(false));
    }



}