package ru.job4j.chess.firuges;


/**
 * Король
 */
public class King extends Figure {

    private final Cell position;
    private boolean whiteColor;

    public King(final Cell position, boolean whiteColor) {
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
            if ((Math.abs(dest.x - source.x) <= 1 && Math.abs(dest.y - source.y) <= 1)) {
                steps = new Cell[] {
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
        return new King(dest, whiteColor);
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
            return "KingWhite.png";
        } else {
            return "KingBlack.png";
        }
    }
}
