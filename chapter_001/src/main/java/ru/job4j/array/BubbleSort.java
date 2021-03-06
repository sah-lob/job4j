package ru.job4j.array;

/**
 * Сортировка.
 */
public class BubbleSort {

    /**
     * Сортировка массива методом пузырька.
     * @param array входной массив.
     * @return отсортированный массив.
     */
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int num = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = num;
                }
            }
        }
        return array;
    }
}
