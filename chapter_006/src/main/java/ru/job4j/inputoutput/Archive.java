package ru.job4j.inputoutput;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



public class Archive {

    public static void main(String[] args) {

        Option option1 = new Option("d", "directory", true, "Directory");
        option1.setArgs(1);
        option1.setOptionalArg(false);
        option1.setArgName("Directory ");

//        java -jar archive.jar -d /Users/alexanderlobachev/Desktop/testing -e java.xml -o project.zip

        Option option2 = new Option("e", "formats", true, "Formats");
        option2.setArgs(1);
        option2.setOptionalArg(false);
        option2.setArgName("Formats");

        Option option3 = new Option("o", "zipName", true, "zimName");
        option3.setArgs(1);
        option3.setOptionalArg(false);
        option3.setArgName("zipName");

        Options options = new Options();
        options.addOption(option1);
        options.addOption(option2);
        options.addOption(option3);

        CommandLineParser cmdLinePosixParser = new PosixParser();
        CommandLine commandLine = null;

        try {
            commandLine = cmdLinePosixParser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        var arguments = new Args(args);
        var address = arguments.directory();
        var keys = arguments.excule();
        var zipName = arguments.output();
        var archive = new Archive();
        archive.archiveProject(address, keys, zipName);
    }

    public void archiveProject(String address, HashSet<String> keys, String nameOfProject) {
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
            if (!keys.contains(files[i].toString().substring(files[i].toString().lastIndexOf(".") + 1))) {
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

 class Args {
    String[] args;

     public Args(String[] args) {
         this.args = args;
     }

     public String directory() {
         return this.args[0];
     }
     public HashSet<String> excule() {
         String[] sd = args[1].split(",");
         HashSet<String> hashSet = new HashSet<>();
         hashSet.addAll(Arrays.asList(sd));
         return hashSet;
     }
     public String output() {
         return this.args[2];
     }
}