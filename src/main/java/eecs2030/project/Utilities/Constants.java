package eecs2030.project.Utilities;

import java.awt.*;

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

    public static final int HEIGHT = 600;
    public static final int WIDTH = 600;

    public static final String START_COMMAND = "start";
    public static final String EXIT_COMMAND = "exit";

    public static final int VERTICAL_PADDING = 25;

    public static final String HIGHSCORES_HEADER_NAME = "Player Name";
    public static final String HIGHSCORES_HEADER_POINTS = "Points";

    //<----- COLORS ------->
    public static final Color BACKGROUND_COLOR = new Color(0,209,73);

    //<----- Labels ------->
    public static final String START_GAME_BUTTON = "START GAME!";
    public static final String EXIT_GAME_BUTTON = "QUIT";
    public static final String HIGHSCORES_LABEL = "TOP 20 SCORES";
}
