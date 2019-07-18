package ru.job4j.thread.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private HashMap<Integer, User> users = new HashMap<>();

    public void add(User user) {
        synchronized (this) {
        users.put(user.getId(), user);
        }
    }

    public void update(User user) {
        synchronized (this) {
            add(user);
        }
    }

    public void delete(User user) {
        synchronized (this) {
            users.remove(user.getId());
        }
    }

    public void transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            var user = users.get(fromId);
            user.setAmount(amount);
            users.put(fromId, users.get(toId));
            users.put(toId, user);
        }
    }

    public synchronized HashMap<Integer, User> getUsers() {
        return users;
    }
}
