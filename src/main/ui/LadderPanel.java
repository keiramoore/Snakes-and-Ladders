package ui;

import model.Board;

import javax.swing.*;
import java.awt.*;

// container for ladders
public class LadderPanel extends JPanel {

    // EFFECTS: Creates transparent layer with ladders
    public LadderPanel() {
        setOpaque(false);
        setBackground(new Color(0,0,0,0));
        setLayout(null);
        addLadders();
    }

    // MODIFIES: this
    // EFFECTS: add ladders to JPanel
    public void addLadders() {
        JLabel ladder = smallLadder();
        Dimension size = ladder.getPreferredSize();
        ladder.setBounds(25, 355, size.width, size.height);
        add(ladder);
        JLabel ladder1 = smallLadder();
        size = ladder1.getPreferredSize();
        ladder1.setBounds(175, 305, size.width, size.height);
        add(ladder1);
        JLabel ladder2 = bigLadder();
        size = ladder2.getPreferredSize();
        ladder2.setBounds(328, 68, size.width, size.height);
        add(ladder2);
        JLabel ladder3 = smallLadder();
        size = ladder3.getPreferredSize();
        ladder3.setBounds(225, 10, size.width, size.height);
        add(ladder3);
        JLabel ladder4 = smallLadder();
        size = ladder4.getPreferredSize();
        ladder4.setBounds(75, 110, size.width, size.height);
        add(ladder4);
    }

    // EFFECTS: creates small ladder icon
    public JLabel smallLadder() {
        ImageIcon ladderIcon = new ImageIcon("./data/smallLadder.png");
        Image ladderImage = ladderIcon.getImage();
        Image scaledLadder = ladderImage.getScaledInstance(100, 140, Image.SCALE_SMOOTH);
        ladderIcon = new ImageIcon(scaledLadder);
        JLabel ladder = new JLabel(ladderIcon);
        return ladder;
    }

    // EFFECTS: creates big ladder icon
    public JLabel bigLadder() {
        ImageIcon ladderIcon = new ImageIcon("./data/bigLadder.png");
        Image ladderImage = ladderIcon.getImage();
        Image scaledLadder = ladderImage.getScaledInstance(100, 280, Image.SCALE_SMOOTH);
        ladderIcon = new ImageIcon(scaledLadder);
        JLabel ladder = new JLabel(ladderIcon);
        return ladder;
    }
}
