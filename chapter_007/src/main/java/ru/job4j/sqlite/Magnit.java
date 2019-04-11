package ru.job4j.sqlite;

import java.io.File;

public class Magnit {

    public static void main(String[] args) {
        var config = new Config();
        config.init();
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.init();
        storeSQL.generate(23);
        var entries = storeSQL.load();
        var file = new File("/Users/alexanderlobachev/Desktop/testing/test.xml");
        var storeXML = new StoreXML(file);
        storeXML.save(entries);
    }
}
