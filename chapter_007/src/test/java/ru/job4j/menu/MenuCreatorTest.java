package ru.job4j.menu;

import org.junit.Test;

public class MenuCreatorTest {

    @Test
    public void showMenu() {
        var menuCreator = new MenuCreator();
        var c1 = menuCreator.addCell("Зоголовок 1", null);
        var c13 = menuCreator.addCell("Зоголовок 1.3", c1);
        var c2 = menuCreator.addCell("Зоголовок 2", null);
        menuCreator.addCell("Зоголовок 1.1", c1);
        menuCreator.addCell("Зоголовок 1.2", c1);
        menuCreator.addCell("Зоголовок 1.3.1", c13);
        menuCreator.addCell("Зоголовок 2.1", c2);
        menuCreator.addCell("Зоголовок 2.2", c2);
        menuCreator.addCell("Зоголовок 2.3", c2);
        menuCreator.addCell("Зоголовок 3", null);


        menuCreator.showMenu();
    }

}