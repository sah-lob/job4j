package ru.job4j.usersmodel.logic;

import ru.job4j.usersmodel.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ValidateStub implements Validate {

    private final Map<Integer, User> users = new HashMap<>();
    private int id = 0;


    @Override
    public void add(User user) {
        user.setId(id);
        users.put(id++, user);
    }

    @Override
    public void update(String id, String name, String login, String email, boolean admin, String password) {
        int id1 = Integer.parseInt(id);
        users.get(id1).setLogin(login);
        users.get(id1).setName(name);
        users.get(id1).setEmail(email);
        users.get(id1).setAdmin(admin);
        users.get(id1).setPassword(password);
    }

    @Override
    public void delete(String id) {
        users.remove(Integer.parseInt(id));
    }

    @Override
    public List<User> findAll() {
        List<User> arrayList = new CopyOnWriteArrayList<>();
        for (var v: users.values()) {
            arrayList.add(v);
        }
        return arrayList;
    }

    @Override
    public User findById(String id) {
        return users.get(Integer.parseInt(id));
    }

//    @Override
    public int isCredentional(String email, String password) {
        var result = -1;
        for (var u : findAll()) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                result = u.getId();
                break;
            }
        }
        return result;
    }
}
