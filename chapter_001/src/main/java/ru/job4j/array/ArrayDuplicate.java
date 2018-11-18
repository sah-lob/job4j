package ru.job4j.array;


import java.util.Arrays;

/**
 * Удаление дубликатов.
 */
public class ArrayDuplicate {

    String[] newArryay = new String[0];


    /**
     * метод удалет дубликаты в массиве.
     * @param array массив, в котором надо удалить дубликаты.
     * @return массив без дубликатов.
     */
    public String[] remove(String[] array) {
        for (String s:array) {
            if (!checkDup(s)) {
                newArryay = Arrays.copyOf(newArryay, newArryay.length + 1);
                newArryay[newArryay.length - 1] = s;
            }
        }
        array = newArryay;
        return array;
    }

    /**
     * метод проверяет, есть ли уже в новом списке такой элемент.
     * @param st элемент.
     * @return Существует элемент или нет.
     */
    public boolean checkDup(String st) {

        boolean flag = false;

        if (newArryay.length > 0) {
        for (String s: newArryay) {
            if (s.equals(st)) {
                flag = true;
                break;
            }
        }
        }
        return flag;
    }
}
