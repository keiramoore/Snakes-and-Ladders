package ui;

import model.Board;
import ui.PlayerPanel;

import javax.swing.*;
import java.awt.*;


// layered pane containing background, snakes, ladders, and players
public class BoardPanel extends JLayeredPane {

    public JPanel base;
    public JPanel snakes;
    public JPanel ladders;
    public JPanel players;

    public BoardPanel(Board b) {

        base = new BackgroundPanel(b);
        base.setOpaque(true);
        base.setBackground(new Color(0,0,0,0));
        base.setSize(500, 500);
        this.add(base, new Integer(0));

        snakes = new SnakePanel();
        snakes.setOpaque(false);
        snakes.setBackground(new Color(0,0,0));
        snakes.setSize(500, 500);
        this.add(snakes, new Integer(1));

        ladders = new LadderPanel();
        ladders.setOpaque(false);
        ladders.setBackground(new Color(0,0,0));
        ladders.setSize(500, 500);
        this.add(ladders, new Integer(2));

        players = new PlayerPanel(b);
        players.setOpaque(false);
        players.setBackground(new Color(0,0,0,0));
        players.setSize(500, 500);
        this.add(players, new Integer(3));
    }

    // MODIFIES: this
    // EFFECTS: repaints player panel with updated positions
    public void movePawns(Board board) {
        remove(players);
        repaint();
        players = new PlayerPanel(board);
        players.setOpaque(false);
        players.setBackground(new Color(0,0,0,0));
        players.setSize(500, 500);
        this.add(players, new Integer(3));
    }


}
