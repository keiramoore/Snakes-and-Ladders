package model;

import model.exceptions.InvalidNameException;

import java.util.*;

import static sun.management.Agent.error;

// represents board containing players, snakes, and ladders
public class Board {
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    public static final int TOP = ROWS * COLUMNS;

    public Player winner;
    public int[][] boardPosition;
    public List<Snake> snakes;
    public List<Ladder> ladders;
    public List<Player> players;
    //public Board board;

    // EFFECTS: constructs a snakes and ladders board
    public Board() {
        boardPosition = assignPositions();
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        setUpSnakes();
        setUpLadders();
        players = new ArrayList<>();
        initializePlayers();
    }

    // EFFECTS: assigns position to each space in board
    public int[][] assignPositions() {
        boardPosition = new int[ROWS][COLUMNS];
        for (int r = 9; r >= 0; r--) {
            for (int c = 0; c < COLUMNS; c++) {
                if ((r % 2) == 0) {
                    boardPosition[r][c] = ((ROWS - r - 1) * 10) + (COLUMNS - c);
                } else {
                    boardPosition[r][c] = ((ROWS - r - 1) * 10) + (c + 1);
                }
            }
        }
        return boardPosition;
    }


    // MODIFIES: this
    // EFFECTS: generate list of snakes
    public void setUpSnakes() {
        Snake s1 = new Snake(98, 60);
        Snake s2 = new Snake(93, 69);
        Snake s3 = new Snake(74, 26);
        Snake s4 = new Snake(64, 44);
        Snake s5 = new Snake(40, 4);
        Snake s6 = new Snake(29, 8);
        snakes.add(s1);
        snakes.add(s2);
        snakes.add(s3);
        snakes.add(s4);
        snakes.add(s5);
        snakes.add(s6);
    }

    // MODIFIES: this
    // EFFECTS: generate list of ladders
    public void setUpLadders() {
        Ladder l1 = new Ladder(2, 22);
        Ladder l2 = new Ladder(16, 36);
        Ladder l3 = new Ladder(33, 88);
        Ladder l4 = new Ladder(58, 78);
        Ladder l5 = new Ladder(75, 95);
        ladders.add(l1);
        ladders.add(l2);
        ladders.add(l3);
        ladders.add(l4);
        ladders.add(l5);
    }

    // MODIFIES: this
    // EFFECTS: adds computer and player to list of players
    public void initializePlayers() {
        try {
            Player computer = new Player("Computer");
            players.add(computer);
        } catch (InvalidNameException e) {
            error("Invalid name");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }


    // MODIFIES: Player p
    // EFFECTS: changes position of player on board according to dice roll
    public int movePlayer(Player p, Dice d) {
        return (p.position += d.rolled);
    }


    // MODIFIES: Player p
    // EFFECTS: return true and changes position if player position collides with
    // top of snake, otherwise return false and keep position
    public boolean hitLadder(Player p) {
        for (Ladder l : ladders) {
            if (l.getBottom() == p.getPosition()) {
                p.position = l.getTop();
                return true;
            }
        }
        return false;
    }


    // MODIFIES: Player p
    // EFFECTS: return true and change position if player collides with
    // bottom of ladder, otherwise return false and keep position
    public boolean hitSnake(Player p) {
        for (Snake s : snakes) {
            if (s.getTop() == p.getPosition()) {
                p.position = s.getBottom();
                return true;
            }
        }
        return false;
    }

    // MODIFIES: Player p
    // EFFECTS: return true and fix position if player reached top,
    // otherwise return false and maintain position
    public boolean reachTop(Player p) {
        if (p.position >= TOP) {
            p.position = TOP;
            winner = p;
            return true;
        } else {
            return false;
        }
    }






}
