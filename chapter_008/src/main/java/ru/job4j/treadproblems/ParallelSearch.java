package ru.job4j.treadproblems;

import ru.job4j.blocking.SimpleBlockingQueue;

public class ParallelSearch {

    private volatile boolean finish = false;

    public static void main(String[] args) {
        ParallelSearch parallelSearch = new ParallelSearch();
        var queue = new SimpleBlockingQueue<>();

         new Thread(() -> {
            while (!parallelSearch.finish) {
                System.out.println(queue.poll());
            }
        }).start();

        new Thread(() -> {
            int maxIndex = 5;
            for (int index = 0; index < maxIndex; index++) {
                if (index == maxIndex - 1) {
                    parallelSearch.finish = true;
                }
                queue.offer(index);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }
}