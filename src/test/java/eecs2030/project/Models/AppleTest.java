package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppleTest {
    @Test
    public void addToSlow() throws Exception {
        Apple amodel = new Apple(2,3);
        Snake snake = new Snake();
        int expectedGains = 1;
        int temp = snake.getStarterLength();

        amodel.addTo(snake,Difficulty.SLOW);
        //snake has to move for the length variable to be increased
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        boolean gains = false;
        boolean score = false;

        int actualScore = snake.getScore();
        int actualGains = snake.getStarterLength() - temp;

        if (actualScore>=(1*expectedGains)&&actualScore<=(3*expectedGains)){
            score = true;
        }
        if(expectedGains == actualGains){
            gains = true;
        }
        assertTrue(score&&gains);
    }

    @Test
    public void addToMedium() throws Exception {
        Apple amodel = new Apple(2,3);
        Snake snake = new Snake();
        int expectedGains = 2;
        int temp = snake.getStarterLength();

        amodel.addTo(snake,Difficulty.MEDIUM);
        //snake has to move for the length variable to be increased
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        boolean gains = false;
        boolean score = false;

        int actualScore = snake.getScore();
        int actualGains = snake.getStarterLength() - temp;

        if (actualScore>=(1*expectedGains)&&actualScore<=(3*expectedGains)){
            score = true;
        }
        if(expectedGains == actualGains){
            gains = true;
        }
        assertTrue(score&&gains);
    }

    @Test
    public void addToFast() throws Exception {
        Apple amodel = new Apple(2,3);
        Snake snake = new Snake();
        int expectedGains = 3;
        int temp = snake.getStarterLength();

        amodel.addTo(snake,Difficulty.FAST);
        //snake has to move for the length variable to be increased
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        boolean gains = false;
        boolean score = false;

        int actualScore = snake.getScore();
        int actualGains = snake.getStarterLength() - temp;

        if (actualScore>=(1*expectedGains)&&actualScore<=(3*expectedGains)){
            score = true;
        }
        if(expectedGains == actualGains){
            gains = true;
        }
        assertTrue(score&&gains);
    }

    @Test
    public void addToExtreme() throws Exception {
        Apple amodel = new Apple(2,3);
        Snake snake = new Snake();
        int expectedGains = 4;
        int temp = snake.getStarterLength();

        amodel.addTo(snake,Difficulty.EXTREME);
        //snake has to move for the length variable to be increased
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        boolean gains = false;
        boolean score = false;

        int actualScore = snake.getScore();
        int actualGains = snake.getStarterLength() - temp;

        if (actualScore>=(1*expectedGains)&&actualScore<=(3*expectedGains)){
            score = true;
        }
        if(expectedGains == actualGains){
            gains = true;
        }
        assertTrue(score&&gains);
    }

    //custom game setting not testable because difficulty constructor is private

    @Test
    public void toStringTest() throws Exception {
        Apple amodel = new Apple(20,100);
        String expected = "Tile x=20 y=100 contains Apple.";
        String actual = amodel.toString();
        assertEquals(expected, actual);
    }

}