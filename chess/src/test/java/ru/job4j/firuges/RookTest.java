package ru.job4j.firuges;

import org.junit.After;
import org.junit.Test;
import ru.job4j.chess.Chess;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.King;
import ru.job4j.chess.firuges.Pawn;
import ru.job4j.chess.firuges.Rook;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RookTest {

        Chess chess = new Chess();
        Logic logic = new Logic();


        @Test
        public void rookMoveFromA7ToA2() {
            logic.setWhiteFigureMove(true);
            logic.add(new King(Cell.F1, false));
            logic.add(new Rook(Cell.A7, true));
            boolean fl = logic.move(Cell.A7, Cell.A2, chess.getRectangles());
            assertThat(fl, is(true));
        }

        @Test
        public void rookMoveFromB7ToF7() {
            logic.setWhiteFigureMove(true);
            logic.add(new King(Cell.F1, false));
            logic.add(new Rook(Cell.B7, true));
            boolean fl = logic.move(Cell.B7, Cell.F7, chess.getRectangles());
            assertThat(fl, is(true));
        }

        @Test
        public void rookMoveFromA7ToA8() {
            logic.setWhiteFigureMove(true);
            logic.add(new King(Cell.F1, false));
            logic.add(new Rook(Cell.A7, true));
            boolean fl = logic.move(Cell.A7, Cell.A8, chess.getRectangles());
            assertThat(fl, is(true));
        }

        @Test
        public void rookWrongMoveFromB7ToC8() {
            logic.setWhiteFigureMove(true);
            logic.add(new King(Cell.F1, false));
            logic.add(new Rook(Cell.B7, true));
            boolean fl = logic.move(Cell.B7, Cell.C8, chess.getRectangles());
            assertThat(fl, is(false));
        }


        @Test
        public void rookWrongMoveFromB7ToB5BlackPawnB6() {
            logic.setWhiteFigureMove(true);
            logic.add(new King(Cell.F1, false));
            logic.add(new Rook(Cell.B7, true));
            logic.add(new Pawn(Cell.B6, false));
            boolean fl = logic.move(Cell.B7, Cell.B5, chess.getRectangles());
            assertThat(fl, is(false));
        }


        @Test
        public void rookEatFromD7ToD5() {
            logic.setWhiteFigureMove(true);
            logic.add(new King(Cell.F1, false));
            logic.add(new Rook(Cell.D7, true));
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
