package ru.job4j.usersmodel.logic;

import ru.job4j.usersmodel.User;
import ru.job4j.usersmodel.persistent.DBStore;
import ru.job4j.usersmodel.persistent.MemoryStore;
import java.util.List;

public class ValidateService {

    private static final ValidateService VALIDATE_SERVICE = new ValidateService();
    private final DBStore memoryStore;

    public static ValidateService getInstance() {
        return VALIDATE_SERVICE;
    }

    public ValidateService() {
        memoryStore = DBStore.getInstance();
    }

    public void add(String name, String login, String email, String createDate, boolean admin, String password) {
        memoryStore.add(name, login, email, createDate, admin, password);
    }

    public void update(String id, String name, String login, String email, boolean admin, String password) {
        if (memoryStore.isExists(id)) {
            memoryStore.update(id, name, login, email, admin, password);
        }
    }

    public void delete(String id) {
        if (memoryStore.isExists(id)) {
            memoryStore.delete(id);
        }
    }

    public List<User> findAll() {
        return memoryStore.findAll();
    }

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
