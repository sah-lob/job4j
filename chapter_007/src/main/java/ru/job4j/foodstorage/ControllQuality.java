package ru.job4j.foodstorage;
import ru.job4j.foodstorage.extension.FreeseWarehouse;
import ru.job4j.foodstorage.extension.NewTrash;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Warehouse;

import java.util.ArrayList;
import java.util.Date;

public class ControllQuality {

    public int percentageOfLife(Date createDate, Date expaireDate) {
        var nowDate = new Date();
        var allDays = daysBetween(createDate, expaireDate);
        var daysPassed = daysBetween(createDate, nowDate);
        return (daysPassed * 100) / allDays;
    }

    public int daysBetween(Date d1, Date d2) {
        return (int) (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
    }

    public void resort() {
        var foods = new ArrayList<Food>();
        var shop = new Shop();
        var warehouse = new Warehouse();
        var freeseWarehouse = new FreeseWarehouse();
        var trash = new NewTrash();
        foods.addAll(shop.getFoods());
        foods.addAll(warehouse.getFoods());
        foods.addAll(freeseWarehouse.getFoods());
        foods.addAll(trash.getFoods());

        freeseWarehouse.deleteAllFoods();
        warehouse.deleteAllFoods();
        shop.deleteAllFoods();

        freeseWarehouse.setNext(warehouse);
        warehouse.setNext(shop);
        shop.setNext(trash);
        freeseWarehouse.add(foods);
    }
}
