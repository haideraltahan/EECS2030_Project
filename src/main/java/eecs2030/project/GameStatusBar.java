package eecs2030.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eecs2030.project.Enums.Difficulty;
import eecs2030.project.Utilities.Constants;

/**
 * A Game status bar view that shows the player name and current game score
 */
@SuppressWarnings("serial")
public class GameStatusBar extends JPanel {
	
	private final JLabel playerNameLabel;
	private final JLabel scoreLabel = new JLabel("Score: 0");
	private final JLabel difficultyLabel;

	/**
     * Constructor - Initialize the visuals for Player name and score.
     *
     * @param playerName Player Name that inputted
     */
    public GameStatusBar(String playerName) {
    	this.setFocusable(false);
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(Constants.GAME_WIDTH, Constants.BAR_HEIGHT));
        this.playerNameLabel = new JLabel( Constants.PLAYER_NAME_LABEL + playerName);
        this.playerNameLabel.setForeground(Color.WHITE);
        this.scoreLabel.setForeground(Color.WHITE);
        this.difficultyLabel = new JLabel("Difficulty: " + Difficulty.SLOW.toString());
        this.difficultyLabel.setForeground(Color.WHITE);
        this.setLayout(new FlowLayout());
        this.add(difficultyLabel);
        this.add(Box.createHorizontalStrut(Constants.HORIZONTAL_PADDING*2));
        this.add(playerNameLabel);
        this.add(Box.createHorizontalStrut(Constants.HORIZONTAL_PADDING*2));
        this.add(scoreLabel);
    }

    /**
     * Update the score label
     *
     * @param score new score
     */
    public void setScoreLabel(int score) {
    	this.scoreLabel.setText("Score: " + score);
    }

    /**
     * Update the difficulty label
     *
     * @param difficulty new difficulty
     */
    public void setDifficultyLabel(Difficulty difficulty) {
        this.difficultyLabel.setText("Difficulty: " + difficulty.toString());
    }

}
