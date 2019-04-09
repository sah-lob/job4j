package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItemById() {
        var tracker = new TrackerSQL("trackerSQL.properties");
        var item = new Item("test1", "testDescription");
        var id = tracker.add(item);
        assertThat(tracker.findById(String.valueOf(id)).getId() + tracker.findById(String.valueOf(id)).getName(), is(id + item.getName()));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItemByName() {
        var tracker = new TrackerSQL("trackerSQL.properties");
        var item = new Item("test1", "testDescription");
        var id = tracker.add(item);
        ArrayList<Item> items = tracker.findByName(item.getName());
        assertThat(Integer.parseInt(items.get(items.size() - 1).getId()), is(id));
    }

    @Test
    public void whenFindAll() {
        var tracker = new TrackerSQL("trackerSQL.properties");
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
        var tracker = new TrackerSQL("trackerSQL.properties");
        var previous = new Item("old_Test1", "testDescription");
        var next = new Item("new_Test2", "testDescription2");
        var id = tracker.add(previous);
        tracker.replace(String.valueOf(id), next);
        assertThat(tracker.findById(String.valueOf(id)).getName(), is("new_Test2"));
    }

    @Test
    public void whenAddNewItemThenDelete() {
        var tracker = new TrackerSQL("trackerSQL.properties");
        var size = tracker.countOfItems();
        var item = new Item("test1", "testDescription");
        var id = tracker.add(item);
        tracker.delete(String.valueOf(id));
        assertThat(tracker.findAll().size(), is(size));
    }

}