package ru.job4j.thread.userstorage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddNewUser() {
        var userStorage = new UserStorage();
        var user = new User(1, 1_000);
        userStorage.add(user);
        assertThat(userStorage.getUsers().size(), is(1));
    }

    @Test
    public void whenUpdateSelectedUser() {
        var userStorage = new UserStorage();
        var user = new User(1, 1_000);
        userStorage.add(user);
        user.setAmount(2_000);
        userStorage.update(user);
        assertThat(userStorage.getUsers().get(1).getAmount(), is(2_000));
    }

    @Test
    public void whenAddNewUserThenDeleteIt() {
        var userStorage = new UserStorage();
        var user = new User(1, 1_000);
        userStorage.add(user);
        userStorage.delete(user);
        assertThat(userStorage.getUsers().size(), is(0));
    }

    @Test
    public void whenAddTwoNewUsersThenTransferIt() {
        var userStorage = new UserStorage();
        var user = new User(1, 1_000);
        var user2 = new User(2, 5_000);
        userStorage.add(user);
        userStorage.add(user2);
        userStorage.transfer(1, 2, 9_000);
        assertThat(userStorage.getUsers().get(2).getAmount(), is(9_000));
        assertThat(userStorage.getUsers().get(1).getAmount(), is(5_000));
    }
}