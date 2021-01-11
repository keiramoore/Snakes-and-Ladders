package model;

import model.exceptions.InvalidNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Board.TOP;
import static org.junit.jupiter.api.Assertions.*;
import static sun.management.Agent.error;

public class TestBoard {

    private Board b;

    @BeforeEach
    public void runBefore() {
        b = new Board();
    }

    @Test
    public void testAssignPositions() {
        assertEquals(10, b.boardPosition[9][9]);
    }

    @Test
    public void testAssignPositions1() {
        assertEquals(11, b.boardPosition[8][9]);
    }

    @Test
    public void testAssignPositions2() {
        assertEquals(100, b.boardPosition[0][0]);
    }

    @Test
    public void testHitSnake() {
        try {
            Player p = new Player("Player");
            p.setPosition(98);
            assertTrue(b.hitSnake(p));
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testHitSnakeNo() {
        try {
            Player p = new Player("Player");
            p.setPosition(43);
            assertFalse(b.hitSnake(p));
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testHitSnakeBottom() {
        try {
        Player p = new Player("Player");
        p.setPosition(44);
        assertFalse(b.hitSnake(p));
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testHitLadder() {
        try {
        Player p = new Player("Player");
        p.setPosition(75);
        assertTrue(b.hitLadder(p));
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testHitLadderNo() {
        try {
            Player p = new Player("Player");
            p.setPosition(10);
            assertFalse(b.hitLadder(p));
        } catch (InvalidNameException e) {
        fail("Should not have thrown exception.");
    }
    }

    @Test
    public void testHitLadderTop() {
        try {
        Player p = new Player("Player");
        p.setPosition(88);
        assertFalse(b.hitLadder(p));
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testGetPlayers() {
        b.initializePlayers();
        assertEquals("Computer", b.getPlayers().get(0).getName());
    }

    @Test
    public void testReachTop() {
        try {
        Player p = new Player("Player");
        p.setPosition(101);
        assertTrue(b.reachTop(p));
        assertEquals(TOP, p.getPosition());
        assertEquals("Player", b.winner.getName());
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testReachTopExact() {
        try {
        Player p = new Player("Player");
        p.setPosition(TOP);
        assertTrue(b.reachTop(p));
        assertEquals(TOP, p.getPosition());
        assertEquals("Player", b.winner.getName());
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testReachTopNo() {
        try {
        Player p = new Player("Player");
        p.setPosition(72);
        assertFalse(b.reachTop(p));
        assertEquals(72, p.getPosition());
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testMovePlayer() {
        try {
        Player p = new Player("Player");
        Dice d = new Dice();
        assertEquals((p.getPosition() + d.rolled), b.movePlayer(p, d));
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testMovePlayerAgain() {
        try {
        Dice d = new Dice();
        d.setRolled();
        Player p = new Player("Bob");
        p.setPosition(72);
        b.movePlayer(p, d);
        assertEquals(75, p.getPosition());
        } catch (InvalidNameException e) {
            fail("Should not have thrown exception.");
        }
    }



}
