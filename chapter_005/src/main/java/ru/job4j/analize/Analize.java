package ru.job4j.analize;

import java.util.List;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {

        var info = new Info();
        var in = true;

        for (int i = 0; i < current.size(); i++) {
            for (int j = 0; j < previous.size(); j++) {
                if (current.get(i).id == previous.get(j).id) {
                    in = false;
                    if (!current.get(i).name.equals(previous.get(j).name)) {
                        info.changed++;
                    }
                   previous.remove(j);
                }
            }
            if (in) {
                info.added++;
            } else {
                in = true;
            }
        }

        info.deleted = previous.size();


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

