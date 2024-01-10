package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.Random;

/**
 * ModelCraps class represents the game of craps.It keeps track
 * of all the variables required to play the game of craps.
 * It implements PropertyChangeListener in order to fire off property
 * changes to the View panels.
 * @author Michael Mapanao
 * @version 1.0.05
 */
public final class ModelCraps implements PropertyChangeListener {

    /** myPoint represents the point after the first roll. */
    private int myPoint;
    /** myMax represents the maximum value a die can hold. */
    private final int myMax;
    /** myMin represents the minimum value a die can hold. */
    private final int myMin;
    /** myDie1 represents the first die in the game of craps. */
    private int myDie1;
    /** myDie2 represents the second die in the game of craps. */
    private int myDie2;
    /** myTotal represents the total of myDie1 and myDie2. */
    private int myTotal;
    /** myPlayerWins represents the amount of times the player
     * wins in a session. */
    private int myPlayerWins;
    /** myHouseWins represents the amount of times the house
     * wins in a session. */
    private int myHouseWins;
    /** myBet represents the int bet of the player.  */
    private int myBet;
    /** myBank represents the amount of money in a players account. */
    private int myBank;
    /** myGameActive represents if a game is currently active. */
    private boolean myGameActive;
    /**myCounter determines if you're rolling the first time or not. */
    private int myCounter;
    /** changes allows us to listen to property changes and fire property changes
     * when necessary. */
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    /** myInstance represents the current instance of the game of craps
     * for singleton ideology. */
    private static ModelCraps myInstance = new ModelCraps();


    /** modelCraps, the constructor is set to private for singleton usage.
     * it holds the min, max, initializes that the point is 1, and holds the value of the dice
     * of their first roll. Then we start the game. */
    private ModelCraps() {
        myMin = 1;
        myMax = 6;
        myPoint = 0;
        myDie1 = this.diceRoll();
        myDie2 = this.diceRoll();
        startGame();
    }

    /** The toString method overrides Object.toString and allows me to
     * test what is happening in the console. */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("You");
        return sb.toString();
    }

    /**
     * the diceRoll method is used to roll the dice individually.
     * while the game is active, it calculates the total and fires a property change
     * every time it's rolled.
     * @return the total of myDie1 and myDie2 are returned.
     */
    public int diceRoll() {

        myTotal = 0;
        if (myGameActive && myBet <= myBank) {
            final Random random = new Random();
            myDie1 = random.nextInt((this.myMax - this.myMin) + 1) + this.myMin;
            myDie2 = random.nextInt((this.myMax - this.myMin) + 1) + this.myMin;
            myTotal = myDie1 + myDie2;
            System.out.println(this + " rolled: " + myTotal);
            changes.firePropertyChange("rolled", null, true);
            if (myCounter == 0 && (myTotal == 4 || myTotal == 5 || myTotal == 6 || myTotal == 8
                    || myTotal == 9 || myTotal == 10)) {
                setPoint(myTotal);
            }
            if ((myTotal == 11 || myTotal == 7) && myCounter == 0) {
                myPlayerWins += 1;
                changes.firePropertyChange("win", null, true);
                setGameActive(false);
            }
            if ((myTotal == 2 || myTotal == 3 || myTotal == 12) && myCounter == 0) {
                myHouseWins += 1;
                changes.firePropertyChange("lose", null, true);
                setGameActive(false);
            }
            if (myCounter > 0 && myTotal == 7) {
                myHouseWins += 1;
                changes.firePropertyChange("lose", null, true);
                System.out.println("ouch... you rolled a: " + myTotal);
                myCounter = 0;
                setGameActive(false);
            }

            if (myCounter > 0 && myTotal == myPoint) {
                myPlayerWins += 1;
                changes.firePropertyChange("win", null, true);
                System.out.println("nice! you rolled a: " + myTotal);
                myCounter = 0;
                setGameActive(false);
            }
            myCounter++;
            return myTotal;
        }
        myCounter = 0;
        return myTotal;
    }

    /** setDie1 is a setter for setting the value of myDie1. */
    public void setDie1(final int theDie1) {
        myDie1 = theDie1;
    }

    /** getDie1 is a getter for grabbing myDie1 from an outside class. */
    public int getDie1() {
        return  myDie1;
    }

    /** setDie2 is a setter for setting the value of myDie2. */
    public void setDie2(final int theDie2) {
        myDie2 = theDie2;
    }

    /** getDie2 is a getter for grabbing myDie2 from an outside class. */
    public int getDie2() {
        return  myDie2;
    }

    /** getTotal is a getter method for grabbing the total from myTotal.
     * @return returns the total sum of myDie1 + myDie2. */
    public int getTotal() {
        return myTotal;
    }

    /** setTotal is a setter method for setting the value of the total
     * this can be used in the instance of resetting the value when resetting
     * the game or when the dice is rolled a new total is set. */
    public void setTotal(final int theTotal) {
        myTotal = theTotal;
    }

    /** setPoint is a setter method for setting myPoint after
     * the first roll of the game.
     * @param thePoint returns the myPoint value.
     */
    public void setPoint(final int thePoint) {
        myPoint = thePoint;
    }

    /** getPoint is a getter method for retrieving myPoint.
     * @return myPoint, or the point that was assigned.
     */
    public int getPoint() {
        return myPoint;
    }

    /** getPlayerWins is a getter method for the amount of player
     * wins.
     * @return the int value of player wins.
     */
    public int getPlayerWins() {
        return myPlayerWins;
    }

    /** setPlayerWins is a setter method to set the amount of wins
     * for a player.
     * @param thePlayerWins sets the value of the player's wins.
     */
    public void setPlayerWins(final int thePlayerWins) {
        myPlayerWins = thePlayerWins;
    }

    /** getHouseWins is a getter method that retrieves myHouseWins.
     * @return myHouseWins (the amount of wins by the House.)
     */
    public int getHouseWins() {
        return myHouseWins;
    }

    /** setHouseWins is a setter method that sets the amount of wins
     * for the House. */
    public void setHouseWins(final int theHouseWins) {
        myHouseWins = theHouseWins;
    }

    /** getBet is a getter method that will retrieve the current bet.
     * @return myBet (the current bet of the player).
     */
    public int getBet() {
        return myBet;
    }

    /**
     * setBet is a setter that allows for setting a new value.
     * @param theBet accepts a new value for betting.
     */
    public void setBet(final int theBet)  {
        myBet = theBet;
    }

    /** setBank is a setter method that sets the Bank value.
     * @param theBank accepts an int value that sets the new Bank value. */
    public void setBank(final int theBank) {
        myBank = theBank;
    }

    /** getBank is a getter method that retrieves the myBank value.
     * @return the current value of myBank.
     */
    public int getBank() {
        return myBank;
    }

    /** adjustBankLoss sets the new value of the bank when the player
     * loses. It takes your current bank value and deducts your bet value. */
    public void adjustBankLoss() {
        setBank(myBank - myBet);
    }

    /** adjustBankWin sets the new value of the bank when the player
     * wins. It takes your current bank value and adds your bet value. */
    public void adjustBankWin() {
        setBank(myBank + myBet);
    }

    /** creates an instance of ModelCraps using the singleton design model. */
    public static ModelCraps getModelCrapsInstance() {
        return myInstance;
    }

    /** setGameActive will fire a property change if the game is Active
     * or inactive.
     *
     * @param theGameActive accepts a boolean value to say if the game is
     *                      active or not.
     */
    public void setGameActive(final boolean theGameActive) {
        myGameActive = theGameActive;
        if (myGameActive) {
            changes.firePropertyChange("active", null, false);
        }

        if (!myGameActive) {
            System.out.println("inactive game");
            changes.firePropertyChange("finishedGame", null, true);
        }
    }

    /**
     * getGameActive is a getter method that retrieves the current
     * Game Active state.
     * @return the current state of myGameActive (boolean value).
     */
    public boolean getGameActive() {
        return myGameActive;
    }

    /** Allows to grab any property changes from this class. */
    public PropertyChangeSupport getChanges() {
        return changes;
    }

    /** sets the myGameActive state to true. */
    public void startGame() {
        setGameActive(true);
    }

    /** the resetGame method sets all values
     * back to zero. The setBank and setBet values
     * are set to 1 to satisfy minimum values of
     * betting and bank amount. */
    public void resetGame() {
        myCounter = 0;
        setPoint(0);
        setDie1(0);
        setDie2(0);
        setTotal(0);
        setPlayerWins(0);
        setHouseWins(0);
        setBet(1);
        setBank(1);
    }

    /** playAgain will reset myCounter back to zero.
     * it will also set myPoint to zero and fire the
     * property change "playAgain".
     */
    public void playAgain() {
        myCounter = 0;
        setPoint(0);
        changes.firePropertyChange("playAgain", null, false);
    }

    /**
     * addPropertyChangeListener allows the propertyChangeListener to
     * be added to the panels in the controller.
     * @param l accepts a propertyChangeListener.
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    /** propertyChange method listens for a firePropertyChange of "finishedGame"
     * and "playAgain".
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (Objects.equals(theEvt.getPropertyName(), "finishedGame")) {
            setGameActive(false);
        }

        if (Objects.equals(theEvt.getPropertyName(), "playAgain")) {
            setPoint(0);
            myCounter = 0;
        }
    }

}
