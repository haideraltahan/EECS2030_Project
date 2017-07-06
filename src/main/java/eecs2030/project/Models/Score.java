package eecs2030.project.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haider on 7/4/2017.
 */
public final class Score implements Comparable<Score> {

    private final String name;
    private final int points;

    public Score(String name, int points) {
        this.name = new String(name);
        this.points = new Integer(points);
    }

    public Score(String jsonSRC) {
        this.name = jsonSRC.substring(jsonSRC.indexOf("name=") + 5, jsonSRC.indexOf(','));
        this.points = Integer.parseInt(jsonSRC.substring(jsonSRC.indexOf("points=") + 7, jsonSRC.indexOf('}')));
        ;
    }

    public String getName() {
        return new String(this.name);
    }

    public int getPoints() {
        return new Integer(this.points);
    }

    public Object[] toArrayString() {
        return new Object[]{ name, points};
    }


    @Override
    public int compareTo(Score score) {
        if (score == null)
            throw new IllegalArgumentException("null parameters");

        return Integer.compare(this.points, score.getPoints());
    }
}
