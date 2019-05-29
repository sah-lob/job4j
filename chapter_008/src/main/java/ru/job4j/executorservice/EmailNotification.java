package ru.job4j.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {


    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());


    public void emailTo(User user) {
        this.pool.submit(new Sender(user));
    }

    public void emailSend(User user) {
        String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
        String body    = String.format("Add a new event to %s", user.getUsername());
        send(subject, body, user.getEmail());
    }

    private synchronized void send(String subject, String body, String email) {
        System.out.println(email);
        System.out.println(subject);
        System.out.println(body);
        System.out.println();
    }

    public void close() {
        this.pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class Sender implements Runnable {
        private final User user;

        public Sender(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            emailSend(user);
        }
    }
}
