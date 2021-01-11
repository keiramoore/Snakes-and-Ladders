package model;


import static org.junit.jupiter.api.Assertions.*;
import static sun.management.Agent.error;
import model.exceptions.InvalidNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    private Player p;

    @BeforeEach
    public void runBefore() {
        try {
            p = new Player("Player");
        } catch (InvalidNameException e){
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testInvalidName() {
        boolean thrown;
        try {
            Player p1 = new Player("");
            thrown = false;
            fail("Should have thrown exception");
        } catch (InvalidNameException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testGetPosition() {
        assertEquals(1, p.getPosition());
    }

    @Test
    public void testSetPosition() {
        p.setPosition(9);
        assertEquals(9, p.getPosition());
    }

    @Test
    public void testGetName() {
        assertEquals("Player", p.getName());
    }

    @Test
    public void testGetY() {
        p.setPosition(80);
        assertEquals(2, p.getY());
    }

    @Test
    public void testGetY1() {
        p.setPosition(100);
        assertEquals(0, p.getY());
    }

    @Test
    public void testGetY2() {
        p.setPosition(76);
        assertEquals(2, p.getY());
    }

    @Test
    public void testGetY3() {
        p.setPosition(69);
        assertEquals(3, p.getY());
    }

    @Test
    public void testGetY4() {
        p.setPosition(1);
        assertEquals(9, p.getY());
    }


    @Test
    public void testGetX() {
        p.setPosition(20);
        assertEquals(0, p.getX());
    }

    @Test
    public void testGetX1() {
        p.setPosition(30);
        assertEquals(9, p.getX());
    }

    @Test
    public void testGetX2() {
        p.setPosition(1);
        assertEquals(0, p.getX());
    }

    @Test
    public void testGetX3() {
        p.setPosition(14);
        assertEquals(6, p.getX());
    }
}
