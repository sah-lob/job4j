package ru.job4j.foodStorage.storage;

import ru.job4j.foodStorage.Food;

import java.util.ArrayList;

public class Storage {

    protected ArrayList<Food> foods = new ArrayList<>();

    public void add(Food food) {
        foods.add(food);
    }

    public void takeAway(String name) {
        for (int i = 0; i < foods.size() - 1; i++) {
            if (name.equals(foods.get(i).getName())) {
                foods.remove(i);
                break;
            }
        }
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }
}
