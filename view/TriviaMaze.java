
package view;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.Maze;

/**
 * This class will serve as the container class where everything will be displayed and ran.
 * @author David Hoang
 * @author Faith Capito
 * @version Fall 2023
 */
public  final class TriviaMaze implements PropertyChangeListener {

    /**
     *  Maze Object to be referenced.
     */
    private final Maze myMaze;
    /**
     * Static variable used to contain the gui components.
     */
    private  JFrame myWindow;

    /**
     * Constructs the JFrame where everything will be contained and displays
     * the game.
     */
    public TriviaMaze() {
        myWindow = new JFrame();

        myMaze = new Maze(4, 4);
        myMaze.newGame();


        setUpJFrame();
        addMazeView();
        addQuestionPrompt();
        addUserOptionsToJFrame();
    }

    /**
     * Sets up the JFrame where everything will be contained.
     */

    private  void setUpJFrame() {

        //This lets the window properly close when user clicks X button.
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setResizable(false);
        myWindow.setTitle("Beabadobee Trivia Maze");
        //Doesn't specify the location of the window, since it is null
        // it wil be displayed in the center.
        myWindow.setLocationRelativeTo(null);
        myWindow.setVisible(true);

    }

    private void addQuestionPrompt() {
        // Pass the Maze object to the QuestionPrompt constructor
        final QuestionPrompt questionPrompt = new QuestionPrompt(myMaze);
        myMaze.addPropertyChangeListener(questionPrompt);
    }
    /**
     * Adds the JPanel of the maze to the JFrame.
     */
    private  void addMazeView() {
        final MazeView mazeview = new MazeView(myMaze);
        myMaze.addPropertyChangeListener(mazeview);
        mazeview.setFocusable(true);
        myWindow.add(mazeview);
        //Window will be sized to fit the preferred size
        // and layouts of its subcomponents.
        myWindow.pack();
        // Request focus on the MazeView
        mazeview.requestFocusInWindow();
    }

    /**
     *  Builds and adds a menu bar that displays options to the user.
     */
    private  void addUserOptionsToJFrame() {
        final JMenuBar menuBar = new JMenuBar();
        //The file option
        final JMenu menu = new JMenu("File");
        menu.add(buildUserOptions());
        menu.addSeparator();
        myWindow.setJMenuBar(menuBar);
    }
    /**
     * Builds the menu Items and adds actionlisteners.
     * Also adds to the sub menu of user options and returns it.
     */
    private  JMenu buildUserOptions() {
        final JMenuItem newGame = new JMenuItem("New game");
        final JMenuItem endGame = new JMenuItem("End Game");
        final JMenuItem exit = new JMenuItem("Exit");
        final JMenuItem about = new JMenuItem("About");


        /**
         * When the New Game option is pressed, start a new game.'
         * User's current game must end to start new game.
         */
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {


                    JOptionPane.showMessageDialog(newGame, "New Game");

            }
        });
        /**
         * The current game is ended and paused.
         */
        endGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                    JOptionPane.showMessageDialog(endGame, "Game Ended");
            }
        });
        /**
         * Closes the window when the exit item is clicked.
         */
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                JOptionPane.showMessageDialog(exit, "Exit!");
                myWindow.dispatchEvent(new WindowEvent(myWindow, WindowEvent.WINDOW_CLOSING));
            }
        });
        /**
         * Adds an about screen.
         */
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                JOptionPane.showMessageDialog(about, "Info to be added.");

            }
        });

        final JMenu subMenu = new JMenu("User Options");
        subMenu.add(newGame);
        subMenu.add(endGame);
        subMenu.add(exit);
        subMenu.add(about);
        return subMenu;
    }


    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {

    }









}
