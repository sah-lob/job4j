package ru.job4j.menu;

import java.util.ArrayList;

public class Cell {
    public String title;
    public Cell father;
    public ArrayList<Cell> children = new ArrayList<>();

    public Cell(String title, Cell father) {
        this.title = title;
        this.father = father;
    }

    public String getTitle() {
        return title;
    }

    public Cell getFather() {
        return father;
    }

    public ArrayList<Cell> getChildren() {
        return children;
    }

    public void addChild(Cell cell) {
        children.add(cell);
    }



}
