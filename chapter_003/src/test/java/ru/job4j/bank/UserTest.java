package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    @Test
    public void createNewUser() {
        String name = "Alex";
        String passport = "3213456";
        User user = new User(name, passport);
        assertThat(user.getName() + user.getPassport(), is(name + passport));
    }
}
