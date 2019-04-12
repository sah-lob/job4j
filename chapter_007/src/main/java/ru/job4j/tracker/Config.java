package ru.job4j.tracker;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final String name;
    private final Properties values = new Properties();


    public Config(String name) {
        this.name = name;
    }

    public void init() {
        try (InputStream in = ru.job4j.sqlite.Config.class.getClassLoader().getResourceAsStream(name)) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}