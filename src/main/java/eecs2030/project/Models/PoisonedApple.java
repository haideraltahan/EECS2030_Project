package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;

/**
 * PoisonedApple, a simple poision to kill a snake.
 */
public class PoisonedApple extends Buffer {

    /**
     * Constructor
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public PoisonedApple(int x, int y) {
        super(x, y);
    }

    /**
     * Copy constructor
     *
     * @param t a Tile
     */
    public PoisonedApple(Tile t) {
        this(t.getX(), t.getY());
    }

    /**
     * Add buffer to snake
     * Snake will be killed and game over.
     *
     * @param snake
     */
    @Override
    public void addTo(Snake snake, Difficulty difficulty) {
        snake.setAlive(false);
    }

    @Override
    public String toString() {
        return super.toString() + " contains PoisonedApple.";
    }
}
