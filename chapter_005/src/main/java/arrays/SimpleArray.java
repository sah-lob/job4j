package arrays;

public class SimpleArray<T> {

    private final Object[] array;
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
        array[index] = model;
    }

    /**
     * - удаляет элемент по указанному индексу
     * @param index индекс
     */
    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
        array[array.length - 1] = null;
    }

    /**
     *  возвращает элемент, расположенный по указанному индексу;
     */
    public Object get(int index) {
        return array[index];
    }

}
