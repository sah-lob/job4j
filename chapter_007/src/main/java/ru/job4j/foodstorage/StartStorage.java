package ru.job4j.foodstorage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StartStorage {

    public static void main(String[] args) throws ParseException {

        var s =  new StartStorage();
        var controllQuality = new ControllQuality();
        var foods = new ArrayList<Food>();
        foods.add(new Food("Сыр", s.dateCreate(-3), s.dateCreate(1), 500, 0));
        foods.add(new Food("Молоко", s.dateCreate(-2), s.dateCreate(2), 500, 0));
        foods.add(new Food("Мясо", s.dateCreate(-1), s.dateCreate(3), 500, 0));
        foods.add(new Food("Яблоки", s.dateCreate(-4), s.dateCreate(4), 500, 0));
        foods.add(new Food("Морковь", s.dateCreate(-5), s.dateCreate(0), 500, 0));
        foods.add(new Food("Пельмени", s.dateCreate(-6), s.dateCreate(-1), 500, 0));
        foods.add(new Food("Сок", s.dateCreate(-7), s.dateCreate(5), 500, 0));
        foods.add(new Food("Торт", s.dateCreate(-8), s.dateCreate(6), 500, 0));
    }

    public Date dateCreate(int dayCount) {
        var cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, dayCount);
        return cal.getTime();
    }
}
