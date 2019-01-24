package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {


    @Test
    public void MapTesting() {
        User user = new User("Q", 2,new GregorianCalendar(2017, 0 , 25));
        User user2 = new User("Q", 2,new GregorianCalendar(2017, 0 , 25));
        Map<User, Object> map = new HashMap<>();
        map.put(user,1);
        map.put(user2,2);
        System.out.println(map);

        /**
         * Добавились два объекта. Так как в методе put сначла проверятеся идентичен ли hashcode, затем equals.
         * Если hashcode разный, тогда добавляется новый элемент. Если hashcode одинаковый, тогда проверяется equals.
         * Hashcode не переопределен, поэтому он разный.
         */
    }

}