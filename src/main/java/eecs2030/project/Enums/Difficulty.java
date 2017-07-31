package eecs2030.project.Enums;

/**
 * Represents the different levels of speed for the game loop.
 *
 * Created by Haider on 7/23/2017.
 */
public enum Difficulty {
    SLOW(140, 35, 3, 1),

    MEDIUM(100, 40, 4, 2),

    FAST(60, 45, 5, 3),

    EXTREME(20, 50, 6, 4);

    private final int timeInterval;
    private final int levelLength;
    private final int max_buffers;
    private final int score_multiplier;

    Difficulty(final int timeInterval, final int levelLength, final int max_buffers
    , final int score_multiplier) {
        this.timeInterval = timeInterval;
        this.levelLength = levelLength;
        this.max_buffers = max_buffers;
        this.score_multiplier = score_multiplier;
    }

    public int getScore_multiplier() {
        return score_multiplier;
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
    public Difficulty getNextLevel() {
        switch (this) {
            case SLOW: return MEDIUM;
            case MEDIUM: return FAST;
            case FAST: return EXTREME;
            default: return SLOW;
        }
    }

    public int getLevelLength() {
        return levelLength;
    }

    public int getMax_buffers() {
        return max_buffers;
    }
}