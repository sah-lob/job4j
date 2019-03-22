package ru.job4j.propertiesmanager;
import java.io.*;
import java.util.Properties;

public class Config {

    Properties property = new Properties();

    public Config(String propertiesFileName) {
        try {
            var classLoader = Config.class.getClassLoader();
            InputStream path = classLoader.getResourceAsStream(propertiesFileName);
            property.load(path);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return property.getProperty(key);
    }

    public void put(String key, String value) {
        try {
            var out = new FileOutputStream("src/main/resources/config.properties");
            property.setProperty(key, value);
            property.store(out, "You change value");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
