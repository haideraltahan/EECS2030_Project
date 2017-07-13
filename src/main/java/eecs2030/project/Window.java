package eecs2030.project;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import eecs2030.project.Models.TableModel;
import eecs2030.project.Utilities.Constants;
import eecs2030.project.Utilities.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Haider on 7/4/2017.
 */
public class Window extends JFrame implements ActionListener {

    private Box Leftbox;
    private JPanel Rightbox;
    private Database database;
    private TableModel tableModel;
    private Box mainMenuBox;

    public Window() throws Exception {
        super(Constants.GAME_TITLE);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        this.setResizable(false);

        //Set the Main Window in the center of the Screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // <------ INITIAL SETUP FUNCTIONS ---->
        screenLeftSide();
        screenRightSide();
        databaseSetUp();

        // <------ END SETUP FUNCTIONS --->


        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void databaseSetUp() throws Exception {
        this.database = new Database();
        DatabaseReference databaseRef = database.getDatabaseRef();
        databaseRef.orderByChild("points").limitToFirst(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                tableModel.addScore(dataSnapshot.getKey(), dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                //Values never Changes.
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                tableModel.removeScore(snapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
                //Values are sorted on the Client Side so this is not needed.
            }

            @Override
            public void onCancelled(DatabaseError error) {
                database.pushError(error.getMessage() + " ||  " + error.getDetails() + " || " + error.getCode());
            }
        });
    }

    private void screenRightSide() {
        //Box Holder for the game
        Rightbox = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        Rightbox.setBackground(Color.ORANGE);
        Rightbox.setMinimumSize(new Dimension(Constants.GAME_WIDTH, Constants.HEIGHT));
        addMainMenu();
        this.getContentPane().add(Rightbox,BorderLayout.CENTER);
    }
    

    private void screenLeftSide() {
        Leftbox = Box.createVerticalBox();
        Leftbox.setMinimumSize(new Dimension(200, 600));
        tableModel = new TableModel();
        JTable table = new JTable();
        table.setModel(tableModel);
        Leftbox.add(new JLabel(Constants.HIGHSCORES_LABEL));
        Leftbox.add(table.getTableHeader());
        Leftbox.add(table);
        this.getContentPane().add(Leftbox,BorderLayout.BEFORE_LINE_BEGINS);
    }

    private void addMainMenu() {
        JButton btnStart = new JButton(Constants.START_GAME_BUTTON);
        JButton btnQuit = new JButton(Constants.EXIT_GAME_BUTTON);

        btnStart.setActionCommand(Constants.START_COMMAND);
        btnQuit.setActionCommand(Constants.EXIT_COMMAND);

        btnStart.addActionListener(this);
        btnQuit.addActionListener(this);

        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainMenuBox = Box.createVerticalBox();
        mainMenuBox.add(Box.createVerticalStrut(Constants.HEIGHT / 2 - 50));
        mainMenuBox.add(btnStart);
        mainMenuBox.add(Box.createVerticalStrut(Constants.VERTICAL_PADDING));
        mainMenuBox.add(btnQuit);

        Rightbox.add(mainMenuBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case Constants.START_COMMAND:
                mainMenuBox.setVisible(false);
                Rightbox.grabFocus();
                Game newGame = new Game();
                Rightbox.add(newGame);
                newGame.requestFocus();
                this.revalidate();
                break;
            case Constants.EXIT_COMMAND:
                System.exit(0);
                break;
        }
    }
}
