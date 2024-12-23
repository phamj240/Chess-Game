//Written by Johnathan Pham: pham0549
//Written by Andi Nguyen: nguy5131

import java.util.Scanner;
public class Game {
    public static void main(String[] args){
        Board myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);

        boolean whiteTurn = true;
        while (myBoard.isGameOver()) {
            //keeps looping until a king is captured
            System.out.println(myBoard.toString());
            //prints the state of the board after every move

            if (whiteTurn == true) {
                //White's turn
                System.out.println("It is currently white's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");
                Scanner scanner = new Scanner(System.in);
                int startRow = scanner.nextInt();
                int startCol = scanner.nextInt();
                int endRow = scanner.nextInt();
                int endCol = scanner.nextInt();

                boolean isPawn = false;

                if (myBoard.verifySourceAndDestination(startRow, startCol, endRow, endCol, false)) {
                    //makes sure that the move is valid
                    if (myBoard.getPiece(startRow, startCol) != null && myBoard.getPiece(startRow, startCol).character == '\u2659') {
                        //checks if the piece being moved is pawn (used for pawn promotion to make sure it is only pawns that get promoted)
                        isPawn = true;
                    }

                    if (myBoard.movePiece(startRow, startCol, endRow, endCol)) {
                        if (isPawn) {
                            //calls promotePawn and gives the option to promote if a pawn reaches the end of the board
                            myBoard.getPiece(endRow, endCol).promotePawn(endRow, false);
                        }

                        //If moving the piece was successful, change whiteTurn to false (allows black to move next)
                        whiteTurn = false;
                    } else {
                        System.out.println("Invalid move");
                    }
                } else {
                    System.out.println("Invalid move");
                }
            } else if (whiteTurn == false) {
                //Black's turn
                System.out.println("It is currently black's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");
                Scanner scanner = new Scanner(System.in);
                int startRow = scanner.nextInt();
                int startCol = scanner.nextInt();
                int endRow = scanner.nextInt();
                int endCol = scanner.nextInt();

                boolean isPawn = false;
                if (myBoard.verifySourceAndDestination(startRow, startCol, endRow, endCol, true)) {
                    //makes sure that the move is valid
                    if (myBoard.getPiece(startRow, startCol) != null && myBoard.getPiece(startRow, startCol).character == '\u265F') {
                        //checks if the piece being moved is pawn (used for pawn promotion to make sure it is only pawns that get promoted)
                        isPawn = true;
                    }

                    if (myBoard.movePiece(startRow, startCol, endRow, endCol)) {
                        if (isPawn) {
                            //calls promotePawn and gives the option to promote if a pawn reaches the end of the board
                            myBoard.getPiece(endRow, endCol).promotePawn(endRow, true);
                        }

                        //If moving the piece was successful, change whiteTurn to true (allows white to move next)
                        whiteTurn = true;
                    } else {
                        System.out.println("Invalid move");
                    }
                } else {
                    System.out.println("Invalid move");
                }
            }
        }

        if (whiteTurn == false) {
            //If it is black's turn next, but the while loop breaks,
            // that means that the black king has been captured. White wins
            System.out.println("White has won the game!");
        }

        if (whiteTurn == true) {
            //If it is white's turn next, but the while loop breaks,
            // that means that the white king has been captured. Black wins
            System.out.println("Black has won the game!");
        }
    }
}
