package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.Food;

public class Warehouse extends Storage {

    @Override
    void productRequirements(int procent, Food f) {
        if (procent >= 0 && procent < 25) {
            foods.add(f);
            System.out.println("добавляем " + f.getName() + "на склад");
        } else {
            remainingFood.add(f);
        }
    }
}
