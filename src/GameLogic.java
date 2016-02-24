/**
 * Created by Scott on 2/23/16.
 */

public class GameLogic {

    public int rows = 7;
    public int columns = 7;
    private int shipSections = 3;
    private int alignment;
    private int[] startingLocation = new int[2];
    public boolean match = false;
    BattleshipTestDrive battle = new BattleshipTestDrive();


    public int getShipAlignment() {
        return alignment;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }

    // randomly set ship as vertical or horizontal: 0 == horizontal 1 == vertical
    public int setShipAlignment() {
        alignment = (int) (Math.random() * 2);
        return alignment;

    }

    //	find random row and column to place ship on board
    public int[] getRandomLocation() {
        if(alignment == 0) {
            int rowLocation = (int) (Math.random() * rows);
            int columnLocation = (int) (Math.random() * (columns - 2));
            startingLocation[0] = rowLocation;
            startingLocation[1] = columnLocation;
        } else if(alignment == 1) {
            int rowLocation = (int) (Math.random() * (rows - 2));
            int columnLocation = (int) (Math.random() * (columns));
            startingLocation[0] = rowLocation;
            startingLocation[1] = columnLocation;
        }
        return startingLocation;
    }

    // check ships arrays against other ship arrays
    public void checkforOpenSpotsOnBoard(int[][] ship, boolean canPlaceShip) {
        if(canPlaceShip == true) {
            if(alignment == 0 && canPlaceShip == true) {
                //cycle through rows of randomly generated ship
                for(int i = 0; i < shipSections; i++) {
                    //cycles through rows of existing ships placed on the board
                    if(canPlaceShip == true) {
                        for(int j = 0; j < shipSections; j++) {
                            if(canPlaceShip == true) {
                                if(startingLocation[0] == ship[j][0] && startingLocation[1] + i == ship[j][1]) {
                                    canPlaceShip = false;
                                    battle.setCanPlaceShip(false);
                                } else {
                                    canPlaceShip = true;
                                }
                            }
                        }
                    }
                }
            } else if(alignment == 1 && canPlaceShip == true) {
                //cycle through rows of randomly generated ship
                for(int i = 0; i < shipSections; i++) {
                    //cycles through rows of existing ships placed on the board
                    if(canPlaceShip == true) {
                        for(int j = 0; j < shipSections; j++) {
                            if(canPlaceShip == true) {
                                if((startingLocation[0] + i) == ship[j][0] && startingLocation[1] == ship[j][1]) {
                                    canPlaceShip = false;
                                    battle.setCanPlaceShip(false);
                                } else {
                                    canPlaceShip = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //create ship array
    public void createShipArray(int[][] ship) {
        for(int i = 0; i < shipSections; i++) {
            if(alignment == 0) {
                ship[0 + i][0] = startingLocation[0];
                ship[0 + i][1] = startingLocation[1] + i;
            } else if(alignment == 1) {
                ship[0 + i][0] = startingLocation[0] + i;
                ship[0 + i][1] = startingLocation[1];
            }
        }
        //print ship array coordinates
        for(int i = 0; i < ship.length; i++) {
            for(int j = 0; j < ship[0].length; j++) {
                System.out.print(ship[i][j] + " ");
            }
            System.out.print("\r");
        }
        System.out.print("\r");
        BattleshipTestDrive.shipsPlaced++;
    }

    // check the user's guess against ship arrays for hits
    public void checkShipArrayForHit(int[] userGuess, int[][] ship, int hitCounter) {
        for(int i = 0; i < shipSections; i++) {
            if( match == false) {
                if (userGuess[0] == ship[i][0] && userGuess[1] == ship[i][1]){
                    ship[i][2] = 1;
                    BattleshipTestDrive.hitCounter++;
                    checkShipArrayForSink(ship);
                    match = true;
                    break;
                } else {
                    match = false;
                }
            }
        }
    }

    //check the ship array for sink
    public void checkShipArrayForSink(int[][] ship) {
        if(ship[0][2] == 1 && ship[1][2] == 1 && ship[2][2] == 1) {
            System.out.println("Sunk!");
        } else {
            System.out.println("Hit!");
        }
    }
}
