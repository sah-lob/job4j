package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {

    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenSecondLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstAndSecondIsTwo(){
        Max maxim = new Max();
        int result = maxim.max(2,2);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstBiggerSecondAndThird(){
        Max max = new Max();
        int result = max.max(78,3,3);
        assertThat(result,is(78));
    }

    @Test
    public void whenSecondBiggerFirstAndThird(){
        Max max = new Max();
        int result = max.max(3,78,3);
        assertThat(result,is(78));
    }

    @Test
    public void whenThirdBiggerFirstAndSecond(){
        Max max = new Max();
        int result = max.max(3,3,78);
        assertThat(result,is(78));
    }

    @Test
    public void whenFirstSecondThirdIsFive(){
        Max max = new Max();
        int result = max.max(5,5,5);
        assertThat(result,is(5));
    }

}
