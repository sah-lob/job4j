package ru.job4j.foodstorage;

import org.junit.Test;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
import ru.job4j.foodstorage.storage.Warehouse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartStorageTest {


    @Test
    public void test () {
        var foods = foods();
        var shop = new Shop();
        var warehouse = new Warehouse();
        var trash = new Trash();
        warehouse.setNext(shop);
        shop.setNext(trash);
        warehouse.add(foods);
        int shopFoodSize = shop.getFoods().size();
        int wareHouseFoodSize = warehouse.getFoods().size();
        int trashFoodSize = trash.getFoods().size();
        assertThat(shopFoodSize, is(6));
        assertThat(wareHouseFoodSize, is(0));
        assertThat(trashFoodSize, is(2));
        assertThat(foods.size(), is(shopFoodSize + wareHouseFoodSize + trashFoodSize));
    }


    public Date dateCreate(int dayCount) {
        var cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, dayCount);
        return cal.getTime();
    }

    public ArrayList<Food> foods () {
        var foods = new ArrayList<Food>();
        foods.add(new Food("Сыр", dateCreate(-3), dateCreate(1), 500, 0));
        foods.add(new Food("Молоко", dateCreate(-2), dateCreate(2), 500, 0));
        foods.add(new Food("Мясо", dateCreate(-1), dateCreate(3), 500, 0));
        foods.add(new Food("Яблоки", dateCreate(-4), dateCreate(4), 500, 0));
        foods.add(new Food("Морковь", dateCreate(-5), dateCreate(0), 500, 0));
        foods.add(new Food("Пельмени", dateCreate(-6), dateCreate(-1), 500, 0));
        foods.add(new Food("Сок", dateCreate(-7), dateCreate(5), 500, 0));
        foods.add(new Food("Торт", dateCreate(-8), dateCreate(6), 500, 0));
        return foods;
    }
}