package ChessPiece;

import MVC.ChessConstants;
import MVC.ChessModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    private Piece n1;
    private Piece n2;
    private Piece r1;
    private Piece r2;
    private Piece p1;
    private Piece p2;
    private ChessModel chessModel1;

    @BeforeEach
    void setUp(){
        n1 = new Knight(3, 3, Player.WHITE, ChessConstants.wKnight);
        n2 = new Knight(4, 5, Player.BLACK, ChessConstants.bKnight);
        r1 = new Rook(3,4, Player.WHITE, ChessConstants.wRook);
        r2 = new Rook(5,5, Player.BLACK, ChessConstants.bRook);
        p1 = new Pawn(5,2, Player.WHITE, ChessConstants.wPawn);
        p2 = new Pawn(6,4, Player.BLACK, ChessConstants.bPawn);
        Piece[] piecesArray = new Piece[] {n1,n2,r1,r2, p1, p2};

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
//        assertFalse();  assert false for n1.getLegalMoves contain Coordinate(5,2)
//        assertFalse();  assert false for n2.getLegalMoves contain Coordinate(6,4)

        n1.printLegalMoves(chessModel1);
        n2.printLegalMoves(chessModel1);
    }


}