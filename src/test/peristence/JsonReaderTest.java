package peristence;

import model.Player;
import model.Score;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Score s = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyScore() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyScore.json");
        try {
            Score s = reader.read();
            assertEquals("My Scores", s.getName());
            assertEquals(0, s.numWinners());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralScore() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralScore.json");
        try {
            Score s = reader.read();
            assertEquals("Keira's Scores", s.getName());
            List <Player> winners = s.getWinners();
            assertEquals(2, winners.size());
            checkWinner("User", winners.get(0));
            checkWinner("Computer", winners.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
