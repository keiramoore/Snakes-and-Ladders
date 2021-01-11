package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Citation: Based off of CPSC 210 JSonSerializationDemo WorkRoom class
// Represents game having a collection of winning players
public class Score implements Writable {
    private String name;
    private List<Player> winners;

    // EFFECTS: Constructs a score with a name and empty list of winners
    public Score(String name) {
        this.name = name;
        winners = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: add
    public void addWinner(Player winner) {
        winners.add(winner);
    }

    // EFFECTS: returns an unmodifiable list of winners in this score
    public List<Player> getWinners() {
        return Collections.unmodifiableList(winners);
    }

    // EFFECTS: returns number of winners in this score
    public int numWinners() {
        return winners.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("winners", winnersToJson());
        return json;
    }

    // EFFECTS: returns winners in this score as a JSON array
    private JSONArray winnersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player w : winners) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}





