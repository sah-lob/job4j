package ru.job4j.loop;


/**
 * Сумма четных чисел в диапазоне.
 */
public class Counter {

    /**
     * Сумма всех четных чисел в диапазоне.
     * @param start - начало диапазона.
     * @param finish - конец диапазона.
     * @return - сумма чисел в диапазоне.
     */
    public int add(int start, int finish) {
        int summ = 0;
        for (int i = start; i <= finish; i++) {
            summ = i % 2 == 0 ? summ + i : summ;
        }
        return summ;
    }
}
