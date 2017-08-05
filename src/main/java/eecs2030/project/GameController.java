package eecs2030.project;

import eecs2030.project.Enums.Difficulty;
import eecs2030.project.Models.GameModel;
import eecs2030.project.GameStatusBar;
import eecs2030.project.GameView;

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
        this.timer = new Timer(this.gameModel.getDifficulty().getTimeInterval(), this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameModel.isInGame()) {
            int score = this.gameModel.getSnake().getScore();
            if (this.gameModel.ableToUpgradeDifficultyLevel()) {
                // reset game with next difficulty level
                this.gameModel.upgradeDifficultyLevel();
                this.gameModel.initGame(score);
                resetTimer();
            } else {
                this.gameModel.prepareNextMove();
            }
            this.gameStatusBar.updateScoreLabel(score);
        } else {
            // Game over
            this.timer.stop();
            this.gameModel.saveScoreToDatabase();
            this.gameModel.setDifficulty(Difficulty.SLOW);
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
                gameModel.initGame(0);
                resetTimer();
            }
            else if (key == KeyEvent.VK_Q) System.exit(0);
            else gameModel.setDirection(key);
        }
    }
}



