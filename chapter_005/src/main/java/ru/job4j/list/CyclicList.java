package ru.job4j.list;

public class CyclicList {

    boolean hasCycle(Node first) {
        Node turtle = first;
        Node hare = first;
        var result = false;
        while (hare != null && hare.next != null) {
            turtle = turtle.next;
            hare = hare.next.next;
            if (turtle == hare) {
                result = true;
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
