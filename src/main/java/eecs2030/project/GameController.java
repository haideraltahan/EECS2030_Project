package eecs2030.project;

import eecs2030.project.Enums.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A game controller contains the 2 views and 1 model.
 */
@SuppressWarnings("serial")
public class GameController extends JPanel implements ActionListener, Runnable{

    private GameView gameView;
    private GameStatusBar gameStatusBar;
    private GameModel gameModel;

    private Difficulty difficulty = Difficulty.SLOW;  // delay for timer
    private int levelLength = difficulty.getLevelLength();  // the snake length to upgrade the game difficulty level
    private Timer timer;


    /**
     * Constructor
     * @param playerName the player name
     *
     */
    public GameController(String playerName) {
        super(new BorderLayout());
        addKeyListener(new TAdapter());
        this.gameModel = new GameModel(playerName);
        this.gameStatusBar = new GameStatusBar(playerName);
        this.gameView = new GameView(this.gameModel);
    }

    @Override
    public void run() {
        this.add(this.gameStatusBar,BorderLayout.PAGE_START);
        this.add(this.gameView, BorderLayout.CENTER);
        resetTimer();
    }

    /**
     * Reset the timer and start the timer
     */
    void resetTimer() {
        if (this.timer != null) this.timer.stop();
        this.timer = new Timer(this.difficulty.getTimeInterval(), this);
        timer.start();
    }

    /**
     * Speed up to next level.
     */
    private void speedUp() {
        this.difficulty = this.difficulty.getNextLevel();
        this.levelLength = this.difficulty.getLevelLength();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameModel.isInGame()) {
            if (this.gameModel.getSnake().getLength() > levelLength) {
                // Game upgrade the speed into next level
                this.speedUp();
                int score = this.gameModel.getSnake().getScore();
                this.gameModel.initGame(difficulty);
                this.gameModel.getSnake().addScore(score);
                this.gameStatusBar.updateScoreLabel(score);
                resetTimer();
            } else {
                // Move the snake
                this.gameModel.prepareNextMove();
            }
        } else {
            // Game over
            this.timer.stop();
            this.gameModel.saveScoreToDatabase();
            this.difficulty = Difficulty.SLOW;
            this.levelLength = Difficulty.SLOW.getLevelLength();
        }
        this.gameView.repaint();
        this.gameStatusBar.updateScoreLabel(this.gameModel.getSnake().getScore());
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
            if (key == KeyEvent.VK_R) {
                gameModel.initGame(difficulty);
                resetTimer();
            }
            else if (key == KeyEvent.VK_Q) System.exit(0);
            else gameModel.setDirection(key);
        }
    }
}



