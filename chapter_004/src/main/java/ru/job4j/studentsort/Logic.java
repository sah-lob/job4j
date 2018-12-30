package ru.job4j.studentsort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Logic {
    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream().flatMap(Stream::ofNullable).sorted().takeWhile(v -> v.getScope() > bound).collect(Collectors.toList());
    }
}
