package eecs2030.project;

import eecs2030.project.Utilities.Constants;
import eecs2030.project.Utilities.Constants.Directions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener {

    private final int B_WIDTH = Constants.GAME_WIDTH;
    private final int B_HEIGHT = Constants.HEIGHT;
    private final int DOT_SIZE = 25;
    private final int X_DOTS = B_WIDTH / DOT_SIZE;
    private final int Y_DOTS = B_HEIGHT / DOT_SIZE;
    
    private final int DELAY = 140;

    private final LinkedList<Point> positions = new LinkedList<Point>();

    private int dots;
    private int apple_x;
    private int apple_y;

    private Directions direction = Directions.EAST;
    private boolean inGame = true;
    
    private Timer timer;
    
    // Images
    private Image rightMouth;
    private Image leftMouth;
    private Image upMouth;
    private Image downMouth;
    private Image snake;
    private Image apple;

    /**
     * Constructor for the Game
     */
    public Game() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
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
    	snake = new ImageIcon("src/main/resources/snakeimage.png").getImage();
    	apple = new ImageIcon("src/main/resources/apple.png").getImage();
    }

    /**
     * Initiate the game, reset fields
     */
    private void initGame() {
    	initSnakeDots();
    	locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
        inGame = true;
    }
    
    /**
     * Initiate the first three dots and direction for the snake
     */
    private void initSnakeDots() {
    	direction = Directions.EAST;
    	dots = 3;
    	positions.clear();
    	for (int i=0; i<dots; i++)
    		positions.add(new Point(25*(dots-i),100));
    	
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    /**
     * Draw the apple and snake
     * @param g
     */
    private void doDrawing(Graphics g) {

        if (inGame) {
        	// draw apple
            g.drawImage(apple, apple_x, apple_y, this);
            
            // draw snake
            Iterator<Point> iter = positions.iterator();
            Point head = iter.next();
            switch (direction) {
        	case EAST: g.drawImage(rightMouth, head.x, head.y, this); break;
        	case WEST: g.drawImage(leftMouth, head.x, head.y, this); break;
        	case NORTH: g.drawImage(upMouth, head.x, head.y, this); break;
        	case SOUTH: g.drawImage(downMouth, head.x, head.y, this); break;
        	}
            
            while(iter.hasNext()) {
            	Point tile = iter.next();
            	g.drawImage(snake, tile.x, tile.y, this);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else {

            gameOver(g);
        }
    }

    /**
     * Game over. Screen shows game over, and instructions for restart and quit.
     * @param g the Graphics
     */
    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font large = new Font("Helvetica", Font.BOLD, 32);
        FontMetrics metr = getFontMetrics(large);

        g.setColor(Color.white);
        g.setFont(large);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        
        String restart = "R to restart or Q to quit";
        Font small = new Font("Helvetica", Font.PLAIN, 16);
        metr = getFontMetrics(small);
        g.setFont(small);
        g.drawString(restart, (B_WIDTH - metr.stringWidth(restart)) / 2, B_HEIGHT / 2 + 50);
    }

    /**
     * Check if snake eats an apple, if so locate a new apple.
     */
    private void checkApple() {
    	Point head = positions.getFirst();
    	if (head.x == apple_x && head.y == apple_y) {
    		dots++;
    		locateApple();
    	}
    }
    
    /**
     * Randomize new apple location that is not contains in the snake body
     */
    private void locateApple() {
        do {
            apple_x = (((int) (Math.random() * (X_DOTS-1)) * DOT_SIZE));
            apple_y = (((int) (Math.random() * (Y_DOTS-1)) * DOT_SIZE));        	
        } while (positions.contains(new Point(apple_x, apple_y)));
    }

    /**
     * Update snake's positions
     */
    private void move() {
    	Point newHead = (Point) positions.getFirst().clone();
    	// remove last only if snake didn't eat an apple
    	if (positions.size() == dots)
    		positions.removeLast();
    	switch (direction) {
    	case WEST: newHead.setLocation(newHead.x-DOT_SIZE, newHead.y); break;
    	case EAST: newHead.setLocation(newHead.x+DOT_SIZE, newHead.y); break;
    	case NORTH: newHead.setLocation(newHead.x, newHead.y-DOT_SIZE); break;
    	case SOUTH: newHead.setLocation(newHead.x, newHead.y+DOT_SIZE); break;
    	}
    	positions.addFirst(newHead);
    }

    /**
     * Check if snake hit itself or the walls. Game Over if collision happens.
     */
    private void checkCollision() {
    	Point head = positions.getFirst();
    	// collision when head touches body or walls
    	if (positions.lastIndexOf(head) > 0 || head.y >= B_HEIGHT || head.y < 0 || head.x >= B_WIDTH || head.x < 0) {
    		inGame = false;
    		timer.stop();
    	}   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    /**
     * TAdapter manage all necessary keys operations
     * 
     * @author jianxiongwang
     *
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
            case KeyEvent.VK_LEFT:
            	if (direction != Directions.EAST)
            		direction = Directions.WEST;
            	break;
            case KeyEvent.VK_RIGHT: 
            	if (direction != Directions.WEST)
            		direction = Directions.EAST; 
            	break;
            case KeyEvent.VK_UP: 
            	if (direction != Directions.SOUTH)
            		direction = Directions.NORTH; 
            	break;
            case KeyEvent.VK_DOWN: 
            	if (direction != Directions.NORTH)
            		direction = Directions.SOUTH; 
            	break;
            case KeyEvent.VK_R:
            	if (!inGame)
            		initGame();
            	break;
            case KeyEvent.VK_Q:
            	System.exit(0);
            	break;
            }
        }
    }
}



