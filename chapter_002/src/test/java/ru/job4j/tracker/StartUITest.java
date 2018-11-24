package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "Имя заявки", "Описание заявки", "да", "комментарий 1", "нет", "6"});
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("Имя заявки"));
    }

    @Test
    public void  whenUserAddItemThenEditItemThenEditName() {
        Tracker tracker = new Tracker();
        //0   Имя заявки  Описание заявки да комментарий 1 нет 3 Имя заявки 6
        Input input = new StubInput(new String[]{"0", "Имя заявки", "Описание заявки", "да", "комментарий 1", "нет", "2", "Имя заявки", "да", "Новое имя заявки", "нет", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("Новое имя заявки"));
    }

    @Test
    public void whenUserAddItemThenEditItemThenAddComment() {
        Tracker tracker = new Tracker();
        //0   Имя заявки  Описание заявки да комментарий 1 нет 3 Имя заявки 6
        Input input = new StubInput(new String[]{"0", "Имя заявки", "Описание заявки", "да", "комментарий 1", "нет", "2", "Имя заявки", "нет", "да", "Новый комментарий", "нет", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getComments().get(1), is("Новый комментарий"));
    }

    @Test
    public void whenUserAddItemThenDeleteItem() {
        Tracker tracker = new Tracker();
        //0   Имя заявки  Описание заявки да комментарий 1 нет 3 Имя заявки 6
        Input input = new StubInput(new String[]{"0", "Имя заявки", "Описание заявки", "да", "комментарий 1", "нет", "3", "Имя заявки", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(0));
    }

}
