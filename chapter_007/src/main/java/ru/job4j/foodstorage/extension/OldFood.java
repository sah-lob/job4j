package ru.job4j.foodstorage.extension;

import ru.job4j.foodstorage.Food;

import java.util.Date;

public class OldFood implements Food {

    private String name;
    private Date expaireDate;
    private Date createDate;
    private int price;
    private int disscount;

    public OldFood(String name, Date createDate, Date expaireDate, int price, int disscount) {
        this.name = name;
        this.expaireDate = expaireDate;
        this.createDate = createDate;
        this.price = price;
        this.disscount = disscount;
    }


    public String getName() {
        return name;
    }

    public Date getExpaireDate() {
        return expaireDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getPrice() {
        return price;
    }

    public int getDisscount() {
        return disscount;
    }

    public void setDisscount(int disscount) {
        this.disscount = disscount;
    }
}
