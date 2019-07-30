package ru.job4j.persistence;

import java.util.HashMap;
import java.util.Map;

public interface UsersStore {
    void add(Person person);
    Map<String, Person> findAll();
    Person findByLogin(String login);
    boolean isExists(String login);
    boolean validatePerson(Person person);
    boolean editPerson(String login, String fio, String number);
    String[] getFioAndNumber(String login);
}
