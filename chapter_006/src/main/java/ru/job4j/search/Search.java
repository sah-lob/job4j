package ru.job4j.search;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    private final String nameOfFile;
    private final String path;
    private final int type;
    private final String writeFileName;


    public Search(String nameOfFile, String path, String type, String writeFileName) {
        this.nameOfFile = nameOfFile;
        this.path = path;
        this.type = typeConverter(type);
        this.writeFileName = writeFileName;
    }

    public static void main(String[] args) throws IOException {

        Option option1 = new Option("d", "directory", true, "Directory");
        option1.setArgs(1);
        option1.setOptionalArg(false);
        option1.setArgName("Directory ");

        Option option2 = new Option("-n", "name", true, "NameOfSearchingFile");
        option2.setArgs(1);
        option2.setOptionalArg(false);
        option2.setArgName("Name");

        Option option3 = new Option("-m", "type", true, "type");
        option3.setArgs(0);
        option3.setOptionalArg(false);
        option3.setArgName("type");

        Option option4 = new Option("-o", "newFile", true, "nameOfNewFile");
        option3.setArgs(1);
        option3.setOptionalArg(false);
        option3.setArgName("nameOfNewFile");

        Options options = new Options();
        options.addOption(option1);
        options.addOption(option2);
        options.addOption(option3);
        options.addOption(option4);

        var  cmdLinePosixParser = new PosixParser();

        try {
            var commandLine = cmdLinePosixParser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        var arguments = new Args(args);
        var nameOfFile = arguments.fileName();
        var path = arguments.directory();
        var type = arguments.type();
        var writeFileName = arguments.outputFileName();


        Search search = new Search(nameOfFile, path, type, writeFileName);
        search.start();
    }

    public void start() throws IOException {
        var file = new File(path);
        ArrayList<String> listOfFiles = new ArrayList<>();
        listOfFiles = searchInDirectory(file, listOfFiles);
        try (var fos = new FileWriter(writeFileName)) {
            for (var ss : listOfFiles) {
                fos.write(ss);
            }
        }
    }

    public ArrayList<String> searchInDirectory(File file, ArrayList<String> info) {
        if (file.listFiles().length > 0) {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    searchInDirectory(f, info);
                } else {
                    switch (type) {
                        case (0):
                            if (f.getName().equals(nameOfFile)) {
                                info.add(info(f));
                            }
                        case (1):
                            if (maskValidate(f)) {
                                info.add(info(f));
                            }
                        case (2):
                            if (regexValidate(f)) {
                                info.add(info(f));
                            }
                        default:
                            System.out.println("Ошибка");
                    }
                }
            }
        }
        return info;
    }


    public boolean maskValidate(File f) {
        var st = nameOfFile.replaceAll("\\.", "\\\\\\.");
        st = st.replaceAll("\\?", ".");
        st = st.replaceAll("\\*", ".*");
        Pattern p = Pattern.compile(st);
        Matcher m = p.matcher(f.getName());
        return m.matches();
    }

    public boolean regexValidate(File f) {
        Pattern p = Pattern.compile(nameOfFile);
        Matcher m = p.matcher(f.getName());
        return m.matches();
    }

    public String info(File f) {
        return f.getName() + " - имя файла. Его путь: " + f.getAbsolutePath() + "\n";
    }

    public int typeConverter(String type) {
        int result;
        switch (type) {
            case ("-f"): result = 0;
                break;
            case ("-m"): result = 1;
                break;
            case ("-r"): result = 2;
                break;
            default: result = -1;
                break;
        }
        return result;
    }

    static class Args {
        String[] args;

        public Args(String[] args) {
            this.args = args;
        }

        public String directory() {
            return this.args[0];
        }

        public String fileName() {
            return this.args[1];
        }

        public String type() {
            return this.args[2];
        }

        public String outputFileName() {
            return this.args[3];
        }
    }
}


