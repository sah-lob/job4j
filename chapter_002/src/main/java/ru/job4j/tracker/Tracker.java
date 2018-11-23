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
    public Item findByName(String name) {
        Item result = null;
        for (Item item: items) {
            if (item != null && item.getName().equals(name)) {
                result = item;
                break;
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
     * @param id - id заявки, в которой меняется имя.
     * @param newitem - Заявка с измененным именем.
     */
    public void replace(String id, Item newitem) {
        String newName = newitem.getName();
        findById(id).setName(newName);
    }

    /**
     * Удаление заявки.
     * @param id - id заявки, которую нужно удалить.
     */
    public boolean delete(String id) {
        boolean result = false;
        Item deleteItem = findById(id);
        if (deleteItem != null) {
            for (int indx = 0; indx < this.position; indx++) {
                if (this.items[indx].getId().equals(deleteItem.getId())) {
                    result = true;
                    this.items[indx] = null;
                    this.position--;
                    for (int offset = indx; offset < this.position; offset++) {
                        this.items[offset] = this.items[offset + 1];
                    }
                    break;
                }
            }
        }
        return result;
    }
}