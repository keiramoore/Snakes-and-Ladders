package persistence;

import model.Board;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Player;
import model.Score;
import model.exceptions.InvalidNameException;
import org.json.*;

import static sun.management.Agent.error;

// Citation: CPSC 210 JSONSerializationDemo JSonReader class
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Score read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScore(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses score from JSON object and returns it
    private Score parseScore(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Score s = new Score(name);
        addWinners(s, jsonObject);
        return s;
    }

    // MODIFIES: s
    // EFFECTS: parses winners from JSON object and adds them to score
    private void addWinners(Score s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("winners");
        for (Object json : jsonArray) {
            JSONObject nextWinner = (JSONObject) json;
            addWinner(s, nextWinner);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses winner from JSON object and adds it to score
    private void addWinner(Score s, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        try {
            Player winner = new Player(name);
            s.addWinner(winner);
        } catch (InvalidNameException e) {
            error("Invalid name");
        }
    }


}