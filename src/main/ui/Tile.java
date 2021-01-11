package ui;

import javax.swing.*;
import java.awt.*;

// Creates coloured tiles for grid pattern of board
public class Tile extends JLabel {

    // Citation: https://stackoverflow.com/questions/51746451/how-do-i-make-a-jpanel-grid

    // EFFECTS: creates tile of desired colour displaying position number
    public Tile(Color c, int pos) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(50,50));
        setOpaque(true);
        setBackground(c);
        setText("    " + Integer.toString(pos));
    }
}
