package ru.job4j.foodstorage;

import java.util.Date;

public interface Food {
    String getName();
    Date getExpaireDate();
    Date getCreateDate();
    int getPrice();
    int getDisscount();
    void setDisscount(int disscount);
}
