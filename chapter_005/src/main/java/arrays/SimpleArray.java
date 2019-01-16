package arrays;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class SimpleArray<T> {

    private Object[] array;
    private int index = 0;


    public SimpleArray(int len) {
        this.array = new Object[len];
    }

    /**
     * добавляет указанный элемент (model) в первую свободную ячейку;
     * @param model элемент ячейки
     */
    public void add(T model) {

        if (index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index++] = model;
    }

    /**
     * заменяет указанным элементом (model) элемент, находящийся по индексу index;
     */
    public void set(int index, T model) {
        if (index > this.index && index <= 0) {
            throw new NoSuchElementException();
        }
            array[index] = model;
    }

    /**
     * - удаляет элемент по указанному индексу
     * @param index индекс
     */
    public void remove(int index) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }
        System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
        array = Arrays.copyOfRange(array, 0, array.length - 1);
    }

    /**
     *  возвращает элемент, расположенный по указанному индексу;
     */
    public Object get(int index) {
        return array[index];
    }

    public int length() {
        return array.length;
    }

}
