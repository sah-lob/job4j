package ru.job4j.inputoutput;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InputOutputTest {

    @Test
    public void whenIsNumberIsEven() throws IOException {
        var inputOutput = new InputOutput();
        try (var in = new ByteArrayInputStream("32".getBytes())) {
        System.setIn(in);
        }
        assertThat(inputOutput.isNumber(new InputStreamReader(System.in)), is(true));
    }

    @Test
    public void whenIsNumberIsNotEven() throws IOException {
        var inputOutput = new InputOutput();
        try (var in = new ByteArrayInputStream("31".getBytes())) {
            System.setIn(in);
        }
        assertThat(inputOutput.isNumber(new InputStreamReader(System.in)), is(false));
    }

}