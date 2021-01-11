package model;

import model.exceptions.InvalidNameException;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Random;

// represents player having position
public class Player implements Writable {

    public int position;
    public String name;

    // EFFECTS: constructs player with given name and assigns it a position of the board.
    // If the string passed to the constructor is null, throw an emptyNameException
    public Player(String name) throws InvalidNameException {
        if (name.length() > 0) {
            this.name = name;
            position = 1;
        } else {
            throw new InvalidNameException("Name cannot be null.");
        }

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int p) {
        position = p;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns X coordinate
    public int getX() {
        int x = 0;
        int y = position / 10;
        if (position % 10 == 0) {
            if (y % 2 == 0) {
                x = 0;
            } else {
                x = 9;
            }
        } else {
            if (y % 2 == 0) {
                x = (position % 10) - 1;
            } else {
                x = 10 - (position % 10);
            }
        }
        return x;
    }

    // EFFECTS: returns y coordinate
    public int getY() {
        int y = position / 10;
        if (position % 10 == 0) {
            y = 10 - y;
        } else {
            y = 10 - y - 1;
        }
        return y;
    }

    // Citation: CPSC 210 JSonSerializationDemo Thingy class
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}


