package eecs2030.project;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import eecs2030.project.Utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Haider on 7/4/2017.
 */
public class Window extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();

    public Window() {
        super(Constants.GAME_TITLE);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        this.setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        startMainMenu();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void startMainMenu() {
        JButton btnStart = new JButton("Start Game");
        JButton btnQuit = new JButton("Quit");

        btnStart.setActionCommand(Constants.START_COMMAND);
        btnQuit.setActionCommand(Constants.EXIT_COMMAND);

        btnStart.addActionListener(this);
        btnQuit.addActionListener(this);

        Box box = Box.createVerticalBox();
        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(Constants.HEIGHT / 2 - 50));
        box.add(btnStart);
        box.add(Box.createVerticalStrut(Constants.VERTICAL_PADDING));
        box.add(btnQuit);

        this.add(box, BorderLayout.CENTER);
    }

    private void startGame() {
        new Game();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case Constants.START_COMMAND:
                startGame();
                break;
            case Constants.EXIT_COMMAND:
                System.exit(0);
                break;
        }
    }
}
