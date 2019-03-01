package ru.job4j.inputoutput;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class InputOutput {

    public boolean isNumber(InputStreamReader in) {
        var result = false;
        try (var scanner = new Scanner(in)) {
            result = scanner.nextInt() % 2 == 0;
        }
        return result;
    }

    public void dropAbuses(InputStream in, OutputStream out, HashSet<String> abuse) {
        try {
            var line = new BufferedReader(new InputStreamReader(in)).readLine();
            var strings = line.split(" ");
            abuse.forEach(x -> x.toLowerCase());
            line = "";
            for (int i = 0; i < strings.length; i++) {
                if (!abuse.contains(strings[i].toLowerCase())) {
                    line += strings[i] + " ";
                }
            }
            line = line.substring(0, line.length() - 1);
            out.write(line.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
