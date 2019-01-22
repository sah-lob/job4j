package ru.job4j.list;

public class CyclicList {

    boolean hasCycle(Node first) {
        var nodeDynamicContainer = new NodeDynamicContainer();
        var result = false;
        while (first.next != null) {
            for (int i = 0; i < nodeDynamicContainer.length(); i++) {
                if (first == nodeDynamicContainer.get(i)) {
                    result = true;
                    break;
                }
            }
            nodeDynamicContainer.add(first);
            first = first.next;
            if (result) {
                break;
            }
        }
        return result;
    }

    static class Node<T> {
        T value;
        Node<T> next;
        public Node(T value) {
            this.value = value;
        }
    }
}
