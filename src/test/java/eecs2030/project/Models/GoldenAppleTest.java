package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;
import eecs2030.project.Enums.Directions;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoldenAppleTest {
    @Test
    public void addToSlow() throws Exception {
        GoldenApple gamodel = new GoldenApple(4,7);
        Snake snake = new Snake();
        int expectedGains = 1;
        int temp = snake.getStarterLength();

        gamodel.addTo(snake, Difficulty.SLOW);
        for (int i =0; i<50;i++){
            snake.move();
            if (i==20){
                snake.setDirection(Directions.NORTH);
            }
            if (i==40){
                snake.setDirection(Directions.WEST);
            }
        }

        boolean gains = false;
        boolean score = false;

        int actualScore = snake.getScore();
        int actualGains = snake.getStarterLength() - temp;

        if (50*expectedGains==actualScore){
            score = true;
        }
        if(expectedGains*2 == actualGains){
            gains = true;
        }
        assertTrue(score&&gains);
    }

    @Test
    public void addToMedium() throws Exception {
        GoldenApple gamodel = new GoldenApple(4,7);
        Snake snake = new Snake();
        int expectedGains = 2;
        int temp = snake.getStarterLength();

        gamodel.addTo(snake,Difficulty.MEDIUM);

        for (int i =0; i<50;i++){
            snake.move();
            if (i==20){
                snake.setDirection(Directions.NORTH);
            }
            if (i==40){
                snake.setDirection(Directions.WEST);
            }
        }
        System.out.println(snake.isAlive());
        boolean gains = false;
        boolean score = false;

        int actualScore = snake.getScore();
        int actualGains = snake.getStarterLength() - temp;

        if (50*expectedGains==actualScore){
            score = true;
        }
        if(expectedGains*2 == actualGains){
            gains = true;
        }
        assertTrue(score&&gains);
    }

    @Test
    public void addToFast() throws Exception {
        GoldenApple gamodel = new GoldenApple(4,7);
        Snake snake = new Snake();
        int expectedGains = 3;
        int temp = snake.getStarterLength();

        gamodel.addTo(snake,Difficulty.FAST);

        for (int i =0; i<50;i++){
            snake.move();
            if (i==20){
                snake.setDirection(Directions.NORTH);
            }
            if (i==40){
                snake.setDirection(Directions.WEST);
            }
        }

        boolean gains = false;
        boolean score = false;

        int actualScore = snake.getScore();
        int actualGains = snake.getStarterLength() - temp;

        if (50*expectedGains==actualScore){
            score = true;
        }
        if(expectedGains*2 == actualGains){
            gains = true;
        }
        assertTrue(score&&gains);
    }

    @Test
    public void addToExtreme() throws Exception {
        GoldenApple gamodel = new GoldenApple(4,7);
        Snake snake = new Snake();
        int expectedGains = 4;
        int temp = snake.getStarterLength();

        gamodel.addTo(snake,Difficulty.EXTREME);

        for (int i =0; i<50;i++){
            snake.move();
            if (i==20){
                snake.setDirection(Directions.NORTH);
            }
            if (i==40){
                snake.setDirection(Directions.WEST);
            }
        }

        boolean gains = false;
        boolean score = false;

        int actualScore = snake.getScore();
        int actualGains = snake.getStarterLength() - temp;

        if (50*expectedGains==actualScore){
            score = true;
        }
        if(expectedGains*2 == actualGains){
            gains = true;
        }
        assertTrue(score&&gains);
    }

    //custom game setting not testable because difficulty constructor is private


    @Test
    public void toStringTest() throws Exception {
        GoldenApple gamodel = new GoldenApple(8,42);
        String expected = "Tile x=8 y=42 contains Golden Apple.";
        String actual = gamodel.toString();
        assertEquals(expected, actual);
    }

}