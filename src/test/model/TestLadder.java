package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLadder {

    private Ladder l;

    @BeforeEach
    public void runBefore() {
        l = new Ladder(12, 76);
    }

    @Test
    public void testGetBottom() {
        assertEquals(12, l.getBottom());
    }

    @Test
    public void testGetTop() {
        assertEquals(76, l.getTop());
    }

}
