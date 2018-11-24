package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    //0   Имя заявки  Описание заявки да комментарий 1 нет 3 Имя заявки 6

    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDBYID = "4";
    private static final String FINDBYNAME = "5";
    private static final String EXIT = "6";
    private final Input input;
    private final Tracker tracker;

    /**
     * Конструктор.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDBYID.equals(answer)) {
                this.findById();
            } else if (FINDBYNAME.equals(answer)) {
                this.findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавление новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки:");
        String desc = this.input.ask("Введите описание заявки:");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        haveComments(item);
        showItem(item);
    }
    /**
     * Метод выводит на экран переданную заявку.
     * @param item заявка, которую необходимо вывести на экран.
     */
    private void showItem(Item item) {
        System.out.println();
        System.out.println("Заявка с именем: " + item.getName());
        System.out.println("Id заявки: " + item.getId());
        System.out.println("Описание заявки: " + item.getDesc());
        System.out.println(String.format("Текущая дата и время: %tc", item.getDateOfCreation()));
        if (item.getComments().size() > 0) {
            System.out.println();
            System.out.println("Комментарии: ");
            System.out.println();
            for (String s: item.getComments()) {
                System.out.println(s);
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * Метод показывает все созданные заявки.
     */
    private void showAllItems() {
        System.out.println("Существующие заявки:");
        System.out.println();
        Item[] items = this.tracker.findAll();
        for (int i = 0; i < items.length; i++) {
            System.out.println("Заявка номер: " + (i + 1));
            System.out.println();
            showItem(items[i]);
        }
    }

    /**
     * Метод спрашивает имя заявки и изменяет ее на новое,
     * Добавляет комментарии, если орни есть.
     */
    private void editItem() {
        String id = tracker.nameOrIdToId(this.input.ask("Введите имя или id заявки, которую вы хотите изменить."));
        String newName = id;
        if (this.input.ask("Вы хотите изменить имя? (да, нет)").equals("да")) {
            newName = this.input.ask("Введите новое имя заявки: ");
            tracker.replace(id, newName);
        }
        id = tracker.nameOrIdToId(newName);
        haveComments(tracker.findById(id));
    }

    /**
     * Метод спрашивает имя метода и удаляет его.
     */
    private void deleteItem() {
        tracker.delete(this.input.ask("Введите имя или id заявки, которую вы хотите изменить."));
    }

    /**
     * Метод выводит найденную по id заявку.
     */
    private void findById() {
        showItem(tracker.findById(this.input.ask("Ведите Id: ")));
    }

    /**
     * Метод выводит найденную по имени заявку.
     */
    private void findByName() {
        showItem(tracker.findByName(this.input.ask("Ведите Имя: ")));
}

    /**
     * метод который выводит меню в консоль.
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
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
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}