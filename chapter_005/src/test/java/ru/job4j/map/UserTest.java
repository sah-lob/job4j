package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {


    @Test
    public void mapTesting() {
        User user = new User("Q", 2, new GregorianCalendar(2017, 0, 25));
        User user2 = new User("Q", 2, new GregorianCalendar(2017, 0, 25));
        System.out.println(user.hashCode());
        System.out.println(user2.hashCode());
        Map<User, Object> map = new HashMap<>();
        map.put(user, 1);
        map.put(user2, 2);
        System.out.println(map);

        /**
         *  Из-за того, что хешкод одинаковый, элементы попадут в одну ячейку.
         *  Начнется сравнение по HashCode и equals.
         *  так как equals не переопределен, то объекты будут разные, поэтому добавится новый элемент.
         */
    }

}