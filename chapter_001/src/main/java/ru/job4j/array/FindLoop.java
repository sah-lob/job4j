package ru.job4j.array;


/**
 * Поиск элемента массива.
 */
public class FindLoop {


    /**
     *  Поиск номера элемента массива.
     * @param data - массив.
     * @param el - элемент.
     * @return номер элемента.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }
        return rst;
    }
}