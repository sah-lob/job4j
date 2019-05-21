package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.ControllQuality;
import ru.job4j.foodstorage.Food;

import java.util.ArrayList;

public abstract class Storage {

    ControllQuality controllQuality = new ControllQuality();
    Storage next = null;
    protected ArrayList<Food> foods = new ArrayList<>();
    protected ArrayList<Food> remainingFood = new ArrayList<>();

    public void setNext(Storage next) {
        this.next = next;
    }

    public void add(ArrayList<Food> newFood) {
        if (newFood.size() > 0) {
            for (var f : newFood) {
                var procent = controllQuality.percentageOfLife(f.getCreateDate(), f.getExpaireDate());
                productRequirements(procent, f);
            }
            if (next != null) {
                next.add(remainingFood);
            }
        }
    }


    public abstract void productRequirements(int procent, Food f);

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

    public void deleteAllFoods() {
        foods = new ArrayList<>();
        remainingFood = new ArrayList<>();
    }
}
