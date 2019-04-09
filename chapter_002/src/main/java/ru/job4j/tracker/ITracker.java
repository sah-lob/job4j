package ru.job4j.tracker;

import java.util.ArrayList;

public interface ITracker {
    int add(Item item);
    void replace(String id, Item item);
    void delete(String id);
    ArrayList<Item> findAll();
    ArrayList<Item> findByName(String key);
    Item findById(String id);
}