package ru.job4j.foodStorage;

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

        ControllQuality controllQuality = new ControllQuality();
        controllQuality.analize(foods);
    }


    public Date dateCreate(int day_count) {
        var cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, day_count);
        System.out.println(cal.getTime());
        return cal.getTime();
    }
}
