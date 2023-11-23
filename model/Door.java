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
     * Whether the question of the door has been correctly answered or has.
     */
    private boolean myQuestionHasBeenAnsweredCorrectly;

    /**
     * Whether the question  has been not prompted or has.
     */
    private boolean myQuestionHasNotBeenPrompted;

    /**
     * Question associated with current Door.
     */
    private Question myQuestion;

    /**
     * Constructor for Door.
     */
    public Door(final String theDoorDescription) {
        myQuestionHasNotBeenPrompted = true;
        myQuestionHasBeenAnsweredCorrectly = false;
        myDoorDescription = theDoorDescription;

    }

    public Question getMyQuestion(final Door theDoor) {

        return myQuestion;
    }


    /**
     * Returns a boolean if the door's Question has not been prompted or has.
     * @return true if the door's question has not been prompted, false if it has been prompted.
     */
    public boolean hasMyQuestionBeenNotPrompted() {
        return myQuestionHasNotBeenPrompted;
    }

    /**
     * Sets the status if the door's question has not been prompted or has.
     * @param theStatus if true, it will set door's myQuestionHasNotBeenPrompted status to true, false
     *                  will set myQuestionHasNotBeenPrompted status to false .
     */
    public void setMyQuestionNotPromptedStatus(final boolean theStatus) {
        myQuestionHasNotBeenPrompted = theStatus;
    }


    /**
     *  Returns if the door's questions has been answered correclty or not.
     * @return boolean if the question is answered or not.
     */
    public boolean hasMyQuestionBeenAnsweredCorrectly() {
        return myQuestionHasBeenAnsweredCorrectly;
    }

    /**
     * Sets the question status if the door has been answered correctly or not.
     * @param theStatus of the question to be set to.
     */
    public void setMyQuestionHasBeenAnsweredCorrectlyStatus(final boolean theStatus) {
        myQuestionHasBeenAnsweredCorrectly = theStatus;
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
