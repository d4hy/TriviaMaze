

package model;
import controller.MazeControls;
import java.awt.Point;
import java.util.ArrayList;

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
    private int myMaxRows;
    /**
     * This field will be the bounds for which columns the user can enter.
     *
     */
    private int myMaxCols;


    /**
     * This instance field will be used to pause the player from moving, such
     * as when answering a question.
     */
    private boolean myMobility;

    /**
     * This method initializes the start position.
     * @param theStartX coordinate
     * @param theStartY coordinate
     * @param theMaxRows boundaries
     * @param theMaxCols boundaries
     */
    public Character(final int theStartX, final int theStartY,
                     final int theMaxRows, final int theMaxCols) {
        myCurrentPosition = new Point(theStartX, theStartY);
        myMobility = true;
        myMaxRows = theMaxRows;
        myMaxCols = theMaxCols;

    }

    /**
     * Moves the user up if it is  within the boundaries.
      */
    public void moveUp() {
        if (myCurrentPosition.getY() > 0 && myMobility) {
            myCurrentPosition.setLocation(myCurrentPosition.getX(), myCurrentPosition.getY() - MazeControls.MY_TILE_SIZE);
        } else {
            System.out.println("Cannot move up. Already at the top boundary or mobility is restricted.");
        }
    }


    /**
     * Method to move the user down if it is within the boundaries.
     */
    public void moveDown() {
        if (myCurrentPosition.getY() < myMaxCols && myMobility) {
            myCurrentPosition.setLocation(myCurrentPosition.getX(),
                    myCurrentPosition.getY() + MazeControls.MY_TILE_SIZE);
        } else if (myCurrentPosition.getY() < myMaxCols) {
            System.out.println("Cannot move down. Already at the bottom boundary.");
        }
    }

    /**
     * Method to move the user right if it is within the boundaries.
     */
    public void moveRight() {
        if (myCurrentPosition.getX() < myMaxRows && myMobility) {
            myCurrentPosition.setLocation(myCurrentPosition.getX() + MazeControls.MY_TILE_SIZE,
                    myCurrentPosition.getY());
        } else if (myCurrentPosition.getX() > myMaxRows) {
            System.out.println("Cannot move right. Already at the right boundary.");
        }
    }

    /**
     * Method to move the user left if it is within the boundaries.
     */
    public void moveLeft() {
        if (myCurrentPosition.getX() > 0  && myMobility) {
            myCurrentPosition.setLocation(myCurrentPosition.getX() - MazeControls.MY_TILE_SIZE,
                    myCurrentPosition.getY());
        } else if (myCurrentPosition.getX() < 0) {
            System.out.println("Cannot move left. Already at the left boundary.");
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
