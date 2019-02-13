package ru.job4j.tree;


import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;

    public Tree(E root) {
        this.root = new Node(root);
    }

    @Override
    public boolean add(E parent, E child) {
        var result = false;
        if (!findBy(parent).isEmpty()) {
            result = true;
            for (Node<E> v: findBy(parent).get().leaves()) {
                if (v.eqValue(child)) {
                    result = false;
                }
            }
            if (result) {
                findBy(parent).get().add(new Node<>(child));
            }
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }


    public boolean isBinary() {
        var result = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            if (queue.peek().leaves().size() > 2) {
                result = false;
                break;
            }
            queue.addAll(queue.poll().leaves());
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        Queue<Node> queue  = new LinkedList<>();
        queue.add(root);
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }
            @Override
            public E next() {
                Node<E> node = null;
                if (hasNext()) {
                    node = queue.poll();
                    queue.addAll(node.leaves());
                }
                return node.getValue();
            }
        };
    }
}
