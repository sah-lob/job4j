package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TreckerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItemById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItemByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        assertThat(tracker.findByName(item.getName()).get(0), is(item));
    }

    @Test
    public void whenFindAll() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription1");
        Item item2 = new Item("test2", "testDescription2");
        Item item3 = new Item("test3", "testDescription3");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        ArrayList<Item> items = tracker.findAll();
        ArrayList<Item> result = new ArrayList<>();
        result.add(item);
        result.add(item2);
        result.add(item3);
        assertThat(items, is(result));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenAddNewItemThenDelete() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        tracker.delete(item.getId());

        assertThat(tracker.findAll().size(), is(0));
    }
}
