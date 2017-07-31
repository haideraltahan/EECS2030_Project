package eecs2030.project.Utilities;

/**
 * Created by Haider on 7/4/2017.
 *
 * This class holds all constant values that can be used throughout the project's lifecycle.
 *
 */
public final class Constants {

    public static final String VERSION = "v1.0.0";
    public static final String GAME_TITLE = "Snake Game - EECS 2030 Project - " + VERSION ;
    public static final String FIREBASE_FILE_PATH = "service-account.json";
    public static final String FIREBASE_LINK = "https://snakegame-2a153.firebaseio.com/";
    public static final String DATABASE_MAIN_OBJECT = "Scores";
    public static final String DATABASE_ERROR_OBJECT = "Error";

    public static final int HEIGHT = 625;
    public static final int WIDTH = 800;
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 600;
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


}
