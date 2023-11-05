package view;




import controller.MazeControls;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Maze;




/**
 *  This class serves as a screen class that displays the game.
 * @author David Hoang,
 * @author Faith Capito.
 * @version Fall 2023
 */
public class MazeView extends JPanel implements PropertyChangeListener {


    /**
     * The initial delay that the timer has.
     */
    private static final int TIMER_DELAY = 1000;


    /**
     * Timer that will be used for game and question functionality.
     */
    private static Timer myTimer;

    /**
     *  Maze Object to be referenced.
     */
    private final Maze myMaze = new Maze(4, 4);

    /**
     *  Character to reference.
     */
    private Character myCharacter;


    MazeView() {
        setUp();


    }

    /**
     * This method will set up the JPanel.
     */
    public void setUp() {
        //sets the size of the jPanel.
        this.setPreferredSize(new Dimension(MazeControls.MY_SCREEN_WIDTH,
                MazeControls.MY_SCREEN_HEIGHT));
        this.setBackground(Color.black);

        //will improve game's rendering performance.
        this.setDoubleBuffered(true);

    }

    /**
     * Sets initial timer that will be used to track user input in the game.
     */
    private void setTimer() {
        myTimer = new Timer(TIMER_DELAY, null);
        myTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (myTimer.isRunning()) {
                    // depending on implementation, show the timer during the game
                    // or only use to calculate time limit for user to answer questions.
                }
            }
        });
    }





    /**
     * This method will paint the needed information of the game onto the screen.
     */
    public void paintComponent(final Graphics theG) {
        super.paintComponent(theG);

        //Graphics2D is a more sophisticated child class of
        //Needed to cast
        final Graphics2D g2 = (Graphics2D) theG;

        g2.setColor(Color.white);

        //a rectangle that will represent the character for now.
        g2.fillRect((getWidth() - MazeControls.MY_TILE_SIZE) / 2,
                (getHeight() - MazeControls.MY_TILE_SIZE) / 2,
                MazeControls.MY_TILE_SIZE, MazeControls.MY_TILE_SIZE);

        //good practice to save memory, release any system resources it's using
        // after it is done drawing.
        g2.dispose();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        // if the property name equals to any of the specified properties below,
        // it repaints and updates the screen.

        final String propertyName = theEvt.getPropertyName();

        if (propertyName.equals(propertyName.equals(Maze.PROPERTY_CHARACTER_MOVE))) {
            myCharacter = (Character) theEvt.getNewValue();
            repaint();
        }
    }
}
