package ru.job4j.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SeatsStore {
    void addAll(int rows, int numbers, int price);
    List<Seat> findAll();
    boolean update(String login, int row, int number);
}