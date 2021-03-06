package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SourUserTest {

    @Test
    public void userSoring() {
        SortUser sortUser = new SortUser();
        List<User> users = List.of(new User("Anna", 21),
                new User("Alexander", 23),
                new User("NoName", 12),
                new User("Egor", 32));
        TreeSet<User> setUsers = sortUser.sort(users);
        String result = users.get(2).name;
        String name = setUsers.first().name;
        assertThat(name, is(result));
    }

    @Test
    public void sortByNameLength() {
        SortUser sortUser = new SortUser();
        List<User> users = List.of(new User("Anna", 21),
                new User("Alexander", 23),
                new User("NoName", 12),
                new User("Egor", 32));
        users = sortUser.sortNameLength(users);
        String name = users.get(0).name;
        String result = "Anna";
        assertThat(name, is(result));
    }

    @Test
    public void sortByAllFields() {
        SortUser sortUser = new SortUser();
        List<User> users = List.of(new User("Сергей", 25),
                new User("Иван", 30),
                new User("Сергей", 20),
                new User("Иван", 25));
        String result = users.get(3).name + users.get(3).age;
        users = sortUser.sortByAllFields(users);
        String name = users.get(0).name + users.get(0).age;
        assertThat(name, is(result));
    }

}
