package ru.job4j.list;

public class SimpleStack<T> {

    NodeDynamicContainer nodeDynamicContainer = new NodeDynamicContainer();

    public T poll() {
        T t = (T) nodeDynamicContainer.get(nodeDynamicContainer.length() - 1);
            nodeDynamicContainer.delete();
        return t;
    }

    public void push(T value) {
        nodeDynamicContainer.add(value);
    }
}
