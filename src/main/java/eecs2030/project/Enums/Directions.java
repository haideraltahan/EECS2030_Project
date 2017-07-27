package eecs2030.project.Enums;

import eecs2030.project.Utilities.DirectionException;

/**
 * A simple directions enum
 */
public enum Directions {
    // NORTH, EAST, SOUTH, WEST have the same value as arrow keys.
    NORTH(38), EAST(39), SOUTH(40), WEST(37);
    private final int value;
    Directions(final int v) {
        this.value = v;
    }
    public int getValue() {
        return this.value;
    }
    public static Directions getDirection(int v) throws Exception {
        switch(v) {
            case 37: return WEST;
            case 38: return NORTH;
            case 39: return EAST;
            case 40: return SOUTH;
        }
        throw new DirectionException();
    }
}