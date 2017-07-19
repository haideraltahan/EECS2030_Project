package eecs2030.project.Models;

/**
 * Tile is a dot with coordinates on the game board. Immutable class object.
 * 
 * @author jianxiongwang
 *
 */
public class Tile {
	public final int x;
	public final int y;
	
	/**
	 * Constructor
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		Tile other = (Tile) obj;
		return this.x == other.x && this.y == other.y;
	}
	
	@Override
	public String toString() {
		return String.format("Tile x=%d y=%d",x,y);
	}
}
