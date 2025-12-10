/**
 * @author Omeir Haroon 61810
 */
package types;

public class Game {
    Table table;
    int score;
    int moves;
    public static final String EOL = System.lineSeparator();

    /**
     * Constructor for the Game class.
     * 
     * @param symbols             An array of Filling objects to be used in the
     *                            game.
     * @param numberOfUsedSymbols The number of symbols to be used from the symbols
     *                            array.
     * @param seed                The seed for the random number generator.
     * @param capacity            The capacity of each bottle in the game.
     */
    public Game(Filling[] symbols, int numberOfUsedSymbols, int seed, int capacity) {
        table = new Table(symbols, numberOfUsedSymbols, seed, capacity);
    }

    /**
     * Constructor for the Game class with score.
     * 
     * @param symbols             An array of Filling objects to be used in the
     *                            game.
     * @param numberOfUsedSymbols The number of symbols to be used from the symbols
     *                            array.
     * @param seed                The seed for the random number generator.
     * @param capacity            The capacity of each bottle in the game.
     * @param score               The initial score of the game.
     */
    public Game(Filling[] symbols, int numberOfUsedSymbols, int seed, int capacity, int score) {
        table = new Table(symbols, numberOfUsedSymbols, seed, capacity);
        this.score = score;
    }

    /**
     * Provides help by adding a new bottle to the table and reducing the score.
     */
    public void provideHelp() {
        table.addBottle(getNewBottle());
        score -= 100;
    }

    /**
     * Returns the number of moves made in the game.
     * 
     * @return The number of moves made.
     */
    public int jogadas() {
        return this.moves;
    }

    /**
     * Updates the score based on the number of moves made and whether the round is
     * finished.
     */
    public void updateScore() {
        if (isRoundFinished()) {
            if (moves <= 10) {
                score += 1000;
            }
            if (moves > 10 && moves <= 15) {
                score += 500;
            }
            if (moves > 15 && moves <= 25) {
                score += 200;
            }
        }
    }

    /**
     * Checks if the round is finished.
     * 
     * @return True if the round is finished, false otherwise.
     */
    public boolean isRoundFinished() {
        return table.areAllFilled();
    }

    /**
     * Returns the current score of the game.
     * 
     * @return The current score.
     */
    public int score() {
        return score;
    }

    /**
     * Starts a new round by resetting the moves and regenerating the table.
     */
    public void startNewRound() {
        moves = 0;
        table.regenerateTable();
    }

    /**
     * Returns a new bottle.
     * 
     * @return A new bottle.
     */
    public Bottle getNewBottle() {
        return new Bottle();
    }

    /**
     * Returns a string representation of the game.
     * 
     * @return A string representation of the game.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Score: ").append(score() + EOL);
        sb.append(table.toString());
        sb.append("Status: ");
        if (isRoundFinished()) {
            sb.append("This round is finished." + EOL + jogadas() + " moves were used." + EOL);
        }
        else {
            sb.append("The round is not finished." + EOL + jogadas() + " moves have been used until now." + EOL);
        }
        return sb.toString();
    }

    /**
     * Makes a move in the game by pouring from one bottle to another.
     * 
     * @param i The index of the bottle to pour from.
     * @param j The index of the bottle to pour into.
     */
    public void play(int i, int j) {
        boolean poured = false;
        if ((table.singleFilling(i) && table.isFull(i))) {// se estiver a garrafa certinha porque mexer
            return;
        }
        while (i != j && !table.isEmpty(i) && (table.isEmpty(j) || table.top(i) == table.top(j) && !table.isFull(j))) {
            System.out.println("pouring...");
            poured = true;
            table.pourFromTo(i, j);
        }
        if (poured) {
            moves++;
        }
        updateScore();
    }
}