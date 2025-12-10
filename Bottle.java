/**
 * @author Omeir Haroon 61810
 */
package types;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

public class Bottle implements Iterable<Filling> {
    public static final int DEFAULT_CAPACITY = 5;
    public static final String EMPTY = "⬜";
    public static final String EOL = System.lineSeparator();

    private Stack<Filling> contents;
    private int capacity;
    private int spaceAvailable;

    /**
     * Default constructor for the Bottle class.
     * Initializes the bottle with the default capacity.
     */
    public Bottle() {
        this.capacity = DEFAULT_CAPACITY;
        this.contents = new Stack<>();
        spaceAvailable = capacity;
    }

    /**
     * Constructor for the Bottle class with specified capacity.
     * 
     * @param capacity The capacity of the bottle.
     */
    public Bottle(int capacity) {
        this.capacity = capacity;
        this.contents = new Stack<>();
        spaceAvailable = capacity;
    }

    /**
     * Constructor for the Bottle class with specified content.
     * 
     * @param content An array of Filling objects to be used as the initial content of the bottle.
     */
    public Bottle(Filling[] content) {
        this.contents = new Stack<>();
        this.capacity = content.length;
        for (int i = capacity - 1; i >= 0; i--) {
            if (content[i] == null) {
                spaceAvailable++;
            }
            else {
                this.contents.push(content[i]);
            }
        }
    }

    /**
     * Checks if the bottle is full.
     * 
     * @return True if the bottle is full, false otherwise.
     */
    public boolean isFull() {
        return spaceAvailable == 0;
    }

    /**
     * Checks if the bottle is empty.
     * 
     * @return True if the bottle is empty, false otherwise.
     */
    public boolean isEmpty() {
        return spaceAvailable == capacity;
    }

    /**
     * Returns the top filling of the bottle.
     * 
     * @return The top filling of the bottle.
     * @throws EmptyStackException If the bottle is empty.
     */
    public Filling top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return contents.peek();
    }

    /**
     * Returns the available space in the bottle.
     * 
     * @return The available space.
     */
    public int spaceAvailable() {
        return spaceAvailable;
    }

    /**
     * Pours out the top filling of the bottle.
     */
    public void pourOut() {
        if (isEmpty()) {
            return;
        }
        contents.pop();
        spaceAvailable++;
    }

    /**
     * Receives a filling into the bottle.
     * 
     * @param s The filling to be received.
     * @return True if the filling was received, false otherwise.
     */
    public boolean receive(Filling s) {
        if (s != null && (isEmpty() || (top() == s && spaceAvailable > 0))) {
            contents.push(s);
            spaceAvailable--;
            return true;
        }
        return false;
    }

    /**
     * Returns the capacity of the bottle.
     * 
     * @return The capacity of the bottle.
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Returns the size of the bottle, i.e., the number of fillings in it.
     * 
     * @return The size of the bottle.
     */
    public int size() {
        return contents.size();
    }

    /**
     * Checks if all fillings in the bottle are the same.
     * 
     * @return True if all fillings are the same, false otherwise.
     */
    public boolean isSingleFilling() {
        if (contents.isEmpty()) {
            return true;
        }
        Filling firstFilling = top();
        for (Filling filling : contents) {
            if (filling != firstFilling) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the content of the bottle as an array of fillings.
     * 
     * @return An array of fillings representing the content of the bottle.
     */
    public Filling[] getContent() {
        return contents.toArray(new Filling[contents.size()]);
    }

    /**
     * Returns a string representation of the bottle.
     * 
     * @return A string representation of the bottle.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Filling[] temp = getContent();
        for (int i = 0; i < spaceAvailable; i++) {
            sb.append(EMPTY).append(EOL);
        }
        for (int i = size() - 1; i >= 0; i--) {
            sb.append(temp[i].toString() + EOL);
        }
        return sb.toString();
    }

    /**
     * Returns an iterator over the fillings in the bottle.
     * 
     * @return An Iterator.
     */
    @Override
    public Iterator<Filling> iterator() {
        return contents.iterator();
    }
}