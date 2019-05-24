package ru.job4j.treadproblems;

public class Swapclass {
    private String name1 = "Оля";
    private String name2 = "Лена";

    public void swap() {
        var s = name1;
        name1 = name2;
        name2 = s;

        System.out.println(name1);
        System.out.println(name2);
    }
}