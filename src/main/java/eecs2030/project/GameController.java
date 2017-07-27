package eecs2030.project;

import eecs2030.project.Enums.SpeedLevel;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;

/**
 * A game controller contains the 2 views and 1 model.
 */
@SuppressWarnings("serial")
public class GameController extends JPanel implements ActionListener {

    private GameView gameView;
    private GameStatusBar gameStatusBar;
    private GameModel gameModel;

    private int speedUpLength = 20; // the snake length to upgrade the game difficulty level
    private SpeedLevel speedLevel = SpeedLevel.SLOW;  // delay for timer
    private Timer timer;


    /**
     * Constructor
     * @param playerName the player name
     * @param sender parent panel
     */
    public GameController(String playerName, JPanel sender) {
        addKeyListener(new TAdapter());
        this.gameModel = new GameModel(playerName);
        this.gameStatusBar = new GameStatusBar(playerName);
        this.gameView = new GameView(this.gameModel);
        sender.add(this.gameStatusBar);
        sender.add(this.gameView);
        resetTimer();
    }

    /**
     * Reset the timer and start the timer
     */
    void resetTimer() {
        if (this.timer != null) this.timer.stop();
        this.timer = new Timer(this.speedLevel.getTimeInterval(), this);
        timer.start();
    }

    /**
     * Speed up to next level.
     */
    private void speedUp() {
        this.speedLevel = this.speedLevel.getNextLevel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameModel.isInGame()) {
            if (this.gameModel.getSnake().getLength() > speedUpLength) {
                // Game upgrade the speed into next level
                this.speedUp();
                int score = this.gameModel.getSnake().getScore();
                this.gameModel.initGame();
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
                gameModel.initGame();
                resetTimer();
            }
            else if (key == KeyEvent.VK_Q) System.exit(0);
            else gameModel.setDirection(key);
        }
    }
}



