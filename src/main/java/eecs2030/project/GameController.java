package eecs2030.project;

import eecs2030.project.Enums.Difficulty;
import eecs2030.project.Models.GameModel;
import eecs2030.project.Utilities.Constants;

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
public final class GameController extends JPanel implements ActionListener, Runnable {

    private GameView gameView;
    private GameStatusBar gameStatusBar;
    private GameModel gameModel;

    private Timer timer;


    /**
     * Overridden method from Runnable interface to run the game on different Thread.
     */
    @Override
    public void run() {
//        this.add(this.gameStatusBar, BorderLayout.PAGE_START);
//        this.add(this.gameView, BorderLayout.CENTER);
        resetTimer();
    }

    /**
     * Constructor
     *
     * @param playerName the player name
     */
    public GameController(String playerName) {
        super(new BorderLayout());
        addKeyListener(new TAdapter());
        this.gameModel = new GameModel(playerName);
        this.gameStatusBar = new GameStatusBar(playerName);
        this.gameView = new GameView(this.gameModel);
        this.add(this.gameStatusBar, BorderLayout.PAGE_START);
        this.add(this.gameView, BorderLayout.CENTER);
    }

    /**
     * Reset the timer and start the timer
     */
    void resetTimer() {
        if (this.timer != null) this.timer.stop();
        this.timer = new Timer(this.gameModel.getDifficulty().getTimeInterval(), this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameModel.isInGame()) {
            int score = this.gameModel.getSnake().getScore();
            if (this.gameModel.ableToUpgradeDifficultyLevel()) {
                // reset game with next difficulty level
                this.timer.stop();
                for (int i = Constants.LOADING_TIME; i > 0; i--) {
                    this.gameView.drawLevelUpMessage(this.gameModel.getDifficulty(), i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                this.gameModel.upgradeDifficultyLevel();
                this.gameModel.initGame(score);
                this.gameStatusBar.setDifficultyLabel(this.gameModel.getDifficulty());
                resetTimer();

            } else {
                this.gameModel.prepareNextMove();
            }
            this.gameStatusBar.setScoreLabel(score);
        } else {
            // Game over
            this.timer.stop();
            this.gameModel.saveScoreToDatabase();
            this.gameModel.setDifficulty(Difficulty.SLOW);
        }
        this.gameView.repaint();
        this.gameStatusBar.setScoreLabel(this.gameModel.getSnake().getScore());
    }

    /**
     * TAdapter manage all necessary keys operations for Game
     *
     * @author jianxiongwang
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_R:
                    gameModel.setDifficulty(Difficulty.SLOW);
                    gameModel.initGame(0);
                    gameStatusBar.setDifficultyLabel(gameModel.getDifficulty());
                    resetTimer();
                    break;
                case KeyEvent.VK_Q:
                    System.exit(0);
                    break;
                default:
                    gameModel.setDirection(key);
            }
        }
    }
}



