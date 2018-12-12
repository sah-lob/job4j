package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SortUser {


    public TreeSet<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }


    public List<User> sortNameLength(List<User> users) {
        users.sort(
                new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.name.length(), o2.name.length());
            }
        });
        return users;
    }


    public List<User> sortByAllFields(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int rsl = o1.name.compareTo(o2.name);
                return rsl != 0 ? rsl : o1.age.compareTo(o2.age);
            }
        });
        return users;
    }
}
