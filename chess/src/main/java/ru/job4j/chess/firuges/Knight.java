package ru.job4j.chess.firuges;

/**
 * Конь
 */
public class Knight extends Figure {

    private final Cell position;
    private boolean whiteColor;

    public Knight(final Cell position, boolean whiteColor) {
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
        if (dest.getFigure() == null || dest.getFigure().isWhiteColor() != whiteColor) {
            if ((Math.abs(source.y - dest.y) == 2 && Math.abs(source.x - dest.x) == 1)
                    || (Math.abs(source.x - dest.x) == 2 && Math.abs(source.y - dest.y) == 1)) {
                steps = new Cell[]{
                        dest
                };
                if (!check) {
                    this.position.setFigure(null);
                }
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Knight(dest, whiteColor);
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
            return "KnightWhite.png";
        } else {
            return "KnightBlack.png";
        }

    }
}
