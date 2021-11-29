package ChessPiece;

import MVC.*;
import ChessPiece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    private Piece q1;
    private Piece q2;
    private Piece r1;
    private Piece r2;
    private Piece p1;
    private Piece p2;
    private ChessModel chessModel1;
    private ChessModel chessModel2;

    @BeforeEach
    void setUp(){
        q1 = new Queen(3, 5, Player.WHITE, ChessConstants.wQueen);
        q2 = new Queen(5, 3, Player.BLACK, ChessConstants.bQueen);
        r1 = new Rook(3,2, Player.WHITE, ChessConstants.wRook);
        r2 = new Rook(5,5, Player.BLACK, ChessConstants.bRook);
        p1 = new Pawn(6,2, Player.WHITE, ChessConstants.wPawn);
        p2 = new Pawn(1,5, Player.BLACK, ChessConstants.bPawn);
        Piece[] piecesArray = new Piece[] {q1,q2,r1,r2,p1,p2};

        HashSet<Piece> piecesBox = new HashSet<Piece>(Arrays.asList(piecesArray));

        chessModel1 = new ChessModel(piecesBox);

    }

    @Test
    void setupPrint(){
        chessModel1.printPiecesBox();
        System.out.println(chessModel1);
    }

    @Test
    void moveTest(){
        //TODO: write test for legal move. based on printed moves, print is correct here.
        System.out.println(chessModel1);
        q1.printLegalMoves(chessModel1);
        q2.printLegalMoves(chessModel1);
    }

}