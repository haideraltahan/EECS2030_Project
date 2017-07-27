package eecs2030.project.Enums;

/**
 * Represents the different levels of speed for the game loop.
 *
 * Created by Haider on 7/23/2017.
 */
public enum SpeedLevel {
    SLOW(140),

    MEDIUM(100),

    FAST(60),

    EXTREME(20);

    private int timeInterval;

    SpeedLevel(final int timeInterval) {
        this.timeInterval = timeInterval;
    }

    /**
     * Get the time interval of the speed level
     *
     * @return time interval of speed level
     */
    public int getTimeInterval() {
        return timeInterval;
    }

    /**
     * Get the next speed level, if it's extreme, go back to slow.
     *
     * @return next speed level
     */
    public SpeedLevel getNextLevel() {
        switch (this) {
            case SLOW: return MEDIUM;
            case MEDIUM: return FAST;
            case FAST: return EXTREME;
            default: return SLOW;
        }
    }

}