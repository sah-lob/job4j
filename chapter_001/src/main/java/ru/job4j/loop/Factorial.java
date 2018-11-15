package ru.job4j.loop;

/**
 * Нахождение факториала.
 */
public class Factorial {
    /**
     * Нахождение факториала при помощи рекурсии.
     * @param n число для которого вычисляется факториал.
     * @return Факториал для числа n;
     */
    public int calc(int n) {
        if (n < 2) {
            return n;
        } else {
            n = n * calc(n - 1);
        }
        return n;
    }
}