package ru.job4j.firuges;

import org.junit.After;
import org.junit.Test;
import ru.job4j.chess.Chess;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueenTest {

    Chess chess = new Chess();
    Logic logic = new Logic();


    @Test
    public void queenMoveFromA7ToA6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.A6, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenMoveFromA7ToB6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.B6, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenMoveFromB7ToA6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.B7, true));
        boolean fl = logic.move(Cell.B7, Cell.A6, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenMoveFromA7ToB8() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.B8, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenMoveFromB7ToA8() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.B7, true));
        boolean fl = logic.move(Cell.B7, Cell.A8, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenMoveFromB7ToB8() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.B7, true));
        boolean fl = logic.move(Cell.B7, Cell.B8, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenMoveFromB7ToG7() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.B7, true));
        boolean fl = logic.move(Cell.B7, Cell.G7, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenWrongMoveFromB7ToD5BlackPawnC6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.B7, true));
        logic.add(new Pawn(Cell.C6, false));
        boolean fl = logic.move(Cell.B7, Cell.D5, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void queenWrongMoveFromD5ToB7BlackPawnC6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.D5, true));
        logic.add(new Pawn(Cell.C6, false));
        boolean fl = logic.move(Cell.D5, Cell.B7, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void queenWrongMoveFromB5ToD7BlackPawnC6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.B5, true));
        logic.add(new Pawn(Cell.C6, false));
        boolean fl = logic.move(Cell.B5, Cell.D7, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void queenWrongMoveFromD7ToB5BlackPawnC6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.D7, true));
        logic.add(new Pawn(Cell.C6, false));
        boolean fl = logic.move(Cell.D7, Cell.B5, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void queenEatFromD7ToB5() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.D7, true));
        logic.add(new Pawn(Cell.B5, false));
        boolean fl = logic.move(Cell.D7, Cell.B5, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenEatFromB7ToD5() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.B7, true));
        logic.add(new Pawn(Cell.D5, false));
        boolean fl = logic.move(Cell.B7, Cell.D5, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenMoveFromA7ToA2() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.A2, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenMoveFromB7ToF7() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.B7, true));
        boolean fl = logic.move(Cell.B7, Cell.F7, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenMoveFromA7ToA8() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.A8, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void queenWrongMoveFromB7ToB5BlackPawnB6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.B7, true));
        logic.add(new Pawn(Cell.B6, false));
        boolean fl = logic.move(Cell.B7, Cell.B5, chess.getRectangles());
        assertThat(fl, is(false));
    }


    @Test
    public void queenEatFromD7ToD5() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Queen(Cell.D7, true));
        logic.add(new Pawn(Cell.D5, false));
        boolean fl = logic.move(Cell.D7, Cell.D5, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @After
    public void afterTest() {
        for (Cell c: Cell.values()) {
            c.figure = null;
        }
    }
}
