package ru.job4j.list;

public class SimpleStack<T> {

    private NodeDynamicContainer nodeDynamicContainer = new NodeDynamicContainer();

    public T poll() {
        T t;
        if (nodeDynamicContainer.length() == 0) {
            t = null;
        } else {
            t = (T) nodeDynamicContainer.delete();
        }
        return t;
    }

    public void push(T value) {
        nodeDynamicContainer.add(value);
    }
}
