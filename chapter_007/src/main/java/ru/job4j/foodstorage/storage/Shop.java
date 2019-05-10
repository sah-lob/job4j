package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.Food;

public class Shop extends Storage {

    @Override
    public void productRequirements(int procent, Food f) {
        if (procent >= 25 && procent < 75) {
            foods.add(f);
            System.out.println("добавляем " + f.getName() + " в " + this.toString());
        } else if (procent >= 75 && procent < 100) {
            f.setDisscount(procent - 50);
            foods.add(f);
            System.out.println("добавляем " + f.getName() + " в " + this.toString() + " со скидкой " + f.getDisscount() + "%");
        } else {
            remainingFood.add(f);
        }
    }

    @Override
    public String toString() {
        return "магазин";
    }
}
