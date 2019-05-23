package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {

    private final Rectangle rect;
    private final int limitX;
    private final int limitY;
    private boolean right = true;
    private boolean up = true;
    private int speedY = 1;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public void run() {
        while (true) {
            xMove();
            yMove();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void xMove() {
        int speedX = 1;
        if (right) {
            if (rect.getX() + rect.getWidth() + speedX < limitX) {
                this.rect.setX(this.rect.getX() + speedX);
            } else {
                this.rect.setX(this.rect.getX() - speedX);
                right = false;
                if (speedX < 3) {
                    speedX++;
                }
            }
        } else {
            if (rect.getX() - speedX > 0) {
                this.rect.setX(this.rect.getX() - speedX);
            } else {
                this.rect.setX(this.rect.getX() - speedX);
                right = true;
                if (speedX < 3) {
                    speedX++;
                }
            }
        }
    }


    public void yMove() {
        if (!up) {
            if (rect.getY() + rect.getHeight() < limitY) {
                this.rect.setY(this.rect.getY() + speedY);
            } else {
                this.rect.setY(this.rect.getY() - speedY);
                up = true;
                if (speedY < 3) {
                    speedY++;
                }
            }
        } else {
            if (rect.getY() > 0) {
                this.rect.setY(this.rect.getY() - speedY);
            } else {
                this.rect.setY(this.rect.getY() + speedY);
                up = false;
                if (speedY < 3) {
                    speedY++;
                }
            }
        }
    }
}