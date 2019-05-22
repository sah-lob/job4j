package ru.job4j.foodstorage;
import ru.job4j.foodstorage.storage.Storage;

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

    public void resort(ArrayList<Storage> storage) {

        var foods = new ArrayList<Food>();

        for (int i = 0; i < storage.size(); i++) {
            foods.addAll(storage.get(i).getFoods());
            storage.get(i).deleteAllFoods();
            if (i < storage.size() - 1) {
                storage.get(i).setNext(storage.get(i + 1));
            }
        }
        storage.get(0).add(foods);
    }
}
