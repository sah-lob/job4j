package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "Имя заявки", "Описание заявки", "да", "комментарий 1", "нет", "6"});
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll().get(0).getName(), is("Имя заявки"));
    }

    @Test
    public void whenUserAddItemThenEditItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "Имя заявки", "Описание заявки", "да", "комментарий 1", "нет", "0", "Имя заявки 2", "Описание заявки2", "да", "комментарий 2", "нет", "6"});
        new StartUI(input, tracker).init();
        input = new StubInput(new String[]{"2", tracker.findAll().get(0).getId(), tracker.findAll().get(1).getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0), is(tracker.findAll().get(1)));
    }

    @Test
    public void whenUserAddItemThenDeleteItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "Имя заявки", "Описание заявки", "да", "комментарий 1", "нет", "6"}); //Добавляем заявку
        new StartUI(input, tracker).init();
        input = new StubInput(new String[]{"3", tracker.findAll().get(0).getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().size(), is(0));
    }

    @Test
    public void whenUserAddItemThenShowAll() {
        Tracker tracker = new Tracker();
        String name = "Имя заявки";
        String desc = "Описание заявки";
        String comment = "Комментарий";
        Input input = new StubInput(new String[]{"0", name, desc, "да", comment, "нет", "6"});
        new StartUI(input, tracker).init();
        input = new StubInput(new String[]{"1", "6"});
        System.setOut(new PrintStream(this.out));
        new StartUI(input, tracker).init();
        StringBuilder pic = new StringBuilder();
        pic.append(menu() + System.lineSeparator()
                + "Существующие заявки:" + System.lineSeparator() + System.lineSeparator()
                + "Заявка номер: 1" + System.lineSeparator() + System.lineSeparator() + System.lineSeparator()
                + showItem(name, tracker, desc, comment, 0)
                + menu() + System.lineSeparator());
        String result = pic.toString();
        assertThat(new String(out.toByteArray()), is(result));
    }

    @Test
    public void whenUserAddItemThenFindById() {
        Tracker tracker = new Tracker();
        String name = "Имя заявки";
        String desc = "Описание заявки";
        String comment = "Комментарий";
        Input input = new StubInput(new String[]{"0", name, desc, "да", comment, "нет", "6"});
        new StartUI(input, tracker).init();
        input = new StubInput(new String[]{"4", tracker.findAll().get(0).getId(), "6"});
        System.setOut(new PrintStream(this.out));
        new StartUI(input, tracker).init();
        StringBuilder pic = new StringBuilder();
        pic.append(menu() + System.lineSeparator() + System.lineSeparator()
                + showItem(name, tracker, desc, comment, 0)
                + menu() + System.lineSeparator());
        String result = pic.toString();
        assertThat(new String(out.toByteArray()), is(result));
    }

    @Test
    public void whenUserAddItemThenAddItemWithSameNameThenFindByName() {
        Tracker tracker = new Tracker();
        String name = "Имя заявки";
        String desc = "Описание заявки";
        String comment = "Комментарий";
        Input input = new StubInput(new String[]{"0", name, desc, "да", comment, "нет", "0", name, desc, "да", comment, "нет", "6"});
        new StartUI(input, tracker).init();
        input = new StubInput(new String[]{"5", name, "6"});
        System.setOut(new PrintStream(this.out));
        new StartUI(input, tracker).init();
        StringBuilder pic = new StringBuilder();
        pic.append(menu() + System.lineSeparator() + System.lineSeparator()
                + showItem(name, tracker, desc, comment, 0) + System.lineSeparator()
                + showItem(name, tracker, desc, comment, 1)
                + menu() + System.lineSeparator());
        String result = pic.toString();
        assertThat(new String(out.toByteArray()), is(result));
    }

    /**
     * Метод возвращет строку с меню.
     */
    public String menu() {
        StringBuilder pic = new StringBuilder();
        pic.append("0. Add new Item" + System.lineSeparator()
                + "1. Show all items" + System.lineSeparator()
                + "2. Edit item" + System.lineSeparator()
                + "3. Delete item" + System.lineSeparator()
                + "4. Find item by id" + System.lineSeparator()
                + "5. Find items by name" + System.lineSeparator()
                + "6. Exit");
        return pic.toString();
    }

    /**
     * Метод возвращет строку с заявкой.
     * @param name - имя заявки
     * @param tracker - передается трекер, в котором массив с заявками.
     * @param desc - описание заявки
     * @param comment - комментарий заявки
     * @param numOfItem - номер заявки в массиве
     */
    public String showItem(String name, Tracker tracker, String desc, String comment, int numOfItem) {
        StringBuilder pic = new StringBuilder();
        pic.append("Заявка с именем: " + name + System.lineSeparator()
                + "Id заявки: " + tracker.findAll().get(numOfItem).getId() + System.lineSeparator()
                + "Описание заявки: " + desc + System.lineSeparator()
                + String.format("Текущая дата и время: %tc", tracker.findAll().get(numOfItem).getDateOfCreation()) + System.lineSeparator() + System.lineSeparator()
                + "Комментарии: " + System.lineSeparator() + System.lineSeparator()
                + comment + System.lineSeparator() + System.lineSeparator() + System.lineSeparator());
        return pic.toString();
    }
}
