package eecs2030.project;

import eecs2030.project.Models.*;
import eecs2030.project.Utilities.Constants;
import eecs2030.project.Utilities.Constants.Directions;
import eecs2030.project.Utilities.Database;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener {

    private final int X_DOTS = Constants.GAME_WIDTH / Constants.DOT_SIZE;
    private final int Y_DOTS = Constants.GAME_HEIGHT / Constants.DOT_SIZE;
    private final int DELAY = 140;  // delay for timer
    private final int ADVANCED_BUFFER_CYCLE = 50;  // number of cycles for an advanced buffer to be located
    private final GameStatusBar gameBar;
    private final String playerName;

    private Snake snake;
    private List<Buffer> buffers = new ArrayList<>();
    private Class<?>[] bufferTypes = new Class[]{PoisonedApple.class};
    private Map<Directions, Image> snakeHeadImages = new HashMap<>();
    private Map<Class, Image> bufferImages = new HashMap<>();

    private boolean ableToSetDirection = true;
    private int cycleCounter = 0;  // number of game cycles
    private boolean inGame = true;
    private Timer timer;
    private Image snakeBodyImage;

    /**
     * Constructor for the Game
     */
    public Game(String playerName, GameStatusBar bar) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
        this.playerName = playerName;
        this.gameBar = bar;
        loadImages();
        initGame();
    }

    /**
     * Load/initiate necessary images
     */
    private void loadImages() {

    	snakeBodyImage = new ImageIcon(getClass().getResource("snakeimage.png")).getImage();
    	this.snakeHeadImages.put(Directions.NORTH, new ImageIcon(getClass().getResource("upmouth.png")).getImage());
        this.snakeHeadImages.put(Directions.SOUTH, new ImageIcon(getClass().getResource("downmouth.png")).getImage());
        this.snakeHeadImages.put(Directions.EAST, new ImageIcon(getClass().getResource("rightmouth.png")).getImage());
        this.snakeHeadImages.put(Directions.WEST, new ImageIcon(getClass().getResource("leftmouth.png")).getImage());
    	this.bufferImages.put(Apple.class, new ImageIcon(getClass().getResource("apple.png")).getImage());
        this.bufferImages.put(PoisonedApple.class, new ImageIcon(getClass().getResource("poision.png")).getImage());
    }

    /**
     * Initiate the game, reset fields
     */
    private void initGame() {
    	this.snake = new Snake();
    	this.gameBar.updateScoreLabel(0);
    	buffers.clear();
    	buffers.add(new Apple(this.getFreeTile()));
    	timer = new Timer(DELAY, this);
        timer.start();

        inGame = true;
        cycleCounter = 0;
        ableToSetDirection = true;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    /**
     * Draw the apple and snake
     * @param g The graphics
     */
    private void doDrawing(Graphics g) {

        if (inGame) {
        	// draw buffers
            for (Buffer b : buffers) {
                g.drawImage(this.bufferImages.get(b.getClass()), b.x, b.y, this);
            }
            // draw snake head
        	Tile head = snake.getHead();
            g.drawImage(this.snakeHeadImages.get(snake.getDirection()), head.x, head.y, this);
        	// draw snake body
        	Iterator<Tile> iter = snake.getBodyIterator();
        	while(iter.hasNext()) {
            	Tile tile = iter.next();
            	g.drawImage(snakeBodyImage, tile.x, tile.y, this);
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else {

            gameOver(g);
        }
    }

    /**
     * Game over. Screen shows game over, and instructions for restart and quit.
     * Score will be added to the database.
     * 
     * @param g The graphics
     */
    private void gameOver(Graphics g) {
    	Font large = new Font("Helvetica", Font.BOLD, 32);
    	Font medium = new Font("Helvetica", Font.PLAIN, 19);
        Font small = new Font("Helvetica", Font.PLAIN, 16);
        g.setColor(Color.white);
        
        String msg = "Game Over";
        g.setFont(large);
        int y = Constants.GAME_HEIGHT / 3;
        g.drawString(msg, this.calcMessageCenterPositionX(msg, large), y);
        int score = snake.getScore();
        msg = "Score: " + score;
        g.setFont(small);
        g.drawString(msg, this.calcMessageCenterPositionX(msg, medium), y+=40);
        msg = "R to restart or Q to quit";
        g.setFont(small);
        g.drawString(msg, this.calcMessageCenterPositionX(msg, small), y+=40);
        
        // Add score to database
        try {
        	Database.getInstance().addScore(new Score(this.playerName, score));
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
    
    /**
     * Get the center x coordinate for a given message and its font
     * 
     * @param msg a message
     * @param font message font
     * @return the x coordinate
     */
    private int calcMessageCenterPositionX(String msg, Font font) {
    	FontMetrics metr = getFontMetrics(font);
    	return (Constants.GAME_WIDTH - metr.stringWidth(msg))/2;
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
                this.gameBar.updateScoreLabel(snake.getScore());
                if (i == 0) locateApple();
                else buffers.remove(i);
            }
        }
    }

    /**
     * Get an unoccupied Tile
     *
     * @return an unoccupied TIle
     */
    private Tile getFreeTile() {
        Tile newTile;
        while(true) {
            newTile  = new Tile((((int) (Math.random() * (X_DOTS-1)) * Constants.DOT_SIZE)),(((int) (Math.random() * (Y_DOTS-1)) * Constants.DOT_SIZE)));
            for (Tile t : buffers) {
                if (t != null && t.equals(newTile)) continue;
            }
            if (snake.containsTile(newTile)) continue;
            break;
        }
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
        int n = (int)((this.bufferTypes.length-1) * Math.random());
        try {
            Class bufferClass = Class.forName(this.bufferTypes[n].getName());
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
			timer.stop();
		}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkBuffers();
            checkCollisions();
            snake.move();
            this.ableToSetDirection = true;
            if (++cycleCounter%this.ADVANCED_BUFFER_CYCLE == 0) {
                locateRandomBuffer();
                cycleCounter = 1; // set cycleCounter back to 1 prevent over flow.
            }
        }
        repaint();
    }

    /**
     * TAdapter manage all necessary keys operations for Game
     * 
     * @author jianxiongwang
     *
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
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
            } else {
            	if (key == KeyEvent.VK_R) initGame();
            	else if (key == KeyEvent.VK_Q) System.exit(0);
            }
        }
    }
}



