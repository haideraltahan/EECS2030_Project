package eecs2030.project.Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {
    @Test
    public void getName() throws Exception {
        Score model = new Score("Ammiel", 3);
        String actual = model.getName();
        String expected = "Ammiel";
        assertEquals(expected, actual);
    }

    @Test
    public void getPoints() throws Exception {
        Score model = new Score("Ammiel", 3);
        int actual = model.getPoints();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void compareToZero() throws Exception {
        Score model1 = new Score("Ammiel", 3);
        Score model2 = new Score("Jafar", 3);
        int expected = 0;
        int actual = model1.compareTo(model2);
        assertEquals(expected, actual);
    }

    @Test
    public void compareToOne() throws Exception {
        Score model1 = new Score("Ammiel", 4);
        Score model2 = new Score("Jafar", 3);
        int expected = 1;
        int actual = model1.compareTo(model2);
        assertEquals(expected, actual);
    }

    @Test
    public void compareToNegOne() throws Exception {
        Score model1 = new Score("Ammiel", 2);
        Score model2 = new Score("Jafar", 3);
        int expected = -1;
        int actual = model1.compareTo(model2);
        assertEquals(expected, actual);
    }
}