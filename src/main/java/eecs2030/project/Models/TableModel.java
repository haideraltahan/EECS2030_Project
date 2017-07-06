package eecs2030.project.Models;

import eecs2030.project.Utilities.Constants;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Created by Haider on 2017-07-06.
 * This class model point on the surface of the Earth.
 * It is immutable and multiton.
 */
public class TableModel extends AbstractTableModel {
    private Map<String, Score> scores = new LinkedHashMap<>();

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return Constants.HIGHSCORES_HEADER_NAME;
            case 1:
                return Constants.HIGHSCORES_HEADER_POINTS;
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return scores.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Set<Map.Entry<String, Score>> mapSet = scores.entrySet();
        Map.Entry<String, Score> elementAt5 = (new ArrayList<>(mapSet)).get((scores.size()-1) - rowIndex);

        Score score = elementAt5.getValue();
        switch (columnIndex){
            case 0:
                return score.getName();
            case 1:
                return score.getPoints();
        }
        return null;
    }

    public void addScore(String key, String name, int points) {
        Score score = scores.get(key);
        if (score == null) {
            score = new Score(name, points);
            scores.put(key, score);
        }
        this.fireTableDataChanged();
    }

    public void addScore(String key, String packed) {
        Score score = scores.get(key);
        if (score == null) {
            score = new Score(packed);
            scores.put(key, score);
            sortByValue();
        }
        this.fireTableDataChanged();
    }

    public void removeScore(String key) {
        Score point = scores.get(key);
        if (point != null) {
            scores.remove(key);
        }
        this.fireTableDataChanged();
    }

    private void sortByValue() {
        List<Map.Entry<String, Score>> list = new LinkedList<>(this.scores.entrySet());
        Collections.sort(list, (o1, o2) -> ((Comparable<Score>) o1.getValue()).compareTo(o2.getValue()));

        Map<String, Score> result = new LinkedHashMap<>();
        for (Iterator<Map.Entry<String, Score>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String , Score> entry = it.next();
            result.put(entry.getKey(), entry.getValue());
        }

        this.scores =  result;
    }

}
