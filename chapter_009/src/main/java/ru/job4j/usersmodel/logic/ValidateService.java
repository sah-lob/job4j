package ru.job4j.usersmodel.logic;

import ru.job4j.usersmodel.User;
import ru.job4j.usersmodel.persistent.DBStore;

import java.util.List;

public class ValidateService implements Validate {

    private static final Validate VALIDATE_SERVICE = new ValidateService();
    private final DBStore memoryStore;

    public static Validate getInstance() {
        return VALIDATE_SERVICE;
    }

    public ValidateService() {
        memoryStore = DBStore.getInstance();
    }

    @Override
    public void add(User user) {
        memoryStore.add(user);
    }

    @Override
    public void update(String id, String name, String login, String email, boolean admin, String password) {
        if (memoryStore.isExists(id)) {
            memoryStore.update(id, name, login, email, admin, password);
        }
    }

    @Override
    public void delete(String id) {
        if (memoryStore.isExists(id)) {
            memoryStore.delete(id);
        }
    }

    @Override
    public List<User> findAll() {
        return memoryStore.findAll();
    }

    @Override
    public User findById(String id) {
        User result = null;
        if (memoryStore.isExists(id)) {
            result = memoryStore.findById(id);
        }
        return result;
    }

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
