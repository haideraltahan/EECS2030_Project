package eecs2030.project;

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

    private final int DELAY = 140;  // delay for timer
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
        timer = new Timer(DELAY, this);
        timer.start();
    }

    void resetTimer() {
        this.timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameModel.isInGame()) {
            this.gameModel.prepareNextMove();
        } else {
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
            System.out.println("Receive key");
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



