package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {

    @Test
    public void factorialOfFifeIs120() {
        Factorial f = new Factorial();
        int result = f.calc(5);
        assertThat(result, is(120));
    }

    @Test
    public void factorialOf10Is3628800() {
        Factorial f = new Factorial();
        int result = f.calc(10);
        assertThat(result, is(3628800));
    }
}