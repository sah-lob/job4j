package ru.job4j.chess.firuges;

import ru.job4j.chess.Chess;

public class Bishop extends Figure {

    private final Cell position;
    private boolean whiteColor;

    public Bishop(final Cell position, boolean whiteColor) {
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
            if (Math.abs(source.y - dest.y) == Math.abs(source.x - dest.x)) {
                if (checkToOtherFigure(source, dest)) {
                    flag = true;
                }
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
        return new Bishop(dest, whiteColor);
    }

    @Override
    public boolean isWhiteColor() {
        return whiteColor;
    }

    public String icon() {
        if (whiteColor) {
            return "BishopWhite.png";
        } else {
            return "BishopBlack.png";
        }
    }

    public static boolean checkToOtherFigure(Cell source, Cell dest) {
        boolean flag = true;
        int n = 0;

        if (Math.abs(source.y - dest.y) > 1) {
            if (dest.y < source.y) {
                if (source.x < dest.x) {
                    for (int i = dest.y + 1; i < source.y; i++) {
                        if (!Chess.findByCell(dest.x - n - 1, i)) {
                            flag = false;
                            break;
                        }
                        n++;
                    }
                } else {
                    for (int i = dest.y + 1; i < source.y; i++) {
                        if (!Chess.findByCell(dest.x + n + 1, i)) {
                            flag = false;
                            break;
                        }
                        n++;
                    }
                }
            } else {
                if (source.x < dest.x) {
                    for (int i = source.y + 1; i < dest.y; i++) {
                        if (!Chess.findByCell(source.x + n + 1, i)) {
                            flag = false;
                            break;
                        }
                        n++;
                    }
                } else {
                    for (int i = source.y + 1; i < dest.y; i++) {
                        if (!Chess.findByCell(source.x - 1 - n, i)) {
                            flag = false;
                            break;
                        }
                        n++;
                    }
                }
            }
        }
        return flag;
    }
}