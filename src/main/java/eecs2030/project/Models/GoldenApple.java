package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;
import eecs2030.project.Utilities.Constants;

/**
 * GoldenApple
 * a type of Buffer.
 * Snake will gain 50 points times current difficulty level and increase its length by 2 times of current difficulty level
 */
public final class GoldenApple extends Buffer {

    /**
     * Constructor
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public GoldenApple(int x, int y) {
        super(x, y);
    }

    /**
     * Copy constructor
     *
     * @param t a Tile
     */
    public GoldenApple(Tile t) {
        this(t.getX(), t.getY());
    }

    /**
     * Add buffer to snake
     * Snake will gain 50 points times current difficulty level and increase its length by 2 times of current difficulty level
     *
     * @param snake the snake ates the buffer
     * @param difficulty current difficulty
     */
    @Override
    public void addTo(Snake snake, Difficulty difficulty) {
        snake.addScore(Constants.GOLDENAPPLE_SCORE * difficulty.getScoreMultiplier());
        snake.gains(difficulty.getScoreMultiplier() * 2);
    }

    @Override
    public String toString() {
        return super.toString() + " contains Golden Apple.";
    }
}