/**
 * @author Omeir Haroon 61810
 */
package types;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//clear after round0
public class Main {
    private static int level = 3;
    private static int score = 0;
    private static Game game;
    private static int seed;
    private static int numberOfUsedSymbols = 3;
    private static Filling[] symbols = Filling.values();
    private static Scanner scanner = new Scanner(System.in);
    private static int numberOfExtraBottles = 0;
    private static Random random = new Random();

    /**
     * The main method of the application.
     *
     * @param args command line arguments. Not used in this application.
     */
    public static void main(String[] args) {
        while (level <= 7) {
            seedGen();
            levelMessages(level);
            game = new Game(symbols, numberOfUsedSymbols, seed, level, score);
            if (level > 3) {
                System.out.println("LEVEL UP, YOU'RE ON LEVEL " + level);
            }
            playRoundsInLevel();
            prepareForNextLevel();
        }
        System.out.println("CONGRATS\nYOU HAVE WON THE GAME , now go study :)");
        scanner.close();
    }

    /**
     * This method handles the user's decision to play, seek help, or leave the
     * game.
     * 
     * @return 1 if the user chooses to play or declines help, -1 if the user
     *         chooses to leave, 0 if the user confirms they need help.
     */
    private static int checkIfPlayOrStupid() {
        int action;
        String certanty;
        int ret = 0;
        do {
            try {
                do {
                    System.out.print(
                            "CHOOSE YOUR FATE\n Are you ready for this level or do you need some guidance? [play(1),help(2),leave(0)]: ");
                    action = scanner.nextInt();

                } while (action != 1 && action != 2 && action != 0);
                if (action == 1) {
                    ret = 1;
                }
                else if (action == 0) {
                    ret = -1;
                }
                else {
                    do {
                        System.out.print("So you need some help? Alright, no judgment here,"
                                + "\n but only ONE time per LEVEL so be sure you want it now.\n[yes/no]: ");
                        certanty = scanner.nextLine().toUpperCase();
                    } while (!certanty.equals("YES") && !certanty.equals("NO"));
                    if (certanty.equals("NO")) {
                        ret = 1;
                    }
                    else {
                        game.provideHelp();
                        numberOfExtraBottles++;
                        System.out.println("I tried to warn you, now you lost some points :(");
                        System.out.println(game.toString());
                        ret = 0;
                    }
                }
                return ret;
            }
            catch (Exception e) {
                System.out.println("\nOOPSIE, please make sure your input is correct :) ");
            }
        } while (true);

    }

    /**
     * This method generates a random seed for the game.
     * 
     */
    private static void seedGen() {
        seed = random.nextInt(10000);
    }

    /**
     * This method controls the flow of rounds within a level.
     */
    private static void playRoundsInLevel() {
        int roundsInLevel = 1;
        if (roundsInLevel > 1) {
            game = new Game(symbols, numberOfUsedSymbols, seed, level, score);
        }
        while (roundsInLevel <= 3) {
            if (roundsInLevel > 1) {
                System.out.println("\n Starting new round...\n");
            }
            playMove(game);
            if (game.isRoundFinished()) {
                score = game.score();
                game.startNewRound();
                roundsInLevel++;
            }
        }
        clearScreen();
    }

    /**
     * This method controls the flow of a single move in the game.
     * 
     * @param game the current game instance.
     */
    private static void playMove(Game game) {
        System.out.println(game.toString());
        if (checkIfPlayOrStupid() == -1) {
            leaveGame();
        }
        int i = getBottleIndex("Enter the index of the bottle to pour from: ");
        int j = getBottleIndex("Enter the index of the bottle to pour into: ");
        game.play(i-1, j-1);
    }

    private static int getBottleIndex(String prompt) {
        int index = -1;
        int triesEnter = 0;
        do {
            try {
                if (triesEnter == 2) {
                    printConfusionMessage();
                }
                System.out.print(prompt);
                index = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
            triesEnter++;
        } while (index <= 0 || index > level + Table.DIFFICULTY + numberOfExtraBottles);
        return index;
    }

    private static void printConfusionMessage() {
        System.out
                .println("Is someone confused?\n Don't worry heres an example:\n" + " 1     2     3     4     5     6\n"
                        + "😃    😒    😡    ⬜    ⬜    ⬜\n" + "😒    😡    😡    ⬜    ⬜    ⬜\n"
                        + "😒    😒    😃    ⬜    ⬜    ⬜\n" + "😃    😡    😃    ⬜    ⬜    ⬜\n");
    }

    private static void prepareForNextLevel() {
        level++;
        numberOfUsedSymbols++;
    }

    private static void levelMessages(int level) {
        if (level == 3) {
            clearScreen();
            System.out.println(
                    "WELCOME\nassuming you know how the game works\nYou have 3 puzzles per level and the highest level is 7"
                            + "\nhelp on the first round of a level can be provided but not advised(USE YOUR BRAIN!!)"
                            + "\nYou're just getting started, good luck");
        }
        if (level == 5) {
            System.out.println("Now it gets interesting, start planning ahead for those points");
        }
        if (level == 6) {
            System.out.println("Hats of to you if you get any points from here on out");
        }
        if (level == 7) {
            System.out.println("TA TA TANNNNNNN");
        }
    }

    private static void leaveGame() {
        System.out.println("You have chosen to leave the game. Goodbye!");
        scanner.close();
        System.exit(0);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
