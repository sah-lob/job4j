package ru.job4j.pseudo;

/**
 * Реализация треугольника.
 */
public class Triangle implements Shape {

    /**
     * Рисуется треугольник.
     * @return Возвращается строка с треугольником.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("   +\n");
        pic.append("  + +\n");
        pic.append(" +   +\n");
        pic.append("+++++++");
        return pic.toString();
    }
}