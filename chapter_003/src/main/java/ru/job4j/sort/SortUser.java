package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SortUser {
    
    public TreeSet<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    public List<User> sortNameLength(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User -> User.getName().length())).collect(Collectors.toList());
    }

    public List<User> sortByAllFields(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getName).thenComparing(User::getAge)).collect(Collectors.toList());
    }
}
