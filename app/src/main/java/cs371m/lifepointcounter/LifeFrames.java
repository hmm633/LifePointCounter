package cs371m.lifepointcounter;

import android.util.Log;

import java.util.Random;

/**
 * Created by Monolith on 4/4/2016.
 */
public class LifeFrames {

    // DEFAULTS FOR PARTICULAR GAMES
    public static final int YUGIOH_TOTAL = 8000;
    public static final int YUGIOH_SMALLINC = 100;
    public static final int YUGIOH_LARGEINC = 1000;

    public static final int MAGIC_TOTAL = 20;
    public static final int MAGIC_SMALLINC = 1;
    public static final int MAGIC_LARGEINC = 5;

    public int startingLifeTotal = MAGIC_TOTAL; // The default
    public int smallInc = MAGIC_SMALLINC;        // Value used when tapping on frames
    public int largeInc = MAGIC_LARGEINC;        // Value used when swiping on frames
    public int numPlayers = 2;                  // Current number of players displayed

    // Array that stores life totals of all players
    int[] playerLPArray = new int[8];

    // Array that stores the names of the players
    // Need to make dynamic, or more customizable?
    // Needs methods
    char[][] playerNames = new char[20][8];

    private Random mRand;       // For use with coin flip and dice roll functions

    // Players enum structure (May be a better way to do this?)
    public enum player {One, Two, Three, Four, Five, Six, Seven, Eight}

    // CONSTRUCTOR:
    public LifeFrames() {

        // May could use this to set the game to
        // particular settings before starting?

        // Set all players' life total to the starting life total
        for(int i = 0; i < 8; i++){
            playerLPArray[i] = startingLifeTotal;
        }

        // Seed the random number generator
        mRand = new Random();
    }

    // GETTERS AND SETTERS:

    // Returns the current life total of the specified player
    public int getPlayerLP(int player){
        return playerLPArray[player];
    }

    // Sets the current life total of the specified player
    public void setPlayerLP(int player, int life){
        playerLPArray[player] = life;
    }

    public void setAllPlayerLp(int life){
        // Set all players' life total to the starting life total
        for(int i = 0; i < 8; i++){
            playerLPArray[i] = life;
        }
    }

    // Gets the smallInc
    public int getSmallInc(){
        return smallInc;
    }

    /* Eddy: for setting small and large inc, should we check that they're
     * not negative and larger than small inc? */

    // Sets the smallInc
    public void setSmallInc(int life){
        smallInc = life;
        Log.d("In setSmallInc", Integer.toString(smallInc));
    }

    // Gets the largeInc
    public int getLargeInc(){
        return largeInc;
    }

    // Sets the largeInc
    public void setLargeInc(int life){
        largeInc = life;
        Log.d("In setLargeInc", Integer.toString(largeInc));
    }

    // Get number of players
    public int getNumOfPlayers(){
        return numPlayers;
    }

    // Set number of players
    public void setNumOfPlayers(int players){ // better variable name?
        numPlayers = players;
    }

    public void setStartingLifeTotal(int lifeTotal){
        startingLifeTotal = lifeTotal;
        Log.d("In LifeFrames", Integer.toString(startingLifeTotal));
    }

    // OTHER FUNCTIONS:

    /*  Starts a new game by setting the life totals of all
        players to the starting life total
        Should also reset the size of the text within texviews
    */
    public void newGame(){
        for(int i = 0; i < 8; i++){
            playerLPArray[i] = startingLifeTotal;
        }
//        Entry entry = new Entry("New Game", "", "", "");
//        Record.entryList.add(entry);
    }


    // Increments player life by smallInc
    public boolean smallIncUp(int player){

        if(player >= 0 && player <= 7){
            playerLPArray[player] += smallInc;
//            System.out.println("INCREMENTED");
            return true;
        }
        else
//            System.out.println("DECREMENTED");
            return false;
    }


    // Increments player life by largeInc
    public boolean largeIncUp(int player){
        if(player >= 0 && player <= 7){
            playerLPArray[player] += largeInc;
            return true;
        }
        else
            return false;
    }

    // Decrements the smallInc from player
    // Returns true if correct input, false otherwise
    public boolean smallIncDown(int player){
        if(player >= 0 && player <= 7){
            playerLPArray[player] -= smallInc;
            return true;
        }
        else
            return false;
    }

    // Decrements the largeInc from player
    // Returns true if correct input, false otherwise
    public boolean largeIncDown(int player){
        if(player >= 0 && player <= 7){
            playerLPArray[player] -= largeInc;
            return true;
        }
        else
            return false;
    }

    // Adds a single player to the game
    // Returns true if operation successful, false otherwise
    public boolean addPlayer(){
        if(numPlayers < 8){
            numPlayers++;
            return true;
        }
        else
            return false;
    }

    // Removes a player by decrementing numPlayers
    // Returns true if operation successful, false otherwise
    public boolean removePlayer(){
        if(numPlayers > 0){
            numPlayers--;
            return true;
        }
        else
            return false;
    }

    // Returns the result of a coin toss (True == Heads, False == Tails)
    public boolean tossCoin(){
        return mRand.nextInt(2) == 1;
    }

    // Returns the result of a dice roll
    public int rollDie(int numOfSides){
        return mRand.nextInt(numOfSides)+1;
    }

    public int numOfPlayers(){
        return numPlayers;
    }

}
