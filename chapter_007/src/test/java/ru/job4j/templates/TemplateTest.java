package ru.job4j.templates;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TemplateTest {

    @Test
    public void whenGenereteStringWithTemplateAction() {
        var template = new TemplateAction();
        var templateStr = "I am ${name}, Who are ${subject}?";
        var hashMap = new HashMap<String, String>();
        var result = "I am Petr, Who are you?";
        hashMap.put("name", "Petr");
        hashMap.put("subject", "you");
        assertThat(template.generate(templateStr, hashMap), is(result));
    }

}