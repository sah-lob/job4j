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
    public void add(User user) {
        user.setId(id.get());
        users.put(id.getAndIncrement(), user);
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

    public boolean isExists(String id) {
        return users.containsKey(Integer.parseInt(id));
    }
}
