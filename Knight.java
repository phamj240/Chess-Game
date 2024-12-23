//Written by Johnathan Pham: pham0549
//Written by Andi Nguyen: nguy5131

public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {

            int rowDifference = Math.abs(this.row-endRow);
            int colDifference = Math.abs(this.col-endCol);
            //used to check the distance for an L-Shape

            if ((rowDifference ==1 && colDifference == 2) || (rowDifference == 2 && colDifference == 1)){
                //checks if the move creates an L-shape
                return true;
            }
        }
        return false;
    }
}
