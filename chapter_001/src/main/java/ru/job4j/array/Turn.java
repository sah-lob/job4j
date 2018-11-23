package ru.job4j.array;

/**
 * Переворот массива.
 */
public class Turn {
    /**
     * Переворот массива по данной формуле: a=a+b-(b=a);
     * @param array массив, который нужно перевернуть.
     * @return перевернутый массив.
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            array[i] = array[i] + array[array.length - 1 - i];
            array[array.length - 1 - i] = array[i] - array[array.length - 1 - i];
            array[i] = array[i] - array[array.length - 1 - i];
        }
        return array;
    }
}
