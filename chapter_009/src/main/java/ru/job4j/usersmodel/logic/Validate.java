package ru.job4j.usersmodel.logic;

import ru.job4j.usersmodel.User;

import java.util.List;

public interface Validate {

    void update(String id, String name, String login, String email, boolean admin, String password);
    void delete(String id);
    List<User> findAll();
    User findById(String id);
    int isCredentional(String email, String password);
    void add(User user);
}
