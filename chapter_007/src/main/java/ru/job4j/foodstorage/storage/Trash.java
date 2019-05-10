package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.Food;

public class Trash extends Storage {
    @Override
    void productRequirements(int procent, Food f) {
        if (procent >= 100) {
            foods.add(f);
            System.out.println("выкидываем " + f.getName());
        } else {
            remainingFood.add(f);
        }
    }
}
