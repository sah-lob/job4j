package ru.job4j.studentsort;

import java.util.Comparator;

public class Student implements Comparable<Student> {

    private String name;
    private int scope;


    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }


    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(o.scope, this.scope);
    }
}
