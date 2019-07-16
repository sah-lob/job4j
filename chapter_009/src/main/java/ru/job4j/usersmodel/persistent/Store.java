package ru.job4j.usersmodel.persistent;

import ru.job4j.usersmodel.User;

import java.util.List;

public interface Store {
    void add(User user);
    void update(String id, String name, String login, String email,  boolean admin, String password);
    void delete(String id);
    List<User> findAll();
    User findById(String id);
}
