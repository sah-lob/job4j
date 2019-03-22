package ru.job4j.search;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    private final String nameOfFile;
    private final String path;
    private final int type;
    private final String writeFileName;

//            1. Создать программу для поиска файла.
//            2. Программа должна искать данные в заданном каталоге и подкаталогах.
//            3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
//            4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
//            Ключи
//                -d - директория в которая начинать поиск.
//                -n - имя файл, маска, либо регулярное выражение.
//                -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
//                -o - результат записать в файл.
//            5. Программа должна записывать результат в файл.
//            6. В программе должна быть валидация ключей и подсказка.


    public Search(String nameOfFile, String path, int type, String writeFileName) {
        this.nameOfFile = nameOfFile;
        this.path = path;
        this.type = type;
        this.writeFileName = writeFileName;
    }

    public static void main(String[] args) throws IOException {
        Search search = new Search("1.png", "/Users/alexanderlobachev/Desktop/testing", 1, "2.txt");
        search.start();
    }

    public void start() throws IOException {
        File file = new File(path);
        ArrayList<String> listOfFiles = new ArrayList<>();
        listOfFiles = searchInDirectory(file, listOfFiles);
        try (FileWriter fos = new FileWriter(writeFileName)) {
            for (var ss : listOfFiles) {
                fos.write(ss);
            }
        }
    }

    public ArrayList<String> searchInDirectory(File file, ArrayList<String> info) throws IOException {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                searchInDirectory(f, info);
            } else {
                if (type == 0) {
                    if (f.getName().equals(nameOfFile)) {
                        info.add(f.getName() + " - имя файла. Его путь: " + f.getAbsolutePath() + "\n");
                    }
                } else if (type == 1) {
                    if (maskValidate(f)) {
                        info.add(f.getName() + " - имя файла. Его путь: " + f.getAbsolutePath() + "\n");
                    }
                } else if (type == 2) {
                    if (regexValidate(f)) {
                        info.add(f.getName() + " - имя файла. Его путь: " + f.getAbsolutePath() + "\n");
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


    class Args {
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


