package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;
import eecs2030.project.Utilities.Constants;

/**
 * Apple, a type of Buffer.
 * Snake will gain a random number of points (difficulty * its random apple score) and
 * increase a number of tiles based on difficulty when eats it.
 */
public final class Apple extends Buffer {

    /**
     * Constructor
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Apple(int x, int y) {
        super(x, y);
    }

    /**
     * Copy constructor
     *
     * @param t a tile
     */
    public Apple(Tile t) {
        super(t);
    }

    /**
     * Add buffer to snake
     * Snake will gain a random number of points (difficulty * its random apple score) and increase a number of tiles based on difficulty
     *
     * @param snake The snake that ate the apple
     * @param difficulty current difficulty
     */
    @Override
    public void addTo(Snake snake, Difficulty difficulty) {
        int n = (int) (Math.random() * Constants.APPLE_MAX_SCORE) + Constants.APPLE_MIN_SCORE;
        snake.addScore(n * difficulty.getScoreMultiplier());
        snake.gains(difficulty.getScoreMultiplier());
    }

    @Override
    public String toString() {
        return super.toString() + " contains Apple.";
    }
}
