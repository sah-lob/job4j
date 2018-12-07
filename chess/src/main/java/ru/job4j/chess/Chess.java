package ru.job4j.chess;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.firuges.*;

import java.util.ArrayList;

/**
 * Шахматы.
 */
public class Chess extends Application {

    private static final String JOB4J = "Шахматы на www.job4j.ru";
    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private final int size = 8;
    private final Logic logic = new Logic();
    private boolean win = false;
    public static boolean checkmate = false;
    public static boolean check = false;
    Group grid = this.buildGrid();

    /**
     * Метод отвечает за построение прямоугольников шахматной доски и их заполнение.
     * @param x координата x.
     * @param y координата y.
     * @param size размер клетки
     * @param white
     * @return
     */
    private Rectangle buildRectangle(int x, int y, int size, boolean white) {
        Rectangle rect = new Rectangle();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        if (white) {
            rect.setFill(Color.WHITE);
        } else {
            rect.setFill(Color.GRAY);
        }
        rect.setStroke(Color.BLACK);
        return rect;
    }


    private Rectangle buildFigure(int x, int y, int size, String image) {
        Rectangle rect = new Rectangle();
        rectangles.add(rect);
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(size);
        rect.setWidth(size);
        Image img = new Image(this.getClass().getClassLoader().getResource(image).toString());
        rect.setFill(new ImagePattern(img));
        final Rectangle momento = new Rectangle(x, y);
        momento.setX(x);
        momento.setY(y);


        rect.setOnDragDetected(
                event -> {
                    momento.setX(event.getX());
                    momento.setY(event.getY());
                }
        );
        rect.setOnMouseDragged(
                event -> {
                    rect.setX(event.getX() - size / 2);
                    rect.setY(event.getY() - size / 2);
                }
        );

        rect.setOnMouseReleased(
                event -> {
                    if (logic.move(this.findBy(momento.getX(), momento.getY()), this.findBy(event.getX(), event.getY()), this.rectangles)) {
                        rect.setX(((int) event.getX() / 40) * 40 + 5);
                        rect.setY(((int) event.getY() / 40) * 40 + 5);
                        momento.setX(((int) event.getX() / 40) * 40 + 5);
                        momento.setY(((int) event.getY() / 40) * 40 + 5);
                    } else {
                        rect.setX(((int) momento.getX() / 40) * 40 + 5);
                        rect.setY(((int) momento.getY() / 40) * 40 + 5);
                    }
                    if (checkmate) {
                        this.gameOverImages(grid);
                    }
                }
        );
        return rect;
    }

    /**
     * Отображение поля.
     */
    public Group  buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                panel.getChildren().add(
                        this.buildRectangle(x, y, 40, (x + y) % 2 == 0)
                );
            }
        }
        return panel;
    }

    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Начать");
        start.setOnMouseClicked(
                event -> this.refresh(border)
        );
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 400, 400));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        this.refresh(border);
    }

    /**
     * метод отвеяает за перезапуск программы.
     * @param border
     */
    private void refresh(final BorderPane border) {
        this.logic.clean();
        border.setCenter(grid);
        this.buildWhiteTeam(grid);
        this.buildBlackTeam(grid);
    }

    /**
     * Создание черной команды
     * @param grid поле
     */
    private void buildBlackTeam(Group grid) {
        this.add(new Pawn(Cell.A2, false), grid);
        this.add(new Pawn(Cell.B2, false), grid);
        this.add(new Pawn(Cell.C2, false), grid);
        this.add(new Pawn(Cell.D2, false), grid);
        this.add(new Pawn(Cell.E2, false), grid);
        this.add(new Pawn(Cell.F2, false), grid);
        this.add(new Pawn(Cell.G2, false), grid);
        this.add(new Pawn(Cell.H2, false), grid);
        this.add(new Rook(Cell.A1, false), grid);
        this.add(new Knight(Cell.B1, false), grid);
        this.add(new Bishop(Cell.C1, false), grid);
        this.add(new Queen(Cell.D1, false), grid);
        this.add(new King(Cell.E1, false), grid);
        this.add(new Bishop(Cell.F1, false), grid);
        this.add(new Knight(Cell.G1, false), grid);
        this.add(new Rook(Cell.H1, false), grid);
    }

    /**
     * Создание белой командры
     * @param grid поле
     */
    public void buildWhiteTeam(Group grid) {
        this.add(new Pawn(Cell.A7, true), grid);
        this.add(new Pawn(Cell.B7, true), grid);
        this.add(new Pawn(Cell.C7, true), grid);
        this.add(new Pawn(Cell.D7, true), grid);
        this.add(new Pawn(Cell.E7, true), grid);
        this.add(new Pawn(Cell.F7, true), grid);
        this.add(new Pawn(Cell.G7, true), grid);
        this.add(new Pawn(Cell.H7, true), grid);
        this.add(new Rook(Cell.A8, true), grid);
        this.add(new Knight(Cell.B8, true), grid);
        this.add(new Bishop(Cell.C8, true), grid);
        this.add(new Queen(Cell.D8, true), grid);
        this.add(new King(Cell.E8, true), grid);
        this.add(new Bishop(Cell.F8, true), grid);
        this.add(new Knight(Cell.G8, true), grid);
        this.add(new Rook(Cell.H8, true), grid);
    }

    /**
     * Метод отвечает за дабовление новой фигуры.
     * @param figure Новая фигура.
     * @param grid
     */
    public void add(Figure figure, Group grid) {
        this.logic.add(figure);
        Cell position = figure.position();
        grid.getChildren().add(
                this.buildFigure(
                        position.x * 40 + 5,
                        position.y * 40 + 5,
                        30,
                        figure.icon()
                )
        );
    }

    /**
     * Поиск по поля по координатам x,y.
     * @param graphX координата X.
     * @param graphY координата Y.
     * @return поле, соответствующее координатам.
     */
    private Cell findBy(double graphX, double graphY) {
        Cell rst = Cell.A1;
        int x = (int) graphX / 40;
        int y = (int) graphY / 40;
        for (Cell cell : Cell.values()) {
            if (cell.x == x && cell.y == y) {
                rst = cell;
                break;
            }
        }
        return rst;
    }


    /**
     * Проверка, стоит ли фигура на клетке по координатам.
     * @param x координата x.
     * @param y координата y.
     */
    public static boolean findByCell(int x, int y) {
        boolean flag = false;
        for (Cell cell : Cell.values()) {
            if (cell.x == x && cell.y == y) {
                if (cell.getFigure() == null) {
                    flag = true;
                    try {
                        throw new FigureNotFoundException("Фигуры на " + cell + " нет");
                    } catch (FigureNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
        return flag;
    }

    public static Cell findCell(int x, int y) {
        Cell c = null;
        for (Cell cell : Cell.values()) {
            if (cell.x == x && cell.y == y) {
                c = cell;
            }
        }
        return c;
    }

    /**
     * Создание победной надписи.
     * @param grid поле
     */
    public void gameOverImages(Group grid) {

        for (Rectangle r:rectangles) {
            r.setFill(null);
        }

        if (!logic.isWhiteFigureMove()) {
            this.addGameOver(new GameOver(Cell.C4, "W"), grid);
            this.addGameOver(new GameOver(Cell.D4, "H"), grid);
            this.addGameOver(new GameOver(Cell.E4, "I"), grid);
            this.addGameOver(new GameOver(Cell.F4, "T"), grid);
            this.addGameOver(new GameOver(Cell.G4, "E"), grid);
        } else {
            this.addGameOver(new GameOver(Cell.C4, "B"), grid);
            this.addGameOver(new GameOver(Cell.D4, "L"), grid);
            this.addGameOver(new GameOver(Cell.E4, "A"), grid);
            this.addGameOver(new GameOver(Cell.F4, "C"), grid);
            this.addGameOver(new GameOver(Cell.G4, "K"), grid);
        }
        this.addGameOver(new GameOver(Cell.D5, "w_small"), grid);
        this.addGameOver(new GameOver(Cell.E5, "I"), grid);
        this.addGameOver(new GameOver(Cell.F5, "N"), grid);
    }

    /**
     * Добавление букв победной надписи на поле.
     * @param gameOver Класс с буквами.
     * @param grid поле.
     */
    public void addGameOver(GameOver gameOver, Group grid) {
        Cell position = gameOver.position();
        grid.getChildren().add(
                this.buildFigure(
                        position.x * 40 + 5,
                        position.y * 40 + 5,
                        30,
                        gameOver.icon()
                )
        );
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }
}


