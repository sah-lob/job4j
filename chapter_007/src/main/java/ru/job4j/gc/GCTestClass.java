package ru.job4j.gc;

public class GCTestClass {


    // в одном мегабайте 1_000_000 байт.
    public static void main(String[] args) {
        for (int i = 0; i < 156_600; i++) {
            var user = new User();
        }
    }
}
