package ru.job4j.nonblockingcache;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

public class OptimisticExceptionTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        Assert.assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }

}