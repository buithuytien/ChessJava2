import ChessPiece.*;
import MVC.ChessConstants;
import MVC.ChessModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ChessModelTest {
    private ChessModel chessModel;

    @BeforeEach
    void setUp() {
        chessModel = new ChessModel();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNovePiece(){
//        chessModel.reset();
//        System.out.println(chessModel);
        int fromRow = 1;
        int fromCol = 4;

        int toRow = 3;
        int toCol = 4;

        Piece mp = chessModel.pieceAt(fromCol, fromRow);
        assertEquals(Rank.PAWN, mp.getRank());

        chessModel.movePiece(fromCol, fromRow, toCol, toRow);
        System.out.println(chessModel);

        assertNull(chessModel.pieceAt(fromCol, fromRow));

        System.out.println(chessModel.pieceAt(fromCol, fromRow));
        assertEquals(Rank.PAWN, chessModel.pieceAt(toCol, toRow).getRank());

    }


    @Test
    void testPieceAt(){
        assertNull(chessModel.pieceAt(5,5));
        Piece p = chessModel.pieceAt(4,1);
        assertEquals(Player.WHITE, p.getPlayer());
        assertEquals(Rank.PAWN, p.getRank());
    }

    @Test
    void testToString() {
//        assertE
        System.out.println(chessModel.toString());

    }

    @Test
    void testPawnPromotion(){
        ChessModel chessModel1;
        Piece n1 = new Knight(5, 3, Player.WHITE, ChessConstants.wKnight);
        Piece n2 = new Knight(4, 5, Player.BLACK, ChessConstants.bKnight);
        Piece r1 = new Rook(1,5, Player.WHITE, ChessConstants.wRook);
        Piece r2 = new Rook(5,5, Player.BLACK, ChessConstants.bRook);

        Piece p1 = new Pawn(5,1, Player.WHITE, ChessConstants.wPawn);
        Piece p2 = new Pawn(0,6, Player.BLACK, ChessConstants.bPawn);

        Piece p3 = new Pawn(2,6, Player.WHITE, ChessConstants.wPawn); // promote
        Piece p4 = new Pawn(6,1, Player.BLACK, ChessConstants.bPawn); // promote

        Piece p5 = new Pawn(4,6, Player.BLACK, ChessConstants.bPawn);
        Piece[] piecesArray = new Piece[] {n1, n2, r1, r2, p1, p2, p3, p4, p5};

        HashSet<Piece> activePieces = new HashSet<Piece>(Arrays.asList(piecesArray));

        chessModel1 = new ChessModel(activePieces);
        System.out.println(chessModel1);
        // check for pawn promotion. white turn first

        chessModel1.movePiece(2, 6, 2, 7);
        chessModel1.movePiece(6, 1, 6, 0);
        System.out.println(chessModel1); // two pawn sucessfully promoted to queens


    }
}