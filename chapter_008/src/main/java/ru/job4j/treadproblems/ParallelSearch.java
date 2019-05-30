package ru.job4j.treadproblems;

import ru.job4j.blocking.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {
        var queue = new SimpleBlockingQueue<>();
        var t =  new Thread(() -> {
             while (!Thread.currentThread().isInterrupted()) {
                System.out.println(queue.poll());
            }
        });
        t.start();
        new Thread(() -> {
            int maxIndex = 5;
            for (int index = 0; index < maxIndex; index++) {
                if (index == maxIndex - 1) {
                    t.interrupt();
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