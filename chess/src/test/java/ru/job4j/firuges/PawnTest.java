package ru.job4j.firuges;

import org.junit.After;
import org.junit.Test;
import ru.job4j.chess.Chess;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.King;
import ru.job4j.chess.firuges.Pawn;

import java.sql.SQLOutput;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class PawnTest {

    Chess chess = new Chess();
    Logic logic = new Logic();


    @Test
    public void pawnMoveFromA7ToA6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Pawn(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.A6, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void pawnMoveFromA7ToA5() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Pawn(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.A5, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void pawnWrongMoveFromA7ToA4() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Pawn(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.A4, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void pawnWrongMoveFromA7ToB6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Pawn(Cell.A7, true));
        boolean fl = logic.move(Cell.A7, Cell.B6, chess.getRectangles());
        assertThat(fl, is(false));
    }


    @Test
    public void pawnWrongMoveFromA7ToA5WithBlackFigureWithA6() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Pawn(Cell.A7, true));
        logic.add(new Pawn(Cell.A6, false));
        boolean fl = logic.move(Cell.A7, Cell.A5, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void pawnEatFromA4ToB3() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Pawn(Cell.A4, true));
        logic.add(new Pawn(Cell.B3, false));
        boolean fl = logic.move(Cell.A4, Cell.B3, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void pawnMoveFromA7ToA5ThanA5ToA4() {
        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Pawn(Cell.A7, true));
        logic.move(Cell.A7, Cell.A5, chess.getRectangles());
        logic.setWhiteFigureMove(true);
        logic.move(Cell.A5, Cell.A4, chess.getRectangles());
        boolean flag = Cell.A4.figure != null;
        assertThat(flag, is(true));
    }


    @After
    public void afterTest() {
        for (Cell c: Cell.values()) {
            c.figure = null;
        }
    }
}