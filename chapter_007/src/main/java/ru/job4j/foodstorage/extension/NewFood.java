package ru.job4j.foodstorage.extension;

import ru.job4j.foodstorage.Food;

import java.util.Date;

public class NewFood extends OldFood implements Food {
    protected boolean canReproduct = false;
    public NewFood(String name, Date createDate, Date expaireDate, int price, int disscount, boolean canReproduct) {
        super(name, createDate, expaireDate, price, disscount);
        this.canReproduct = canReproduct;
    }
    public boolean isCanReproduct() {
        return canReproduct;
    }
}
