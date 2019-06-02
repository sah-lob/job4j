package ru.job4j.bomberman;

public class Hero {
    final private Cell position;

    public Hero(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return position;
    }
}