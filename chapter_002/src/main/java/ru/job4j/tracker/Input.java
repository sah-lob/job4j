package ru.job4j.tracker;

import java.util.List;

/**
 * Интерфейс ввода данных.
 */
public interface Input {
    String ask(String question);
    int ask(String question, List<Integer> range);
}
