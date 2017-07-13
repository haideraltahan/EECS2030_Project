package eecs2030.project;

import eecs2030.project.Utilities.Constants;
import eecs2030.project.Utilities.Constants.Directions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Haider,Jason on 7/4/2017.
 *
 */
public class Game extends JPanel implements ActionListener {

    private final int B_WIDTH = Constants.GAME_WIDTH;
    private final int B_HEIGHT = Constants.HEIGHT;
    private final int DOT_SIZE = 25;
    private final int X_DOTS = B_WIDTH / DOT_SIZE;
    private final int Y_DOTS = B_HEIGHT / DOT_SIZE;
    private final int ALL_DOTS = X_DOTS * Y_DOTS;
    
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

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

    public Game() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
    	rightMouth = new ImageIcon("assets/rightmouth.png").getImage();
    	leftMouth = new ImageIcon("assets/leftmouth.png").getImage();
    	upMouth = new ImageIcon("assets/upmouth.png").getImage();
    	downMouth = new ImageIcon("assets/downmouth.png").getImage();
    	snake = new ImageIcon("assets/snakeimage.png").getImage();
    	apple = new ImageIcon("assets/apple.png").getImage();
    }

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
    	for (int i=0; i<dots; i++) {
    		x[i] = (dots-i)*50;
    		y[i] = 100;
    	}
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {

        if (inGame) {
        	// draw apple
            g.drawImage(apple, apple_x, apple_y, this);
            
            // draw snake
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                	switch (direction) {
                	case EAST: g.drawImage(rightMouth, x[z], y[z], this); break;
                	case WEST: g.drawImage(leftMouth, x[z], y[z], this); break;
                	case NORTH: g.drawImage(upMouth, x[z], y[z], this); break;
                	case SOUTH: g.drawImage(downMouth, x[z], y[z], this); break;
                	}
                } else {
                    g.drawImage(snake, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else {

            gameOver(g);
        }
    }

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

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
        	System.out.println("Got an apple");
            dots++;
            locateApple();
        }
    }
    
    private void locateApple() {
        int r = (int) (Math.random() * (X_DOTS-1));
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * (Y_DOTS-1));
        apple_y = ((r * DOT_SIZE));
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        
        
        switch (direction) {
    	case WEST: x[0] -= DOT_SIZE; break;
    	case EAST: x[0] += DOT_SIZE; break;
    	case NORTH: y[0] -= DOT_SIZE; break;
    	case SOUTH: y[0] += DOT_SIZE; break;
    	}

    }

    private void checkCollision() {

    	// collision when head touches body
        for (int z = dots-1; z > 3; z--) {
        	if (x[0] == x[z] && y[0] == y[z])
        		inGame = false;
        }
        
        // collision when head touches wall
        if (y[0] >= B_HEIGHT || y[0] < 0 || x[0] >= B_WIDTH || x[0] < 0)
            inGame = false;

        if (!inGame)
            timer.stop();
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



