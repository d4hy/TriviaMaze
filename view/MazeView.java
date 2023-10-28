package view;





import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


/**
 *  This class serves as a screen class that displays the game.
 * @author David Hoang, Faith Capito.
 * @version Fall 2023
 */
public class MazeView extends JPanel implements Runnable {

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
     * This object will be used to let the game loop and update.
     */
    private Thread myGameThread;

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
     * This method will create a thread object which will represent our game clock.
     *
     */
    public void startGameThread() {
        myGameThread = new Thread(this);
        myGameThread.start();
    }

    /**
     * This method will be automatically called by the thread object to update and draw
     * the screen with update information.
     */
    @Override
    public void run() {
        //Game will loop as long as the thread exists.
        while (myGameThread != null) {
            //System.out.println("The game is running");
            update();

            //This method will call paintComponent to repaint.
            repaint();

        }
    }
    public void update() {

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
        g2.fillRect(100,100, MY_TILE_SIZE, MY_TILE_SIZE);

        //good practice to save memory, release any system resources it's using
        // after it is done drawing.
        g2.dispose();
    }
}
