package ru.job4j.array;


/**
 * Создание упорядоченного массива из двух.
 */
public class ArrayMerging {

    /**
     * Объединение массивов в один.
     * @param array1 первый упорядоченный массив
     * @param array2 второй упорядоченный массив
     * @return объединенный упорядоченный массив
     */
    public int[] merginArrays(int[] array1, int[] array2) {
        int[] newArray = new int[array1.length + array2.length];
        int num1 = 0;
        int num2 = 0;

        for (int i = 0; i < newArray.length; i++) {
            if (num1 < array1.length - 1 && num2 < array2.length) {
                if (array1[num1] <= array2[num2]) {
                    newArray[i] = array1[num1];
                    num1++;
                } else {
                    newArray[i] = array2[num2];
                    num2++;
                }
            } else {
                if (num1 == array1.length - 1) {
                    if (array1[num1] <= array2[num2]) {
                        newArray[i] = array1[num1];
                        num1++;
                    } else {
                        newArray[i] = array2[num2];
                        num2++;
                    }
                } else if (num1 > array1.length - 1) {
                    newArray[i] = array2[num2];
                }
            }
        }
        return newArray;
    }
}
