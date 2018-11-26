package ru.job4j.tracker;

import java.util.Scanner;


/**
 * Ввод данных с консоли.
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.println(question);
        return  scanner.nextLine();
    }
}
