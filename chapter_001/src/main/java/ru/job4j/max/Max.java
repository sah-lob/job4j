package ru.job4j.max;


public class Max {


    /**
     * Определение большего числа из двух.
     * @param first - первое число
     * @param second - второе число
     * @return - наибольшее число.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Определение большего числа из трех.
     * @param first - первое число.
     * @param second - второе число.
     * @param third - третье число.
     * @return - наибольшее из трех чисел.
     */
    public int max(int first, int second, int third) {
        int temp = this.max(first, second);
        return this.max(temp, third);
    }

}
