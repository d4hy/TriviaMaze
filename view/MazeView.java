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
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Character;
import model.Door;
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
     * Counter for which walking animation to choose.
     */
    private int mySpriteNumber = 1;
    /**
     *  Maze Object to be referenced.
     */
    private final Maze myMaze;
    /**
     *  Character to reference.
     */
    private Character myCharacter;
    /**
     * The current room to reference.
     */
    private Room myRoom;

    /**
     * Counter for how many steps has taken the sprite alternates.
     */
    private int mySpriteCounter;
    /**
     * These will be sprites that we use for the character.
     */
    @SuppressWarnings("checkstyle:MultipleVariableDeclarations")
    private BufferedImage myUp1, myUp2, myDown1,
            myDown2, myLeft1, myLeft2, myRight1, myRight2;

    MazeView(final Maze theMaze) {
        this.myMaze = theMaze;
        setUp();
        myMaze.addPropertyChangeListener(this);
        myMaze.newGame();
        addKeyListener(this);
        setFocusable(true);
    }

    public void setUp() {
        this.setPreferredSize(new Dimension(
                MazeControls.MY_SCREEN_WIDTH, MazeControls.MY_SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        setTimer();
    }

    private void setTimer() {
        myTimer = new Timer(TIMER_DELAY, e -> {
            if (myTimer.isRunning()) {
                // Depending on implementation, show the timer during the game
                // or only use to calculate time limit for user to answer questions.
            }
        });
    }

    public void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2 = (Graphics2D) theG;
        g2.setColor(Color.WHITE);

        if (myRoom != null) {
            drawRoomsFloor(g2);
            drawRoomsDoors(g2);
        }

        if (myCharacter != null) {
            drawCharacter(g2);
        }

        g2.dispose();
    }

    private void drawRoomsDoors(final Graphics2D g2) {
        drawDoorIfNotNull(g2, myRoom.getLeftDoor(), getLeftDoorImg(),
                0, MazeControls.MY_SCREEN_HEIGHT / 2 - MazeControls.MY_TILE_SIZE / 2);
        drawDoorIfNotNull(g2, myRoom.getRightDoor(), getRightDoorImg(),
                MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE,
                MazeControls.MY_SCREEN_HEIGHT / 2 - MazeControls.MY_TILE_SIZE / 2);
        drawDoorIfNotNull(g2, myRoom.getBottomDoor(), getBottomDoorImg(),
                MazeControls.MY_SCREEN_WIDTH / 2 - MazeControls.MY_TILE_SIZE / 2,
                MazeControls.MY_SCREEN_HEIGHT - MazeControls.MY_TILE_SIZE);
        drawDoorIfNotNull(g2, myRoom.getTopDoor(), getTopDoorImg(),
                MazeControls.MY_SCREEN_WIDTH / 2 - MazeControls.MY_TILE_SIZE / 2, 0);
    }

    private void drawDoorIfNotNull(final Graphics2D g2, final Door theDoor,
                                   final BufferedImage theDoorImg,
                                   final int theX, final int theY) {
        if (theDoor != null) {
            drawDoor(g2, theDoorImg, theX, theY);
        }
    }

    private void drawRoomsFloor(final Graphics2D g2) {
        final BufferedImage image = getRoomTileImage();
        for (int i = 0; i < MazeControls.MY_MAX_SCREEN_ROW; i++) {
            for (int j = 0; j < MazeControls.MY_MAX_SCREEN_COL; j++) {
                final int x = i * MazeControls.MY_TILE_SIZE;
                final int y = j * MazeControls.MY_TILE_SIZE;
                g2.drawImage(image, x, y, MazeControls.MY_TILE_SIZE,
                        MazeControls.MY_TILE_SIZE, null);
            }
        }
    }

    private void drawDoor(final Graphics2D g2, final BufferedImage theDoorImg,
                          final int theX, final int theY) {
        g2.drawImage(theDoorImg, theX, theY, MazeControls.MY_TILE_SIZE,
                MazeControls.MY_TILE_SIZE, null);
    }

    private BufferedImage getRoomTileImage() {

        final String roomTilePath = "/res/room_tile_red.png";
        return loadImage(roomTilePath);
    }

    private BufferedImage getLeftDoorImg() {

        final String leftDoorPath = "/res/left_door.png";
        return loadImage(leftDoorPath);
    }

    private BufferedImage getRightDoorImg() {

        final String rightDoorPath = "/res/right_door.png";
        return loadImage(rightDoorPath);
    }

    private BufferedImage getBottomDoorImg() {

        final String bottomDoorPath = "/res/bottom_door.png";
        return loadImage(bottomDoorPath);
    }

    private BufferedImage getTopDoorImg() {
        final String topDoorPath = "/res/top_door.png";
        return loadImage(topDoorPath);
    }

    private BufferedImage loadImage(final String thePath) {
        try {
            return ImageIO.read
                    (Objects.requireNonNull(getClass().getResourceAsStream(thePath)));
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Draw the characters, based on their current position.
     */
    public void drawCharacter(final Graphics2D g2) {
        getPlayerImage();
        //the x and y coordinates of the character.
        int x = myCharacter.getCurrentPosition().x;
        int y = myCharacter.getCurrentPosition().y;

        // Ensure the rectangle is within the panel's boundaries
        x = Math.max(0, Math.min(x, MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE));
        y = Math.max(0, Math.min(y,  MazeControls.MY_SCREEN_HEIGHT- MazeControls.MY_TILE_SIZE));


        final BufferedImage image = getSpriteImage();

        g2.drawImage(image, x, y, MazeControls.MY_TILE_SIZE, MazeControls.MY_TILE_SIZE, null);

    }
    /**
     * Get the appropriate sprite image based on the
     * character's direction and walking animation.
     * @return theBufferedImage that represents the character.
     */
    private BufferedImage getSpriteImage() {
        BufferedImage image = null;

        switch (myCharacter.getMyDirection()) {
            case "up":
                image = getSprite(myUp1, myUp2);
                break;
            case "down":
                image = getSprite(myDown1, myDown2);
                break;
            case "right":
                image = getSprite(myRight1, myRight2);
                break;
            case "left":
                image = getSprite(myLeft1, myLeft2);
                break;
            default:

        }

        return image;
    }
    /**
     * Gets the sprite image based on the current sprite number.
     *
     * @param sprite1 The first sprite image.
     * @param sprite2 The second sprite image.
     * @return The sprite image based on the current sprite number.
     */
    private BufferedImage getSprite(final BufferedImage sprite1, final BufferedImage sprite2) {
        if (mySpriteNumber == 1) {
            return sprite1;
        } else {
            return sprite2;
        }
    }
    /**
     * Alternates the walking animation of the character.
     */
    private void alternateWalkingSprite() {
        mySpriteCounter++;
        //every 10 movements, change the walking animation
        if (mySpriteCounter > 1) {
            if (mySpriteNumber == 1) {
                mySpriteNumber = 2;
            } else if (mySpriteNumber == 2) {
                mySpriteNumber = 1;
            }
            mySpriteCounter = 0;
        }
    }

    /**
     * This method will load the user images.
     */
    private void getPlayerImage() {
        try {
            //the string is the path of where the file is
            myUp1 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/up_1.png")));
            myUp2 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/up_2.png")));
            myDown1 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/down_1.png")));
            myDown2 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/down_2.png")));
            myLeft1 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/left_1.png")));
            myLeft2 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/left_2.png")));
            myRight1 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/right_1.png")));
            myRight2 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/right_2.png")));
        } catch (final IOException e) {
            e.printStackTrace();

        }
    }
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        // if the property name equals to any of the specified properties below,
        // it repaints and updates the screen.

        final String propertyName = theEvt.getPropertyName();

        if (propertyName.equals(myMaze.PROPERTY_CHARACTER_MOVE)) {
            myCharacter = (Character) theEvt.getNewValue();
            alternateWalkingSprite();
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
            default ->  { }
        }
    }

    @Override
    public void keyTyped(final KeyEvent theE) {
        // Not needed for this example
    }

    @Override
    public void keyReleased(final KeyEvent theE) {
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