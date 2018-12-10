package ru.job4j.maps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void findUserInHashMapById() {
        UserConvert userConvert = new UserConvert();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "Alexander", "Moscow"));
        users.add(new User(2, "Anton", "Moscow"));
        users.add(new User(3, "Egor", "Moscow"));
        users.add(new User(4, "Polina", "Moscow"));
        users.add(new User(5, "Anna", "Moscow"));

        HashMap<Integer, User> hashMap = userConvert.process(users);
        String result = "Anna";
        String name = hashMap.get(5).getName();
        assertThat(result, is(name));

    }
}
