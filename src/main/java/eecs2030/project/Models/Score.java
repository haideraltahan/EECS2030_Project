package eecs2030.project.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haider on 7/4/2017.
 */
public final class Score {
    private static final Map<String, Integer> instances = new HashMap<>();

    public static void updateInstance(String name, int points) {
        String key = name;
        Integer point = instances.get(key);
        if (point == null || point < points) {
            point = points;
            instances.put(key, point);
        }
    }

    public static void removeInstance(String key) {
        Integer point = instances.get(key);
        if (point != null) {
            instances.remove(key);
        }
    }

    public static Map<String, Integer> getInstances() {
        return instances;
    }
}
