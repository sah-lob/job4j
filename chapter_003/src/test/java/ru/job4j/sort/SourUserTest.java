package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SourUserTest {

    @Test
    public void userSoring() {

        SortUser sortUser = new SortUser();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Anna", 21));
        users.add(new User("Alexander", 23));
        users.add(new User("NoName", 12));
        users.add(new User("Egor", 32));
        TreeSet<User> setUsers = sortUser.sort(users);
        String result = users.get(2).name;
        String name = setUsers.first().name;
        assertThat(name, is(result));
    }
}
