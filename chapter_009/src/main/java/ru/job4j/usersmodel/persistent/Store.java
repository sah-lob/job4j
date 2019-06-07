package ru.job4j.usersmodel.persistent;

import java.util.List;

public interface Store {

    public void add(String name, String login, String email, String createDate);
    public void update(String id, String login);
    public void delete(String id);
    public List<String> findAll();
    public void findById(String id);
}
