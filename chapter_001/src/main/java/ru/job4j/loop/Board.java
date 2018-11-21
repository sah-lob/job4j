package ru.job4j.loop;

/**
 * Создание шахматного поля.
 */
public class Board {

    /**
     * Метод рисующие шахмотное поле с нужными размарами.
     * @param width - ширина поля.
     * @param height - высота поля.
     * @return - возвращается строка с шахматным полем.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}