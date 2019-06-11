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

    public void add(String name, String login, String email, String createDate) {
        memoryStore.add(name, login, email, createDate);
    }

    public void update(String id, String name, String login, String email) {
        if (memoryStore.isExists(id)) {
            memoryStore.update(id, name, login, email);
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
}
