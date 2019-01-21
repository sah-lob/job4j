package ru.job4j.list;

public class SimpleQueue<T> {

    private SimpleStack stack = new SimpleStack();

    public T poll() {
        stack = reverse(stack);
        T t = (T) stack.poll();
        stack = reverse(stack);
        return t;
    }

    public void push(T value) {
        stack.push(value);
    }

    private SimpleStack reverse(SimpleStack stack) {
        SimpleStack simpleStack = new SimpleStack();
        T obj = (T) stack.poll();
        while (obj != null) {
            simpleStack.push(obj);
            obj = (T) stack.poll();
        }
        return simpleStack;
    }
}
