package ru.job4j.array;


import java.util.Arrays;

/**
 * Удаление дубликатов.
 */
public class ArrayDuplicate {

    /**
     * метод удалет дубликаты в массиве.
     * @param array массив, в котором надо удалить дубликаты.
     * @return массив без дубликатов.
     */
    public String[] remove(String[] array) {

        int num = 0;
        for (int i = 0; i < array.length; i++) {
            boolean flag = false;
            for (int j = 0; j < num; j++) {
                if (array[j].equals(array[i])) {
                    flag = true;
                }
            }
            if (!flag) {
                array[num] = array[i];
                num++;
            }
        }

        return Arrays.copyOf(array, num);
    }
}
