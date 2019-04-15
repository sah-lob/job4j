package ru.job4j.tracker;

import java.io.InputStream;
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
        try (InputStream in = ru.job4j.sqlite.Config.class.getClassLoader().getResourceAsStream(name)) {
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
            Config config = new Config("trackerSQL.properties");
            config.init();
            return DriverManager.getConnection(
                    config.get("url"),
                    config.get("username"),
                    config.get("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}