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

        var level = -1;
        var size = 1;
        var thisSize = 1;
        var nextSize = 0;
        var iterator = iterator();

        while (iterator.hasNext()) {
            var node = findBy(iterator.next()).get();
            nextSize += node.leaves().size();
            if (size == thisSize) {
                thisSize = nextSize;
                nextSize = 0;
                level++;
                size = 1;
            } else {
                size++;
            }
        }

        return level <= 2;
    }

    @Override
    public Iterator<E> iterator() {

        ArrayList<Node<E>> nodes = new ArrayList<>();
        nodes.add(root);
        nodes = addChildNodes(nodes);
        ArrayList<Node<E>> finalNodes = nodes;

        return new Iterator<>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                var result = false;
                if (index != finalNodes.size()) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                return finalNodes.get(index++).getValue();
            }
        };
    }

    public ArrayList<Node<E>> addChildNodes(ArrayList<Node<E>> list) {
        ArrayList<Node<E>> childList = new ArrayList<>();
        for (Node<E> node : list) {
            if (node.leaves() != null) {
                childList.addAll(node.leaves());
            }
        }
        if (!childList.isEmpty()) {
            list.addAll(addChildNodes(childList));
        }
        return list;
    }
}
