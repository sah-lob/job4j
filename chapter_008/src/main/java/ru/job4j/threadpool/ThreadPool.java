package ru.job4j.threadpool;

import ru.job4j.blocking.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;


public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private volatile boolean noShutdown = true;



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
        synchronized (this.tasks) {
            this.tasks.offer(job);
            this.tasks.notifyAll();
            if (tasks.size() == 10) {
                try {
                    System.out.println("я тут");
                    tasks.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown() {
        synchronized (this.tasks) {
            noShutdown = false;
            this.tasks.notifyAll();
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
            while (noShutdown) {
                synchronized (tasks) {
                    while (tasks.isEmpty() && noShutdown) {
                        try {
                            System.out.println("Поток ждет.");
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (noShutdown) {
                        tasks.poll().run();
                        System.out.println(String.format("Поток № %s взял в работу задачу. Осталось - %s",
                                Thread.currentThread().getName(), tasks.size()));
                        tasks.notify();
                    }
                }
            }
        }
    }
}