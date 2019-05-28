package ru.job4j.treadproblems;

public class TestThread extends Thread {

    private Swapclass swapclass;

    public TestThread(Swapclass swapclass) {
        this.swapclass = swapclass;
    }

    public static void main(String[] args) {
        var swapclass = new Swapclass();
        var tread = new TestThread(swapclass);
        var tread2 = new TestThread(swapclass);
        var tread3 = new TestThread(swapclass);
        tread.start();
        tread2.start();
        tread3.start();

    }

    @Override
    public void run() {
        swapclass.swap();
    }
}
