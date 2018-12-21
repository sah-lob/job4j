package ru.job4j.chess;

import javafx.scene.shape.Rectangle;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.OccupiedCellException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Класс отвечает за логику программы.
 * @author Alexander Lobachev (sah-lob@ya.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {

    Figure[] figures = new Figure[32];
    private int index = 0;
    private boolean whiteFigureMove = true;
    private ArrayList<Cell> matesells = new ArrayList<>();

    /**
     * Метод добавляет фигруру в массив с фигурами.
     * @param figure
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }


    /**
     * Метод отвечачает за движение фигуры.
     * Если фигуру можно переместить, она перемещается, метоод возвращает true.
     *
     * Метод проверяет какого цвета фигура может ходить.
     * Метод проверяет может ли фигура встать на место, которое пользователь хочет ее переместить.
     * Метод заканчивает игру, если был поставлен мат.
     *
     * @param source - клетка на которой стояла фигура.
     * @param dest - клетка на которую пользователь хочет переместить фигуру.
     * @param rectangles - Списо прямоугольников, на которых находятся фигуры.
     * @return возвращает true если фигуру можно переместить.
     */
    public boolean move(Cell source, Cell dest, ArrayList<Rectangle> rectangles) {

        boolean rst = false;

        int index = this.findBy(source);

        if (whiteFigureMove == figures[index].isWhiteColor() && !Chess.checkmate) {

            if (figures[index] != null) {

                Cell[] steps = figures[index].way(source, dest, false);
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

                    if (index >= 0) {
                        this.figures[index] = this.figures[index].copy(dest);
                    }
                    rst = true;
                }
            }
            if (rst) {
            whiteFigureMove = !whiteFigureMove;
            }

            if (dest.getFigure() != null) {
                if (checkControl(dest.getFigure().isWhiteColor())) {
                    Chess.check = true;
                    Chess.checkmate = mateControl(!dest.getFigure().isWhiteColor());
                } else {
                    Chess.check = false;
                }
            }
        }
        return rst;
    }

    /**
     * Метод удаляет всю логику текущей игры и начинает новую
     */
    public void clean() {

        for (Cell cell: Cell.values()) {
            cell.figure = null;
        }
        matesells = new ArrayList<>();
        this.index = 0;
    }

    /**
     * Программа ищет фигуру по клетке.
     * @param cell
     * @return
     */
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

    /**
     * Метод проверяет, не поставила ли фигура шаг.
     * @param attackColor цвет фигуры, которая перединулась.
     * @return результат проверки.
     */
    public boolean checkControl(boolean attackColor) {
        ArrayList<Figure> yourfigures = new ArrayList<>();
        boolean b = false;
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
                matesells = cellsOnThePathFromACheckShapeToTheKing(figure.position(), enemyKingCell);
                Chess.check = true;
                b = true;
                break;
            } else {
                Chess.check = false;
            }
        }
        return b;
    }

    /**
     * Метод проверяет может ли фигура передвинутсья со своего места, до места, где стоит вражеский король.
     * @param source место, где стоит фигура.
     * @param dest место, где стоит король.
     * @return результат проверки.
     */
    public boolean wayCheck(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);

            if (index != -1) {
                Cell[] steps = this.figures[index].way(source, dest, true);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = true;
                } else {
                    new OccupiedCellException("Такой ход невозможен.");
                }
            }
        return rst;
    }

    /**
     * Список с пустыми клетками от фигуры, которая поставила шаг(мат), до короля.
     * Сначала проверяется как именно фигура поставила шаг королю: поодигонали или по прямой линии.
     *
     * @param source - клетка, на которой стоит фигура.
     * @param dest - клетка, на которой стоит вражеский король.
     * @return лист с клетками.
     */
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
        cells.add(source);
        return cells;
    }

    /**
     * Метод проверяет поставлен ли мат королю.
     * @param defenderColor Цвет защищающийхся фигур.
     * @return мат или не мат.
     */
    public boolean mateControl(boolean defenderColor) {
        ArrayList<Figure> protectedfigures = new ArrayList<>();
        ArrayList<Figure> attackFigures = new ArrayList<>();

        for (Figure f : figures) {
            if (f != null) {
                if (f.isWhiteColor() == defenderColor) {
                    protectedfigures.add(f);
                } else {
                    attackFigures.add(f);
                }
            } else {
                try {
                    throw new FigureNotFoundException("Фигура не найдена");
                } catch (FigureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        boolean mate = true;
        // если можно съесть фигуру или встать на путь.
        for (Figure figure: protectedfigures) {
            if (!figure.getClass().getSimpleName().equals("King")) {
                for (int i = 0; i < matesells.size(); i++) {
                    if (wayCheck(figure.position(), matesells.get(i))) {
                        mate = false;
                        break;
                    }
                }
            }
            if (figure.getClass().getSimpleName().equals("King")) {
                if (availableCellsForKing(figure.position(), attackFigures)) {
                    mate = false;
                }
            }
        }
        return mate;
    }

    /**
     * Метод проверяет, есть ли куда уйти королю от мата. Метод вызывается при шаге.
     *
     * Метод проверяет:
     *       - можно ли съесть фигуру, которая поставила шаг
     *       - есть ли рядом свободные поля, которые не защищены атакующими фигурами.
     * @param cell
     * @param attackFigures
     * @return
     */
    public boolean availableCellsForKing(Cell cell, ArrayList<Figure> attackFigures) {
        boolean eatCheckFigure = false;
        // Возможные подя отъода короля.
        ArrayList<Cell> availableCells = new ArrayList<>();
        // Перебор полей рядом с королем.
        for (int i = cell.x - 1; i <= cell.x + 1; i++) {
            for (int j = cell.y - 1; j <= cell.y + 1; j++) {
                // Поле, которое находится вокруг короля.
                Cell perhapsASuitableField = Chess.findCell(i, j);
                // Оставляем только существующие поля вокруг короля.
                if (perhapsASuitableField != null && perhapsASuitableField != cell) {
                    //Проверка на то, защищают ли белую фигуру, чтобы она была съедена королем.
                    if (perhapsASuitableField.figure != null) {
                        // Проверка является ли цвет фигуры на поле сеll1 от фигуры короля.
                        if (perhapsASuitableField.figure.isWhiteColor() != cell.figure.isWhiteColor()) {
                            // Смена цвета фигуры, чтобы проверить защиту фигуры своими фигурами.
                            matesells.get(matesells.size() - 1).figure.setWhiteColor(!matesells.get(matesells.size() - 1).figure.isWhiteColor());
                            // фигуры, которые атакуют.
                            eatCheckFigure = attackFigures.stream().anyMatch(Figure -> wayCheck(Figure.position(), matesells.get(matesells.size() - 1)));
                            // Смена цвета фигуры обратно.
                            matesells.get(matesells.size() - 1).figure.setWhiteColor(!matesells.get(matesells.size() - 1).figure.isWhiteColor());
                        }
                    }
                    // Если король может
                    if (wayCheck(cell, perhapsASuitableField) && perhapsASuitableField != matesells.get(matesells.size() - 1)) {
                        if(!attackFigures.stream().anyMatch(Figure -> wayCheck(Figure.position(), perhapsASuitableField))) {
                            availableCells.add(perhapsASuitableField);
                        }
                    }
                }
            }
        }
        return availableCells.size() > 0 ? true : false || !eatCheckFigure ? true : false;
    }

    /**
     * Геттер, возврощает очередь хождения фигуры.
     * @return
     */
    public boolean isWhiteFigureMove() {
        return whiteFigureMove;
    }

    /**
     * Сеттер для выбора какая фигура ходит.
     * @param whiteFigureMove
     */
    public void setWhiteFigureMove(boolean whiteFigureMove) {
        this.whiteFigureMove = whiteFigureMove;
    }

}