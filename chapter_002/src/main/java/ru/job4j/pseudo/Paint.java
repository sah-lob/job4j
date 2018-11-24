package ru.job4j.pseudo;

/**
 * Отображение на фигур на экран.
 */
public class Paint {

    /**
     * Вывод фигуры на экран.
     * @param shape класс фигуры.
     */
    public void draw(Shape shape) {
        System.out.print(shape.draw());
    }
}
