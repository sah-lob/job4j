package ru.job4j.vacancy;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class Config {

    private final String name;
    private final Properties values = new Properties();


    public Config(String name) {
        this.name = name;
    }

    public void init() {
        try (var in = Config.class.getClassLoader().getResourceAsStream(name)) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }

    public Connection create() {
        try {
            var config = new Config(name);
            config.init();
            return DriverManager.getConnection(
                    config.get("jdbc.url"),
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
