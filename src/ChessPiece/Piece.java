package ChessPiece;
import MVC.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements IPieceUtils{
    int row, col;
    Player player;
    Rank rank;
    String imgPath;
//    List<Coordinate> legalMoves;

    public Piece (int col, int row, Player player, Rank rank, String imgPath) { //
        this.row = row;
        this.col = col;
        this.player = player;
        this.rank = rank;
        this.imgPath = imgPath;
//        legalMoves = new ArrayList<Coordinate>();
    }

    public Piece(int col, int row, Player player, Rank rank) {
        this.row = row;
        this.col = col;
        this.player = player;
        this.rank = rank;
        this.imgPath = this.retrieveImgPath();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Rank getRank() {
        return rank;
    }

    public String getImgPath() {
        return imgPath;
    }

    @Override
    public String toString() {
        return "{" +
                "row=" + row +
                ", col=" + col +
                ", player=" + player +
                ", rank=" + rank +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public abstract Piece clone();

//    public abstract List<Coordinate> getLegalMoves(MVC.ChessModel chessModel);

//    public void cloneLegalMove(){
//        List<Coordinate> legalMovesClone = new ArrayList<Coordinate>();
//        for(Coordinate coord : legalMoves){
//            legalMovesClone.add(coord.clone());
//        }
//    }

    public void printLegalMoves(ChessModel chessModel){
        List<Coordinate> legalMoves = this.getLegalMoves(chessModel);
        System.out.println("-----");
        if (legalMoves == null){
            System.out.print("null");
            return;
        }

        for(Coordinate coord : legalMoves){
            System.out.print(coord + "\n");
        }
        System.out.println("-----");
    }

    public abstract String retrieveImgPath();

}
