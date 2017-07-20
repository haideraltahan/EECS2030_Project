package eecs2030.project.Utilities;

/**
 * Created by Haider on 7/4/2017.
 *
 * This class holds all constant values that can be used throughout the project's lifecycle.
 *
 */
public final class Constants {

    public static final String GAME_TITLE = "Snake Main";
    public static final String VERSION = "v0.0.1";
    public static final String FIREBASE_FILE_PATH = "service-account.json";
    public static final String FIREBASE_LINK = "https://snakegame-2a153.firebaseio.com/";
    public static final String DATABASE_MAIN_OBJECT = "Scores";
    public static final String DATABASE_ERROR_OBJECT = "Error";

    public static final int HEIGHT = 642;
    public static final int WIDTH = 572;
    public static final int GAME_WIDTH = 400;
    public static final int GAME_HEIGHT = 622;
    public static final int DOT_SIZE = 25;

    public static final String START_COMMAND = "start";
    public static final String EXIT_COMMAND = "exit";

    public static final int HORIZONTAL_PADDING = 10;
    public static final int VERTICAL_PADDING = 25;

    public static final String HIGHSCORES_HEADER_NAME = "Player Name";
    public static final String HIGHSCORES_HEADER_POINTS = "Points";


    //<----- Labels ------->
    public static final String START_GAME_BUTTON = "START GAME!";
    public static final String EXIT_GAME_BUTTON = "QUIT";
    public static final String HIGHSCORES_LABEL = "TOP 20 SCORES";
    public static final String PLAYER_NAME_LABEL = "Player Name:";

    //<----- Enums -------->
    public enum Directions {
    	// NORTH, EAST, SOUTH, WEST have the same value as arrow keys.
    	NORTH(38), EAST(39), SOUTH(40), WEST(37);
    	private final int value;
    	private Directions(int v) {
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
    		throw new Exception("No such direction.");
    	}
    }
}
