package eecs2030.project.Models;

public final class Score implements Comparable<Score> {

    private final String name;
    private final int points;

    /**
     * Constructor
     *
     * @param name The name of the player
     * @param points The points of the player
     */
    public Score(String name, int points) {
        this.name = new String(name);
        this.points = new Integer(points);
    }

    /**
     * Constructor
     *
     * @param jsonSRC Parses the JSON representation of the player and points
     */
    public Score(String jsonSRC) {
        this.name = jsonSRC.substring(jsonSRC.indexOf("name=") + 5, jsonSRC.indexOf(','));
        this.points = Integer.parseInt(jsonSRC.substring(jsonSRC.indexOf("points=") + 7, jsonSRC.indexOf('}')));
    }

    /**
     * Returns the current player's name
     *
     * @return The current player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the current player's points
     *
     * @return The current player's points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Returns a negative integer, zero, or a positive integer if the provided score is less than, equal to, or greater
     * than the current points.
     *
     * @param score The score to be compared with the current points
     * @return -1, 0, or 1 if the provided score is less than, equal to, or greater than the current points.
     */
    @Override
    public int compareTo(Score score) {
        if (score == null)
            throw new IllegalArgumentException("null parameters");

        return Integer.compare(this.points, score.getPoints());
    }
}
