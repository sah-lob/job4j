package ru.job4j.chess.firuges;

public class GameOver {

    private final Cell position;
    private String letter;


    public GameOver(final Cell position, String letter) {
        this.position = position;
        this.letter = letter;
    }


    public Cell position() {
        return position;
    }

    public Cell[] way(Cell source, Cell dest) {
        return new Cell[0];
    }

    public Figure copy(Cell dest) {
        return null;
    }

    public boolean isWhiteColor() {
        return false;
    }

    public String icon() {
        return String.format("%S.png", letter);
    }
}
