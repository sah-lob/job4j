package ru.job4j.gc.cache;

import java.io.*;

public class CacheManager {

    Cache<String, File> fileCache = new Cache<>();
    String pathname;


    public CacheManager(String pathname) {
        this.pathname = pathname;
        unloadFiles(pathname);
    }

    public void printFile(String name) {
        var file = fileCache.get(name);
        if (file == null) {
            var dir = pathname + "/" + name;
            file = new File(dir);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileCache.add(name, file);
        }
        try {
            var br = new BufferedReader(new FileReader(file));
            var currentLine = br.readLine();
            if (currentLine == null) {
                System.out.println("Файл с именем " + file.getName() + " пустой");
            } else {
                System.out.println(currentLine);
                while ((currentLine = br.readLine()) != null) {
                    System.out.println(currentLine);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void unloadFiles(String pathname) {
        var folder = new File(pathname);
        var files = folder.listFiles();
        if (files.length > 0) {
            for (var f : files) {
                fileCache.add(f.getName(), f);
            }
        }
    }
}


