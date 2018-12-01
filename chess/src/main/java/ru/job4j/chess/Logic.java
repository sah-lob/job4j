package ru.job4j.chess;

import javafx.scene.shape.Rectangle;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.ArrayList;

/**
 * //TODO add comments.
 *
 * @author Alexander Lobachev (sah-lob@ya.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    Figure[] figures = new Figure[32];
    private int index = 0;
    private boolean whiteFigureMove = true;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest, ArrayList<Rectangle> rectangles) {
        boolean rst = false;

        if (whiteFigureMove == source.getFigure().isWhiteColor()) {
            int index = this.findBy(source);
            if (index != -1) {
                Cell[] steps = this.figures[index].way(source, dest);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    if (dest.getFigure() != null) {
                        for (int i = 0; i < figures.length; i++) {
                            if (figures[i] == dest.getFigure()) {
                                figures[i] = null;
                                break;
                            }
                        }
                        for (Rectangle r : rectangles) {
                            if (r.getX() == (dest.x * 40 + 5) && r.getY() == (dest.y * 40 + 5)) {
                                r.setFill(null);
                            }
                        }
                    }
                    rst = true;
                    this.figures[index] = this.figures[index].copy(dest);
                }
            }
            if (rst) {
            whiteFigureMove = !whiteFigureMove;
            }
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
