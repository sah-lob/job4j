package ru.job4j.propertiesmanager;
import java.io.*;
import java.util.Properties;

public class Config {


    private String propertyPath;
    private Properties property = new Properties();
    private String propertiesFileName;


    public Config(String propertiesFileName, String propertyPath) {
        this.propertyPath = propertyPath;
        this.propertiesFileName = propertiesFileName;
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
            var out = new FileOutputStream(propertyPath + propertiesFileName);
            property.setProperty(key, value);
            property.store(out, "You change value");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
