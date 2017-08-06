package eecs2030.project.Models;

import eecs2030.project.Utilities.Constants;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Table Model for the HighScore Table.
 */
@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {
    private Map<String, Score> scores = new LinkedHashMap<>();

    /**
     * Get the Column Header
     *
     * @param column the index of the header
     * @return Column Header
     */
    @Override
    public String getColumnName(int column) {
        return Constants.HIGHSCORES_HEADERS[column];
    }

    /**
     * Get the size of the list of scores
     *
     * @return the size of the list of scores
     */
    @Override
    public int getRowCount() {
        return scores.size();
    }

    /**
     * Get the number of Columns in the Table Model
     *
     * @return the number of Columns in the Table Model
     */
    @Override
    public int getColumnCount() {
        return Constants.HIGHSCORES_HEADERS.length;
    }

    /**
     *  Get the Value at a given column and row index
     *
     * @param rowIndex the row index of the value required
     * @param columnIndex the column index of the value required
     * @return the Value at a given column and row index
     */
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

    /**
     * Adds a new score in the linked hashmap if it is a new score and sorts the linked hashmap. FireTableDataChange
     * method is called
     *
     * @param key The key
     * @param packed The value
     */
    public void addScore(String key, String packed) {
        Score score = scores.get(key);
        if (score == null) {
            score = new Score(packed);
            scores.put(key, score);
            sortByValue();
        }
        this.fireTableDataChanged();
    }

    /**
     * Removes the key from the linked hashmap if it does not exist and calls the fireTableData method
     *
     * @param key The key to check in the linked hashmap
     */
    public void removeScore(String key) {
        Score point = scores.get(key);
        if (point != null) {
            scores.remove(key);
        }
        this.fireTableDataChanged();
    }

    /**
     *  Sorts the scores by value every time the table updates (new value is added to the database)
     */
    private void sortByValue() {
        List<Map.Entry<String, Score>> list = new LinkedList<>(this.scores.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));

        Map<String, Score> result = new LinkedHashMap<>();
        for (Iterator<Map.Entry<String, Score>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String , Score> entry = it.next();
            result.put(entry.getKey(), entry.getValue());
        }

        this.scores =  result;
    }

}
