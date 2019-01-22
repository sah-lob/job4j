package ru.job4j.list;

public class SimpleQueue<T> {

    private SimpleStack stack = new SimpleStack();
    private SimpleStack simpleStack = new SimpleStack();

    public T poll() {
        T obj = (T) stack.poll();
        while (obj != null) {
            simpleStack.push(obj);
            obj = (T) stack.poll();
        }
        T result = (T) simpleStack.poll();
        obj = (T) simpleStack.poll();
        while (obj != null) {
            stack.push(obj);
            obj = (T) simpleStack.poll();
        }
        return result;
    }

    public void push(T value) {
        stack.push(value);
    }

}
