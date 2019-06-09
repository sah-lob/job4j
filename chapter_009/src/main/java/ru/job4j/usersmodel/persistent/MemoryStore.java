package ru.job4j.usersmodel.persistent;
import ru.job4j.usersmodel.User;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStore implements Store {

    private static final MemoryStore MEMORY_STORE = new MemoryStore();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private volatile AtomicInteger id = new AtomicInteger(0);


    public static MemoryStore getInstance() {
        return MEMORY_STORE;
    }

    public MemoryStore() {

    }

    @Override
    public void add(String name, String login, String email, String createDate) {
        users.put(id.get(), new User(id.getAndIncrement(), name, login, email, createDate));
    }

    @Override
    public void update(String id, String name, String login, String email) {
        users.get(Integer.parseInt(id)).setLogin(login);
        users.get(Integer.parseInt(id)).setName(name);
        users.get(Integer.parseInt(id)).setEmail(email);
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

    public boolean isExists(String id) {
        return users.containsKey(Integer.parseInt(id));
    }
}
