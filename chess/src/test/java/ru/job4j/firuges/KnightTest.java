package ru.job4j.firuges;

import org.junit.After;
import org.junit.Test;
import ru.job4j.chess.Chess;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.King;
import ru.job4j.chess.firuges.Knight;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KnightTest {

    @Test
    public void knightMoveFromC4ToB2() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Knight(Cell.C4, true));
        boolean fl = logic.move(Cell.C4, Cell.B2, chess.getRectangles());
        assertThat(fl, is(true));
    }


    @Test
    public void knightWrongMoveFromC4ToC3() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Knight(Cell.C4, true));
        boolean fl = logic.move(Cell.C4, Cell.C3, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void knightEatFromC4ToB2() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Knight(Cell.C4, true));
        logic.add(new Knight(Cell.B2, false));
        boolean fl = logic.move(Cell.C4, Cell.B2, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void knightWrongMoveFromC4toB2() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new Knight(Cell.C4, true));
        logic.add(new Knight(Cell.B2, true));
        boolean fl = logic.move(Cell.C4, Cell.B2, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @After
    public void afterTest() {
        for (Cell c: Cell.values()) {
            c.figure = null;
        }
    }

}
