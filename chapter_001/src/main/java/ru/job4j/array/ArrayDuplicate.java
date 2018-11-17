package ru.job4j.array;

import java.util.ArrayList;

/**
 * Удаление дубликатов.
 */
public class ArrayDuplicate {

    ArrayList<String> arrayList = new ArrayList<>();


    /**
     * метод удалет дубликаты в массиве.
     * @param array массив, в котором надо удалить дубликаты.
     * @return массив без дубликатов.
     */
    public String[] remove(String[] array) {
        for (String s:array) {
            if (!checkDup(s)) {
                arrayList.add(s);
            }
        }
        array = arrayList.toArray(new String[0]);
        return array;
    }

    /**
     * метод проверяет, есть ли уже в новом списке такой элемент.
     * @param st элемент.
     * @return Существует элемент или нет.
     */
    public boolean checkDup(String st) {

        boolean flag = false;

        for (String s: arrayList) {
            if (s.equals(st)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
