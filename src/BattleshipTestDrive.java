/**
 * Created by Scott on 2/23/16.
 */

import java.util.Scanner;

public class BattleshipTestDrive {

    static String userGuess;
    static int[] guessArray = new int[2];
    static Board gameBoard = new Board();
    static GameLogic game = new GameLogic();
    static int boardRows = game.rows;
    static int boardColumns = game.columns;
    static String[][] board = new String[boardRows][boardColumns];
    static boolean newGuess;
    static int numOfShips = 3;
    static int[][] ship1 = new int[3][3];
    static int[][] ship2 = new int[3][3];
    static int[][] ship3 = new int[3][3];
    static int hitCounter = 0;
    static int numOfGuesses = 0;
    static boolean canPlaceShip = true;
    static int shipsPlaced = 0;

    public static void main(String[] args) {
        setUpGame();
        playGame();
        endGame();
    }

    //set up the game
    public static void setUpGame() {
        fillBoard();
        //placing the first ship on the board
        game.setShipAlignment();
        game.getRandomLocation();
        game.createShipArray(ship1);
        //placing the second ship on the board
        while(shipsPlaced < 2) {
            canPlaceShip = true;
            game.setShipAlignment();
            game.getRandomLocation();
            game.checkforOpenSpotsOnBoard(ship1, canPlaceShip);
            if(canPlaceShip == true) {
                game.createShipArray(ship2);
            }
        }
        //placing the third ship on the board
        while(shipsPlaced < 3) {
            canPlaceShip = true;
            game.setShipAlignment();
            game.getRandomLocation();
            game.checkforOpenSpotsOnBoard(ship1, canPlaceShip);
            if(canPlaceShip == true) {
                game.checkforOpenSpotsOnBoard(ship2, canPlaceShip);
            }
            if(canPlaceShip == true) {
                game.createShipArray(ship3);
            }
        }
    }

    public static void playGame() {
        while(hitCounter < ship1.length * numOfShips) {
            gameBoard.printBoard(board);
            getUserGuess();
            convertGuessToIntArray();
            compareGuessToBoard();
            if(newGuess = true) {
                game.checkShipArrayForHit(guessArray, ship1, hitCounter);
                game.checkShipArrayForHit(guessArray, ship2, hitCounter);
                game.checkShipArrayForHit(guessArray, ship3, hitCounter);
                if(game.match == false){
                    System.out.println("Miss!");
                }
                markBoard();
            }
        }
    }

    public static void endGame() {
        System.out.println("You Sank all the Ships!!! You won the battle, BUT AT WHAT COST???");
        System.out.println("You took " + numOfGuesses + " guesses and hundreds of people died. Play again to try for a lower score, you monster.");
    }

    public void setCanPlaceShip(boolean canPlaceShip) {
        this.canPlaceShip = canPlaceShip;
    }

    public void increaseHitCounter(int hitCounter) {
        this.hitCounter = hitCounter++;
    }

    //fill board array with the letter "O" in each cell
    public static void fillBoard() {
        // Loops through rows of board array
        for(int i = 0; i < board.length; i++) {
            // Loops through the values in each row and prints them
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = "O";
            }
        }
    }

    //take users guess
    public static void getUserGuess() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter missile coordinates: ");
        userGuess = userInput.next();
        numOfGuesses++;
    }

    //convert to numbers for array cells
    public static void convertGuessToIntArray(){
        char letterGuess = userGuess.charAt(0);
        char letterGuessUpper = Character.toUpperCase(letterGuess);
        int letter = letterGuessUpper - 'A';
        int numberGuess = Character.getNumericValue(userGuess.charAt(1) - 1);
        guessArray[0] = letter;
        guessArray[1] = numberGuess;
    }

    //search board to see if that space was already guessed
    public static void compareGuessToBoard() {
        if(board[guessArray[0]][guessArray[1]].equals("O")) {
            newGuess = true;
        } else {
            newGuess = false;
            System.out.println("These coordinates have already been entered. Try again.");
        }
    }

    //mark board for new guess
    public static void markBoard() {
        if(newGuess == true) {
            if(game.match == true) {
                board[guessArray[0]][guessArray[1]] = "H";
            } else {
                board[guessArray[0]][guessArray[1]] = "M";
            }
        }
        game.setMatch(false);
    }

}
