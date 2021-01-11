package persistence;

import org.json.JSONObject;


// Citation: CPSC 210 JsonSerializationDemo Writable Interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
