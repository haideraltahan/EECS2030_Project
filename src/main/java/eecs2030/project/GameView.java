package eecs2030.project;

import com.sun.tools.internal.jxc.ap.Const;
import eecs2030.project.Models.*;
import eecs2030.project.Enums.*;
import eecs2030.project.Utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The main game view
 */
public class GameView extends JPanel {

    private final GameModel model;
    private Map<Directions, Image> snakeHeadImages = new HashMap<>();
    private Map<Class, Image> bufferImages = new HashMap<>();
    private Image snakeBodyImage;

    /**
     * Constructor
     *
     * @param model the game model instance
     */
    public GameView(GameModel model) {
        this.model = model;
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
        loadImages();
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
        this.bufferImages.put(GoldenApple.class, new ImageIcon(getClass().getResource("golden_apple.png")).getImage());
        this.bufferImages.put(PoisonedApple.class, new ImageIcon(getClass().getResource("poison.png")).getImage());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Draw the game based on current game state
     *
     * @param g The graphics
     */
    private void doDrawing(Graphics g) {

        if (model.isInGame()) {
            drawSnakeAndBuffers(g);
        } else {
            gameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /**
     * Draw Difficulty Level up Message
     *
     * @param difficulty new Difficulty
     */
    public void drawLevelUpMessage(Difficulty difficulty) {
        Graphics g = this.getGraphics();
        g.drawRect(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        Font large = new Font("Helvetica", Font.BOLD, 32);
        g.setColor(Color.white);
        String msg = "Congratulations!";
        g.setFont(large);
        int y = Constants.GAME_HEIGHT / 2 - 50;
        g.drawString(msg, this.calcMessageCenterPositionX(msg, large), y);

        msg = "New Level: " + difficulty;
        y = Constants.GAME_HEIGHT / 2;
        g.drawString(msg, this.calcMessageCenterPositionX(msg, large), y);
    }

    /**
     * Draw the apple and snake
     *
     * @param g The graphics
     */
    private void drawSnakeAndBuffers(Graphics g) {
        // draw buffers
        for (Buffer b : model.getBuffers()) {
            g.drawImage(this.bufferImages.get(b.getClass()), b.getX(), b.getY(), this);
        }
        // draw snake head
        Snake s = model.getSnake();
        Tile head = s.getHead();
        g.drawImage(this.snakeHeadImages.get(s.getDirection()), head.getX(), head.getY(), this);
        // draw snake body
        Iterator<Tile> iter = s.getBodyIterator();
        while (iter.hasNext()) {
            Tile tile = iter.next();
            g.drawImage(snakeBodyImage, tile.getX(), tile.getY(), this);
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
        int score = model.getSnake().getScore();
        msg = "Score: " + score;
        g.setFont(small);
        g.drawString(msg, this.calcMessageCenterPositionX(msg, medium), y += 40);
        msg = "R to restart or Q to quit";
        g.setFont(small);
        g.drawString(msg, this.calcMessageCenterPositionX(msg, small), y += 40);

    }

    /**
     * Get the center x coordinate for a given message and its font
     *
     * @param msg  a message
     * @param font message font
     * @return the x coordinate
     */
    private int calcMessageCenterPositionX(String msg, Font font) {
        FontMetrics metr = getFontMetrics(font);
        return (Constants.GAME_WIDTH - metr.stringWidth(msg)) / 2;
    }

}
