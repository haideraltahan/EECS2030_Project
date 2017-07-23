package eecs2030.project.Models;

/**
 * A Buffer abstract class. It contains x,y coordinates.
 */
public abstract class Buffer extends Tile {

    /**
     * Constructor
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Buffer(int x, int y) {
        super(x,y);
    }


    /**
     * A copy constructor
     *
     * @param t a Tile
     */
    public Buffer(Tile t) {
        this(t.x, t.y);
    }

    /**
     * Add this buffer to a snake.
     *
     * @param snake the snake
     */
    public abstract void addTo(Snake snake);

}
