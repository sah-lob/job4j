package ru.job4j.filemanager;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserAction {

    void execute() throws IOException;
    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню
     */
    String info();
}