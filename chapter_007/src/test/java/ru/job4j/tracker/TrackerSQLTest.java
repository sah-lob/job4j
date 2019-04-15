package ru.job4j.tracker;


import org.junit.Test;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    private Config config = new Config("trackerSQL.properties");

    @Test
    public void whenAddNewItemThenTrackerHasSameItemById() {
        var tracker = new TrackerSQL(ConnectionRollback.create(config.create()));
        var item = new Item("test1", "testDescription");
        var id = tracker.add(item);
        assertThat(tracker.findById(String.valueOf(id)).getId() + tracker.findById(String.valueOf(id)).getName(), is(id + item.getName()));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItemByName() {
        var tracker = new TrackerSQL(ConnectionRollback.create(config.create()));
        var item = new Item("test1", "testDescription");
        ArrayList<Item> items = tracker.findByName(item.getName());
        tracker.add(item);
        ArrayList<Item> newItems = tracker.findByName(item.getName());
        assertThat(items.size(), is(newItems.size() - 1));
    }

    @Test
    public void whenFindAll() {
        var tracker = new TrackerSQL(ConnectionRollback.create(config.create()));
        var count = tracker.countOfItems();
        var item = new Item("test1", "testDescription1");
        var item2 = new Item("test2", "testDescription2");
        var item3 = new Item("test3", "testDescription3");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        var newCount = tracker.findAll().size();
        assertThat(newCount, is(count + 3
        ));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        var tracker = new TrackerSQL(ConnectionRollback.create(config.create()));
        var previous = new Item("old_Test1", "testDescription");
        var next = new Item("new_Test2", "testDescription2");
        var id = tracker.add(previous);
        tracker.replace(String.valueOf(id), next);
        assertThat(tracker.findById(String.valueOf(id)).getName(), is("new_Test2"));
    }

    @Test
    public void whenAddNewItemThenDelete() {
        var tracker = new TrackerSQL(ConnectionRollback.create(config.create()));
        var size = tracker.countOfItems();
        var item = new Item("test1", "testDescription");
        var id = tracker.add(item);
        tracker.delete(String.valueOf(id));
        assertThat(tracker.findAll().size(), is(size));
    }
}