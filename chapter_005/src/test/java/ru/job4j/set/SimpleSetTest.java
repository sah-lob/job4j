package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void addThreeElementsAndOneWrong() {
        var simpleSet = new SimpleSet();
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(3);
        simpleSet.add(3);
        assertThat(simpleSet.size(), is(3));
    }

    @Test
    public void addElementAndThanCheckContains() {
        var simpleSet = new SimpleSet();
        simpleSet.add(1);
        assertThat(simpleSet.contains(1), is(true));
    }

    @Test
    public void addThreeElementsThanDeleteOne() {
        var simpleSet = new SimpleSet();
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(3);
        simpleSet.remove(3);

        assertThat(simpleSet.size(), is(2));

    }

    @Test(expected = AssertionError.class)
    public void addThreeElementsThanDeleteNonexistent() {
        var simpleSet = new SimpleSet();
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(3);
        simpleSet.remove(33);
    }

}