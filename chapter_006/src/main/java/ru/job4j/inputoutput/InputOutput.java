package ru.job4j.inputoutput;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InputOutput {

    public boolean isNumber(InputStreamReader in) {
        var result = false;
        try (var scanner = new Scanner(in)) {
            result = scanner.nextInt() % 2 == 0;
        }
        return result;
    }

}
