package ru.job4j.list;

public class SimpleQueue<T> {

    private SimpleStack stack = new SimpleStack();
    private SimpleStack newStack = new SimpleStack();

    public T poll() {
        T result = (T) newStack.poll();
        if (result == null) {
            result = (T) stack.poll();
            while (result != null) {
                newStack.push(result);
                result = (T) stack.poll();
            }
                result = (T) newStack.poll();
        }
        return result;
    }

    public void push(T value) {
        stack.push(value);
    }

}
