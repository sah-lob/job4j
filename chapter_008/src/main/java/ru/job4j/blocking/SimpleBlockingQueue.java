package ru.job4j.blocking;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;



@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final static int QUEUE_SIZE = 10;
    private final Object lock = new Object();

    @GuardedBy("lock")
    private Queue<T> queue = new LinkedList<>();

    public void offer(T value) {
        synchronized (lock) {
            if (size() == QUEUE_SIZE) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.offer(value);
            lock.notify();
        }
    }

    public T poll() {
        synchronized (lock) {
            if (size() == 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            var result = queue.poll();
            lock.notify();
            return result;
        }
    }

    @GuardedBy("lock")
    public int size() {
        synchronized (lock) {
            return this.queue.size();
        }
    }

    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }
}