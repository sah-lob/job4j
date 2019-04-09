package ru.job4j.tracker;


import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker implements ITracker {
    /**
     * Массив для хранение заявок.
     */
    private ArrayList<Item> items = new ArrayList<>();
    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public int add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return Integer.parseInt(item.getId());
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
        return items.stream().filter(Item -> Item.getId().equals(id)).findFirst().get();
    }

    /**
     * Поиск заявки по имени.
     * @param name имя заявки.
     * @return - найденная заявка.
     */
    public ArrayList<Item> findByName(String name) {
        return (ArrayList<Item>) items.stream().filter(Item -> Item.getName().equals(name)).collect(Collectors.toList());
    }

    /**
     *  Все созданные заявки.
     * @return список заявок.
     */
    public ArrayList<Item> findAll() {
        return items;
    }

    /**
     * Замена имени в заявки.
     * @param id - id заявки.
     * @param item - другая заявка.
     */
    public void replace(String id, Item item) {
        items = (ArrayList<Item>) items.stream().map(Item -> Item.getId().equals(id) ? item : Item).collect(Collectors.toList());
    }

    /**
     * Удаление заявки.
     * @param id - id или, которую нужно удалить.
     */
    public void delete(String id) {
        items = (ArrayList<Item>) items.stream().filter(Item -> !Item.getId().equals(id)).collect(Collectors.toList());
    }
}