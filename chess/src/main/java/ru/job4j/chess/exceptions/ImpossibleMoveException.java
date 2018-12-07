package ru.job4j.chess.exceptions;

public class ImpossibleMoveException extends Exception {
    public ImpossibleMoveException(String message) {
        super(message);
    }
}