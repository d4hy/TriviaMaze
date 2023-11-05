/*
 * TriviaMaze
 * Fall 2023
 */
package model;

import controller.MazeControls;
import controller.PropertyChangedEnabledMazeControls;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Maze class contains data that will be responsible for current data of game.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class Maze implements PropertyChangedEnabledMazeControls {



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
    private Character myCharacter; // reference changed to private

    /**
     * Signals change from the model to the view.
     */
    private PropertyChangeSupport myPcs;

    /**
     * Constructor for new game of Maze, creating starting point of a Character and Rooms.
     */
    public Maze(final int theWidth, final int theHeight) {
        super();

        // Calculate the initial position for the Character to be in the middle of the screen.
        // since it is represented within the top left corner of a pixel, you have to subtract the tile size.
        int startX = (MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE) / 2;
        int startY = (MazeControls.MY_SCREEN_HEIGHT - MazeControls.MY_TILE_SIZE) / 2;

        // Instantiate the Character with the calculated initial position.
        myCharacter = new Character(startX, startY, MazeControls.MY_SCREEN_WIDTH, MazeControls.MY_SCREEN_HEIGHT);


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
        myCurrentRoom = myRooms[0][0];

    }

    @Override
    public void moveDown() {

        if (canMove()) {
            myCharacter.moveDown();
            myPcs.firePropertyChange(PROPERTY_MOVE_DOWN,
                    null, myCharacter);
        }

    }

    @Override
    public void moveUp() {

        if (canMove()) {
            myCharacter.moveUp();
            myPcs.firePropertyChange(PROPERTY_MOVE_UP, null, myCharacter);
        }

    }

    @Override
    public void moveLeft() {

        if (canMove()) {
            myCharacter.moveLeft();
            myPcs.firePropertyChange(PROPERTY_MOVE_LEFT,
                    null, myCharacter);
        }

    }

    @Override
    public void moveRight() {

        if (canMove()) {
            myCharacter.moveRight();
            myPcs.firePropertyChange(PROPERTY_MOVE_RIGHT,
                    null, myCharacter);
        }

    }

    @Override
    public void pauseGame() {

    }
    /**
     * adds an object as a listener to the propertyChangeSupport object.
     * @param theListener The PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {

    }

    /**
     * adds an object as a listener to the propertyChangeSupport object.
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {

    }

    /**
     * removes an object as a listener to the propertyChangeSupport object.
     * @param theListener The PropertyChangeListener to be removed
     */
    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {

    }

    /**
     * removes an object as a listener to the propertyChangeSupport object.
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {

    }
}
