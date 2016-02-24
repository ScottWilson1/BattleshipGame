/**
 * Created by Scott on 2/23/16.
 */
public class Board {

    private int rows = 7;
    private int columns = 7;
    private String[] rowLetters = new String[] {"A", "B", "C", "D", "E", "F", "G"};

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    void printBoard(String[][] board) {
        // print out top row of Column number labels
        System.out.println("\r");
        System.out.print("    ");
        for(int i = 0; i < 7; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println("\r");
        System.out.println("-----------------" + "\r");
        // Loops through rows of board array
        for(int i = 0; i < board.length; i++) {
            // Loops through the values in each row and prints them
            System.out.print(rowLetters[i] + " | ");
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("\r");
        }
    }
}
