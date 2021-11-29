package MVC;

import ChessPiece.*;
import ChessPiece.Piece;

public interface IChessControllerUtils {
    Piece pieceAt(int col, int row);
    void movePiece(int fromCol, int fromRow, int toCol, int toRow);
    boolean isGameOver();
}
