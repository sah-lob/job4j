package ru.job4j.sort;

public class User implements Comparable<User> {

    String name;
    Integer age;


    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
            return this.age.compareTo(o.age);
    }

    @Override
    public String toString() {
        return name + " - Имя " + age + " - возраст";
    }
}
