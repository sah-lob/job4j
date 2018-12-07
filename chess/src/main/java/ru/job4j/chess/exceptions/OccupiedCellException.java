package ru.job4j.chess.exceptions;

public class OccupiedCellException extends Exception {
    public OccupiedCellException(String message) {
        super(message);
    }
}
