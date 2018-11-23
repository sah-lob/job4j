package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDBYID = "4";
    private static final String FINDBYNAME = "5";
    private static final String EXIT = "6";
    private final Input input;
    private final Tracker tracker;

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
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки:");
        String desc = this.input.ask("Введите описание заявки:");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        showItem(item);
    }

    public void showItem(Item item) {
        System.out.println();
        System.out.println("Заявка с именем: " + item.getName());
        System.out.println("Id заявки: " + item.getId());
        System.out.println("Описание заявки: " + item.getDesc());
        System.out.println();
        System.out.println();
    }
    public void showAllItems() {
        System.out.println("Существующие заявки:");
        System.out.println();
        Item[] items = this.tracker.findAll();
        for (int i = 0; i < items.length; i++) {
            System.out.println("Заявка номер: " + (i + 1));
            System.out.println();
            showItem(items[i]);
        }
    }

    public void editItem() {
        tracker.replace(this.input.ask("Введите имя или id заявки, которую вы хотите изменить."), this.input.ask("Введите новое имя заявки: "));
    }
    public void deleteItem() {
        tracker.delete(this.input.ask("Введите имя или id заявки, которую вы хотите изменить."));
    }

    public void findById() {
        showItem(tracker.findById(this.input.ask("Ведите Id: ")));
    }

    public void findByName() {
        showItem(tracker.findByName(this.input.ask("Ведите Id: ")));
}



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
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}