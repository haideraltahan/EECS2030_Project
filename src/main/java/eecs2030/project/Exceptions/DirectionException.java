package eecs2030.project.Exceptions;

/**
 * A direction exception when user gives invalid KeyCode for Directions enum
 */
public class DirectionException extends RuntimeException {

    /**
     * Default Constructor
     */
    public DirectionException() {
        super("Invalid Direction KeyCode.");
    }

    /**
     * Custom Constructor
     *
     * @param message Custom Exception Message
     */
    public DirectionException(String message) {
        super(message);
    }
}
