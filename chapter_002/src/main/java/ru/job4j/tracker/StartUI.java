package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private final Input input;
    private final ITracker tracker;
    Consumer<String> output;


    /**
     * Конструктор.
     */
    public StartUI(Input input, ITracker tracker, Consumer output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this.output);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            menu.select(input.ask("  Select:", range));
        } while (!menu.exit());
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(input, new Tracker(), x -> System.out.println(x)).init();
    }
}