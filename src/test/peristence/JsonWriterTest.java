package peristence;

import model.Player;
import model.Score;
import model.exceptions.InvalidNameException;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Score s = new Score("Keira's Scores");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyScore() {
        try {
            Score s = new Score("My Scores");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyScore.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyScore.json");
            s = reader.read();
            assertEquals("My Scores", s.getName());
            assertEquals(0, s.numWinners());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralScore() {
        try {
            Score s = new Score("Keira's Scores");
            try {
                s.addWinner(new Player("User"));
                s.addWinner(new Player("Computer"));
            } catch (InvalidNameException e) {
                fail("Exception should not have been thrown.");
            }
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralScore.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralScore.json");
            s = reader.read();
            assertEquals("Keira's Scores", s.getName());
            List<Player> winners = s.getWinners();
            assertEquals(2, winners.size());
            checkWinner("User", winners.get(0));
            checkWinner("Computer", winners.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
