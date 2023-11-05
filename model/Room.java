/*
 * TriviaMaze
 * Fall 2023
 */
package model;

/**
 * Room class will contain information about its Doors in response to player actions in Maze.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class Room {

    /**
     * The available Doors within current Room.
     */
    private Door[] myDoors;

    /**
     * Left door in Room.
     */
    private Door myLeftDoor;

    /**
     * Right door in Room.
     */
    private Door myRightDoor;

    /**
     * Top door in Room.
     */
    private Door myTopDoor;

    /**
     * Bottom door in Room.
     */
    private Door myBottomDoor;

    /**
     * Constructor for Room.
     */
    public Room(final Door theLeftDoor, final Door theRightDoor,
                final Door theTopDoor, final Door theBottomDoor) {

        myLeftDoor = theLeftDoor;
        myRightDoor = theRightDoor;
        myTopDoor = theTopDoor;
        myBottomDoor = theBottomDoor;

    }

    public Room() {
        myLeftDoor = new Door("Left Door");
        myRightDoor = new Door("Right Door");
        myTopDoor = new Door("Top Door");
        myBottomDoor = new Door("Bottom Door");
    }

    /**
     * Retrieves left door in specific Room.
     * @return left Door.
     */
    public Door getLeftDoor() {
        return myLeftDoor;
    }

    /**
     * Retrieves right door in specific Room.
     * @return right Door.
     */
    public Door getRightDoor() {
        return myRightDoor;
    }

    /**
     * Retrieves top door in specific Room.
     * @return top Door.
     */
    public Door getTopDoor() {
        return myTopDoor;
    }

    /**
     * Retrieves bottom door in specific Room.
     * @return bottom Door.
     */
    public Door getBottomDoor() {
        return myBottomDoor;
    }

    /**
     * Depending on validity of answered Question, will unlock specified Door
     * in this Room.
     */
    public void unlockDoors() {

    }

    /**
     * Returns validity of Character answered Question.
     * @return boolean if question asked was answered.
     */
    public boolean isQuestionAnswered() {

        boolean answer = false;

        return answer;
    }
}
