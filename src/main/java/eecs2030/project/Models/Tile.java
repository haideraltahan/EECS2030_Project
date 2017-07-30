package eecs2030.project.Models;

/**
 * Tile is a dot with coordinates on the game board. Immutable class object.
 * 
 * @author jianxiongwang
 *
 */
public class Tile {
	private final int x;
	private final int y;

	/**
	 * Constructor
     *
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

    /**
     * Copy Constructor
     *
     * @param t another Tile
     */
	public Tile(Tile t) {
	    this.x = t.x;
	    this.y = t.y;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || !(obj instanceof Tile)) return false;
		Tile other = (Tile) obj;
		return this.x == other.x && this.y == other.y;
	}
	
	@Override
	public String toString() {
		return String.format("Tile x=%d y=%d",x,y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
