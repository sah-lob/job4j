package ru.job4j.inputoutput;

import java.io.*;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



public class Archive {

    public void archiveProject(String nameOfProject, String address, HashSet<String> keys) {

        try (var fout = new FileOutputStream(address + nameOfProject + ".zip")) {
            var zout = new ZipOutputStream(fout);
            var fileSource = new File(address);
            addDirectory(zout, fileSource, keys);
            zout.close();
            System.out.println("Zip файл создан!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDirectory(ZipOutputStream zout, File fileSource, HashSet<String> keys) {

        var files = fileSource.listFiles();
        System.out.println("Добавление директории <" + fileSource.getName() + ">");
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                addDirectory(zout, files[i], keys);
                continue;
            }
            if (keys.contains(files[i].toString().substring(files[i].toString().lastIndexOf(".") + 1))) {
                System.out.println("Добавление файла <" + files[i].getName() + ">");
                try {
                    var fis = new FileInputStream(files[i]);
                    zout.putNextEntry(new ZipEntry(files[i].getPath()));
                    var buffer = new byte[4048];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zout.write(buffer, 0, length);
                    }
                    zout.closeEntry();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}