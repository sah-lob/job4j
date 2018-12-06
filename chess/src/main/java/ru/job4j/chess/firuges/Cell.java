package ru.job4j.chess.firuges;

/**
 * Клетки поля.
 */
public enum Cell {
    A1(0, 0, null), A2(0, 1, null), A3(0, 2, null), A4(0, 3, null), A5(0, 4, null), A6(0, 5, null), A7(0, 6, null), A8(0, 7, null),
    B1(1, 0, null), B2(1, 1, null), B3(1, 2, null), B4(1, 3, null), B5(1, 4, null), B6(1, 5, null), B7(1, 6, null), B8(1, 7, null),
    C1(2, 0, null), C2(2, 1, null), C3(2, 2, null), C4(2, 3, null), C5(2, 4, null), C6(2, 5, null), C7(2, 6, null), C8(2, 7, null),
    D1(3, 0, null), D2(3, 1, null), D3(3, 2, null), D4(3, 3, null), D5(3, 4, null), D6(3, 5, null), D7(3, 6, null), D8(3, 7, null),
    E1(4, 0, null), E2(4, 1, null), E3(4, 2, null), E4(4, 3, null), E5(4, 4, null), E6(4, 5, null), E7(4, 6, null), E8(4, 7, null),
    F1(5, 0, null), F2(5, 1, null), F3(5, 2, null), F4(5, 3, null), F5(5, 4, null), F6(5, 5, null), F7(5, 6, null), F8(5, 7, null),
    G1(6, 0, null), G2(6, 1, null), G3(6, 2, null), G4(6, 3, null), G5(6, 4, null), G6(6, 5, null), G7(6, 6, null), G8(6, 7, null),
    H1(7, 0, null), H2(7, 1, null), H3(7, 2, null), H4(7, 3, null), H5(7, 4, null), H6(7, 5, null), H7(7, 6, null), H8(7, 7, null);

    public final int x;
    public final int y;
    public Figure figure;

    Cell(int x, int y, Figure figure) {
        this.x = x;
        this.y = y;
        this.figure = figure;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}
