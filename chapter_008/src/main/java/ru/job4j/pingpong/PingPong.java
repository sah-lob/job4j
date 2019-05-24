package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {

    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        int x = (int) (Math.random() * (limitX - 10));
        int y = (int) (Math.random() * (limitY - 10));
        Rectangle rect = new Rectangle(x, y, 10, 10);
        group.getChildren().add(rect);
        new Thread(new RectangleMove(rect, limitX, limitY)).start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}