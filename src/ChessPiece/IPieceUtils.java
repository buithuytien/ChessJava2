package ChessPiece;
import MVC.*;

//import com.goldthumb.chess.MVC.ChessModel;

import java.util.List;

public interface IPieceUtils {
    List<Coordinate> getLegalMoves(ChessModel chessModel);
}
