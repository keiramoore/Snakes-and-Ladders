package ui;

import model.Board;
import model.Dice;
import model.Player;
import model.Score;
import model.exceptions.InvalidNameException;
import persistence.JsonReader;
import persistence.JsonWriter;
import sound.PlaySound;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

// Citation: all images are from shutterstock
// Citation: all sounds from zapsplat.com
// window for game and menus
public class SnakesAndLadders extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/score.json";
    private Board board;
    public BoardPanel bp;
    private JButton button;
    private Score score;
    private boolean loaded = false;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private int turn = 1;
    private int order;
    boolean valid = true;


    // EFFECTS: sets up window where game will be played
    public SnakesAndLadders() {
        super("Snakes and Ladders");
        score = new Score("Winners");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setUpBoard();
        popUp();
    }

    // MODIFIES: this
    // EFFECTS: creates pop-up window with option to load file or play game
    private void popUp() {
        int n = JOptionPane.showOptionDialog(this,
                "Welcome! Would you like to load the file containing\n"
                        + "previous winner data?",
                "Snakes and Ladders",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, snakeIcon(), null, null);
        if (n == YES_OPTION) {
            getFile();
        }
        chooseNumPlayers();

    }

    // MODIFIES: this
    // EFFECTS: pop-up window prompting user for number of players
    private void chooseNumPlayers() {
        Object[] options = {"1", "2", "3"};
        String input;
        if (!loaded) {
            input = (String) JOptionPane.showInputDialog(this,
                    "How many players would you like to\n"
                            + "add to the board?",
                    "Number of Players", JOptionPane.QUESTION_MESSAGE, questionIcon(),
                    options, null);
        } else {
            input = (String) JOptionPane.showInputDialog(this,
                    "File has been loaded. How many players would you like to\n"
                            + "add to the board?",
                    "Number of Players", JOptionPane.QUESTION_MESSAGE, questionIcon(),
                    options, null);
        }
        if (input != null) {
            order = Integer.parseInt(input);
            game(order);
        } else {
            this.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds selected amount of players to the board
    private void game(int o) {
        switch (o) {
            case 1:
                addPlayer1();
                break;
            case 2:
                addPlayer1();
                if (valid) {
                    addPlayer2();
                }
                break;
            case 3:
                addPlayer1();
                if (valid) {
                    addPlayer2();
                }
                if (valid) {
                    addPlayer3();
                }
                break;
        }
    }


    // MODIFIES: this
    // EFFECTS: takes user input and sets it as player name to start game
    private void addPlayer1() {
        String s = (String) JOptionPane.showInputDialog(this,
                " Please enter your name.\n "
                        + "Press ok to be added to the board.", "Player 1",
                JOptionPane.PLAIN_MESSAGE, playerIcon(), null, null);
        if (s != null) {
            try {
                Player p = new Player(s);
                board.getPlayers().add(1, p);
                bp.movePawns(board);
            } catch (InvalidNameException e) {
                valid = false;
                JOptionPane.showMessageDialog(this, "Invalid name.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
        } else {
            this.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: takes user input and sets it as player name to start game
    private void addPlayer2() {
        String s = (String) JOptionPane.showInputDialog(this,
                " Please enter your name.\n "
                        + "Press ok to be added to the board.", "Player 2",
                JOptionPane.PLAIN_MESSAGE, player2Icon(), null, null);
        if (s != null) {
            try {
                Player p = new Player(s);
                board.getPlayers().add(2, p);
                bp.movePawns(board);
            } catch (InvalidNameException e) {
                valid = false;
                JOptionPane.showMessageDialog(this, "Invalid name.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
        } else {
            this.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: takes user input and sets it as player name to start game
    private void addPlayer3() {
        String s = (String) JOptionPane.showInputDialog(this,
                " Please enter your name.\n "
                        + "Press ok to be added to the board.", "Player 3",
                JOptionPane.PLAIN_MESSAGE, player3Icon(), null, null);
        if (s != null) {
            try {
                Player p = new Player(s);
                board.getPlayers().add(3, p);
                bp.movePawns(board);
            } catch (InvalidNameException e) {
                valid = false;
                JOptionPane.showMessageDialog(this, "Invalid name.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
        } else {
            this.dispose();
        }
    }

/*    // MODIFIES: this
    // EFFECTS: acknowledges that file has been loaded and
    //          takes user input and sets it as player name to start game
    private void startGameLoaded() {
        String s = (String) JOptionPane.showInputDialog(this,
                " File has been loaded. Please enter your name to start.\n "
                        + "Press ok to be added to the board.", "Snakes and Ladders",
                JOptionPane.PLAIN_MESSAGE, playerIcon(), null, null);
        if ((s != null) && (s.length() > 0)) {
            Player p = new Player(s);
            board.getPlayers().add(1, p);
            bp.movePawns(board);
        } else {
            this.dispose();
        }
    }*/


    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: adds board and side menu to frame
    private void setUpBoard() {
        setSize(600, 520);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        board = new Board();
        bp = new BoardPanel(board);
        add(bp, BorderLayout.CENTER);
        button = new JButton(diceIcon());
        button.setActionCommand("Roll");
        button.addActionListener(this);
        add(button, BorderLayout.EAST);
        setVisible(true);
        centreOnScreen();
    }

    // MODIFIES: this
    // EFFECTS: takes computer's turn
    private void computerTurn() {
        Dice d = new Dice();
        computerDice(d);
        board.movePlayer(board.players.get(0), d);
        if (board.hitLadder(board.players.get(0))) {
            board.hitLadder(board.players.get(0));
            new PlaySound("src/main/sound/ladderSound.wav");
        } else if (board.hitSnake(board.players.get(0))) {
            board.hitSnake(board.players.get(0));
            new PlaySound("src/main/sound/snakeSound.wav");
        }
        computerVictory();
        bp.movePawns(board);
    }

    // EFFECTS: displays victory message for computer and gives option of adding winner
    private void computerVictory() {
        if (board.reachTop(board.getPlayers().get(0))) {
            bp.movePawns(board);
            new PlaySound("src/main/sound/lose.wav");
            int n = JOptionPane.showOptionDialog(this,
                    "The computer wins! Would you like to\n"
                            + "add it to the winner board?",
                    "Computer Victory!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, winnerIcon(), null, null);
            if (n == YES_OPTION) {
                saveScore();
            }
            this.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: takes player's turn
    private void playerTurn() {
        Dice d = new Dice();
        String name = board.players.get(1).getName();
        userDice(d, name);
        board.movePlayer(board.players.get(1), d);
        if (board.hitLadder(board.players.get(1))) {
            board.hitLadder(board.players.get(1));
            new PlaySound("src/main/sound/ladderSound.wav");
        } else if (board.hitSnake(board.players.get(1))) {
            board.hitSnake(board.players.get(1));
            new PlaySound("src/main/sound/snakeSound.wav");
        }
        bp.movePawns(board);
        if (order > 1) {
            turn = 2;
        }
    }

    // MODIFIES: this
    // EFFECTS: takes player's turn
    private void player2Turn() {
        Dice d = new Dice();
        String name = board.players.get(2).getName();
        userDice(d, name);
        board.movePlayer(board.players.get(2), d);
        if (board.hitLadder(board.players.get(2))) {
            board.hitLadder(board.players.get(2));
            new PlaySound("src/main/sound/ladderSound.wav");
        } else if (board.hitSnake(board.players.get(2))) {
            board.hitSnake(board.players.get(2));
            new PlaySound("src/main/sound/snakeSound.wav");
        }
        bp.movePawns(board);
        if (order > 2) {
            turn = 3;
        } else {
            turn = 1;
        }

    }

    // MODIFIES: this
    // EFFECTS: takes player's turn
    private void player3Turn() {
        Dice d = new Dice();
        String name = board.players.get(3).getName();
        userDice(d, name);
        board.movePlayer(board.players.get(3), d);
        if (board.hitLadder(board.players.get(3))) {
            board.hitLadder(board.players.get(3));
            new PlaySound("src/main/sound/ladderSound.wav");
        } else if (board.hitSnake(board.players.get(3))) {
            board.hitSnake(board.players.get(1));
            new PlaySound("src/main/sound/snakeSound.wav");
        }
        bp.movePawns(board);
        turn = 1;
    }

    // EFFECTS: displays victory message for player and displays option of adding winner
    public boolean playerVictory() {
        if (board.reachTop(board.getPlayers().get(1))) {
            String name = board.getPlayers().get(1).getName();
            bp.movePawns(board);
            new PlaySound("src/main/sound/win.wav");
            int n = JOptionPane.showOptionDialog(this,
                    name + " wins! Would you like to\n"
                            + "be added to the winner board?",
                    "Player Victory!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, winnerIcon(), null, null);
            if (n == YES_OPTION) {
                saveScore();
            }
            this.dispose();
            return true;
        }
        return false;
    }

    // EFFECTS: displays victory message for player and displays option of adding winner
    public boolean player2Victory() {
        if (board.reachTop(board.getPlayers().get(2))) {
            String name = board.getPlayers().get(2).getName();
            bp.movePawns(board);
            new PlaySound("src/main/sound/win.wav");
            int n = JOptionPane.showOptionDialog(this,
                    name + " wins! Would you like to\n"
                            + "be added to the winner board?",
                    "Player Victory!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, winnerIcon(), null, null);
            if (n == YES_OPTION) {
                saveScore();
            }
            this.dispose();
            return true;
        }
        return false;
    }

    // EFFECTS: displays victory message for player and displays option of adding winner
    public boolean player3Victory() {
        if (board.reachTop(board.getPlayers().get(3))) {
            String name = board.getPlayers().get(3).getName();
            bp.movePawns(board);
            new PlaySound("src/main/sound/win.wav");
            int n = JOptionPane.showOptionDialog(this,
                    name + " wins! Would you like to\n"
                            + "be added to the winner board?",
                    "Player Victory!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, winnerIcon(), null, null);
            if (n == YES_OPTION) {
                saveScore();
            }
            this.dispose();
            return true;
        }
        return false;
    }

    // EFFECTS: creates option pane displaying computer roll
    public void userDice(Dice d, String name) {
        JOptionPane.showMessageDialog(this,
                name + " rolled a " + d.rolled + ".",
                "User Roll:", JOptionPane.INFORMATION_MESSAGE, diceIcon());
    }

    // EFFECTS: creates option pane displaying computer roll
    public void computerDice(Dice d) {
        JOptionPane.showMessageDialog(this,
                "The computer rolled a " + d.rolled + ".",
                "Computer Roll:", JOptionPane.INFORMATION_MESSAGE, diceIcon());
    }

    // EFFECTS: saves the score to file
    private void saveScore() {
        try {
            score.addWinner(board.winner);
            jsonWriter.open();
            jsonWriter.write(score);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this,
                    board.winner.getName() + " has been added to the winner board!\n"
                            + "Saved " + score.getName() + " to " + JSON_STORE,
                    "Saved to File", JOptionPane.INFORMATION_MESSAGE, winnerIcon());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Citation: CPSC 210 JsonSpecializationDemo loadWorkRoom method
    // MODIFIES: this
    // EFFECTS: loads score from file and displays next menu
    private void getFile() {
        try {
            score = jsonReader.read();
            loaded = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // EFFECTS: Generates dice icon to show on option pane
    public ImageIcon diceIcon() {
        ImageIcon dice = new ImageIcon("./data/Dice.png");
        Image diceImage = dice.getImage();
        Image scaledDice = diceImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        dice = new ImageIcon(scaledDice);
        return dice;
    }

    // EFFECTS: Generates dice icon to show on option pane
    public ImageIcon winnerIcon() {
        ImageIcon winner = new ImageIcon("./data/winner.png");
        Image ribbonImage = winner.getImage();
        Image scaledRibbon = ribbonImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        winner = new ImageIcon(scaledRibbon);
        return winner;
    }

    // EFFECTS: creates snake icon
    public ImageIcon snakeIcon() {
        ImageIcon snakeIcon = new ImageIcon("./data/littleYellow.png");
        Image snakeImage = snakeIcon.getImage();
        Image smallSnake = snakeImage.getScaledInstance(100, 140, Image.SCALE_SMOOTH);
        snakeIcon = new ImageIcon(smallSnake);
        return snakeIcon;
    }

    // EFFECTS: makes player avatar
    public ImageIcon playerIcon() {
        ImageIcon playerIcon = new ImageIcon("./data/player.png");
        Image playerImage = playerIcon.getImage();
        Image scaledImage = playerImage.getScaledInstance(25, 40, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(scaledImage);
        return playerIcon;
    }

    // EFFECTS: makes player avatar
    public ImageIcon player2Icon() {
        ImageIcon playerIcon = new ImageIcon("./data/player4.png");
        Image playerImage = playerIcon.getImage();
        Image scaledImage = playerImage.getScaledInstance(25, 40, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(scaledImage);
        return playerIcon;
    }

    // EFFECTS: makes player avatar
    public ImageIcon player3Icon() {
        ImageIcon playerIcon = new ImageIcon("./data/player3.png");
        Image playerImage = playerIcon.getImage();
        Image scaledImage = playerImage.getScaledInstance(25, 40, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(scaledImage);
        return playerIcon;
    }

    // EFFECTS: makes player avatar
    public ImageIcon questionIcon() {
        ImageIcon icon = new ImageIcon("./data/questionMan.png");
        Image iconImage = icon.getImage();
        Image scaledImage = iconImage.getScaledInstance(55, 70, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        return icon;
    }


    // MODIFIES: this
    // EFFECTS: complete turns and update board when button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Roll")) {
            switch (turn) {
                case 1:
                    playerTurn();
                    if (!playerVictory() && order == 1) {
                        computerTurn();
                    }
                    break;
                case 2:
                    player2Turn();
                    if (!player2Victory() && order == 2) {
                        computerTurn();
                    }
                    break;
                case 3:
                    player3Turn();
                    if (!player3Victory()) {
                        computerTurn();
                    }
                    break;
            }
        }
    }


}
