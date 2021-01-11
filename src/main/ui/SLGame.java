package ui;

import model.Board;
import model.Dice;
import model.Player;
import model.Score;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Represents the snakes and ladders game application
public class SLGame {
    private static final String JSON_STORE = "./data/score.json";
    private int turn = 1;
    private Board board;

    private Score score;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs score and runs game
    public SLGame() {
        score = new Score("Winners");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean go = true;
        String command = null;
        Scanner input = new Scanner(System.in);

        while (go) {
            display();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                go = false;
            } else {
                processStartCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: runs the game
    private void runGame() {
        boolean win = false;

        board = new Board();

        while (!win) {
            if (turn == 1) {
                win = playerTurn();
                turn = 2;
            } else {
                win = computerTurn();
                turn = 1;
            }

        }

    }

    // Citation: CPSC 210 JsonSerializationDemo WorkRoomApp displayMenu method
    // EFFECTS: displays options to user
    private void display() {
        System.out.println("\nSelect from:");
        System.out.println("\tl -> load previous scores from file");
        System.out.println("\tp -> print winners");
        System.out.println("\ts -> start the game");
        System.out.println("\tq -> quit");
    }

    public void processStartCommand(String userCommand) {
        if (userCommand.equals("l")) {
            loadScore();
        } else if (userCommand.equals("p")) {
            printWinners();
        } else if (userCommand.equals("s")) {
            runGame();
            addWinner();
            saveScore();
        } else {
            System.out.println("Invalid selection");
        }
    }

    // Citation: CPSC 210 JsonSpecializationDemo printThingies method
    // EFFECTS: prints all names of winning players in score
    private void printWinners() {
        List<Player> winners = score.getWinners();

        for (Player p : winners) {
            System.out.println(p.name);
        }
    }

    // Citation: CPSC 210 JsonSpecializationDemo loadWorkRoom method
    // MODIFIES: this
    // EFFECTS: loads score from file
    private void loadScore() {
        try {
            score = jsonReader.read();
            System.out.println("Loaded " + score.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the score to file
    private void saveScore() {
        try {
            jsonWriter.open();
            jsonWriter.write(score);
            jsonWriter.close();
            System.out.println("Saved " + score.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds winner to score
    private void addWinner() {
        score.addWinner(board.winner);
        System.out.println("Winner has been added to score board.");
    }


    // MODIFIES: this
    // EFFECTS: takes the player's turn
    private boolean playerTurn() {
        System.out.println("\nYour current position is: " + board.players.get(0).getPosition());
        System.out.println("\nPress \"d\" to roll the dice!");

        String command;
        Scanner input = new Scanner(System.in);
        command = input.next();

        Dice d = processCommand(command);

        System.out.println("You rolled a " + d.rolled);
        board.movePlayer(board.players.get(0), d);

        if (board.hitSnake(board.players.get(0))) {
            System.out.println("You have hit a snake. Your new position is " + board.players.get(0).getPosition());
        } else if (board.hitLadder(board.players.get(0))) {
            System.out.println("You have hit a ladder. Your new position is " + board.players.get(0).getPosition());
        } else if (board.reachTop(board.players.get(0))) {
            System.out.println("You have reached the top!\nCongratulations you win the game!\n\n");
            return true;
        } else {
            System.out.println("Your new position is: " + board.players.get(0).getPosition());
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private Dice processCommand(String command) {
        return new Dice();
    }

    // MODIFIES: this
    // EFFECTS: takes the computer's turn
    private boolean computerTurn() {
        System.out.println("\nThe computer's current position is: " + board.players.get(1).getPosition());
        Dice d = new Dice();
        System.out.println("The computer rolled a " + d.rolled);

        board.movePlayer(board.players.get(1), d);

        if (board.hitSnake(board.players.get(1))) {
            System.out.println("The computer hit a snake. Its new position is " + board.players.get(1).getPosition());
        } else if (board.hitLadder(board.players.get(1))) {
            System.out.println("The computer hit a ladder.Its new position is " + board.players.get(1).getPosition());
        } else if (board.reachTop(board.players.get(1))) {
            System.out.println("The computer reached the top!\nSorry you lost the game! :(\n\n");
            return true;
        } else {
            System.out.println("The computer's new position is: " + board.players.get(1).getPosition());
        }
        return false;
    }
}
