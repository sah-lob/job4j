package ru.job4j.foodstorage;

import ru.job4j.foodstorage.extension.NewControllQuality;
import ru.job4j.foodstorage.extension.NewFood;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StartStorage {

    public static void main(String[] args) throws ParseException {

        StartStorage s =  new StartStorage();

        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("Сыр", s.dateCreate(-3), s.dateCreate(1), 500, 0));
        foods.add(new Food("Молоко", s.dateCreate(-2), s.dateCreate(2), 500, 0));
        foods.add(new Food("Мясо", s.dateCreate(-1), s.dateCreate(3), 500, 0));
        foods.add(new Food("Яблоки", s.dateCreate(-4), s.dateCreate(4), 500, 0));
        foods.add(new Food("Морковь", s.dateCreate(-5), s.dateCreate(0), 500, 0));
        foods.add(new Food("Пельмени", s.dateCreate(-6), s.dateCreate(-1), 500, 0));
        foods.add(new Food("Сок", s.dateCreate(-7), s.dateCreate(5), 500, 0));
        foods.add(new Food("Торт", s.dateCreate(-8), s.dateCreate(6), 500, 0));

        ArrayList<NewFood> newFoods = new ArrayList<>();
        newFoods.add(new NewFood("Сыр", s.dateCreate(-3), s.dateCreate(1), 500, 0, true));
        newFoods.add(new NewFood("Молоко", s.dateCreate(-2), s.dateCreate(2), 500, 0, false));
        newFoods.add(new NewFood("Мясо", s.dateCreate(-1), s.dateCreate(3), 500, 0, true));
        newFoods.add(new NewFood("Яблоки", s.dateCreate(-4), s.dateCreate(4), 500, 0, false));
        newFoods.add(new NewFood("Морковь", s.dateCreate(-5), s.dateCreate(0), 500, 0, true));
        newFoods.add(new NewFood("Пельмени", s.dateCreate(-6), s.dateCreate(-1), 500, 0, false));
        newFoods.add(new NewFood("Сок", s.dateCreate(-7), s.dateCreate(5), 500, 0, true));
        newFoods.add(new NewFood("Торт", s.dateCreate(-8), s.dateCreate(6), 500, 0, false));

        NewControllQuality controllQuality = new NewControllQuality();
//        controllQuality.analize(foods);
        controllQuality.newAnalize(newFoods);
    }


    public Date dateCreate(int dayCount) {
        var cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, dayCount);
//        System.out.println(cal.getTime());
        return cal.getTime();
    }
}
