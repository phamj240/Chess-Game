//Written by Johnathan Pham: pham0549
//Written by Andi Nguyen: nguy5131

public class Queen {
    private int row;
    private int col;
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            if (this.col == endCol) {
                //checks if the move is vertical and calls verifyVertical

                if(board.verifyVertical(this.row, this.col, endRow, endCol)){
                    return true;
                }
            }
            if (this.row == endRow){
                //checks if the move is horizontal and calls verifyVertical

                if(board.verifyHorizontal(this.row, this.col, endRow, endCol)){
                    return true;
                }
            }
            if (board.verifyDiagonal(this.row, this.col, endRow, endCol)){
                //calls verifyDiagonal if the end position is on neither the same row or column
                return true;
            }
        }
        return false;
    }
}
