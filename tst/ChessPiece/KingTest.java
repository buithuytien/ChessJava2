package ChessPiece;

import MVC.ChessConstants;
import MVC.ChessModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    private Piece k1;
    private Piece k2;
    private Piece r1;
    private Piece r2;
    private ChessModel chessModel1;

    @BeforeEach
    void setUp(){
        k1 = new King(3, 5, Player.WHITE); // , ChessConstants.wKing
        k2 = new King(4, 5, Player.BLACK); // , ChessConstants.bKing
        r1 = new Rook(3,4, Player.WHITE); // , ChessConstants.wRook
        r2 = new Rook(5,5, Player.BLACK); // , ChessConstants.bRook
        Piece[] piecesArray = new Piece[] {k1,k2,r1,r2};

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
        k1.printLegalMoves(chessModel1);
        k2.printLegalMoves(chessModel1);
    }
}