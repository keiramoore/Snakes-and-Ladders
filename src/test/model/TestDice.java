package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDice {

    private Dice d;

    @Test
    public void testRollDiceMax() {
        d = new Dice();
        assertTrue(d.rolled <= Dice.MAX_DICE_ROLL);
    }

    @Test
    public void testRollDiceMin() {
        d = new Dice();
        assertTrue(d.rolled >= Dice.MIN_DICE_ROLL);
    }
}
