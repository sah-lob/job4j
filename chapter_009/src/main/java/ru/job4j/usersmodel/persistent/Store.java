package ru.job4j.usersmodel.persistent;

import ru.job4j.usersmodel.User;

import java.util.List;

public interface Store {

    void add(String name, String login, String email, String createDate);
    void update(String id, String name, String login, String email);
    void delete(String id);
    List<User> findAll();
    User findById(String id);
}
