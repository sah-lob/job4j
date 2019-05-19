package ru.job4j.menu;

import java.util.ArrayList;

public class MenuCreator {

    ArrayList<Cell> cells = new ArrayList<>();

    public Cell addCell(String title, Cell father) {
        var cell =  new Cell(title, father);
        if (father != null) {
            father.addChild(cell);
        }
        cells.add(cell);
        return cell;
    }

    public void showMenu() {
        for (var c : cells) {
            if (c.getFather() == null) {
                printCell(c, 0);
            }
        }
    }

    public void printHyphen() {
        System.out.print("---");
    }
    
    
    public void printCell(Cell cell, int level) {
        for (int i = 0; i < level; i++) {
            printHyphen();
        }
        if (level > 0) {
            System.out.print(" ");
        }
        System.out.println(cell.getTitle());
        if (cell.getChildren().size() != 0) {
            for (var c : cell.getChildren()) {
                printCell(c, level + 1);
            }
        }
    }
}
