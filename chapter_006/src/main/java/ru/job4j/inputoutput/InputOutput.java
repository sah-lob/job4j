package ru.job4j.inputoutput;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InputOutput {

    public boolean isNumber(InputStreamReader in) {
        var result = false;
        try (var scanner = new Scanner(in)) {
            result = scanner.nextInt() % 2 == 0;
        }
        return result;
    }


    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {

        int q;
        var word = new StringBuilder();
        while (true) {
            q = in.read();
            if (q == -1) {
                abuseTest(word.toString(), abuse, out, true);
                break;
            } else if (q != 32) {
                word.append((char) q);
            } else {
                abuseTest(word.toString(), abuse, out, false);
                word = new StringBuilder();
            }
        }
    }


    public void abuseTest(String word, String[] abuse, OutputStream out, boolean end) throws IOException {
        var result = true;
        if (abuse.length != 0) {
            for (var w : abuse) {
                if (w.toUpperCase().equals(word.toUpperCase())) {
                    result = false;
                    break;
                }
            }
        }
        if (result) {
            for (var c : word.toCharArray()) {
                out.write(c);
            }
            if (!end) {
                out.write(32);
            }
        }
    }

}
