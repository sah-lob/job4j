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
        assertThat(tracker.findAll()[0].getName(), is("Имя заявки"));
    }

    @Test
    public void whenUserAddItemThenEditItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "Имя заявки", "Описание заявки", "да", "комментарий 1", "нет", "0", "Имя заявки 2", "Описание заявки2", "да", "комментарий 2", "нет", "6"});
        new StartUI(input, tracker).init();
        input = new StubInput(new String[]{"2", tracker.findAll()[0].getId(), tracker.findAll()[1].getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0], is(tracker.findAll()[1]));
    }

    @Test
    public void whenUserAddItemThenDeleteItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "Имя заявки", "Описание заявки", "да", "комментарий 1", "нет", "6"}); //Добавляем заявку
        new StartUI(input, tracker).init();
        input = new StubInput(new String[]{"3", tracker.findAll()[0].getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(0));
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
        pic.append(menu() + "\n"
                + "Существующие заявки:\n" + "\n"
                + "Заявка номер: 1\n" + "\n" + "\n"
                + showItem(name, tracker, desc, comment, 0)
                + menu() + "\n");
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
        input = new StubInput(new String[]{"4", tracker.findAll()[0].getId(), "6"});
        System.setOut(new PrintStream(this.out));
        new StartUI(input, tracker).init();
        StringBuilder pic = new StringBuilder();
        pic.append(menu() + "\n\n"
                + showItem(name, tracker, desc, comment, 0)
                + menu() + "\n");
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
        pic.append(menu() + "\n\n"
                + showItem(name, tracker, desc, comment, 0) + "\n"
                + showItem(name, tracker, desc, comment, 1)
                + menu() + "\n");
        String result = pic.toString();
        assertThat(new String(out.toByteArray()), is(result));
    }

    /**
     * Метод возвращет строку с меню.
     */
    public String menu() {
        StringBuilder pic = new StringBuilder();
        pic.append("Меню.\n"
                + "0. Add new Item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find item by Id\n"
                + "5. Find items by name\n"
                + "6. Exit Program");
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
        pic.append("Заявка с именем: " + name + "\n"
                + "Id заявки: " + tracker.findAll()[numOfItem].getId() + "\n"
                + "Описание заявки: " + desc + "\n"
                + String.format("Текущая дата и время: %tc", tracker.findAll()[numOfItem].getDateOfCreation()) + "\n" + "\n"
                + "Комментарии: \n" + "\n"
                + comment + "\n\n\n");
        return pic.toString();
    }
}
