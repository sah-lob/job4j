package ru.job4j.foodstorage;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
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
}
