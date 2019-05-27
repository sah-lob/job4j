package ru.job4j.nonblockingcache;

public class OptimisticException extends RuntimeException {
    public OptimisticException() {
        super("Model is not equal to the one in the cache.");
    }
}
