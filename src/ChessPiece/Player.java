package ChessPiece;

public enum Player{
    WHITE{
        public int getDirection() {
            return +1;
        }

        public String toString() {
            return "WHITE";
        }
    }
    ,
    BLACK {
        public int getDirection() {
            return -1;
        }

        public String toString() {
            return "BLACK";
        }
    };

    public abstract int getDirection() ;

    public abstract String toString() ;
}
