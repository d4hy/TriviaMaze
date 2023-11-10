package model;
import controller.MazeControls;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;


/**
 * This class will represent the controllable character
 * that the user will operate.
 * @author David Hoang
 * @author Faith Capito.
 * @version Fall 2023.
 */


public class Character {
    /**
     * This will be the current position of character.
     */
    private Point myCurrentPosition;

    /**
     * An arrayList serving as what items the user has collected.
     */
    private ArrayList<AbstractItem> myInventory;

    /**
     * This field will be the bounds for which rows the user can enter.
     */
    private final int myMaxRows;
    /**
     * This field will be the bounds for which columns the user can enter.
     *
     */
    private final  int myMaxCols;


    /**
     * This instance field will be used to pause the player from moving, such
     * as when answering a question.
     */
    private boolean myMobility;

    /**
     * The starting X coordinate of where the user spawned within the room.
     */

    private final int myStartX;

    /**
     * The starting Y coordinate of where the user spawned within the room.
     */

    private final int myStartY;

    /**
     * The speed at which the character moves between each movement.
     */
    private final int mySpeed = 8;

    /**
     * Counter for how many steps has taken the sprite alternates.
     */
    private int mySpriteCounter;


    /**
     * Counter for which walking animation to choose.
     */
    private int mySpriteNumber = 1;
    /**
     * These will be sprites that we use.
     */
    @SuppressWarnings("checkstyle:MultipleVariableDeclarations")
    private BufferedImage myUp1, myUp2, myDown1,
            myDown2, myLeft1, myLeft2, myRight1, myRight2;
    /**
     * The direction that the character is facing.
     */
    private String myDirection;

    /**
     * This method initializes the start position.
     * @param theStartX coordinate
     * @param theStartY coordinate
     * @param theMaxRows boundaries
     * @param theMaxCols boundaries
     */
    public Character(final int theStartX, final int theStartY,
                     final int theMaxRows, final int theMaxCols) {
        myDirection = "down";
        myStartX = theStartX;
        myStartY = theStartY;
        myCurrentPosition = new Point(theStartX, theStartY);
        myMobility = true;
        myMaxRows = theMaxRows;
        myMaxCols = theMaxCols;
        myInventory = new ArrayList<>(); // Add this line to initialize myInventory
        getPlayerImage();
    }

    /**
     * Draw the characters, based on their current position.
     */
    public void draw(final Graphics2D g2) {
        //the x and y coordinates of the character.
        int x = getCurrentPosition().x;
        int y = getCurrentPosition().y;

        // Ensure the rectangle is within the panel's boundaries
        x = Math.max(0, Math.min(x, myMaxRows - MazeControls.MY_TILE_SIZE));
        y = Math.max(0, Math.min(y, myMaxCols - MazeControls.MY_TILE_SIZE));


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

        switch (myDirection) {
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
                throw new IllegalStateException("Unexpected value: " + myDirection);
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
     * Resets the character's position to where they spawned.
     */
    public void resetToMiddle() {
        myCurrentPosition = new Point(myStartX,  myStartY);
    }

    /**
     * Moves the user up if it is within the boundaries.
     */
    public void moveUp() {
        final double newY = myCurrentPosition.getY() - mySpeed;
        // Check if the new Y-coordinate is above the top boundary
        if (newY >= 0) {
            myDirection = "up";
            myCurrentPosition.setLocation(myCurrentPosition.getX(), newY);
        } else {
            // If already at or above the top boundary, set Y-coordinate to 0
            myCurrentPosition.setLocation(myCurrentPosition.getX(), 0);
        }
        alternateWalkingSprite();
    }

    /**
     * Method to move the user down if it is within the boundaries.
     */
    public void moveDown() {
        final double newY = myCurrentPosition.getY() + mySpeed;
        // Check if the new Y-coordinate is below the bottom boundary
        if (newY < myMaxCols - MazeControls.MY_TILE_SIZE) {
            myDirection = "down";
            myCurrentPosition.setLocation(myCurrentPosition.getX(), newY);

        } else {
            // If already at or below the bottom boundary, set Y-coordinate to bottom boundary
            myCurrentPosition.setLocation(myCurrentPosition.getX(),
                    myMaxCols - MazeControls.MY_TILE_SIZE);
        }
        alternateWalkingSprite();
    }

    /**
     * Method to move the user right if it is within the boundaries.
     */
    public void moveRight() {
        final double newX = myCurrentPosition.getX() + mySpeed;
        // Check if the new X-coordinate is beyond the right boundary
        if (newX < myMaxRows - MazeControls.MY_TILE_SIZE) {
            myDirection = "right";
            myCurrentPosition.setLocation(newX, myCurrentPosition.getY());
        } else {
            // If already at or beyond the right boundary, set X-coordinate to right boundary
            myCurrentPosition.setLocation(myMaxRows - MazeControls.MY_TILE_SIZE,
                    myCurrentPosition.getY());
        }
        alternateWalkingSprite();
    }


    /**
     * Method to move the user left if it is within the boundaries.
     */
    public void moveLeft() {
        final double newX = myCurrentPosition.getX() - mySpeed;
        // Check if the new X-coordinate is beyond the left boundary
        if (newX >= 0) {
            myDirection = "left";
            myCurrentPosition.setLocation(newX, myCurrentPosition.getY());
        } else {
            // If already at or beyond the left boundary, set X-coordinate to 0
            myCurrentPosition.setLocation(0, myCurrentPosition.getY());
        }
        alternateWalkingSprite();
    }
    /**
     * Alternates the walking animation of the character.
     */
    private void alternateWalkingSprite() {
        mySpriteCounter++;
        //every 10 movements, change the walking animation
        if (mySpriteCounter > 6) {
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



    /**
     * Method to return the current position of the character.
     * @return a point object that represents the current position.
     */
    public Point getCurrentPosition() {
        return new Point(myCurrentPosition);
    }

    /**
     * Method adds item to the inventory of the user.
     * @param theItem to add the inventory of the user.
     */
    public void addToInventory(final AbstractItem theItem) {
        myInventory.add(theItem);
        System.out.println("Added " + theItem.getName() + " to the inventory.");
    }

    /**
     * Method to set if the user can move or not.
     */
    // Setter method to directly set whether the user can move or not
    public void setCanMove(final boolean theMobility) {
        myMobility = theMobility;
        System.out.println("User can");

        if (theMobility) {
            System.out.println("User can move");
        } else {
            System.out.println("User can not move");
        }
    }
}