package ChessPiece;

import MVC.ChessConstants;
import MVC.ChessModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class PawnTest {
    private Piece n1;
    private Piece n2;
    private Piece r1;
    private Piece r2;
    private Piece p1;
    private Piece p2;
    private Piece p3;
    private Piece p4;
    private Piece p5;
    private ChessModel chessModel1;
    private ChessModel chessModel2;

    @BeforeEach
    void setUp(){
        n1 = new Knight(5, 3, Player.WHITE, ChessConstants.wKnight);
        n2 = new Knight(4, 5, Player.BLACK, ChessConstants.bKnight);
        r1 = new Rook(1,5, Player.WHITE, ChessConstants.wRook);
        r2 = new Rook(5,5, Player.BLACK, ChessConstants.bRook);

        p1 = new Pawn(5,1, Player.WHITE, ChessConstants.wPawn);
        p2 = new Pawn(0,6, Player.BLACK, ChessConstants.bPawn);

        p3 = new Pawn(6,2, Player.WHITE, ChessConstants.wPawn);
        p4 = new Pawn(6,4, Player.BLACK, ChessConstants.bPawn);

        p5 = new Pawn(2,6, Player.BLACK, ChessConstants.bPawn);
        Piece[] piecesArray = new Piece[] {n1, n2, r1, r2, p1, p2, p3, p4, p5};

        HashSet<Piece> piecesBox = new HashSet<Piece>(Arrays.asList(piecesArray));

        chessModel1 = new ChessModel(piecesBox);
        chessModel2 = new ChessModel();
    }

    @Test
    void setupPrint(){
        chessModel1.printPiecesBox();
        System.out.println(chessModel1);
    }

    @Test
    void firstMoveTest(){
        System.out.println(((Pawn)p1).isFirstMove());
        System.out.println(((Pawn)p2).isFirstMove());
        System.out.println(((Pawn)p3).isFirstMove());
        System.out.println(((Pawn)p4).isFirstMove());
        System.out.println(((Pawn)p5).isFirstMove());
    }


    @Test
    void moveTestSinglePieces(){
        //TODO: write test for legal move. based on printed moves, print is correct here.
        System.out.println(chessModel1);

        p1.printLegalMoves(chessModel1);
        p2.printLegalMoves(chessModel1);
        p3.printLegalMoves(chessModel1);
        p4.printLegalMoves(chessModel1);
        p5.printLegalMoves(chessModel1);
    }

    @Test
    void moveTestFullBoard(){
        //TODO: write test for legal move. based on printed moves, print is correct here.
        System.out.println(chessModel2);

        List<Piece> piecesBoxClone = chessModel2.getActivePieces();
        for(Piece p:piecesBoxClone){
            if(p instanceof Pawn){
                System.out.println(p);
                p.printLegalMoves(chessModel2);
            }
        }
    }
}