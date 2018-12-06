package ru.job4j.chess.firuges;

import ru.job4j.chess.Chess;

/**
 * Ладья
 */
public class Rook extends Figure {

    private final Cell position;
    private boolean whiteColor;

    public Rook(final Cell position, boolean whiteColor) {
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

        boolean flag = false;
        Cell[] steps = new Cell[0];

        if (dest.getFigure() == null) {
            if ((source.y != dest.y && source.x == dest.x) || (source.x != dest.x && source.y == dest.y)) {
                flag = checkToOtherFigure(source, dest);
            }
        } else if (dest.getFigure().isWhiteColor() != whiteColor) {
            if ((source.y != dest.y && source.x == dest.x) || (source.x != dest.x && source.y == dest.y)) {
                flag = checkToOtherFigure(source, dest);
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
    public boolean isWhiteColor() {
        return whiteColor;
    }

    public void setWhiteColor(boolean whiteColor) {
        this.whiteColor = whiteColor;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Rook(dest, whiteColor);
    }

    public static boolean checkToOtherFigure(Cell source, Cell dest) {
        boolean flag = true;

        if (source.y == dest.y) {
            for (int i = Math.min(source.x, dest.x) + 1; i < Math.max(source.x, dest.x); i++) {
                if (!Chess.findByCell(i, source.y)) {
                    flag = false;
                    break;
                }
            }
        }
        if (source.x == dest.x) {
            for (int i = Math.min(source.y, dest.y) + 1; i < Math.max(source.y, dest.y); i++) {
                if (!Chess.findByCell(source.x, i)) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public String icon() {
        if (whiteColor) {
            return "RookWhite.png";
        } else {
            return "RookBlack.png";
        }
    }
}