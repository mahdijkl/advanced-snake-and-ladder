package ir.ac.kntu.gamelogic;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private String[][] board;
    private Player player;
    private Dice dice;
    private int noWildSnake;
    private int noNormalSnake;
    private int noKindSnake;
    private boolean isWin = false;
    private boolean isLost = false;
    private Scanner scanner = new Scanner(System.in);
    private int input;
    private ArrayList<KindSnake> kindSnakes = new ArrayList<>();
    private ArrayList<NormalSnake> normalSnakes = new ArrayList<>();
    private ArrayList<WildSnake> wildSnakes = new ArrayList<>();
    private ArrayList<Position> sources = new ArrayList<>();
    private ArrayList<Position> destinies = new ArrayList<>();
    private final Position FINAL;

    public Game(int boardSize, int noNormalSnake, int noWildSnake, int noKindSnake, Player player, Dice dice) {
        this.board = new String[boardSize][boardSize];
        this.player = player;
        FINAL = new Position(board.length - 1, board.length - 1);
        sources.add(player.getPosition());
        destinies.add(player.getPosition());
        sources.add(FINAL);
        this.dice = dice;
        createKindSnakes(noKindSnake, boardSize);
        createNormalSnakes(noNormalSnake, boardSize);
        createWildSnakes(noWildSnake, boardSize);
    }

    public void createKindSnakes(int noKindSnake, int boardSize) {
        KindSnake kindSnake;
        for (int i = 0; i < noKindSnake; i++) {
            do {
                kindSnake = new KindSnake(boardSize);

            } while (hasThis(sources, kindSnake.getSource()) || hasThis(destinies, kindSnake.getSource())
                    || hasThis(sources, kindSnake.getDestiny()));
            kindSnakes.add(kindSnake);
            sources.add(kindSnake.getSource());
            destinies.add(kindSnake.getDestiny());
        }
    }

    public void createWildSnakes(int noWildSnake, int boardSize) {
        WildSnake wildSnake;
        for (int i = 0; i < noWildSnake; i++) {
            do {
                wildSnake = new WildSnake(boardSize);

            } while (hasThis(sources, wildSnake.getSource()) || hasThis(destinies, wildSnake.getSource())
                    || hasThis(sources, wildSnake.getDestiny()));
            wildSnakes.add(wildSnake);
            sources.add(wildSnake.getSource());
            destinies.add(wildSnake.getDestiny());

        }
    }

    public void createNormalSnakes(int noNormalSnake, int boardSize) {
        NormalSnake normalSnake;
        for (int i = 0; i < noNormalSnake; i++) {
            do {
                normalSnake = new NormalSnake(boardSize);

            } while (hasThis(sources, normalSnake.getSource()) || hasThis(destinies, normalSnake.getSource())
                    || hasThis(sources, normalSnake.getDestiny()));
            normalSnakes.add(normalSnake);
            sources.add(normalSnake.getSource());
            destinies.add(normalSnake.getDestiny());
        }
    }

    public void setBoard() {
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = board.length - 1; j >= 0; j--) {
                board[i][j] = "     ";
            }

        }

        board[board.length - 1][board.length - 1] = String.format("%5s", "$");
        board[0][0] = String.format("%5s", "*");

        for (KindSnake kindSnake : kindSnakes) {
            board[kindSnake.getSource().getLength()][kindSnake.getSource().getWidth()] = "SKS"
                    + String.format("%2d", kindSnake.getId());
            board[kindSnake.getDestiny().getLength()][kindSnake.getDestiny().getWidth()] = "DKS"
                    + String.format("%2d", kindSnake.getId());
        }
        for (WildSnake wildSnake : wildSnakes) {
            board[wildSnake.getSource().getLength()][wildSnake.getSource().getWidth()] = "SWS"
                    + String.format("%2d", wildSnake.getId());
            board[wildSnake.getDestiny().getLength()][wildSnake.getDestiny().getWidth()] = "DWS"
                    + String.format("%2d", wildSnake.getId());
        }
        for (NormalSnake normalSnake : normalSnakes) {
            board[normalSnake.getSource().getLength()][normalSnake.getSource().getWidth()] = "SNS"
                    + String.format("%2d", normalSnake.getId());
            board[normalSnake.getDestiny().getLength()][normalSnake.getDestiny().getWidth()] = "DNS"
                    + String.format("%2d", normalSnake.getId());
        }
        board[player.getPosition().getLength()][player.getPosition().getWidth()] = String.format("%5s", "P");
    }

    public void start() {
        setBoard();
        while (!(isWin || isLost)) {
            System.out.println("Enter 1 to drop the dice. ");
            System.out.println("Enter 0 to exit the game. ");
            input = scanner.nextInt();
            if (input == 0) {
                System.out.println("Exit game. ");
                break;
            }
            if (input == 1) {
                dice.drop();
                show();
                sources.remove(player.getPosition());
                player.move(dice.getState(), board.length);
                if (dice.getState().equals(Dice.Options.EXTRA_LIFE)) {
                    sources.add(player.getPosition());
                    continue;
                }
                setBoard();
                calculateTheTurn();
                setBoard();
                show();
                sources.add(player.getPosition());
                changeSnakesPositions();
            }
            setBoard();
        }
        if (isWin) {
            System.out.println("Player win the game. ");

        }
        if (isLost) {
            System.out.println("player lost the game. ");
        }
    }

    public void show() {
        System.out.println("Dice: " + dice.getState());
        System.out.println("Health: " + player.getLife());
        for (int i = board.length - 1; i >= 0; i--) {
            for (String str : board[i]) {
                System.out.print("|" + str + "|");
            }
            System.out.println("\n" + "-------".repeat(board.length));
        }
    }

    public void changeSnakesPositions() {
        for (KindSnake kindSnake : kindSnakes) {
            destinies.remove(kindSnake.getDestiny());

        }
        for (WildSnake wildSnake : wildSnakes) {
            destinies.remove(wildSnake.getDestiny());
            sources.remove(wildSnake.getSource());
        }
        for (NormalSnake normalSnake : normalSnakes) {
            destinies.remove(normalSnake.getDestiny());
        }
        for (WildSnake wildSnake : wildSnakes) {
            do {
                wildSnake.changePosition();

            } while (hasThis(sources, wildSnake.getSource()) || hasThis(destinies, wildSnake.getSource())
                    || hasThis(sources, wildSnake.getDestiny()));
            sources.add(wildSnake.getSource());
            destinies.add(wildSnake.getDestiny());

        }
        for (KindSnake kindSnake : kindSnakes) {
            do {
                kindSnake.changePosition();

            } while (hasThis(sources, kindSnake.getDestiny()));
            destinies.add(kindSnake.getDestiny());
        }

        for (NormalSnake normalSnake : normalSnakes) {
            do {
                normalSnake.changePosition();

            } while (hasThis(sources, normalSnake.getDestiny()));
            destinies.add(normalSnake.getDestiny());

        }
    }

    public void calculateTheTurn() {
        for (WildSnake wildSnake : wildSnakes) {
            if (wildSnake.getSource().equals(player.getPosition())) {
                player.setPosition(wildSnake.getDestiny());
                player.lostLife();
            }
        }
        for (NormalSnake normalSnake : normalSnakes) {
            if (normalSnake.getSource().equals(player.getPosition())) {
                player.setPosition(normalSnake.getDestiny());
            }
        }
        for (KindSnake kindSnake : kindSnakes) {
            if (kindSnake.getSource().equals(player.getPosition())) {
                player.setPosition(kindSnake.getDestiny());
            }
        }
        if (player.getPosition().equals(FINAL)) {
            isWin = true;
        }
        if (player.getLife() == 0) {
            isLost = true;
        }
    }

    public boolean hasThis(ArrayList<Position> positions, Position position) {
        for (Position point : positions) {
            if (position.equals(point)) {
                return true;
            }
        }
        return false;
    }

}
