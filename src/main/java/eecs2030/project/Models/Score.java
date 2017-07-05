package eecs2030.project.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haider on 7/4/2017.
 */
public class Score {

    private int points;
    private String name;

    public Score(int points, String name) {
        this.points = points;
        this.name = name;
    }

    public int getPoints() {
        return this.points;
    }

    public String getName() {
        return this.name;
    }
}
