package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.Food;

public class Warehouse extends Storage {

    @Override
    public void productRequirements(int procent, Food f) {
        if (procent >= 0 && procent < 25) {
            adding(f);
        } else {
            remainingFood.add(f);
        }
    }

    public void adding(Food f) {
        foods.add(f);
        System.out.println("добавляем " + f.getName() + " на " + this.toString());
    }


    @Override
    public String toString() {
        return "склад";
    }
}
