//Written by Johnathan Pham: pham0549
//Written by Andi Nguyen: nguy5131

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8]; //initializes the board
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. A constraint of a legal move is:
    // - there exists a Piece at (startRow, startCol) and the destination square is seizable.
    // Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal,
    // and to execute the move if it is.
    // Your Game class should not directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {

        //Creates an instance of the "Piece" class and checks if the starting position is valid as well as the ending position
        Piece selectedPiece = getPiece(startRow, startCol);
        if (selectedPiece != null && selectedPiece.isMoveLegal(this, endRow, endCol)){

            //changes the location of the piece and changes the original location of the piece to null because the piece has moved
            setPiece(endRow, endCol, selectedPiece);
            setPiece(startRow, startCol, null);
            selectedPiece.setPosition(endRow, endCol);
            return true;
        }
        return false;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        boolean whiteKing = false;
        boolean blackKing = false;

        //checks every square on the board and returns true if both kings are found
        for (int row = 0; row<8; row++){
            for (int col = 0; col<8; col++) {
                Piece piece = getPiece(row, col);
                if (piece != null) {
                    if (piece.character == '\u2654') {
                        //checks for the white king
                        whiteKing = true;
                    }
                    if (piece.character == '\u265A') {
                        //checks for the black king
                        blackKing = true;
                    }
                }
            }
        }
        if (blackKing && whiteKing){
            return true;
        }
        else{return false;}
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        //sets every cell to null
        for (int row = 0; row<board.length; row++){
            for (int col = 0; col <board[0].length; col++){
                board[row][col] = null;
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    // - where 'start' = (startRow, startCol) and 'end' = (endRow, endCol)
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 8 && startRow >=0 && startCol < 8 && startCol >=0 && endRow < 8 && endRow >=0 && endCol < 8 && endCol >=0){
            //1st 'if': checks if the entered start and end positions are within the board
            if (board[startRow][startCol] != null){
                //2nd 'if': checks if the start position is a piece
                Piece startPiece = board[startRow][startCol];
                if (startPiece.getIsBlack() == isBlack){
                    //3rd 'if': checks if player's color and color of 'start' Piece match
                    Piece endPiece = board[endRow][endCol];
                    if (endPiece == null || endPiece.getIsBlack() != isBlack){
                        //4th 'if': checks if end position contains no piece or a piece of the opposite color
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDifference = Math.abs(startRow-endRow);
        int colDifference = Math.abs(startCol-endCol);
        //Calculates the difference between the start position and end position

        if ((rowDifference == 1 && colDifference == 0) || (rowDifference == 0 && colDifference == 1) || (rowDifference == 1 && colDifference == 1) || (rowDifference ==0 && colDifference==0)){
            //Checks if the difference between the start and end are equal to 1. If true, they are adjacent
            return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow){
            //returns false if the end position is on another row
            return false;
        }

        if (startCol < endCol) {
            for (int i = startCol+1; i < endCol; i++) {
                //checks to the right of the start position
                if (board[startRow][i] != null) {
                    return false;
                }
            }
        }

        else {
            for (int i = startCol-1; i > endCol; i--) {
                //checks to the left of the start position
                if (board[startRow][i] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol){
            //returns false if the end position is on another column
            return false;
        }
        if (startRow<endRow) {
            for (int i = startRow+1; i < endRow; i++) {
                //checks below the start position
                if (board[i][startCol] != null) {
                    return false;
                }
            }
        }

        else {
            for (int i = startRow-1; i > endRow; i--) {
                //checks above the start position
                if (board[i][startCol] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDirection = 0;
        int colDirection = 0;
        //the absolute value of (start row - end row) should always equal (startCol - endCol)
        int rowDifference = (startRow-endRow);
        int colDifference = (startCol-endCol);

        if (Math.abs(rowDifference) != Math.abs(colDifference)) {
            //determines if the move is a valid diagonal move
            return false;
        }

        //if-statements below determine which diagonal to check based off of the difference of the rows and columns
        if (rowDifference < 0) {
            rowDirection = 1;
        }
        else if (rowDifference > 0) {
            rowDirection = -1;
        }

        if (colDifference < 0){
            colDirection = 1;
        }
        else if (colDifference > 0) {
            colDirection = -1;
        }


        for (int i = 1; i<Math.abs(colDifference); i++){
            //iterate through each square on the corresponding diagonal path
            int currRow = startRow + i*rowDirection;
            int currCol = startCol + i*colDirection;

            //checks if the square is empty
            if (board[currRow][currCol] != null){
                return false;
            }
        }
        return true;
    }
}
