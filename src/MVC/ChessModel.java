package MVC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import ChessPiece.*;

public class ChessModel {

    private HashSet<Piece> activePieces;
    private Player playerInTurn;
    private Iterator it; // = piecesBox.iterator();

    public ChessModel(){
        reset();
        it = activePieces.iterator();
    }

    public ChessModel(HashSet<Piece> piecesBox){
        this.activePieces = piecesBox;
        this.playerInTurn = Player.WHITE;
        it = piecesBox.iterator();
    }


    public void reset(){
        activePieces = new HashSet<Piece>();
        for (int i = 0; i < 2; i++) {
            activePieces.add(new Rook(0 + i * 7, 7, Player.BLACK, ChessConstants.bRook));
            activePieces.add(new Rook(0 + i * 7, 0, Player.WHITE, ChessConstants.wRook));

            activePieces.add(new Knight(1 + i * 5, 7, Player.BLACK, ChessConstants.bKnight));
            activePieces.add(new Knight(1 + i * 5, 0, Player.WHITE, ChessConstants.wKnight));

            activePieces.add(new Bishop(2 + i * 3, 7, Player.BLACK, ChessConstants.bBishop));
            activePieces.add(new Bishop(2 + i * 3, 0, Player.WHITE, ChessConstants.wBishop));
        }

        for (int i = 0; i < 8; i++) {
            activePieces.add(new Pawn(i, 6, Player.BLACK, ChessConstants.bPawn));
            activePieces.add(new Pawn(i, 1, Player.WHITE, ChessConstants.wPawn));
        }

        activePieces.add(new Queen(3, 7, Player.BLACK, ChessConstants.bQueen));
        activePieces.add(new Queen(3, 0, Player.WHITE, ChessConstants.wQueen));
        activePieces.add(new King(4, 7, Player.BLACK, ChessConstants.bKing));
        activePieces.add(new King(4, 0, Player.WHITE, ChessConstants.wKing));

        playerInTurn = Player.WHITE;
//        for(ChessPiece.ChessPiece p:piecesBox) System.out.println(p);
    }


    public void movePiece(int fromCol, int fromRow, int toCol, int toRow) throws IllegalStateException {
        if( isGameOver() ) throw new IllegalStateException("Game is over, cannot play");

        Piece movingPiece = pieceAt(fromCol, fromRow);
//        System.out.println("line43 movePiece model " + movingPiece);
        if (movingPiece == null || (fromCol == toCol && fromRow == toRow)) {
            return;
        }

        // if not in turn, player cannot move
        if(movingPiece.getPlayer() != this.playerInTurn){
            return;
        }

        // get possible moves of moving Piece
        // mutating the piecesBox only done here
        // only check if toCol, toRow is allowed (contained in legalMoves)
        List<Coordinate> legalMoves = movingPiece.getLegalMoves(this);
        Coordinate destCoord = new Coordinate(toCol, toRow);
        if (!legalMoves.contains(destCoord)){
            System.out.print("moving piece " + movingPiece.toString() );
            System.out.println("not legal move at col " + toCol + " row " + toRow);
            return;
        }

        Piece target = pieceAt(toCol, toRow);
        if (target != null) {
            if (target.getPlayer() == movingPiece.getPlayer()) {
                return;
            } else {
                // TODO: en passent
                activePieces.remove(target);
            }
        }
        movingPiece.setCol(toCol);
        movingPiece.setRow(toRow);
        /*
        piecesBox.remove(movingPiece);
        Piece movingPieceClone = movingPiece.clone();
        movingPieceClone.setCol(toCol);
        movingPieceClone.setCol(toRow);

        System.out.println(movingPieceClone);

        piecesBox.add(movingPieceClone);
        */
        // TODO: handle en passent move

        // check Queen promotion of a pawn
        if(movingPiece instanceof Pawn ){
            if(checkPawnPromotion(movingPiece, toRow) ){
                activePieces.remove(movingPiece);
                activePieces.add(new Queen(toCol, toRow,
                        movingPiece.getPlayer() ));
            }
        }




        playerInTurn = (playerInTurn == Player.WHITE ? Player.BLACK : Player.WHITE);
    }

    public Piece pieceAt(int col, int row) {
        for (Piece chessPiece : activePieces) {

            if (chessPiece.getCol() == col && chessPiece.getRow() == row) {
                return chessPiece;
            }
        }
        return null;
    }

    public boolean isEmptySquare(int col, int row){
        return pieceAt(col, row) == null;
    }

    @Override
    public String toString() {
        String desc = "";

        for (int row = 7; row >= 0; row--) {
            desc += "" + row;
            for (int col = 0; col < 8; col++) {
                Piece p = pieceAt(col, row);
                if (p == null) {
                    desc += " .";
                } else {
                    desc += " ";
                    switch (p.getRank()) {
                        case KING:
                            desc += p.getPlayer() == Player.WHITE ? "k" : "K";
                            break;
                        case QUEEN:
                            desc += p.getPlayer() == Player.WHITE ? "q" : "Q";
                            break;
                        case BISHOP:
                            desc += p.getPlayer() == Player.WHITE ? "b" : "B";
                            break;
                        case ROOK:
                            desc += p.getPlayer() == Player.WHITE ? "r" : "R";
                            break;
                        case KNIGHT:
                            desc += p.getPlayer() == Player.WHITE ? "n" : "N";
                            break;
                        case PAWN:
                            desc += p.getPlayer() == Player.WHITE ? "p" : "P";
                            break;
                    }
                }
            }
            desc += "\n";
        }
        desc += "  0 1 2 3 4 5 6 7";

        return desc;
    }


    public void printPiecesBox(){
        if (!it.hasNext()) return;
        System.out.println(it.next());
        printPiecesBox();
    }

    public List<Piece> getActivePieces(){
        List<Piece> piecesBoxClone = new ArrayList<Piece>();
        for(Piece p: activePieces){
            piecesBoxClone.add((p.clone()));
        }
        return piecesBoxClone;
    }


    public Player getWinner() {
        boolean existWhiteKing = false;
        boolean existBlackKing = false;

        for(Piece p: activePieces){
            if((p instanceof King) && p.getPlayer() == Player.WHITE) existWhiteKing = true;
            if((p instanceof King) && p.getPlayer() == Player.BLACK) existBlackKing = true;
        }

        if (existWhiteKing & !existBlackKing ) return Player.WHITE;
        if (!existWhiteKing & existBlackKing ) return Player.BLACK;
        return null;
    }

    // Game over when a king is captured
    public boolean isGameOver() {
        return getWinner() != null;
    }

    private boolean checkPawnPromotion(Piece movingPiece, int toRow){
        if(! (movingPiece instanceof Pawn)) return false;
        if((movingPiece.getPlayer() == Player.WHITE && toRow == 7) ||
                (movingPiece.getPlayer() == Player.BLACK && toRow == 0) ) return true;
        return false;
    }
}
