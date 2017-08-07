package eecs2030.project.Models;

import eecs2030.project.Enums.Difficulty;
import eecs2030.project.Enums.Directions;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {
    @Test
    public void moveTailTest() throws Exception {
        //snake starts at tile x=100 y=75 and keeps moving to the right of the y axis in increments of 25
        //snake has length of 3, so last tile (at start) should be x=50 y=75
        Snake snake = new Snake();
        int n = (int)(Math.random()*10);
        Tile tile0 = new Tile(25*n + 125, 75);
        Tile tile1 = new Tile(25*n + 100, 75);
        Tile tile2 = new Tile(25*n + 75, 75);
        Tile tile3 = new Tile(25*n + 50, 75);
        Tile tile4 = new Tile(25*n + 25, 75);

        for(int i = 0 ; i < n ; i++){
            snake.move();
        }

        Boolean statement = (!snake.containsTile(tile0))&&snake.containsTile(tile1)&&snake.containsTile(tile2)&&snake.containsTile(tile3)&&!snake.containsTile(tile4);

        assertTrue(statement);
    }

    @Test
    public void moveDirectionTest() throws Exception {
        Snake snake = new Snake();
        double dir = Math.random()*4;
        int n = (int)(Math.random()*10 + 2);
        Tile expected = new Tile(100,75);

        for (int i =0 ; i < (n - 2) ; i++){
            System.out.println(snake.getHead());
            snake.move();
        }

        if (dir<=1&&dir>0){
            snake.setDirection(Directions.NORTH);
            snake.move();
            snake.move();
            expected = new Tile(100 +25*(n-2),25);
        }
        if (dir<=2&&dir>1){
            snake.setDirection(Directions.EAST);
            snake.move();
            snake.move();
            expected = new Tile(100 +25*(n),75);
        }
        if (dir<=3&&dir>2){
            snake.setDirection(Directions.SOUTH);
            snake.move();
            snake.move();
            expected = new Tile(100 +25*(n-2),125);
        }
        if (dir<=4&&dir>3){
            snake.setDirection(Directions.WEST);
            snake.move();
            snake.move();
            expected = new Tile(100 +25*(n-4),75);
        }
        snake.getHead();
        assertEquals(expected, snake.getHead());
    }

    @Test
    public void containsTile() throws Exception {
        //snake starts at tile x=100 y=75 and keeps moving to the right of the y axis in increments of 25
        //therefore, 3 movements should result in x=175 y=75
        Snake snake = new Snake();
        //Tile expected = new Tile(175,75);
        snake.move();
        snake.move();
        snake.move();
        Tile actual = snake.getHead();

        assertTrue(snake.containsTile(actual));
    }

    @Test
    public void containsFakeTile() throws Exception {
        //snake starts at tile x=100 y=75 and keeps moving to the right of the y axis in increments of 25
        //therefore, 3 movements should result in x=175 y=75
        Snake snake = new Snake();
        snake.move();
        snake.move();
        snake.move();
        Tile test = new Tile(200,75);

        assertTrue(!snake.containsTile(test));
    }

    @Test
    public void isAlive() throws Exception {
        Snake snake = new Snake();
        assertTrue(snake.isAlive());
    }

    @Test
    public void isNotAlive() throws Exception {
        Snake snake = new Snake();
        PoisonedApple apple = new PoisonedApple(2,3);
        apple.addTo(snake, Difficulty.FAST);
        assertTrue(!snake.isAlive());
    }

    @Test
    public void getDirectionNorth() throws Exception {
        Snake snake = new Snake();
        snake.setDirection(Directions.NORTH);
        eecs2030.project.Enums.Directions test = snake.getDirection();
        assertEquals(test, Directions.NORTH);
    }

    @Test
    public void getDirectionEast() throws Exception {
        Snake snake = new Snake();
        snake.setDirection(Directions.EAST);
        eecs2030.project.Enums.Directions test = snake.getDirection();
        assertEquals(test, Directions.EAST);
    }

    @Test
    public void getDirectionSouth() throws Exception {
        Snake snake = new Snake();
        snake.setDirection(Directions.SOUTH);
        eecs2030.project.Enums.Directions test = snake.getDirection();
        assertEquals(test, Directions.SOUTH);
    }

    @Test
    public void getDirectionWest() throws Exception {
        Snake snake = new Snake();
        snake.setDirection(Directions.WEST);
        eecs2030.project.Enums.Directions test = snake.getDirection();
        assertEquals(test, Directions.WEST);
    }

    @Test
    public void getHead() throws Exception {
        Snake snake = new Snake();
        double dir = Math.random()*4;
        int spaces = (int)(Math.random()*10);
        Tile expected = new Tile(0,0);
        //System.out.println(dir);
        //System.out.println(spaces);
        if (dir<=1&&dir>0){
            snake.setDirection(Directions.NORTH);
            for (int i=0;i<spaces ;i++){
                snake.move();
                //System.out.println(snake.getHead());
            }
            expected = new Tile(100, 75-(25*spaces));
        }
        if (dir<=2&&dir>1){
            snake.setDirection(Directions.EAST);
            for (int i=0;i<spaces ;i++){
                snake.move();
            }
            expected = new Tile(100+(25*spaces), 75);
        }
        if (dir<=3&&dir>2){
            snake.setDirection(Directions.SOUTH);
            for (int i=0;i<spaces ;i++){
                snake.move();
                //System.out.println(snake.getHead());
            }
            expected = new Tile(100, 75+(25*spaces));
        }
        if (dir<=4&&dir>3){
            snake.setDirection(Directions.WEST);
            for (int i=0;i<spaces ;i++){
                snake.move();
            }
            expected = new Tile(100-(25*spaces), 75);
        }
        assertEquals(expected, snake.getHead());
    }

    @Test
    public void getScore() throws Exception {
        Snake snake = new Snake();
        int n = (int)(Math.random()*100);
        snake.addScore(n);
        assertEquals(n,snake.getScore());
    }

    @Test
    public void getLength() throws Exception {
        Snake snake = new Snake();
        int n = (int)(Math.random()*11);
        int temp = snake.getStarterLength();
        int length;
        snake.gains(n);
        for(int i =0;i<n;i++){
            snake.move();
        }
        length = snake.getStarterLength() -temp ;
        assertEquals(length, n);
    }

    @Test
    public void getBodyIterator() throws Exception {
        Snake snake = new Snake();
        System.out.println(snake.getBodyIterator());
    }

    @Test
    public void setAlive() throws Exception {
        Snake snake = new Snake();
        snake.setAlive(false);
        assertTrue(!snake.isAlive());
    }

    @Test
    public void setDirection() throws Exception {
        Snake snake = new Snake();
        double dir = Math.random()*4;
        eecs2030.project.Enums.Directions expected = Directions.NORTH;
        if (dir<=1&&dir>0){
            snake.setDirection(Directions.NORTH);
            expected = Directions.NORTH;
        }
        if (dir<=2&&dir>1){
            snake.setDirection(Directions.EAST);
            expected = Directions.EAST;
        }
        if (dir<=3&&dir>2){
            snake.setDirection(Directions.SOUTH);
            expected = Directions.SOUTH;
        }
        if (dir<=4&&dir>3){
            snake.setDirection(Directions.WEST);
            expected = Directions.WEST;
        }
        assertEquals(expected, snake.getDirection());
    }

    @Test
    public void gains() throws Exception {
        Snake snake = new Snake();
        int n = (int)(Math.random()*10);
        int temp = snake.getStarterLength();
        int length;
        snake.gains(n);
        for(int i =0;i<n;i++){
            snake.move();
        }
        length = snake.getStarterLength() -temp ;
        assertEquals(length, n);
    }

    @Test
    public void addScore() throws Exception {
        Snake snake = new Snake();
        int n = (int)(Math.random()*100);
        snake.addScore(n);
        assertEquals(n,snake.getScore());
    }

    @Test
    public void addBuffer() throws Exception {
        Snake snake = new Snake();
        Buffer b = new Apple(0,0);
        for (int i=0; i<50; i++) {
            int n = snake.getScore();
            snake.addBuffer(b, Difficulty.SLOW);
            assertTrue(snake.getScore() > n && snake.getScore() < n+4);
            n = snake.getScore();
            snake.addBuffer(b, Difficulty.EXTREME);
            assertTrue(snake.getScore() >= n+Difficulty.EXTREME.getScoreMultiplier() && snake.getScore() < n+Difficulty.EXTREME.getScoreMultiplier()*3+1);
        }
    }

}