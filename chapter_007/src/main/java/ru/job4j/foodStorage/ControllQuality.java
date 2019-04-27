package ru.job4j.foodStorage;

import ru.job4j.foodStorage.storage.Shop;
import ru.job4j.foodStorage.storage.Storage;
import ru.job4j.foodStorage.storage.Trash;
import ru.job4j.foodStorage.storage.Warehouse;


import java.util.ArrayList;
import java.util.Date;

public class ControllQuality {

    private Shop shop = new Shop();
    private Storage storage = new Storage();
    private Trash trash = new Trash();
    private Warehouse warehouse = new Warehouse();

    public void analize(ArrayList<Food> foods) {
        for (var f: foods) {
            System.out.println("Название продукта:" + f.getName());
            int actual = percentageOfLife(f.getCreateDate(), f.getExpaireDate());
            System.out.println("Процент срока годности: " + actual);
            if (actual >= 0 && actual < 25) {
                System.out.println("Добавляем " + f.getName() + " на склад.");
                warehouse.add(f);
            } else if (actual >= 25 && actual < 75) {
                System.out.println("Добавляем " + f.getName() + " в магазин.");
                shop.add(f);
            } else if (actual >= 75 && actual < 100) {
                System.out.println("Добавляем " + f.getName() + " в магазин со скидкой.");
                f.setDisscount(actual - 50);
                shop.add(f);
            } else {
                System.out.println("Выкидываем " + f.getName());
                trash.add(f);
            }
        }
    }



    public int percentageOfLife(Date createDate, Date expaireDate) {
        var nowDate = new Date();
        var allDays = daysBetween(createDate, expaireDate);
        var daysPassed = daysBetween(createDate, nowDate);
        return (daysPassed * 100) / allDays;
    }

    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
