package view;

import controller.MazeControls;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Character;
import model.Maze;
import model.Room;

/**
 *  This class serves as a screen class that displays the game.
 * @author David Hoang,
 * @author Faith Capito.
 * @version Fall 2023
 */
public class MazeView extends JPanel implements PropertyChangeListener, KeyListener {

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

    private Room myRoom;

    MazeView() {
        setUp();
        myMaze.addPropertyChangeListener(this);
        myMaze.newGame();
        addKeyListener(this); // Add the MazeView itself as a KeyListener
        setFocusable(true);
    }

    /**
     * This method will set up the JPanel.
     */
    public void setUp() {
        // sets the size of the jPanel.
        this.setPreferredSize(new Dimension(MazeControls.MY_SCREEN_WIDTH,
                MazeControls.MY_SCREEN_HEIGHT));
        this.setBackground(Color.black);

        // will improve game's rendering performance.
        this.setDoubleBuffered(true);

        setTimer();
    }

    /**
     * Sets initial timer that will be used to track user input in the game.
     */
    private void setTimer() {
        myTimer = new Timer(TIMER_DELAY, new ActionListener() {
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

        // Graphics2D is a more sophisticated child class of
        // Needed to cast
        final Graphics2D g2 = (Graphics2D) theG;

        g2.setColor(Color.white);

        //if the room reference isn't null draw the room tiles
        if (myRoom != null) {

            myRoom.draw(g2);

        }
        //if the character reference isn't null draw them
        if (myCharacter != null) {
            myCharacter.draw(g2);

        }


        // good practice to save memory, release any system resources it's using
        // after it is done drawing.
        g2.dispose();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        // if the property name equals to any of the specified properties below,
        // it repaints and updates the screen.

        final String propertyName = theEvt.getPropertyName();

        if (propertyName.equals(myMaze.PROPERTY_CHARACTER_MOVE)) {
            myCharacter = (Character) theEvt.getNewValue();
            repaint();
        } else if (propertyName.equals(myMaze.PROPERTY_ROOM_CHANGE)) {
            myRoom = (Room) theEvt.getNewValue();
            repaint();
        }

    }

    // Implementing KeyListener methods

    @Override
    public void keyPressed(final KeyEvent theEvent) {
        switch (theEvent.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> handleUpKey();
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> handleDownKey();
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> handleLeftKey();
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> handleRightKey();
            case KeyEvent.VK_SPACE -> handleSpaceKey();
            default -> {}
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed for this example
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not needed for this example
    }
    private void handleUpKey() {
        myMaze.moveUp();


    }

    private void handleDownKey() {
        myMaze.moveDown();



    }

    private void handleLeftKey() {
        myMaze.moveLeft();


    }

    private void handleRightKey() {
        myMaze.moveRight();


    }

    private void handleSpaceKey() {
        System.out.println("Pressed Space");

    }
}