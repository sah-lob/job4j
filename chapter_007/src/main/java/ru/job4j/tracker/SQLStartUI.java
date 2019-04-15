package ru.job4j.tracker;

public class SQLStartUI {

    public static void main(String[] args) {
        var config = new Config("trackerSQL.properties");
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(config.create()))) {
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(input, new Tracker(), x -> System.out.println(x)).init();
        }
    }
}
