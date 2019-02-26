package ru.job4j.inputoutput;

import org.junit.Test;

import java.io.*;

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

    @Test
    public void filteredOutput() throws IOException {
        var strings = new String[]{"hello", "name"};
        var string = "my is Alexander Lobachev. And I'm 23 year's old.";
        var byteArrayOutputStream = new ByteArrayOutputStream();
        var byteArrayInputStream = new ByteArrayInputStream("Hello my name is Alexander Lobachev. And I'm 23 year's old.".getBytes());
        new InputOutput().dropAbuses(byteArrayInputStream, byteArrayOutputStream, strings);
        assertThat(string, is(byteArrayOutputStream.toString()));
    }


}