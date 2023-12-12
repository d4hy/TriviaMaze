
package view;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    private Maze myMaze;
    /**
     * Static variable used to contain the gui components.
     */
    private  JFrame myWindow;

    /**
     * Constructs the JFrame where everything will be contained and displays
     * the game.
     */
    public TriviaMaze() throws UnsupportedAudioFileException, LineUnavailableException,
            IOException {
        myWindow = new JFrame();
        myMaze = new Maze(4, 4);
//        myMaze.createMaze();


        setUpJFrame();
        addMazeView();
        addQuestionPrompt();

        myMaze.newGame();
    }

    /**
     * Sets up the JFrame where everything will be contained.
     */

    private  void setUpJFrame() {

        //This lets the window properly close when user clicks X button.
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setResizable(false);
        myWindow.setTitle("Beabadoobee Trivia Maze");
        //Doesn't specify the location of the window, since it is null
        // it wil be displayed in the center.


    }

    private void addQuestionPrompt() {
        // Pass the Maze object to the QuestionPrompt constructor
        final QuestionPrompt questionPrompt = new QuestionPrompt(myMaze);
        myMaze.addPropertyChangeListener(questionPrompt);
    }
    /**
     * Adds the JPanel of the maze to the JFrame.
     */
    private  void addMazeView() throws UnsupportedAudioFileException,
            LineUnavailableException, IOException {
        final MazeView mazeview = new MazeView(myMaze);
        myMaze.addPropertyChangeListener(mazeview);
        mazeview.setFocusable(true);
        myWindow.add(mazeview);
        //Window will be sized to fit the preferred size
        // and layouts of its subcomponents.
        myWindow.pack();
        //Doesn't specify the location of the window, since it is null
        // it wil be displayed in the center.
        myWindow.setLocationRelativeTo(null);
        myWindow.setVisible(true);
        // Request focus on the MazeView
        mazeview.requestFocusInWindow();
    }

    /**
     * Listens for property changes in the Maze class.
     * W
     *
     * @param theEvt The property change event.
     */

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        // Listen for changes in the model, if needed.
        final String propertyName = theEvt.getPropertyName();
        if (propertyName.equals(myMaze.PROPERTY_LOAD)) {
            myMaze = (Maze) theEvt.getNewValue();
            myMaze.addPropertyChangeListener(this);
            myWindow.repaint();
        }
    }
}
