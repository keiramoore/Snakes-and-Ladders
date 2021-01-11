package ui;

import javax.swing.*;
import java.awt.*;

// container for snakes
public class SnakePanel extends JPanel {

    // EFFECTS: Creates transparent panel with snakes
    public SnakePanel() {
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);
        addSnakes1();
        addSnakes2();
    }

    // MODIFIES: this
    // EFFECTS: adds snakes to JPanel in board positions
    public void addSnakes1() {
        JLabel snake1 = pinkSnake();
        Dimension size = snake1.getPreferredSize();
        snake1.setBounds(15, 5, size.width, size.height);
        add(snake1);
        JLabel snake3 = yellowSnake();
        size = snake3.getPreferredSize();
        snake3.setBounds(370, 350, size.width, size.height);
        add(snake3);
        JLabel snake2 = longGreenSnake();
        size = snake2.getPreferredSize();
        snake2.setBounds(260, 100, size.width, size.height);
        add(snake2);
    }

    // MODIFIES: this
    // EFFECTS: adds remaining snakes to JPanel in board positions
    public void addSnakes2() {
        JLabel snake4 = purpleSnake();
        Dimension size = snake4.getPreferredSize();
        snake4.setBounds(20, 300, size.width, size.height);
        add(snake4);
        JLabel snake5 = littleYellowSnake();
        size = snake5.getPreferredSize();
        snake5.setBounds(150, 150, size.width, size.height);
        add(snake5);
        JLabel snake6 = babyGreenSnake();
        size = snake6.getPreferredSize();
        snake6.setBounds(350, 10, size.width, size.height);
        add(snake6);
    }

    // EFFECTS: creates pink snake
    public JLabel pinkSnake() {
        ImageIcon snakeIcon = new ImageIcon("./data/pink.png");
        Image snakeImage = snakeIcon.getImage();
        Image smallSnake = snakeImage.getScaledInstance(130, 230, Image.SCALE_SMOOTH);
        snakeIcon = new ImageIcon(smallSnake);
        return new JLabel(snakeIcon);
    }

    // EFFECTS: creates yellow snake
    public JLabel yellowSnake() {
        ImageIcon snakeIcon = new ImageIcon("./data/yellow.png");
        Image snakeImage = snakeIcon.getImage();
        Image smallSnake = snakeImage.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
        snakeIcon = new ImageIcon(smallSnake);
        return new JLabel(snakeIcon);
    }

    // EFFECTS: creates long green snake
    public JLabel longGreenSnake() {
        ImageIcon snakeIcon = new ImageIcon("./data/longGreen.png");
        Image snakeImage = snakeIcon.getImage();
        Image smallSnake = snakeImage.getScaledInstance(100, 300, Image.SCALE_SMOOTH);
        snakeIcon = new ImageIcon(smallSnake);
        return new JLabel(snakeIcon);
    }

    // EFFECTS: creates purple snake
    public JLabel purpleSnake() {
        ImageIcon snakeIcon = new ImageIcon("./data/purple.png");
        Image snakeImage = snakeIcon.getImage();
        Image smallSnake = snakeImage.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        snakeIcon = new ImageIcon(smallSnake);
        return new JLabel(snakeIcon);
    }

    // EFFECTS: creates small yellow snake
    public JLabel littleYellowSnake() {
        ImageIcon snakeIcon = new ImageIcon("./data/littleYellow.png");
        Image snakeImage = snakeIcon.getImage();
        Image smallSnake = snakeImage.getScaledInstance(100, 140, Image.SCALE_SMOOTH);
        snakeIcon = new ImageIcon(smallSnake);
        return new JLabel(snakeIcon);
    }

    // EFFECTS: creates small green snake
    public JLabel babyGreenSnake() {
        ImageIcon snakeIcon = new ImageIcon("./data/green.png");
        Image snakeImage = snakeIcon.getImage();
        Image smallSnake = snakeImage.getScaledInstance(130, 180, Image.SCALE_SMOOTH);
        snakeIcon = new ImageIcon(smallSnake);
        return new JLabel(snakeIcon);
    }

}
