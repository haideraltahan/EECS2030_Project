package eecs2030.project;

import eecs2030.project.Models.Score;
import eecs2030.project.Models.Tile;
import eecs2030.project.Utilities.Constants;
import eecs2030.project.Utilities.Constants.Directions;
import eecs2030.project.Utilities.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener {

    private final int X_DOTS = Constants.GAME_WIDTH / Constants.DOT_SIZE;
    private final int Y_DOTS = Constants.GAME_HEIGHT / Constants.DOT_SIZE;
    private final int APPLE_SCORE = 5;
    
    // delay for timer
    private final int DELAY = 140;
    private boolean ableToSetDirection = true;

    private Snake snake; 
    private Tile apple;
    private final String playerName;
    
    private boolean inGame = true;
    
    private Timer timer;
    
    private final GameStatusBar gameBar;
        
    // Images
    private Image rightMouth;
    private Image leftMouth;
    private Image upMouth;
    private Image downMouth;
    private Image snakeImage;
    private Image appleImage;

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
    	rightMouth = new ImageIcon("src/main/resources/rightmouth.png").getImage();
    	leftMouth = new ImageIcon("src/main/resources/leftmouth.png").getImage();
    	upMouth = new ImageIcon("src/main/resources/upmouth.png").getImage();
    	downMouth = new ImageIcon("src/main/resources/downmouth.png").getImage();
    	snakeImage = new ImageIcon("src/main/resources/snakeimage.png").getImage();
    	appleImage = new ImageIcon("src/main/resources/apple.png").getImage();
    }

    /**
     * Initiate the game, reset fields
     */
    private void initGame() {
    	this.snake = new Snake();
    	this.gameBar.updateScoreLabel(0);
    	locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
        inGame = true;
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
        	// draw apple
            g.drawImage(appleImage, apple.x, apple.y, this);
            
            // draw snake
         // draw head
        	Tile head = snake.getHead();
        	switch (snake.getDirection()) {
        	case EAST: g.drawImage(rightMouth, head.x, head.y, this); break;
        	case WEST: g.drawImage(leftMouth, head.x, head.y, this); break;
        	case NORTH: g.drawImage(upMouth, head.x, head.y, this); break;
        	case SOUTH: g.drawImage(downMouth, head.x, head.y, this); break;
        	}
        	// draw body
        	Iterator<Tile> iter = snake.getBodyIterator();
        	while(iter.hasNext()) {
            	Tile tile = iter.next();
            	g.drawImage(snakeImage, tile.x, tile.y, this);
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
     * Check if snake eats an apple, if so locate a new apple.
     */
    private void checkApple() {
    	Tile head = snake.getHead();
		if (head.x == apple.x && head.y == apple.y) {
    		snake.gains((int) (Math.random() * 2) + 1);
    		snake.addScore(APPLE_SCORE);
    		this.gameBar.updateScoreLabel(snake.getScore());
    		locateApple();
    	}
    }
    
    /**
     * Randomize new apple location that is not contains in the snake body
     */
    private void locateApple() {
    	do {
    		apple = new Tile((((int) (Math.random() * (X_DOTS-1)) * Constants.DOT_SIZE)),(((int) (Math.random() * (Y_DOTS-1)) * Constants.DOT_SIZE)));
    	} while(snake.containsTile(apple)); 
    }

    /**
     * Check if snake hit itself or the walls.
     * Snake will be removed if collision happens and Game Over if all snakes die.
     */
    private void checkCollisions() {
    	Tile head = snake.getHead();
		if (snake.checkCollision() || head.y >= Constants.GAME_HEIGHT || head.y < 0 || head.x >= Constants.GAME_WIDTH || head.x < 0) {
			inGame = false;
			timer.stop();
		}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            snake.move();
            this.ableToSetDirection = true;
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



