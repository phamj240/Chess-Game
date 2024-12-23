//Written by Johnathan Pham: pham0549
//Written by Andi Nguyen: nguy5131

public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            //checks if the move is remotely valid and calls verifyAdjacent
            if (board.verifyAdjacent(this.row, this.col, endRow, endCol)){
                return true;
            }
        }
        return false;
    }
}
