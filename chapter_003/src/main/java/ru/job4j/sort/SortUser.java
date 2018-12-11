package ru.job4j.sort;

import java.util.List;
import java.util.TreeSet;

public class SortUser {
    public TreeSet<User> sort(List<User> list) {
        TreeSet<User> users = new TreeSet<>();
        users.addAll(list);
        return users;
    }
}
