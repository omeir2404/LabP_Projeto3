/**
 * @author Omeir Haroon 61810
 */
package types;

import java.util.Arrays;
import java.util.Random;

public class Table {
    public static final String EMPTY = "⬜";
    public static final String EOL = System.lineSeparator();
    public static final int DIFFICULTY = 3;
    public static final int DEFAULT_BOTTLE_CAPACITY = 5;

    private Bottle[] bottles;
    private int numSymbols;
    private int capacity;
    private Filling[] symbols;
    private Random random;

    /**
     * Constructor for the Table class.
     * 
     * @param symbols             An array of Filling objects to be used in the
     *                            table.
     * @param numberOfUsedSymbols The number of symbols to be used from the symbols
     *                            array.
     * @param seed                The seed for the random number generator.
     * @param capacity            The capacity of each bottle in the table.
     */
    public Table(Filling[] symbols, int numberOfUsedSymbols, int seed, int capacity) {
        this.symbols = symbols;
        numSymbols = Math.min(numberOfUsedSymbols, symbols.length);
        bottles = new Bottle[numSymbols + DIFFICULTY];
        this.random = new Random(seed);
        this.capacity = capacity;
        fillBottles();
    }

    /**
     * Regenerates the table by refilling all the bottles.
     */
    public void regenerateTable() {
        fillBottles();
    }

    /**
     * Fills all the bottles in the table.
     */
    private void fillBottles() {
        int[] howManyTimesUsed = new int[numSymbols];
        for (int i = bottles.length - DIFFICULTY; i < bottles.length; i++) {
            bottles[i] = new Bottle(capacity);
        }
        for (int i = 0; i < bottles.length - DIFFICULTY; i++) {
            Filling[] content = new Filling[capacity];
            for (int j = capacity - 1; j >= 0; j--) {
                int randomIndex = random.nextInt(numSymbols);
                while (howManyTimesUsed[randomIndex] >= capacity) {
                    randomIndex = random.nextInt(numSymbols);
                }
                content[j] = symbols[randomIndex];
                howManyTimesUsed[randomIndex]++;
            }
            bottles[i] = new Bottle(content);
        }
    }

    /**
     * Checks if a bottle at a given index has a single type of filling.
     * 
     * @param i The index of the bottle.
     * @return True if the bottle has a single type of filling, false otherwise.
     */
    public boolean singleFilling(int i) {
        return bottles[i].isSingleFilling();
    }

    /**
     * Checks if a bottle at a given index is empty.
     * 
     * @param i The index of the bottle.
     * @return True if the bottle is empty, false otherwise.
     */
    public boolean isEmpty(int i) {
        return bottles[i].isEmpty();
    }

    /**
     * Checks if a bottle at a given index is full.
     * 
     * @param i The index of the bottle.
     * @return True if the bottle is full, false otherwise.
     */
    public boolean isFull(int i) {
        return bottles[i].isFull();
    }

    /**
     * Checks if all non-empty bottles in the table are fully filled with a single
     * type of content.
     * 
     * @return True if all non-empty bottles are fully filled with a single type of
     *         content, false otherwise.
     */
    public boolean areAllFilled() {
        for (int i = 0; i < bottles.length; i++) {
            if (!isFull(i) && !isEmpty(i) || !singleFilling(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Pours content from one bottle to another.
     * 
     * @param i The index of the bottle to pour from.
     * @param j The index of the bottle to pour into.
     */
    public void pourFromTo(int i, int j) {
        if (!bottles[i].isEmpty() && i != j) {
            Filling looking = bottles[i].top();
            if (bottles[j].receive(looking)) {
                bottles[i].pourOut();
            }
        }
    }

    /**
     * Adds a new bottle to the table.
     * 
     * @param bottle The bottle to be added.
     */
    public void addBottle(Bottle bottle) {
        bottles = Arrays.copyOf(bottles, bottles.length + 1);
        bottles[bottles.length - 1] = bottle;
    }

    /**
     * Returns the capacity of the bottles in the table.
     * 
     * @return The capacity of the bottles.
     */
    public int getSizeBottles() {
        return capacity;
    }

    /**
     * Returns the top filling of a bottle at a given index.
     * 
     * @param i The index of the bottle.
     * @return The top filling of the bottle.
     */
    public Filling top(int i) {
        return bottles[i].top();
    }

    /**
     * Returns a string representation of the table.
     * 
     * @return A string representation of the table.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = capacity - 1; i >= 0; i--) {
            for (int j = 0; j < bottles.length; j++) {
                Filling temp = null;
                if (i < bottles[j].size()) {
                    temp = bottles[j].getContent()[i];
                }
                if (temp != null) {
                    sb.append(temp);
                }
                else {
                    sb.append(EMPTY);
                }
                sb.append("    ");
            }
            sb.append(EOL);
        }
        return sb.toString();
    }
}