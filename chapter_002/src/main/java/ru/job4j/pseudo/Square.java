package ru.job4j.pseudo;

/**
 * Реализация квадрата.
 */
public class Square implements Shape {

    /**
     * Рисуется квадрат.
     * @return возвращается строка с квадратом.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("+++++++\n");
        pic.append("+     +\n");
        pic.append("+     +\n");
        pic.append("+++++++");
        return pic.toString();
    }
}
