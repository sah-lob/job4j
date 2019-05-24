package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {

    private final Rectangle rect;
    private final int limitX;
    private final int limitY;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public void run() {
        var right = true;
        var down = true;
        var speedY = 1;
        var speedX = 1;
        while (true) {
            if (right != move(right, true, speedX)) {
                right = !right;
                speedX++;
            }
            if (down != move(down, false, speedY)) {
                down = !down;
                speedY++;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean move(boolean rightDown, boolean isX, int speed) {
        double p = isX ? this.rect.getX() : this.rect.getY();
        if (rightDown) {
            p += speed;
            if (p + (isX ? rect.getWidth() : rect.getHeight()) > (isX ? limitX : limitY)) {
                p = isX ? limitX - rect.getWidth() : limitY - rect.getHeight();
                rightDown = false;
            }
        } else {
            p -= speed;
            if (p < 0) {
                p = p * (-1);
                rightDown = true;
            }
        }
        if (isX) {
            this.rect.setX(p);
        } else {
            this.rect.setY(p);
        }
        return rightDown;
    }
}