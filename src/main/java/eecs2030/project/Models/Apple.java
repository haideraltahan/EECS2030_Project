package eecs2030.project.Models;

/**
 * Apple, a type of Buffer.
 * Snake will gain 5 points when eats it.
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
     * Snake will gain 5 points and increase 1-3 tiles
     *
     * @param snake
     */
    @Override
    public void addTo(Snake snake) {
        int n = (int) (Math.random() * 2) + 1;
        snake.addScore(n);
        snake.gains(n);
    }

    @Override
    public String toString() {
        return super.toString() + " contains Apple.";
    }
}
