package ru.job4j.firuges;

import org.junit.After;
import org.junit.Test;
import ru.job4j.chess.Chess;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Bishop;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.King;
import ru.job4j.chess.firuges.Pawn;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BishopTest {
    Chess chess = new Chess();
    Logic logic = new Logic();


    @Test
    public void bishopMoveFromA7ToB6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.B6, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void bishopMoveFromB7ToA6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.B7, true));
        boolean fl = logic.move(Cell.B7, Cell.A6, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void bishopMoveFromA7ToB8() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.B8, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void bishopMoveFromB7ToA8() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.B7, true));
        boolean fl = logic.move(Cell.B7, Cell.A8, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void bishopWrongMoveFromB7ToB8() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.B7, true));
        boolean fl = logic.move(Cell.B7, Cell.B8, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void bishopWrongMoveFromB7ToG7() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.B7, true));
        boolean fl = logic.move(Cell.B7, Cell.G7, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void bishopWrongMoveFromB7ToD5BlackPawnC6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.B7, true));
        new Pawn(Cell.C6, false);
        boolean fl = logic.move(Cell.B7, Cell.D5, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void bishopWrongMoveFromD5ToB7BlackPawnC6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.D5, true));
        new Pawn(Cell.C6, false);
        boolean fl = logic.move(Cell.D5, Cell.B7, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void bishopWrongMoveFromB5ToD7BlackPawnC6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.B5, true));
        new Pawn(Cell.C6, false);
        boolean fl = logic.move(Cell.B5, Cell.D7, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void bishopWrongMoveFromD7ToB5BlackPawnC6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.D7, true));
        new Pawn(Cell.C6, false);
        boolean fl = logic.move(Cell.D7, Cell.B5, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void bishopEatFromD7ToB5() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.D7, true));
        new Pawn(Cell.B5, false);
        boolean fl = logic.move(Cell.D7, Cell.B5, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void bishopEatFromB7ToD5() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Bishop(Cell.B7, true));
        new Pawn(Cell.D5, false);
        boolean fl = logic.move(Cell.B7, Cell.D5, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @After
    public void afterTest() {
        for (Cell c: Cell.values()) {
            c.figure = null;
        }
    }
}