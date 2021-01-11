package peristence;

import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkWinner(String name, Player winner) {
        assertEquals(name, winner.getName());
    }
}
