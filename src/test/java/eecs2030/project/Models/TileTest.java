package eecs2030.project.Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    @Test
    public void equalsSame() throws Exception {
        int x = 3+3;
        Tile Ttest = new Tile(6, 21);
        Tile Ttest2 = new Tile(x, 3*7);
        boolean result = Ttest.equals(Ttest2);
        assertTrue(result);
    }

    @Test
    public void equalsCopy() throws Exception {
        int xrnd= (int)(Math.random()*100);
        int yrnd= (int)(Math.random()*100);
        Tile Ttest = new Tile(xrnd, yrnd);
        Tile Ttest2 = Ttest;
        boolean result = Ttest.equals(Ttest2);
        assertTrue(result);
    }

    @Test
    public void equals404() throws Exception {
        int xrnd= (int)(Math.random()*100);
        int yrnd= (int)(Math.random()*100);
        Tile Ttest = new Tile(xrnd, yrnd);
        Tile Ttest2 = null;
        boolean result = Ttest.equals(Ttest2);
        assertTrue(!result);
    }

    @Test
    public void equalsDifferent() throws Exception {
        Tile Ttest = new Tile(6, 21);
        Tile Ttest2 = new Tile(5, 777);
        boolean result = Ttest.equals(Ttest2);
        assertTrue(!result);
    }

    @Test
    public void toStringTest() throws Exception {
        int xrnd= (int)(Math.random()*100);
        int yrnd= (int)(Math.random()*100);
        Tile Ttest = new Tile(xrnd, yrnd);
        String expected = "Tile x="+xrnd+" y="+yrnd;
        String actual = Ttest.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void getX() throws Exception {
        int xExpect= (int)(Math.random()*100);
        Tile Ttest = new Tile(xExpect, 1);
        int xActual = Ttest.getX();
        assertEquals(xActual, xExpect);
    }

    @Test
    public void getY() throws Exception {
        int yExpect= (int)(Math.random()*100);
        Tile Ttest = new Tile(4, yExpect);
        int yActual = Ttest.getY();
        assertEquals(yActual, yExpect);
    }

}