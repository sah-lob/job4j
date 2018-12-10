package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {

    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        if (tasks.size() == 0) {
            tasks.add(0, task);
        } else {
            boolean place = false;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getPriority() > task.getPriority()) {
                    tasks.add(i, task);
                    place = true;
                    break;
                }
            }
            if (!place) {
               tasks.add((tasks.size()), task);
            }
        }
    }
    public Task take() {
        return this.tasks.poll();
    }

    public Task last() {
        return this.tasks.peekLast();
    }
}