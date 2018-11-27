package ru.job4j.tracker;

import java.util.List;
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

    @Override
    public int ask(String question, List<Integer> range) throws MenuOutException {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Ошибка в диаппазоне.");
        } else {
            return key;
        }
    }
}
