package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private ReentrantLock[][] board;
    private final static int MILLIS_FOR_TRYLOCK = 500;
    private int width;
    private int height;

    public Board(int width, int height) throws OutOfBoundsException {
        if (width > 0 && height > 0) {
            this.width = width;
            this.height = height;
            this.board = new ReentrantLock[this.width][this.height];
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height; j++) {
                    this.board[i][j] = new ReentrantLock();
                }
            }
        } else {
            throw new OutOfBoundsException();
        }
    }


    public boolean move(Cell source, Cell dest) throws InterruptedException {
        if (dest.getX() < 0 || dest.getX() >= width || dest.getY() < 0 || dest.getY() >= height) {
            return false;
        }
        boolean result = board[dest.getX()][dest.getY()].tryLock(MILLIS_FOR_TRYLOCK, TimeUnit.MILLISECONDS);
        if (result) {
            board[source.getX()][source.getY()].unlock();
            result = true;
        }
        return result;
    }

    public boolean init(Cell dest) throws InterruptedException {
        boolean result = board[dest.getX()][dest.getY()].tryLock(MILLIS_FOR_TRYLOCK, TimeUnit.MILLISECONDS);
        return result;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
