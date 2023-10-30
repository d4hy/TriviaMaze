/*
 * TriviaMaze
 * Fall 2023
 */
package model;

import controller.MazeControls;

import java.beans.PropertyChangeSupport;

/**
 * Maze class contains data that will be responsible for current data of game.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class Maze implements MazeControls {

    private static final String PROPERTY_MOVE_DOWN = "Character has moved DOWN.";

    /**
     * The room that Character is currently in.
     */
    private Room myCurrentRoom;

    /**
     * 2D Array of all rooms within this Maze.
     */
    private Room[][] myRooms;

    /**
     * Width of Maze rooms.
     */
    private int myWidth;

    /**
     * Height of Maze rooms.
     */
    private int myHeight;

    /**
     * Number of correct answers that the current Character has answered.
     */
    private static int myCorrectAnswers;

    /**
     * Character that is in Maze.
     */
    private Character myCharacter;

    /**
     * Signals change in the model to the view.
     */
    private PropertyChangeSupport myPcs;

    /**
     * Constructor for new game of Maze, creating starting point of a Character and Rooms.
     */
    public Maze(final int theWidth, final int theHeight) {
        super();
        // arbitrary values for a new Character with starting position.
        myCharacter = new Character(0, 0, theWidth, theHeight);

        myWidth = theWidth;
        myHeight = theHeight;

        myCorrectAnswers = 0;
        myRooms = new Room[theWidth][theHeight];
        myCurrentRoom = myRooms[0][0];
        myPcs = new PropertyChangeSupport(this);

    }

    /**
     * Move method to place Character into a different position.
     * @param theInput
     */
    public void move(final String theInput) {

    }

    /**
     * Returns information about the current room Character is in.
     * @return String info about current room.
     */
    public String getCurrentRoomInfo() {

        String info = "This is information";

        return info;
    }

    /**
     * Evaluates the answer given by the Character to a trivia Question.
     */
    public boolean answerQuestion(final String theInput) {

        boolean validity = false;

        return validity;
    }

    /**
     * Evaluates if Character can move in Maze.
     * @return boolean if Character can move.
     */
    public boolean canMove() {

        // TODO: Evaluate nearby cells to see if traversable.

        boolean move = false;

        return move;
    }

    /**
     * Door holding current Question to be locked if answered incorrectly.
     */
    private void lockDoor(final Door theDoor) {

    }

    /**
     * Evaluates current game and determines whether it is won or lost.
     */
    public boolean isGameLost() {

        boolean game = false;

        return game;
    }

    @Override
    public void newGame() {

        myCorrectAnswers = 0;

    }

    @Override
    public void moveDown() {

        if (canMove() == true) {
            myCurrentRoom = myRooms[myWidth][myHeight + 1];
            myPcs.firePropertyChange(PROPERTY_MOVE_DOWN, null, myCurrentRoom);
        }

    }

    @Override
    public void moveUp() {

        if (canMove() == true) {
            myCurrentRoom = myRooms[myWidth][myHeight - 1];
        }

    }

    @Override
    public void moveLeft() {

        if (canMove() == true) {
            myCurrentRoom = myRooms[myWidth - 1][myHeight];
        }

    }

    @Override
    public void moveRight() {

        if (canMove() == true) {
            myCurrentRoom = myRooms[myWidth + 1][myHeight];
        }

    }

    @Override
    public void pauseGame() {

    }
}
