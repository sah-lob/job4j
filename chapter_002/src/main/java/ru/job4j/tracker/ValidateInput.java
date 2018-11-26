package ru.job4j.tracker;

import java.util.List;

public class ValidateInput extends ConsoleInput {

    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException e) {
                System.out.println("Необходимо выбрать значение из диапазона меню");
            } catch (NumberFormatException e) {
                System.out.println("Необходимо ввести корректное значение");
            }
        } while (invalid);
        return value;
    }
}
