package eecs2030.project.Exceptions;

/**
 * A direction exception when user gives invalid KeyCode for Directions enum
 */
public class DirectionException extends RuntimeException {

    public DirectionException() {
        super("Invalid Direction KeyCode.");
    }

    public DirectionException(String message) {
        super(message);
    }
}
