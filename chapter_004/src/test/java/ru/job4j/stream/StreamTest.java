package ru.job4j.stream;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StreamTest {

    @Test
    public void filter() {
        var stream = new Stream();
        var mas = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(stream.filter(mas), is(220));
    }


}