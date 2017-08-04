package eecs2030.project.Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TableModelTest {
    @Test
    public void getColumnNameOne() throws Exception {
        TableModel model = new TableModel();
        String expected = "Points";
        String actual = model.getColumnName(1);
        assertEquals(expected, actual);
    }

    @Test
    public void getColumnNameTwo() throws Exception {
        TableModel model = new TableModel();
        String expected = "Player Name";
        String actual = model.getColumnName(0);
        assertEquals(expected, actual);
    }

    @Test
    public void getRowCount() throws Exception {
        TableModel model = new TableModel();
        model.addScore("Ammiel", "{name=Ammiel,points=3}");
        int expected = 1;
        int actual = model.getRowCount();
        assertEquals(expected, actual);
    }

    @Test
    public void getColumnCount() throws Exception {
        TableModel model = new TableModel();
        int expected = 2;
        int actual = model.getColumnCount();
        assertEquals(expected, actual);
    }

    @Test
    public void getValueAtOne() throws Exception {
        TableModel model = new TableModel();
        model.addScore("Ammiel", "{name=Ammiel,points=3}");
        Object actual = model.getValueAt(0,0);
        String expected = "Ammiel";
        assertEquals(expected, actual);
    }

    @Test
    public void getValueAtTwo() throws Exception {
        TableModel model = new TableModel();
        model.addScore("Ammiel", "{name=Ammiel,points=3}");
        Object actual = model.getValueAt(0,1);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void addScore() throws Exception {
        TableModel model = new TableModel();
        model.addScore("Ammiel", "{name=Ammiel,points=3}");
        int actual = model.getRowCount();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void removeScore() throws Exception {
        TableModel model = new TableModel();
        Score sModel = new Score("Ammiel",3);
        model.addScore("Ammiel", "{name=Ammiel,points=3}");
        model.removeScore("Ammiel");
        int actual = model.getRowCount();
        int expected = 0;
        assertEquals(expected, actual);
    }

}