package ru.job4j.usersmodel;

import java.util.Objects;

public class Person {
    private String name;
    private String surname;
    private String gender;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s, Surname: %s, Gender: %s, Description: %s",
                name,
                surname,
                gender,
                desc
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(name, person.name)
                && Objects.equals(surname, person.surname)
                && Objects.equals(gender, person.gender)
                && Objects.equals(desc, person.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, gender, desc);
    }
}