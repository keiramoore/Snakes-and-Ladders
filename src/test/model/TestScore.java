package model;


import model.exceptions.InvalidNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TestScore {
    private Score s;

    @BeforeEach
    public void runBefore() {
        s = new Score("Winners of Each Round");
    }

    @Test
    public void testGetName() {
        assertEquals("Winners of Each Round", s.getName());
    }

    @Test
    public void testNumWinners() {
        try {
        Player p1 = new Player("Me");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Joe");
        s.addWinner(p1);
        s.addWinner(p2);
        s.addWinner(p3);
        assertEquals(3, s.numWinners());
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testGetWinners() {
        try {
        Player p1 = new Player("Me");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Joe");
        s.addWinner(p1);
        s.addWinner(p2);
        s.addWinner(p3);
        assertEquals(p1.getName(), s.getWinners().get(0).getName());
        assertEquals(p2.getName(), s.getWinners().get(1).getName());
        assertEquals(p3.getName(), s.getWinners().get(2).getName());
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }
}
