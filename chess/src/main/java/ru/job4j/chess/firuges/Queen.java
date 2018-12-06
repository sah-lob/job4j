package ru.job4j.chess.firuges;

/**
 * Дама
 */
public class Queen extends Figure {

    private final Cell position;
    private boolean whiteColor;

    public Queen(final Cell position, boolean whiteColor) {
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

        if (dest.getFigure() == null || dest.getFigure().isWhiteColor() != whiteColor) {
            if ((source.y != dest.y && source.x == dest.x) || ((source.x != dest.x) && source.y == dest.y)) {
                flag = Rook.checkToOtherFigure(source, dest);
            }
            if (Math.abs(source.y - dest.y) == Math.abs(source.x - dest.x)) {
                flag = Bishop.checkToOtherFigure(source, dest);
            }
            if (flag) {
                steps = new Cell[]{dest};
                if (!check) {
                    this.position.setFigure(null);
                }
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Queen(dest, whiteColor);
    }

    @Override
    public boolean isWhiteColor() {
        return whiteColor;
    }

    public void setWhiteColor(boolean whiteColor) {
        this.whiteColor = whiteColor;
    }

    public String icon() {
        if (whiteColor) {
            return "QueenWhite.png";
        } else {
            return "QueenBlack.png";
        }
    }
}
