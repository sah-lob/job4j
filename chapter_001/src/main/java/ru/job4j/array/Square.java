package ru.job4j.array;

/**
 * Создание массива с квадратами чисел.
 */
public class Square {
    /**
     *  Возвращает массив с квадратами чисел с 1 до bound
     * @param bound - До какого числа включительно надо возвращать массив с квадратами.
     * @return массив с квадратами чисел.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 1; i <= bound; i++) {
            rst[i - 1] = i * i;
        }
        return rst;
    }
}
