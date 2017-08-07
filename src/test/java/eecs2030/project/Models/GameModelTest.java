package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;
import eecs2030.project.Enums.Directions;
import eecs2030.project.Utilities.Constants;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameModelTest {
    @Test
    public void initGame() throws Exception {
        GameModel model = new GameModel("Player 1");
        model.getSnake().addScore(50);
        model.getSnake().gains(5);
        model.setDirection(Directions.SOUTH.getValue());
        model.setDifficulty(Difficulty.EXTREME);
        model.initGame(300);
        assertTrue(model.isInGame());
        assertTrue(model.getBuffers().size() == 1);
        assertTrue(model.getSnake().equals(new Snake(300)));
        assertEquals(model.getDifficulty(), Difficulty.EXTREME);
    }

    @Test
    public void isInGame() throws Exception {
        GameModel model = new GameModel("Player 1");
        assertEquals(true, model.isInGame());
    }

    @Test
    public void prepareNextMove() throws Exception {
        GameModel model = new GameModel("Player 1");
        while (model.getSnake().getHead().getX() < Constants.GAME_WIDTH) {
            assertTrue(model.isInGame());
            model.prepareNextMove();
        }
        model.prepareNextMove();
        assertFalse(model.isInGame());
        assertEquals(model.getSnake().getHead().getX(), Constants.GAME_WIDTH);
    }

    @Test
    public void setdirection() throws Exception {
        GameModel model = new GameModel("Player 1");
        model.prepareNextMove();
        model.setDirection(40);
        assertEquals(model.getSnake().getDirection(), Directions.SOUTH);
        model.prepareNextMove();
        model.setDirection(38);
        assertEquals(model.getSnake().getDirection(), Directions.SOUTH);
        model.setDirection(39);
        assertEquals(model.getSnake().getDirection(), Directions.EAST);
        model.prepareNextMove();
        model.setDirection(38);
        assertEquals(model.getSnake().getDirection(), Directions.NORTH);
        model.prepareNextMove();
        model.setDirection(37);
        assertEquals(model.getSnake().getDirection(), Directions.WEST);
        model.setDirection(40);
        assertEquals(model.getSnake().getDirection(), Directions.WEST);
        model.prepareNextMove();
        model.setDirection(39);
        assertEquals(model.getSnake().getDirection(), Directions.WEST);
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
        GameModel model = new GameModel("Player 1");
        model.upgradeDifficultyLevel();
        assertEquals(Difficulty.SLOW, model.getDifficulty());
        int n = Difficulty.SLOW.getLevelLength()-model.getSnake().getStarterLength();
        model.getSnake().gains(n);
        for (int i=0; i<n; i++) model.getSnake().move();
        model.upgradeDifficultyLevel();
        assertEquals(Difficulty.MEDIUM, model.getDifficulty());
        n = Difficulty.MEDIUM.getLevelLength()-model.getSnake().getStarterLength();
        model.getSnake().gains(n);
        for (int i=0; i<n; i++) model.getSnake().move();
        model.upgradeDifficultyLevel();
        assertEquals(Difficulty.FAST, model.getDifficulty());
        n = Difficulty.FAST.getLevelLength()-model.getSnake().getStarterLength();
        model.getSnake().gains(n);
        for (int i=0; i<n; i++) model.getSnake().move();
        model.upgradeDifficultyLevel();
        assertEquals(Difficulty.EXTREME, model.getDifficulty());
        n = Difficulty.EXTREME.getLevelLength()-model.getSnake().getStarterLength();
        model.getSnake().gains(n);
        for (int i=0; i<n; i++) model.getSnake().move();
        model.upgradeDifficultyLevel();
        assertEquals(Difficulty.SLOW, model.getDifficulty());
    }

    @Test
    public void ableToUpgradeDifficultyLevel() throws Exception {
        GameModel model = new GameModel("player 1");
        int n = Difficulty.SLOW.getLevelLength();
        while(model.getSnake().getStarterLength() < n) {
            assertFalse(model.ableToUpgradeDifficultyLevel());
            model.getSnake().gains(1);
            model.getSnake().move();
        }
        assertTrue(model.ableToUpgradeDifficultyLevel());

        model.setDifficulty(Difficulty.EXTREME);
        n = Difficulty.EXTREME.getLevelLength();
        while(model.getSnake().getStarterLength() < n) {
            assertFalse(model.ableToUpgradeDifficultyLevel());
            model.getSnake().gains(1);
            model.getSnake().move();
        }
        assertTrue(model.ableToUpgradeDifficultyLevel());
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

}