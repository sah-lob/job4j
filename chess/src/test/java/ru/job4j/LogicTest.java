package ru.job4j;

import org.junit.After;
import org.junit.Test;
import ru.job4j.chess.Chess;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {


    @Test
    public void whenWhiteFigureGoThanBlack() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.add(new King(Cell.F1, true));
        logic.add(new King(Cell.H1, false));
        logic.add(new Queen(Cell.C7,  true));
        logic.add(new Rook(Cell.A7, false));

        logic.setWhiteFigureMove(true);
        logic.move(Cell.C7, Cell.C2, chess.getRectangles());
        logic.move(Cell.A7, Cell.A2, chess.getRectangles());

        assertThat(true, is(true));
    }


    @Test
    public void whenWhiteFigureGoThanWhite() {
        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.add(new King(Cell.H8, false));
        logic.add(new Rook(Cell.A7, true));
        logic.add(new Rook(Cell.C7, true));

        boolean flag = logic.move(Cell.A7, Cell.A2, chess.getRectangles());
        if (flag) {
            flag = logic.move(Cell.C7, Cell.C2, chess.getRectangles());
        }
        assertThat(flag, is(false));
    }

    @Test
    public void whenWhiteFigureB7ChecksBlackKingB1NotMate() {

        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.add(new Queen(Cell.A7, true));
        logic.add(new King(Cell.B1, false));

        logic.setWhiteFigureMove(true);

        boolean undo = Chess.check;
        logic.move(Cell.A7, Cell.B7, chess.getRectangles());
        boolean result = (Chess.check && !Chess.checkmate);
        assertThat(result, is(undo));
    }

    @Test
    public void whenWhiteFigureB2ChecksBlackKingA1NotMate() {

        Chess chess = new Chess();
        Logic logic = new Logic();

        logic.add(new Queen(Cell.B7, true));
        logic.add(new King(Cell.A1, false));

        boolean undo = Chess.check;
        logic.move(Cell.B7, Cell.B2, chess.getRectangles());
        boolean result = (Chess.check && !Chess.checkmate);
        assertThat(result, is(!undo));

    }



    @Test
    public void whenMateAttackWithoutFreePlacesForKing() {

        Chess chess = new Chess();
        Logic logic = new Logic();

        buildWhiteTeam(logic);
        buildBlackTeam(logic);
        logic.setWhiteFigureMove(true);
        boolean undo = Chess.checkmate;
        logic.move(Cell.E7, Cell.E5, chess.getRectangles());
        logic.move(Cell.E2, Cell.E4, chess.getRectangles());
        logic.move(Cell.D8, Cell.F6, chess.getRectangles());
        logic.move(Cell.A2, Cell.A3, chess.getRectangles());
        logic.move(Cell.F8, Cell.C5, chess.getRectangles());
        logic.move(Cell.A3, Cell.A4, chess.getRectangles());
        logic.move(Cell.F6, Cell.F2, chess.getRectangles());
        boolean result = Chess.checkmate;
        assertThat(result, is(!undo));
    }


    @After
    public void afterTest() {
        Chess.check = false;
        Chess.checkmate = false;
    }


    public void buildWhiteTeam(Logic logic) {
        logic.add(new Pawn(Cell.A7, true));
        logic.add(new Pawn(Cell.B7, true));
        logic.add(new Pawn(Cell.C7, true));
        logic.add(new Pawn(Cell.D7, true));
        logic.add(new Pawn(Cell.E7, true));
        logic.add(new Pawn(Cell.F7, true));
        logic.add(new Pawn(Cell.G7, true));
        logic.add(new Pawn(Cell.H7, true));
        logic.add(new Rook(Cell.A8, true));
        logic.add(new Knight(Cell.B8, true));
        logic.add(new Bishop(Cell.C8, true));
        logic.add(new Queen(Cell.D8, true));
        logic.add(new King(Cell.E8, true));
        logic.add(new Bishop(Cell.F8, true));
        logic.add(new Knight(Cell.G8, true));
        logic.add(new Rook(Cell.H8, true));
    }

    public void buildBlackTeam(Logic logic) {
        logic.add(new Pawn(Cell.A2, false));
        logic.add(new Pawn(Cell.B2, false));
        logic.add(new Pawn(Cell.C2, false));
        logic.add(new Pawn(Cell.D2, false));
        logic.add(new Pawn(Cell.E2, false));
        logic.add(new Pawn(Cell.F2, false));
        logic.add(new Pawn(Cell.G2, false));
        logic.add(new Pawn(Cell.H2, false));
        logic.add(new Rook(Cell.A1, false));
        logic.add(new Knight(Cell.B1, false));
        logic.add(new Bishop(Cell.C1, false));
        logic.add(new Queen(Cell.D1, false));
        logic.add(new King(Cell.E1, false));
        logic.add(new Bishop(Cell.F1, false));
        logic.add(new Knight(Cell.G1, false));
        logic.add(new Rook(Cell.H1, false));
    }
}
