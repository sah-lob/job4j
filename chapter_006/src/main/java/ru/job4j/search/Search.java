package ru.job4j.search;

import java.net.PortUnreachableException;
import java.util.Arrays;
import java.util.HashSet;

public class Search {

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

    public static void main(String[] args) {

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


