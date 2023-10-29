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
    public Door() {}

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
