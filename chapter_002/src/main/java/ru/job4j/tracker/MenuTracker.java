package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Взаимодействие с меню.
 */
public class MenuTracker {


    private static final Integer ADD = 0;
    private static final Integer SHOW = 1;
    private static final Integer EDIT = 2;
    private static final Integer DELETE = 3;
    private static final Integer FINDBYID = 4;
    private static final Integer FINDBYNAME = 5;
    private static final Integer EXIT = 6;
    private boolean exit = false;
    private Consumer<String> output;


    private Input input;
    private ITracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор класса.
     */
    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem(ADD, "Add new Item"));
        this.actions.add(new ShowItems(SHOW, "Show all items"));
        this.actions.add(new EditItem(EDIT, "Edit item"));
        this.actions.add(new DeleteItem(DELETE, "Delete item"));
        this.actions.add(new FindItemById(FINDBYID, "Find item by id"));
        this.actions.add(new FindItemsByName(FINDBYNAME, "Find items by name"));
        this.actions.add(new ExitProgram(EXIT, "Exit"));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }

    /**
     * Класс отвечающий за создание заявки.
     */
    class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки:");
            String desc = input.ask("Введите описание заявки:");
            Item item = new Item(name, desc);
            tracker.add(item);
            haveComments(item);
        }
    }

    /**
     * Класс отвечающий за просмотр всех заявок.
     */
    private class ShowItems extends BaseAction {

        protected ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("Существующие заявки:");
            output.accept("");
                ArrayList<Item> items = tracker.findAll();
                for (int i = 0; i < items.size(); i++) {
                    output.accept("Заявка номер: " + (i + 1));
                    output.accept("");
                    showItem(items.get(i));
                }
        }
    }

    /**
     * Класс отвечающий за удаление заявки.
     */
    private static class DeleteItem extends BaseAction {

        protected DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            tracker.delete(input.ask("Введите id заявки, которую вы хотите удалить."));
        }
    }

    /**
     * Класс отвечающий за поиск заявки по id.
     */
    private class FindItemById extends BaseAction {

        protected FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            showItem(tracker.findById(input.ask("Ведите Id: ")));
        }
    }

    /**
     * Класс отвечающий за поиск массива заявок по имени.
     */
    private class FindItemsByName extends BaseAction {

        protected FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            for (Item item : tracker.findByName(input.ask("Ведите Имя: "))) {
                showItem(item);
            }
        }
    }

    /**
     * Класс отвечающий за выход из программы.
     */
    private class ExitProgram extends BaseAction {


        protected ExitProgram(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            exit = true;
        }
    }


    /**
     * Метод добавляет комментарии в заявку, если они есть у пользователя.
     * @param item заявка, в которую будут добавлены комментарии.
     */
    private void haveComments(Item item) {
        if (this.input.ask("Есть ли у вас комментарии? (да,нет)").equals("да")) {
            boolean haveComments = true;
            while (haveComments) {
                String comment = this.input.ask("Напишите комментарий.");
                ArrayList<String> comments = item.getComments();
                comments.add(comment);
                item.setComments(comments);
                if (this.input.ask("Есть ли у вас еще один комментармй? (да,нет)").equals("нет")) {
                    haveComments = false;
                }
            }
        }
    }

    /**
     * Метод выводит на экран переданную заявку.
     * @param item заявка, которую необходимо вывести на экран.
     */
    private void showItem(Item item) {
        output.accept("");
        output.accept("Заявка с именем: " + item.getName());
        output.accept("Id заявки: " + item.getId());
        output.accept("Описание заявки: " + item.getDesc());
        output.accept(String.format("Текущая дата и время: " + item.getDateOfCreation()));

        if (item.getComments().size() > 0) {
            output.accept("");
            output.accept("Комментарии: ");
            output.accept("");
            for (String s: item.getComments()) {
                output.accept(s);
                output.accept("");
            }
        }
        output.accept("");
    }

    /**
     * геттер для переменной exit
     */
    public boolean exit() {
        return this.exit;
    }
}

/**
 * Класс отвечающий за изменение заявки.
 */
class EditItem extends BaseAction {

    protected EditItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, ITracker tracker) {
        String changingId = input.ask("Введите имя или id заявки, которую вы хотите заменить.");
        String id = input.ask("Введите имя или id заявки, на которую вы хотите заменить.");
        tracker.replace(changingId, tracker.findById(id));
    }
}