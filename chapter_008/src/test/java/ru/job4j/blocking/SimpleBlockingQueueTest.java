package ru.job4j.blocking;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    SimpleBlockingQueue<Integer> queue;
    private Integer result = 0;

    @Test
    public void whenProducerAndCustomerStartWorking() {
        queue = new SimpleBlockingQueue<>();
        var producer = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 12; i++) {
                    queue.offer(i);
                }
            }
        };
        var consumer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    result = queue.poll();
                }
            }
        };
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(result, is(9));
        assertThat(queue.size(), is(2));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        if (!queue.isEmpty()) {
                            buffer.add(queue.poll());
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}