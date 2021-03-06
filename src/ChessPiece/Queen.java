package ChessPiece;
import MVC.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private final static int[] DC = new int[] {+1, +1, -1, -1, +1, -1,  0, 0};
    private final static int[] DR = new int[] {+1, -1, +1, -1,  0,  0, +1, -1};


    public Queen(int col, int row, Player player, String imgPath) {
        super(col, row, player, Rank.QUEEN, imgPath);
    }

    public Queen(int col, int row, Player player) {
        super(col, row, player, Rank.QUEEN);
    }

    @Override
    public Piece clone() {
        return new Queen(this.getCol(), this.getRow(), this.getPlayer(), this.getImgPath());
    }

    @Override
    public List<Coordinate> getLegalMoves(MVC.ChessModel chessModel){
        List<Coordinate> legalMoves = new ArrayList<Coordinate>();

        for(int i = 0; i < DC.length; i++){
            int toRow = this.row;
            int toCol = this.col;

            while (CoordUtils.checkValidCoordinate(toCol, toRow)){
                toCol += DC[i];
                toRow += DR[i];

                if(CoordUtils.checkValidCoordinate(toCol, toRow)){
                    Piece targetPiece = chessModel.pieceAt(toCol, toRow);

                    if(targetPiece == null){ // if toCol, toRow is empty
                        legalMoves.add(new Coordinate(toCol, toRow));
                    } else { // if there is a piece on the way, cannot move forward
                        // if target piece is ally, cannot go. break
                        // if target piece is enemy, can go. break
                        if(targetPiece.getPlayer() != this.player){
                            legalMoves.add(new Coordinate(toCol, toRow)); // attack move
                        }
                        break;
                    }
                }
            }

        }
        return legalMoves;
    }

    public String retrieveImgPath(){
        if(this.player == Player.WHITE){
            return ChessConstants.wQueen;
        } else if(this.player == Player.BLACK){
            return ChessConstants.bQueen;
        }
        return null;
    }


}
