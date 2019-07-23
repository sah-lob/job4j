package ru.job4j.threadpool;

import ru.job4j.blocking.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;


public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(new ThreadWork()));
        }
        for (var t : threads) {
            t.start();
        }
    }

    public void work(Runnable job) {
            this.tasks.offer(job);
    }

    public void shutdown() {
        while (true) {
            if (tasks.isEmpty()) {
                for (var t: threads) {
                    t.interrupt();
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        var threadPool = new ThreadPool();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 999; i++) {
                    threadPool.work(new Work(i));
                    if (i == 998) {
                        threadPool.shutdown();
                    }
                }
            }
        }.start();
    }

    private static class Work implements Runnable {
        private final int i;
        public Work(int i) {
            this.i = i;
        }
        @Override
        public void run() {
            System.out.println("работа номер " + i);
        }
    }

    class ThreadWork implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (!tasks.isEmpty()) {
                    tasks.poll().run();
                    System.out.println(String.format("Поток № %s взял в работу задачу. Осталось - %s",
                            Thread.currentThread().getName(), tasks.size()));
                }
            }
        }
    }
}