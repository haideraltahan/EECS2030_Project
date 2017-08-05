package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;

/**
 * A Buffer abstract class. It contains x,y coordinates.
 */
public abstract class Buffer extends Tile {

    /**
     * Constructora
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
        this(t.getX(), t.getY());
    }

    /**
     * Add this buffer to a snake.
     *
     * @param snake the snake
     */
    public abstract void addTo(Snake snake, Difficulty difficulty);

}
