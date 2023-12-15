package model;

import controller.MazeControls;
import java.awt.Point;
import java.io.Serializable;


/**
 * This class will represent the controllable character
 * that the user will operate through the game.
 * @author David Hoang
 * @author Faith Capito.
 * @version Fall 2023.
 */


public class Character implements Serializable {

    /**
     * String constant flagging when character is moving down.
     */
    private static final String DOWN = "down";

    /**
     * String constant flagging when character is moving up.
     */
    private static final String UP = "up";

    /**
     * String constant flagging when character is moving left.
     */
    private static final String LEFT = "left";

    /**
     * String constant flagging when character is moving right.
     */
    private static final String RIGHT = "right";

    /**
     * This will be the current position of character.
     */
    private Point myCurrentPosition;


    /**
     * This field will be the bounds for which X coordinates the user can enter.
     */
    private final int myXBoundary;
    /**
     * This field will be the bounds for which Y coordinates  the user can enter.
     *
     */
    private final  int myMaxYBoundary;

    /**
     * The starting X coordinate of where the user spawned within the room.
     */

    private final int myStartX;

    /**
     * The starting Y coordinate of where the user spawned within the room.
     */

    private final int myStartY;

    /**
     * The speed at which the character travels between each movement.
     */
    private final int mySpeed = 16;






    /**
     * The direction that the character is facing.
     */
    private String myDirection;

    /**
     * This method initializes the start position.
     * @param theStartX coordinate
     * @param theStartY coordinate
     * @param theMaxX boundaries
     * @param theMaxY boundaries
     */
    public Character(final int theStartX, final int theStartY,
                     final int theMaxX, final int theMaxY) {
        myDirection = DOWN;
        myStartX = theStartX;
        myStartY = theStartY;
        myCurrentPosition = new Point(theStartX, theStartY);
        myXBoundary = theMaxX;
        myMaxYBoundary = theMaxY;
    }


    /**
     * The direction which the character is facing.
     * @return a String that represents which direction the character is facing.
     */
    public String getMyDirection() {
        return myDirection;
    }

    /**
     * Resets the character's position to where they spawned.
     */
    public void resetToSpawn() {
        myCurrentPosition = new Point(myStartX,  myStartY);
    }

    /**
     * Moves the character up if it is within the boundaries.
     */
    public void moveUp() {
        final double newY = myCurrentPosition.getY() - mySpeed;
        // Check if the new Y-coordinate is above the top boundary
        if (newY >= 0) {
            myDirection = UP;
            myCurrentPosition.setLocation(myCurrentPosition.getX(), newY);
        } else {
            // If already at or above the top boundary, set Y-coordinate to 0
            myCurrentPosition.setLocation(myCurrentPosition.getX(), 0);
        }

    }

    /**
     * Method to move the character down if it is within the boundaries.
     */
    public void moveDown() {
        final double newY = myCurrentPosition.getY() + mySpeed;
        // Check if the new Y-coordinate is below the bottom boundary
        if (newY < myMaxYBoundary - MazeControls.MY_TILE_SIZE) {
            myDirection = DOWN;
            myCurrentPosition.setLocation(myCurrentPosition.getX(), newY);

        } else {
            // If already at or below the bottom boundary, set Y-coordinate to bottom boundary
            myCurrentPosition.setLocation(myCurrentPosition.getX(),
                    myMaxYBoundary - MazeControls.MY_TILE_SIZE);
        }

    }

    /**
     * Method to move the character right if it is within the boundaries.
     */
    public void moveRight() {
        final double newX = myCurrentPosition.getX() + mySpeed;
        // Check if the new X-coordinate is beyond the right boundary
        if (newX < myXBoundary - MazeControls.MY_TILE_SIZE) {
            myDirection = RIGHT;
            myCurrentPosition.setLocation(newX, myCurrentPosition.getY());
        } else {
            // If already at or beyond the right boundary, set X-coordinate to right boundary
            myCurrentPosition.setLocation(myXBoundary - MazeControls.MY_TILE_SIZE,
                    myCurrentPosition.getY());
        }

    }


    /**
     * Method to move the character left if it is within the boundaries.
     */
    public void moveLeft() {
        final double newX = myCurrentPosition.getX() - mySpeed;
        // Check if the new X-coordinate is beyond the left boundary
        if (newX >= 0) {
            myDirection = LEFT;
            myCurrentPosition.setLocation(newX, myCurrentPosition.getY());
        } else {
            // If already at or beyond the left boundary, set X-coordinate to 0
            myCurrentPosition.setLocation(0, myCurrentPosition.getY());
        }

    }






    /**
     * Method to return the current position of the character.
     * @return a point object that represents the current position.
     */
    public Point getCurrentPosition() {
        return new Point(myCurrentPosition);
    }


}