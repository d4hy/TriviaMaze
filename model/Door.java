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
     * Whether the question of the door is answered or not.
     */
    private boolean isMyQuestionAnswered;

    /**
     * Question associated with current Door.
     */
    private Question myQuestion;

    /**
     * Constructor for Door.
     */
    public Door(final String theDoorDescription) {

        myDoorDescription = theDoorDescription;

    }

    public Question getMyQuestion(final Door theDoor) {

        return myQuestion;
    }

    /**
     *  Returns if the door's questions is answered or not.
     * @return boolean if the question is answered or not.
     */
    public boolean isMyQuestionAnswered() {
        return isMyQuestionAnswered;
    }

    /**
     * Sets the question status of the door to be answered or not.
     * @param theStatus of the question to be set to.
     */
    public void setMyQuestionAnsweredStatus(final boolean theStatus) {
        isMyQuestionAnswered = theStatus;
    }


    /**
     * Unlocks Door if associated Question is answered correctly.
     */
    public void unlock() {
        myLockedStatus = false;
    }





    /**
     * Returns door's locked status.
     * @return myLockedStatus if Door is locked or not.
     */
    public boolean isLocked() {
        return myLockedStatus;
    }

}
