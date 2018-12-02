package ru.job4j.chess.firuges;

import ru.job4j.chess.Chess;

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
        return new Pawn(dest, whiteColor);
    }

    @Override
    public boolean isWhiteColor() {
        return whiteColor;
    }

    @Override
    public String icon() {
        if (whiteColor) {
           return "PawnWhite.png";
        } else {
            return "PawnBlack.png";
        }

    }
}

