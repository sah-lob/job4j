package ru.job4j.tracker;


import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public void add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(new Random().nextInt());
    }

    /**
     * Поиск заявки по id;
     * @param id - id заявки.
     * @return - найденная заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item: items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Поиск заявки по имени.
     * @param name имя заявки.
     * @return - найденная заявка.
     */
    public Item[] findByName(String name) {

        Item[] result;
        int len = 0;

        for (Item item: items) {
            if (item != null) {
                if (item.getName().equals(name)) {
                    len++;
                }
            }
        }
        result = new Item[len];
        len = 0;
        for (Item item: items) {
            if (item != null) {
                if (item.getName().equals(name)) {
                    result[len] = item;
                    len++;
                }
            }
        }
        return result;
    }

    /**
     *  Все созданные заявки.
     * @return список заявок.
     */
    public Item[] findAll() {
        int numOfItems = 0;
        for (Item item: items) {
            if (item != null) {
                numOfItems++;
            }
        }
        Item[] result = new Item[numOfItems];
        numOfItems = 0;

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                result[numOfItems] = items[i];
                numOfItems++;
            }
        }
        return result;
    }

    /**
     * Замена имени в заявки.
     * @param id - id заявки.
     * @param item - другая заявка.
     */
    public void replace(String id, Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = item;
                break;
            }
        }
    }

    /**
     * Удаление заявки.
     * @param id - id или, которую нужно удалить.
     */
    public boolean delete(String id) {
        boolean result = false;
        Item deleteItem = findById(id);

        for (int i = 0; i < items.length; i++) {
            if (items[i] == deleteItem) {
                items[i] = items[position - 1];
                items[position - 1] = null;
                position--;
                result = true;
                break;
            }
        }
        return result;
    }
}