package ru.job4j.foodstorage.extension;

import ru.job4j.foodstorage.Food;
import ru.job4j.foodstorage.storage.Warehouse;

public class FreeseWarehouse extends Warehouse {

    @Override
    public void adding(Food f) {
        if (f.getName().contains("овощи")) {
            super.adding(f);
        } else {
            remainingFood.add(f);
        }
    }

    @Override
    public String toString() {
        return "cклад с низкой температурой";
    }
}
