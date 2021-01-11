package model;

import java.util.Random;

// represents ladder having a top and bottom coordinate
public class Ladder {

    public static final int MAX_TOP = 99;
    public static final int MIN_BOT = 2;

    private int top;
    private int bottom;


    // REQUIRES: bottom >= MIN_BOT and bottom < top <= MAX_TOP
    // EFFECTS: constructs ladder with given top and bottom
    public Ladder(int bottom, int top) {
        this.top = top;
        this.bottom = bottom;
    }

    public int getBottom() {
        return bottom;
    }

    public int getTop() {
        return top;
    }


}
