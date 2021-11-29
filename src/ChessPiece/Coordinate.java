package ChessPiece;
import MVC.*;

public class Coordinate {
    private int iRow;
    private int iCol;

    public Coordinate(int _iCol, int _iRow) {
        this.iRow = _iRow;
        this.iCol = _iCol;
    }

    public int getRow(){
        return this.iRow;
    }

    public void setRow(int _iRow){
        this.iRow = _iRow;
    }

    public int getCol(){
        return this.iCol;
    }
    public void setCol(int _Col){
        this.iCol = _Col;
    }

    @Override
    public boolean equals(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Coordinate)) {
            return false; //other cannot be equal to this as it is not a Person object!
        }
        Coordinate otherCoord = (Coordinate) other; //this will work

        return  this.iCol == otherCoord.getCol() &&
                this.iRow == otherCoord.getRow();
    }

    @Override
    public Coordinate clone(){
        return new Coordinate(this.getCol(), this.getRow());
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "iCol=" + iCol +
                ", iRow=" + iRow +
                '}';
    }
}
