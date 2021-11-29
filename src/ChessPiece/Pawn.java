package ChessPiece;
import MVC.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    private final static int[] DC = new int[] { 0,  0, +1, -1 }; // first two are regular move, last 2 are attack
    private final static int[] DR = new int[] {+1, +2, +1, +1 }; // first two are regular move, last 2 are attack

    public Pawn(int col, int row, Player player, String imgPath) {
        super(col, row, player, Rank.PAWN, imgPath);
    }

    public Pawn(int col, int row, Player player) {
        super(col, row, player, Rank.PAWN);
    }


    @Override
    public Piece clone() {
        return new Pawn(this.getCol(), this.getRow(), this.getPlayer(), this.getImgPath());
    }

    @Override
    public List<Coordinate> getLegalMoves(ChessModel chessModel) {
        List<Coordinate> legalMoves = new ArrayList<Coordinate>();
        int toRow;
        int toCol;

        for(int i = 0; i < DC.length; i++){
            toCol = this.col + DC[i]*this.player.getDirection();
            toRow = this.row + DR[i]*this.player.getDirection();

            if(CoordUtils.checkValidCoordinate(toCol, toRow)){ // if toRow, toCol is valid
                Piece pieceAtDest = chessModel.pieceAt(toCol, toRow);

                // normal move. destination must not be blocked
                if(DC[i] == 0 && DR[i] == 1 ) {
                    if(pieceAtDest == null){
                        legalMoves.add(new Coordinate(toCol, toRow));
                    }

                    // TODO: pawn promotion
                }
                // first move, pawn jump
                else if (DC[i] == 0 && DR[i] == 2 && isFirstMove() ){
                    int ColBeforeDest = this.col + 0*this.player.getDirection();
                    int RowBeforeDest = this.row + 1*this.player.getDirection();
                    Piece pieceBeforeDest = chessModel.pieceAt(ColBeforeDest, RowBeforeDest);
                    // only jump if there is no piece blocking at 1 step before destination

                    if(pieceBeforeDest == null && pieceAtDest == null ){
                        legalMoves.add(new Coordinate(toCol, toRow));
                    }
                }
                // attack move. go diagonally, there must be another enemy piece there
                else if ( (DC[i] == 1 || DC[i] == -1) &&
                           pieceAtDest != null &&
                           this.player != pieceAtDest.getPlayer() ){
                    // TODO: en passant
                    legalMoves.add(new Coordinate(toCol, toRow));
                }
            }
        }

        return legalMoves;
    }

    public boolean isFirstMove(){
        return (this.player == Player.WHITE && this.row == 1) ||
                (this.player == Player.BLACK && this.row == 6);
    }

    public String retrieveImgPath(){
        if(this.player == Player.WHITE){
            return ChessConstants.wPawn;
        } else if(this.player == Player.BLACK){
            return ChessConstants.bPawn;
        }
        return null;
    }
}
