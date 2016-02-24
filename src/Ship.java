/**
 * Created by Scott on 2/23/16.
 */
public class Ship {

    private int numOfHits;
    private int[] ship = new int[3];

    public int getNumOfHits(){
        return numOfHits;
    }

    public void increaseNumOfHits(){
        if(numOfHits > 0 && numOfHits < 4) {
            numOfHits++;
        }
    }

    public void checkIfSunk(int numOfHits) {
        if (ship[0] == 2 && ship[1] == 2 && ship[2] == 2){
            System.out.println("Sunk!");
        }
    }

}
