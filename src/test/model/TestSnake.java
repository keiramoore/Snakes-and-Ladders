package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSnake {

    private Snake s;

    @BeforeEach
    public void runBefore() {
        s = new Snake(90, 17);
    }

    @Test
    public void testGetTop() {
        assertEquals(90, s.getTop());
    }

    @Test
    public void testGetBottom() {
        assertEquals(17, s.getBottom());
    }


}
