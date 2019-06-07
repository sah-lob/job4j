package ru.job4j.usersmodel.logic;

import ru.job4j.usersmodel.persistent.MemoryStore;
import java.util.List;

public class ValidateService {

    private static final ValidateService VALIDATE_SERVICE = new ValidateService();
    private final MemoryStore memoryStore;

    public static ValidateService getInstance() {
        return VALIDATE_SERVICE;
    }

    public ValidateService() {
        memoryStore = MemoryStore.getInstance();
    }

    public void add(String name, String login, String email, String createDate) {
        memoryStore.add(name, login, email, createDate);
    }

    public void update(String id, String login) {
        if (memoryStore.isExists(id)) {
            memoryStore.update(id, login);
        }
    }

    public void delete(String id) {
        if (memoryStore.isExists(id)) {
            memoryStore.delete(id);
        }
    }

    public List<String> findAll() {
        return memoryStore.findAll();
    }

    public void findById(String id) {
        if (memoryStore.isExists(id)) {
            memoryStore.findById(id);
        }
    }
}
