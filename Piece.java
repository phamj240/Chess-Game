//Written by Johnathan Pham: pham0549
//Written by Andi Nguyen: nguy5131

import java.util.Scanner;

public class Piece {
    public char character;
    public int row;
    public int col;
    public boolean isBlack;


    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;
    }

    /**
     * Handle promotion of a pawn.
     * @param row Current row of the pawn
     * @param isBlack Color of the pawn
     */
    public void promotePawn(int row, boolean isBlack) {
        if (isBlack && row == 7) {
            //checks for the color and if the pawn has reached the end of the board
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose a piece you want your pawn to promote to (queen, rook, knight, bishop)");
            String promotedPiece = scanner.nextLine();

            //changes the pawn to the desired piece
            if (promotedPiece.equals("queen")) {
                character = '\u265B';
            } else if (promotedPiece.equals("rook")) {
                character = '\u265C';
            } else if (promotedPiece.equals("knight")) {
                character = '\u265E';
            } else if (promotedPiece.equals("bishop")) {
                character = '\u265D';
            }
        }
        if (isBlack == false && row == 0) {
            //checks for the color and if the pawn has reached the end of the board
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose a piece you want your pawn to promote to (queen, rook, knight, bishop)");
            String promotedPiece = scanner.nextLine();

            //changes the pawn to the desired piece
            if (promotedPiece.equals("queen")) {
                character = '\u2655';
            } else if (promotedPiece.equals("rook")) {
                character = '\u2656';
            } else if (promotedPiece.equals("knight")) {
                character = '\u2658';
            } else if (promotedPiece.equals("bishop")) {
                character = '\u2657';
            }
        }
    }


    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return String.valueOf(this.character);
    }


}
