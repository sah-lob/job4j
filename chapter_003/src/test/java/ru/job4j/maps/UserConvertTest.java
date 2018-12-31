package ru.job4j.maps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void findUserInHashMapById() {
        UserConvert userConvert = new UserConvert();
        List<User> users = List.of(new User(1, "Alexander", "Moscow"),
                new User(2, "Anton", "Moscow"),
                new User(4, "Polina", "Moscow"),
                new User(5, "Anna", "Moscow"));

        HashMap<Integer, User> hashMap = userConvert.process(users);
        String result = "Anna";
        String name = hashMap.get(5).getName();
        assertThat(result, is(name));

    }
}
