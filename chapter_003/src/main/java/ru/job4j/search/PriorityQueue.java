package ru.job4j.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PriorityQueue {

    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        tasks.add(task);
        List<Task> tt = tasks.stream().sorted(Comparator.comparing(Task::getPriority)).collect(Collectors.toList());
        tasks = new LinkedList<>();
        tasks.addAll(tt);
    }


    public Task take() {
        return this.tasks.poll();
    }

    public Task last() {
        return this.tasks.peekLast();
    }
}