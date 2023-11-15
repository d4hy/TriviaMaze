/*
 * TriviaMaze
 * Fall 2023
 */
package model;

/**
 * Class will contain information of current Door that the character is at.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class Door {

    /**
     * Description of directional door in a room.
     */
    private String myDoorDescription;

    /**
     * Whether Door is locked or not.
     */
    private boolean myLockedStatus;

    /**
     * Question associated with current Door.
     */
    private Question myQuestion;

    /**
     * Constructor for Door
     */
    public Door(final String theDoorDescription) {

        myDoorDescription = theDoorDescription;

    }

    public Question getMyQuestion(final Door theDoor) {

        return myQuestion;
    }

    /**
     * Unlocks Door if associated Question is answered correctly.
     */
    public void unlock() {}

    /**
     * Returns status of Door.
     * @return myLockedStatus if Door is locked or not.
     */
    public boolean isLocked() {

        myLockedStatus = false;

        return myLockedStatus;
    }

}
