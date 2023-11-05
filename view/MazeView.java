package view;






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

    //Screen settings.
    /**
     *  16x 16 tiles, will represent size of characters,and parts of the map.
     */
    private static final int MY_ORIGINAL_TILE_SIZE = 16;
    /**
     *  This variable will be used to upscale the tile size otherwise it's too small to see.
     */
    private static final int MY_SCALE = 3;

    /**
     * The tile size up-scaled to fit on the screen.
     */
    private static final int MY_TILE_SIZE = MY_ORIGINAL_TILE_SIZE * MY_SCALE;

    /**
     *The amount of tiles that will be represented as the rows of the screen.
     *
     */
    private static final int MY_MAX_SCREEN_ROW = 16;
    /**
     *The amount of tiles that will be represented as the columns of the screen.
     *
     */
    private static final int MY_MAX_SCREEN_COL = 12;

    /**
     * The up-scaled amount of tiles that will be represented as the width of the screen.
     */
    private static final int MY_SCREEN_WIDTH = MY_TILE_SIZE  * MY_MAX_SCREEN_ROW; //768 pixels.

    /**
     * The up-scaled amount of tiles that will be represented as the height of the screen.
     */
    private static final int MY_SCREEN_HEIGHT = MY_TILE_SIZE * MY_MAX_SCREEN_COL;
    // 576 pixels.
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
        this.setPreferredSize(new Dimension(MY_SCREEN_WIDTH, MY_SCREEN_HEIGHT));
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
        g2.fillRect(100, 100, MY_TILE_SIZE, MY_TILE_SIZE);

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
