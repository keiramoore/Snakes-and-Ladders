package model;



// Represents dice being rolled before each turn
public class Dice {
    public static final int MAX_DICE_ROLL = 6;
    public static final int MIN_DICE_ROLL = 1;

    public int rolled;

    public Dice() {
        rolled = rollDice();
    }

    // EFFECTS: Generates result of rolling dice
    public int rollDice() {
        return ((int)(Math.random() * (MAX_DICE_ROLL - MIN_DICE_ROLL + 1) + MIN_DICE_ROLL));
    }

    public void setRolled() {
        rolled = 3;
    }
}
