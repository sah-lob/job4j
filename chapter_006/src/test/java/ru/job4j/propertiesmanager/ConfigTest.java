package ru.job4j.propertiesmanager;

import org.junit.Test;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {




    @Test
    public void getValueFromConfigProperties() {
        var config = new Config("config.properties", "src/main/resources/");
        var value = "Alex" + new Random().nextInt();
        var key = "name";
        config.put(key, value);
        assertThat(value, is(config.get(key)));
    }

    @Test
    public void getValueThanPutNewValue() {
        Config config = new Config("config.properties", "src/main/resources/");
        String name = config.get("name");
        var value = "testValue";
        config.put("name", value);
        assertThat(value, is(config.get("name")));
        config.put("name", name);
    }
}