package ru.job4j.check;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CheckTest {

    @Test
    public void checkTestTrue() {
        var check = new Check();
        var firstWord = "мама";
        var secondWord = "амам";
        assertThat(check.check(firstWord, secondWord), is(true));
    }

    @Test
    public void checkTestFalse() {
        var check = new Check();
        var firstWord = "qwerty";
        var secondWord = "амам";
        assertThat(check.check(firstWord, secondWord), is(false));
    }

    @Test
    public void checkTestFalse2() {
        var check = new Check();
        var firstWord = "мфма";
        var secondWord = "амам";
        assertThat(check.check(firstWord, secondWord), is(false));
    }

}