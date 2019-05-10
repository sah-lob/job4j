package ru.job4j.foodstorage;

import org.junit.Test;
import ru.job4j.foodstorage.extension.FreeseWarehouse;
import ru.job4j.foodstorage.extension.NewFood;
import ru.job4j.foodstorage.extension.NewTrash;
import ru.job4j.foodstorage.extension.OldFood;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
import ru.job4j.foodstorage.storage.Warehouse;

import java.util.ArrayList;
import java.util.Date;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartStorageTest {


    @Test
    public void oldFoodsTest() {
        var foods = foods();
        var shop = new Shop();
        var warehouse = new Warehouse();
        var trash = new Trash();
        warehouse.setNext(shop);
        shop.setNext(trash);
        warehouse.add(foods);
        var shopFoodSize = shop.getFoods().size();
        var wareHouseFoodSize = warehouse.getFoods().size();
        var trashFoodSize = trash.getFoods().size();
        assertThat(shopFoodSize, is(6));
        assertThat(wareHouseFoodSize, is(0));
        assertThat(trashFoodSize, is(2));
        assertThat(foods.size(), is(shopFoodSize + wareHouseFoodSize + trashFoodSize));
    }

    @Test
    public void newFoodStorageTest() {
        var foods = newFoods();
        var shop = new Shop();
        var warehouse = new Warehouse();
        var freeseWarehouse = new FreeseWarehouse();
        var trash = new NewTrash();
        freeseWarehouse.setNext(warehouse);
        warehouse.setNext(shop);
        shop.setNext(trash);
        freeseWarehouse.add(foods);
        var shopFoodSize = shop.getFoods().size();
        var wareHouseFoodSize = warehouse.getFoods().size();
        var trashFoodSize = trash.getFoods().size();
        var freeseWarehouseFoodSize = freeseWarehouse.getFoods().size();
        assertThat(shopFoodSize, is(6));
        assertThat(wareHouseFoodSize, is(1));
        assertThat(trashFoodSize, is(1));
        assertThat(foods.size() - 1, is(shopFoodSize + wareHouseFoodSize + trashFoodSize + freeseWarehouseFoodSize));
    }

    @Test
    public void twoWarehouseTest() {
        var foods = newFoods();
        var foods1 = new ArrayList<Food>();
        var foods2 = new ArrayList<Food>();
        var shop = new Shop();
        var warehouse = new Warehouse();
        var warehouse2 = new Warehouse();
        var trash = new NewTrash();
        for (int i = 0; i < foods.size(); i++) {
            if (i % 2 == 0) {
                foods1.add(foods.get(i));
            } else {
                foods2.add(foods.get(i));
            }
        }
        warehouse.setNext(shop);
        warehouse2.setNext(shop);
        shop.setNext(trash);
        warehouse.add(foods1);
        warehouse2.add(foods2);
    }


    public Date dateCreate(int dayCount) {
        var today = new Date();
        var tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24) * dayCount);
        return tomorrow;
    }

    public ArrayList<Food> foods() {
        var foods = new ArrayList<Food>();
        foods.add(new OldFood("Сыр", dateCreate(-3), dateCreate(1), 500, 0));
        foods.add(new OldFood("Молоко", dateCreate(-2), dateCreate(2), 500, 0));
        foods.add(new OldFood("Мясо", dateCreate(-1), dateCreate(3), 500, 0));
        foods.add(new OldFood("Яблоки", dateCreate(-4), dateCreate(4), 500, 0));
        foods.add(new OldFood("Морковь", dateCreate(-5), dateCreate(0), 500, 0));
        foods.add(new OldFood("Пельмени", dateCreate(-6), dateCreate(-1), 500, 0));
        foods.add(new OldFood("Сок", dateCreate(-7), dateCreate(5), 500, 0));
        foods.add(new OldFood("Торт", dateCreate(-8), dateCreate(6), 500, 0));
        return foods;
    }

    public ArrayList<Food> newFoods() {
        var foods = new ArrayList<Food>();
        foods.add(new NewFood("Сметана", dateCreate(-1), dateCreate(20), 500, 0, true));
        foods.add(new NewFood("овощи", dateCreate(-1), dateCreate(20), 500, 0, true));
        foods.add(new NewFood("Сыр", dateCreate(-3), dateCreate(1), 500, 0, true));
        foods.add(new NewFood("Молоко", dateCreate(-2), dateCreate(2), 500, 0, false));
        foods.add(new NewFood("Мясо", dateCreate(-1), dateCreate(3), 500, 0, true));
        foods.add(new NewFood("Яблоки", dateCreate(-4), dateCreate(4), 500, 0, false));
        foods.add(new NewFood("Морковь", dateCreate(-5), dateCreate(0), 500, 0, true));
        foods.add(new NewFood("Пельмени", dateCreate(-6), dateCreate(-1), 500, 0, false));
        foods.add(new NewFood("Сок", dateCreate(-7), dateCreate(5), 500, 0, true));
        foods.add(new NewFood("Торт", dateCreate(-8), dateCreate(6), 500, 0, false));
        return foods;
    }
}