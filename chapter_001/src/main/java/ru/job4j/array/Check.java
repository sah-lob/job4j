package ru.job4j.array;

/**
 * Массив заполнен на true или false.
 */
public class Check {

    /**
     *  Проверка все ли элименты одинаковые
     * @param data входной массив.
     * @return результат проверки на однородность элементов.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            if (data[0] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}