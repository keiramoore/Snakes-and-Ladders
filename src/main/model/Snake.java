package model;

import java.util.Random;

// represents snake having a top and bottom coordinate
public class Snake {

    public static final int MAX_TOP = 99;
    public static final int MIN_BOT = 2;

    private int top;
    private int bottom;

    // REQUIRES: top <= MAX_TOP and top > bottom > MIN_BOT
    // EFFECTS: creates snake with given top and bottom
    public Snake(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }
}
