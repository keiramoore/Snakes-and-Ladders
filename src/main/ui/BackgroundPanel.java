package ui;

import model.Board;
import model.Ladder;
import model.Player;
import model.Snake;


import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.accessibility.*;
import java.awt.event.*;
import java.awt.Graphics;


// The panel containing the checkered background
public class BackgroundPanel extends JPanel {

    final ImageIcon snakeImage = new ImageIcon("snakeImage.jpg");


    public BackgroundPanel(Board b) {
        background(b);
    }

    // EFFECTS: creates grid background of game
    public void background(Board b) {
        setLayout(new GridLayout(Board.ROWS, Board.COLUMNS));
        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                if ((i % 2) == (j % 2)) {
                    add(new Tile(Color.decode("#0074FF"), b.boardPosition[i][j]));
                } else {
                    add(new Tile(Color.decode("#FFA30D"), b.boardPosition[i][j]));
                }
            }
        }
    }

}
