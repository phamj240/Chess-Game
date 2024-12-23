//Written by Johnathan Pham: pham0549
//Written by Andi Nguyen: nguy5131

public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;

    public Bishop(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            //checks if the move is valid and calls verifyDiagonal
           if (board.verifyDiagonal(this.row, this.col, endRow, endCol)){
               return true;
            }
        }
        return false;
    }
}
