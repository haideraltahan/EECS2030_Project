package eecs2030.project.Models;

/**
 * Represents the different levels of speed for the game loop. Have not decided yet on the numbers.
 *
 * Created by Haider on 7/23/2017.
 */
public enum SpeedLevel {
    SLOW(2),

    MEDIUM(5),

    FAST(15),

    EXTRA(30);

    private int fps;

    private SpeedLevel(final int fps) {
        this.fps = fps;
    }

    public int getFps() {
        return fps;
    }

}