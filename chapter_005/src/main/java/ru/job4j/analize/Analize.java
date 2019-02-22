package ru.job4j.analize;

import java.util.HashMap;
import java.util.List;

public class Analize {



    public Info diff(List<User> previous, List<User> current) {

        var info = new Info();

        HashMap<Integer, User> map = new HashMap<>();

        for (var user : previous) {
            map.put(user.id, user);
        }

        for (var user : current) {
            var u = map.get(user.id);
            if (u == null) {
                info.added++;
            } else if (!u.name.equals(user.name)) {
                info.changed++;
            }
            map.remove(user.id);
        }
        info.deleted = map.size();

        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added = 0;
        int changed = 0;
        int deleted = 0;
    }

}

