package ru.job4j.foodstorage.extension;

import ru.job4j.foodstorage.Food;
import ru.job4j.foodstorage.storage.Storage;

public class NewTrash extends Storage {

    @Override
    public void productRequirements(int procent, Food f) {
        NewFood ff = (NewFood) f;
        if (procent >= 100) {
            if (ff.isCanReproduct()) {
                System.out.println("перерабатываем " + f.getName());
            } else {
                foods.add(ff);
                System.out.println("выкидываем " + f.getName());
            }
        } else {
            remainingFood.add(ff);
        }
    }
}
