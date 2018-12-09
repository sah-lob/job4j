package ru.job4j.chess.firuges;

import ru.job4j.chess.Chess;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

import java.awt.*;


/**
 * Пешка.
 */
public class Pawn extends Figure {

    private final Cell position;
    private boolean whiteColor;

    public Pawn(final Cell position, boolean whiteColor) {
        this.position = position;
        this.position.setFigure(this);
        this.whiteColor = whiteColor;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest, boolean check) {
        Cell[] steps = new Cell[0];
        boolean flag = false;


         //const1 - координата на которую может двигаться пешка.
         //const2 - координата на которую может двигаться пешка в первый ход.
         //const3 - координата на которую может встать пешка в первый ход.
         //const4 - координата где может стоять фигура, когда пешка двигается на две клетки.
        int const1, const2, const3, const4;
        if (whiteColor) {
            const1 = 1;
            const2 = 2;
            const3 = 6;
            const4 = 5;
        } else {
            const1 = -1;
            const2 = -2;
            const3 = 1;
            const4 = 2;
        }

        if (dest.getFigure() == null) {
            if (source.y == dest.y + const1 && source.x == dest.x) {
                flag = true;
            } else if (const3 == dest.y + const2 && source.x == dest.x) {
                if (Chess.findByCell(source.x, const4)) {
                    flag = true;
                }
            }
        } else if (dest.getFigure().isWhiteColor() != whiteColor) {
            if (source.y == dest.y + const1 && Math.abs(source.x - dest.x) == 1) {
                flag = true;
            }
        } else {
            try {
                throw new ImpossibleMoveException("Там находится фигура аналогичного цвета.");
            } catch (ImpossibleMoveException e) {
                e.printStackTrace();
            }
        }
        if (flag) {
            steps = new Cell[] {
                    dest
            };
            if (!check) {
                this.position.setFigure(null);
            }
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        if (puwnToQueen(dest)) {
            return new Queen(dest, whiteColor);
        } else {
        return new Pawn(dest, whiteColor);
        }
    }

    @Override
    public boolean isWhiteColor() {
        return whiteColor;
    }

    public void setWhiteColor(boolean whiteColor) {
        this.whiteColor = whiteColor;
    }

    @Override
    public String icon() {
        if (whiteColor) {
           return "PawnWhite.png";
        } else {
            return "PawnBlack.png";
        }

    }

    public boolean puwnToQueen(Cell dest) {
        boolean flag = false;
        if (dest == Cell.A1
                || dest == Cell.B1
                || dest == Cell.C1
                || dest == Cell.D1
                || dest == Cell.E1
                || dest == Cell.G1
                || dest == Cell.H1
                || dest == Cell.A8
                || dest == Cell.B8
                || dest == Cell.C8
                || dest == Cell.D8
                || dest == Cell.E8
                || dest == Cell.F8
                || dest == Cell.G8
                || dest == Cell.H8) {
            flag = true;
        }
        return flag;
    }
}

