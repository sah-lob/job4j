package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Рисуем пирамиду.
 */
public class Paint {



    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }


    /**
     * Создаем строку, выводящую пирамиду.
     * @param height высота пирамиды.
     * @return строка.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Cоздаем строку, выводящую правый треугольник.
     * @param height высота треугольника.
     * @return строка.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Cоздаем строку, выводящую левый треугольник.
     * @param height высота треугольника.
     * @return строка.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }
}
