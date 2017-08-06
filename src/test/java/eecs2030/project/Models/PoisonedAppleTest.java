package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;
import org.junit.Test;

import static org.junit.Assert.*;

public class PoisonedAppleTest {
    //only 1 test is needed for addto because the same outcome happens reguardless of the difficulty (snake dies)
    @Test
    public void addTo() throws Exception {
        PoisonedApple pamodel = new PoisonedApple(2,5);
        Snake snake = new Snake();
        pamodel.addTo(snake, Difficulty.FAST);
        assertTrue(!(snake.isAlive()));
    }

    @Test
    public void toStringTest() throws Exception {
        PoisonedApple pamodel = new PoisonedApple(2,5);
        String expected = "Tile x=2 y=5 contains PoisonedApple.";
        String actual = pamodel.toString();
        assertEquals(expected, actual);
    }

}