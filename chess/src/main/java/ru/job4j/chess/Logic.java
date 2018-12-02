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
    private boolean check = false;
    private boolean checkmate = false;
    private boolean stalemate = false;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest, ArrayList<Rectangle> rectangles) {

        boolean rst = false;
        if (whiteFigureMove == source.getFigure().isWhiteColor()) {
            int index = this.findBy(source);
            if (index != -1) {
                Cell[] steps = this.figures[index].way(source, dest, false);
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
            checkControl(dest.getFigure().isWhiteColor(), rectangles);
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

    public boolean checkControl(boolean attackColor, ArrayList<Rectangle> rectangles) {

        ArrayList<Figure> yourfigures = new ArrayList<>();
        Cell enemyKingCell = null;

        for (Figure f : figures) {
            if (f != null) {
                if (f.isWhiteColor() == attackColor) {
                yourfigures.add(f);
                }
                if (f.isWhiteColor() != attackColor && f.getClass().getSimpleName().equals("King")) {
                enemyKingCell = f.position();
                }
            }
        }

        for (Figure figure: yourfigures) {
            if (wayCheck(figure.position(), enemyKingCell)) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++");

                for (Cell c: cellsOnThePathFromACheckShapeToTheKing(figure.position(), enemyKingCell)) {
                    System.out.println(c);
                }
                check = true;
            }
        }
        return false;
    }

    public boolean wayCheck(Cell source, Cell dest) {
        boolean rst = false;
            int index = this.findBy(source);
            if (index != -1) {
                Cell[] steps = this.figures[index].way(source, dest, true);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = true;
                }
            }
        return rst;
    }

    public ArrayList<Cell> cellsOnThePathFromACheckShapeToTheKing(Cell source, Cell dest) {

        ArrayList<Cell> cells = new ArrayList<>();

        // шаг поставили слон, королева или пешка.
        if (Math.abs(source.y - dest.y) == Math.abs(source.x - dest.x)) {
            int n = 0;
            if (Math.abs(source.y - dest.y) > 1) {
                if (dest.y < source.y) {
                    if (source.x < dest.x) {
                        for (int i = dest.y + 1; i < source.y; i++) {
                            cells.add(Chess.findCell(dest.x - n - 1, i));
                            n++;
                        }
                    } else {
                        for (int i = dest.y + 1; i < source.y; i++) {
                            cells.add(Chess.findCell(dest.x + n + 1, i));
                            n++;
                        }
                    }
                } else {
                    if (source.x < dest.x) {
                        for (int i = source.y + 1; i < dest.y; i++) {
                            cells.add(Chess.findCell(source.x + n + 1, i));
                            n++;
                        }
                    } else {
                        for (int i = source.y + 1; i < dest.y; i++) {
                            cells.add(Chess.findCell(source.x - n - 1, i));
                            n++;
                        }
                    }
                }
            }
        } else if ((source.y != dest.y && source.x == dest.x) || (source.x != dest.x && source.y == dest.y)) {  // шаг послатвили ладья, королева или
            if (source.y == dest.y) {
                for (int i = Math.min(source.x, dest.x) + 1; i < Math.max(source.x, dest.x); i++) {
                    cells.add(Chess.findCell(i, source.y));
                }
            }
            if (source.x == dest.x) {
                for (int i = Math.min(source.y, dest.y) + 1; i < Math.max(source.y, dest.y); i++) {
                    cells.add(Chess.findCell(source.x, i));
                }
            }
        }

        return cells;
    }
}
