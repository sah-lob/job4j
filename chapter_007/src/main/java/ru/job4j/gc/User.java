package ru.job4j.gc;

public class User {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Был вызван метод finalize.");
        super.finalize();
    }
}
