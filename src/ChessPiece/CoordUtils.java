package ChessPiece;
import MVC.*;

public class CoordUtils {
    public static boolean checkValidCoordinate(Coordinate coord){
        int col = coord.getCol();
        int row = coord.getRow();
        return checkValidCoordinate(col, row);

    }

    public static boolean checkValidCoordinate(int col, int row){
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;

    }
}
