package ru.job4j.sutdentsort;

import org.junit.Test;
import ru.job4j.studentsort.Logic;
import ru.job4j.studentsort.Student;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {

    @Test
    public void whenBoundIs25() {

        Student student1 = new Student("Ivan", 27);
        Student student2 = new Student("Khalit", 3);
        Student student3 = new Student("Sergej", 90);
        Student student4 = new Student("Alexander", 52);
        Student student5 = new Student("Vrej", 6);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(null);
        list.add(null);
        list.add(student3);
        list.add(null);
        list.add(student4);
        list.add(student5);
        list = new Logic().levelOf(list, 25);
        List<Student> resultList = List.of(student3, student4, student1);
        assertThat(list, is(resultList));
    }
}
