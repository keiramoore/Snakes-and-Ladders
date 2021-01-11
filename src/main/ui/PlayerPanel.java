package ui;

import model.Board;
import model.Player;

import javax.swing.*;
import java.awt.*;

// container for players
public class PlayerPanel extends JPanel {
    public JLabel player;
    public JLabel computer;

    // EFFECTS: creates transparent layer containing players
    public PlayerPanel(Board b) {
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);
        addComputer(b.getPlayers().get(0));
        switch (b.getPlayers().size()) {
            case 2:
                addPlayer(b.getPlayers().get(1));
                break;
            case 3:
                addPlayer(b.getPlayers().get(1));
                addPlayer2(b.getPlayers().get(2));
                break;
            case 4:
                addPlayer(b.getPlayers().get(1));
                addPlayer2(b.getPlayers().get(2));
                addPlayer3(b.getPlayers().get(3));
                break;
            case 5:
                addPlayer(b.getPlayers().get(1));
                addPlayer2(b.getPlayers().get(2));
                addPlayer3(b.getPlayers().get(3));
                addPlayer3(b.getPlayers().get(4));
        }
    }

    // MODIFIES: this
    // EFFECTS: adds player to position on board
    public void addPlayer(Player p) {
        player = playerIcon();
        Dimension size = player.getPreferredSize();
        player.setBounds((15 + (p.getX() * 50)), (2 + (p.getY() * 50)), size.width, size.height);
        add(player);
    }

    // MODIFIES: this
    // EFFECTS: adds player to position on board
    public void addPlayer2(Player p) {
        player = player2Icon();
        Dimension size = player.getPreferredSize();
        player.setBounds((20 + (p.getX() * 50)), (2 + (p.getY() * 50)), size.width, size.height);
        add(player);
    }

    // MODIFIES: this
    // EFFECTS: adds player to position on board
    public void addPlayer3(Player p) {
        player = player3Icon();
        Dimension size = player.getPreferredSize();
        player.setBounds((25 + (p.getX() * 50)), (2 + (p.getY() * 50)), size.width, size.height);
        add(player);
    }


    // MODIFIES: this
    // EFFECTS: adds computer to position on board
    public void addComputer(Player p) {
        computer = computerIcon();
        Dimension size = computer.getPreferredSize();
        computer.setBounds((6 + (p.getX() * 50)), (2 + (p.getY() * 50)), size.width, size.height);
        add(computer);
    }

    // EFFECTS: makes player avatar
    public JLabel playerIcon() {
        ImageIcon playerIcon = new ImageIcon("./data/player.png");
        Image playerImage = playerIcon.getImage();
        Image scaledImage = playerImage.getScaledInstance(25, 40, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(scaledImage);
        JLabel player = new JLabel(playerIcon);
        return player;
    }

    // EFFECTS: makes player avatar
    public JLabel player2Icon() {
        ImageIcon playerIcon = new ImageIcon("./data/player4.png");
        Image playerImage = playerIcon.getImage();
        Image scaledImage = playerImage.getScaledInstance(25, 40, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(scaledImage);
        JLabel player = new JLabel(playerIcon);
        return player;
    }

    // EFFECTS: makes player avatar
    public JLabel player3Icon() {
        ImageIcon playerIcon = new ImageIcon("./data/player3.png");
        Image playerImage = playerIcon.getImage();
        Image scaledImage = playerImage.getScaledInstance(25, 42, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(scaledImage);
        JLabel player = new JLabel(playerIcon);
        return player;
    }


    // EFFECTS: makes computer avatar
    public JLabel computerIcon() {
        ImageIcon playerIcon = new ImageIcon("./data/unnamed.png");
        Image playerImage = playerIcon.getImage();
        Image scaledImage = playerImage.getScaledInstance(25, 40, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(scaledImage);
        JLabel player = new JLabel(playerIcon);
        return player;
    }


}
