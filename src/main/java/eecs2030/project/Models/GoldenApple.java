package eecs2030.project.Models;

/**
 * GoldenApple
 * a type of Buffer.
 * Snake will gain 50 points when eats it.
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
     * Snake will gain 50 points
     *
     * @param snake
     */
    @Override
    public void addTo(Snake snake) {
        snake.addScore(50);
    }

    @Override
    public String toString() {
        return super.toString() + " contains Golden Apple.";
    }
}