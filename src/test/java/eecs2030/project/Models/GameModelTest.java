package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;
import eecs2030.project.Enums.Directions;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameModelTest {
    @Test
    public void initGame() throws Exception {
    }

    @Test
    public void isInGame() throws Exception {
        GameModel model = new GameModel("Player 1");
        assertEquals(true, model.isInGame());
    }

    @Test
    public void prepareNextMove() throws Exception {
    }

    @Test
    public void setdirection() throws Exception {
        GameModel model = new GameModel("Player 1");
        double dir = Math.random()*4;
        eecs2030.project.Enums.Directions expected = Directions.NORTH;
        if (dir<=1&&dir>0){
            model.setDirection(39);
            expected = Directions.NORTH;
        }
        if (dir<=2&&dir>1){
            model.setDirection(40);
            expected = Directions.EAST;
        }
        if (dir<=3&&dir>2){
            model.setDirection(41);
            expected = Directions.SOUTH;
        }
        if (dir<=4&&dir>3){
            model.setDirection(42);
            expected = Directions.WEST;
        }
        assertEquals(expected, model.getSnake().getDirection());
    }

    @Test
    public void setDifficulty() throws Exception {
        GameModel model = new GameModel("Player 1");
        int n = (int)(Math.random()*4);
        eecs2030.project.Enums.Difficulty difficulty = Difficulty.MEDIUM;
        if (n<=1&&n>0){
            difficulty = Difficulty.SLOW;
        }
        if (n<=2&&n>1){
            difficulty = Difficulty.MEDIUM;
        }
        if (n<=3&&n>2){
            difficulty = Difficulty.FAST;
        }
        if (n<=4&&n>3) {
            difficulty = Difficulty.EXTREME;
        }

        model.setDifficulty(difficulty);
        assertEquals(difficulty, model.getDifficulty());
    }

    @Test
    public void upgradeDifficultyLevel() throws Exception {
    }

    @Test
    public void ableToUpgradeDifficultyLevel() throws Exception {
    }

    @Test
    public void getBuffers() throws Exception {
    }

    @Test
    public void getDifficulty() throws Exception {
        GameModel model = new GameModel("Player 1");
        int n = (int)(Math.random()*4);
        eecs2030.project.Enums.Difficulty difficulty = Difficulty.FAST;
        if (n<=1&&n>0){
            difficulty = Difficulty.SLOW;
        }
        if (n<=2&&n>1){
            difficulty = Difficulty.MEDIUM;
        }
        if (n<=3&&n>2){
            difficulty = Difficulty.FAST;
        }
        if (n<=4&&n>3) {
            difficulty = Difficulty.EXTREME;
        }

        model.setDifficulty(difficulty);
        assertEquals(difficulty, model.getDifficulty());
    }

    @Test
    public void getSnake() throws Exception {
        GameModel model = new GameModel("Player 1");
        Snake expected = new Snake(0);
        Snake actual = model.getSnake();

        assertTrue(actual.equals(expected));
    }

    @Test
    public void saveScoreToDatabase() throws Exception {
        GameModel model = new GameModel("Player 1");
    }

}