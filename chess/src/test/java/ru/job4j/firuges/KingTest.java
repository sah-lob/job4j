package ru.job4j.firuges;

import org.junit.After;
import org.junit.Test;
import ru.job4j.chess.Chess;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.King;
import ru.job4j.chess.firuges.Pawn;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class KingTest {

    @Test
    public void kingMoveFromC4ToC3() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new King(Cell.C4, true));
        boolean fl = logic.move(Cell.C4, Cell.C3, chess.getRectangles());
        assertThat(fl, is(true));
    }
    @Test
    public void kingMoveFromC4ToC5() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new King(Cell.C4, true));
        boolean fl = logic.move(Cell.C4, Cell.C5, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void kingMoveFromC4ToB5() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new King(Cell.C4, true));
        boolean fl = logic.move(Cell.C4, Cell.B5, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @Test
    public void kingWrongMoveFromC4ToB5() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new King(Cell.C4, true));
        new Pawn(Cell.B5, true);
        boolean fl = logic.move(Cell.C4, Cell.B5, chess.getRectangles());
        assertThat(fl, is(false));
    }

    @Test
    public void kingEatFromC4ToB5() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.setWhiteFigureMove(true);
        logic.add(new King(Cell.F1, false));
        logic.add(new King(Cell.C4, true));
        new Pawn(Cell.B5, false);
        boolean fl = logic.move(Cell.C4, Cell.B5, chess.getRectangles());
        assertThat(fl, is(true));
    }

    @After
    public void afterTest() {
        for (Cell c: Cell.values()) {
            c.figure = null;
        }
    }
}
