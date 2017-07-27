package eecs2030.project;

import eecs2030.project.Models.*;
import eecs2030.project.Utilities.Constants;
import eecs2030.project.Utilities.Database;
import eecs2030.project.Enums.Directions;

import java.util.ArrayList;
import java.util.List;

/**
 * A Snake Game Model
 */
public class GameModel {

    private final int X_DOTS = Constants.GAME_WIDTH / Constants.DOT_SIZE;
    private final int Y_DOTS = Constants.GAME_HEIGHT / Constants.DOT_SIZE;
    private final int ADVANCED_BUFFER_CYCLE = 50;  // number of cycles for an advanced buffer to be located
    private final int MAXIMUM_BUFFERS = 5;  // number of maximum buffers that can be present on the board
    private final String playerName;

    private Snake snake;
    private List<Buffer> buffers = new ArrayList<>();
    private Class<?>[] bufferTypes = new Class[]{GoldenApple.class, PoisonedApple.class};
    private boolean ableToSetDirection = true;
    private int cycleCounter = 0;  // number of game cycles
    private boolean inGame = true;


    /**
     * Constructor
     *
     * @param playerName player name
     */
    public GameModel(String playerName) {
        this.playerName = playerName;
        initGame();
    }

    /**
     * Initiate the game, reset fields
     */
    public void initGame() {
        this.snake = new Snake();
        buffers.clear();
        buffers.add(new Apple(this.getFreeTile()));
        inGame = true;
        cycleCounter = 0;
        ableToSetDirection = true;
    }

    /**
     * Get an unoccupied Tile
     *
     * @return an unoccupied TIle
     */
    private Tile getFreeTile() {
        Tile newTile;
        boolean invalid = true;
        do {
            newTile  = new Tile((((int) (Math.random() * (X_DOTS-1)) * Constants.DOT_SIZE)),(((int) (Math.random() * (Y_DOTS-1)) * Constants.DOT_SIZE)));
            if (snake.containsTile(newTile)) continue;
            invalid = false;
            for (Tile t : buffers) {
                if (t != null && t.equals(newTile)) {
                    invalid = true;
                    break;
                }
            }
        } while(invalid);
        return newTile;
    }

    /**
     * Locate a new Apple
     */
    private void locateApple() {
        this.buffers.set(0, new Apple(getFreeTile()));
    }

    /**
     * Locate a buffer to buffers exclude Apple.
     */
    private void locateRandomBuffer() {
        Tile newTile = getFreeTile();
        int n = Math.random() < 0.1 ? 0 : 1;
        try {
            Class bufferClass = Class.forName(this.bufferTypes[n].getName());
            if (this.buffers.size() == MAXIMUM_BUFFERS) this.buffers.remove(1);
            this.buffers.add((Buffer) bufferClass.getDeclaredConstructor(Tile.class).newInstance(newTile));
        } catch (Exception e) {
            System.out.println("Locate buffer failed.");
        }
    }

    /**
     * Check if snake hit itself or the walls.
     * Snake will be removed if collision happens and Game Over if all snakes die.
     */
    private void checkCollisions() {
        Tile head = snake.getHead();
        if (!snake.isAlive() || head.y >= Constants.GAME_HEIGHT || head.y < 0 || head.x >= Constants.GAME_WIDTH || head.x < 0) {
            inGame = false;
        }
    }

    /**
     * Check if snake eats any buffers, if it's an apple, replace a new apple, else remove.
     */
    private void checkBuffers() {
    	Tile head = snake.getHead();
    	for (int i=0; i<buffers.size(); i++) {
    	    Buffer b = buffers.get(i);
    	    if (head.equals(b)) {
    	        snake.addBuffer(b);
                if (i == 0) locateApple();
                else buffers.remove(i);
            }
        }
    }


    /**
     * Check if it's in Game or not
     * @return true if inGame false otherwise
     */
    public boolean isInGame() {
        return this.inGame;
    }

    /**
     * Prepare for next move
     */
    public void prepareNextMove() {
        if (this.isInGame()) {
            checkBuffers();
            checkCollisions();
            snake.move();
            this.ableToSetDirection = true;
            if (++cycleCounter%this.ADVANCED_BUFFER_CYCLE == 0) {
                locateRandomBuffer();
                cycleCounter = 1; // set cycleCounter back to 1 prevent over flow.
            }
        } else {

        }
    }

    /**
     * Set the direction to snake, every move can only set one valid direction
     *
     * @param key keycode of the directions
     */
    public void setDirection(int key) {
        if (inGame && ableToSetDirection) {
            try {
                Directions direction = snake.getDirection();
                Directions newDirection = Directions.getDirection(key);
                if (direction.getValue() % 2 != newDirection.getValue() % 2) {
                    snake.setDirection(newDirection);
                    ableToSetDirection = false;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Get the list of buffers
     *
     * @return list of buffers
     */
    public List<Buffer> getBuffers() {
        return this.buffers;
    }

    /**
     * Get the snake
     * @return the snake
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Save the score to database
     */
    public void saveScoreToDatabase() {
        try {
            Database.getInstance().addScore(new Score(this.playerName, this.getSnake().getScore()));
        } catch (Exception e) {
            System.out.println("Error to save score to Database: " + e.getMessage());
        }

    }

}
