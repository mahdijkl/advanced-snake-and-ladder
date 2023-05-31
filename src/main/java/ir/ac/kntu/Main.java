package ir.ac.kntu;

import ir.ac.kntu.gamelogic.Dice;
import ir.ac.kntu.gamelogic.Game;
import ir.ac.kntu.gamelogic.Player;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of board : ");
        int boardSize = scanner.nextInt();
        System.out.println("Enter number of kind snakes : ");
        int noKindSnakes = scanner.nextInt();
        System.out.println("Enter number of normal snakes : ");
        int noNormalSnakes = scanner.nextInt();
        System.out.println("Enter number of wild snakes : ");
        int noWildSnakes = scanner.nextInt();
        Player player = new Player();
        Dice dice = new Dice();
        Game game = new Game(boardSize, noNormalSnakes, noWildSnakes, noKindSnakes, player, dice);
        game.start();
        scanner.close();

    }
}