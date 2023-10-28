

package model;

import java.awt.Point;
/**
 * This class will represent the controllable character
 * that the user will operate.
 * @author David Hoang, Faith Capito.
 * @version Fall 2023.
 */


public class Character {
    /**
     * This will be the current position of character.
     */
    private Point myCurrentPosition;


    /**
     * This method initializes the start position.
     * @param theStartX coordinate
     * @param theStartY coordinate
     */
    public Character(final int theStartX, final int theStartY) {
        myCurrentPosition = new Point(theStartX, theStartY);
    }


    /**
     * Method to return the current position of the character.
     * @return a point object that represents the current position.
     */
    public Point getCurrentPosition() {
        return new Point(myCurrentPosition);
    }

}
