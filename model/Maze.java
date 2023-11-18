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
     * Constant to use for assigning right door in rooms.
     */
    private static final String RIGHT_DOOR = "Right";

    /**
     * Constant to use for assigning left door in rooms.
     */
    private static final String LEFT_DOOR = "Left";

    /**
     * Constant to use for assigning top door in rooms.
     */
    private static final String TOP_DOOR = "Top";

    /**
     * Constant to use for assigning bottom door in rooms.
     */
    private static final String BOTTOM_DOOR = "Bottom";

    /**
     * Constant to use for reaching the furthest rooms in perimeter of maze.
     */
    private static final int ENDPOINT = 3;
    /**
     * Number of correct answers that the current Character has answered.
     */
    private static int myCorrectAnswers;


    /**
     * Field to show if the character can currently move.
     */

    private boolean myCanMove;
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
     * The status of the game if it is over.
     */
    private boolean  myGameOverStatus;

    /**
     * Character that is in Maze.
     */
    private Character myCharacter;

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
        // since it is represented within the top left corner of a pixel, you have to subtract
        // the tile size.
        final int startX = (MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE) / 2;
        final int startY = (MazeControls.MY_SCREEN_HEIGHT - MazeControls.MY_TILE_SIZE) / 2;

        // Instantiate the Character with the calculated initial position.
        myCharacter = new Character(startX, startY, MazeControls.MY_SCREEN_WIDTH,
                MazeControls.MY_SCREEN_HEIGHT);


        myWidth = theWidth;
        myHeight = theHeight;
        myPcs = new PropertyChangeSupport(this);
        createMaze();
        setMoveTrue();
        setMyGameOverStatus(false);


    }

    /**
     * Fills the maze with Rooms and sets initial game values.
     */
    public void createMaze() {

        myCorrectAnswers = 0;
        myRooms = new Room[myWidth][myHeight];

        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                myRooms[i][j] = new Room();
            }
        }

        createRooms();
        assignDoors();
        myCurrentRoom = myRooms[0][0];
        myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);


    }

    /**
     * Creates the rooms in Maze with only the doors that should exist in each.
     * Ex. Room [0][0] should only have right and bottom doors.
     */
    private void createRooms() {

        createTopRowDoors();
        createLeftColDoors();
        createBottomRowDoors();
        createRightColDoors();

        for (int i = 1; i < myWidth - 1; i++) {
            for (int j = 1; j < myHeight - 1; j++) {
                myRooms[i][j].setDoor(RIGHT_DOOR);
                myRooms[i][j].setDoor(LEFT_DOOR);
                myRooms[i][j].setDoor(TOP_DOOR);
                myRooms[i][j].setDoor(BOTTOM_DOOR);
            }
        }

    }
    /**
     * Print information about the doors of the current room.
     */
    public void printCurrentRoomDoors() {
        System.out.println("Current Room Doors:");
        System.out.println("Left Door: " + (myCurrentRoom.getLeftDoor() != null
                ? "Exists" : "Not Exists"));
        System.out.println("Right Door: " + (myCurrentRoom.getRightDoor() != null
                ? "Exists" : "Not Exists"));
        System.out.println("Top Door: " + (myCurrentRoom.getTopDoor() != null
                ? "Exists" : "Not Exists"));
        System.out.println("Bottom Door: " + (myCurrentRoom.getBottomDoor() != null
                ? "Exists" : "Not Exists"));
    }

    /**
     * Sets the doors of top row in maze.
     */
    private void createTopRowDoors() {

        // assigning outside the loop since it only has to be done once.
        myRooms[0][0].setDoor(RIGHT_DOOR);
        myRooms[0][0].setDoor(BOTTOM_DOOR);
        myRooms[0][ENDPOINT].setDoor(LEFT_DOOR);
        myRooms[0][ENDPOINT].setDoor(BOTTOM_DOOR);

        for (int i = 1; i < myWidth - 1; i++) {
            myRooms[0][i].setDoor(LEFT_DOOR);
            myRooms[0][i].setDoor(RIGHT_DOOR);
            myRooms[0][i].setDoor(BOTTOM_DOOR);
        }

    }

    /**
     * Sets the doors of left column in maze.
     */
    private void createLeftColDoors() {

        // sets [3, 0]
        // assigning outside the loop since it only has to be done once.
        myRooms[ENDPOINT][0].setDoor(RIGHT_DOOR);
        myRooms[ENDPOINT][0].setDoor(TOP_DOOR);

        for (int i = 1; i < myHeight - 1; i++) {
            myRooms[i][0].setDoor(TOP_DOOR);
            myRooms[i][0].setDoor(RIGHT_DOOR);
            myRooms[i][0].setDoor(BOTTOM_DOOR);
        }


    }

    /**
     * Sets the doors of bottom row in maze.
     */
    private void createBottomRowDoors() {

        // sets [3, 3]
        // assigning outside the loop since it only has to be done once.
        myRooms[ENDPOINT][ENDPOINT].setDoor(LEFT_DOOR);
        myRooms[ENDPOINT][ENDPOINT].setDoor(TOP_DOOR);

        for (int i = 1; i < myWidth - 1; i++) {
            myRooms[ENDPOINT][i].setDoor(TOP_DOOR);
            myRooms[ENDPOINT][i].setDoor(RIGHT_DOOR);
            myRooms[ENDPOINT][i].setDoor(LEFT_DOOR);
        }

    }

    /**
     * Sets the doors of right column in maze.
     */
    private void createRightColDoors() {

//        myRooms[ENDPOINT][1].setDoor();
        for (int i = 1; i < myWidth - 1; i++) {
            myRooms[i][ENDPOINT].setDoor(TOP_DOOR);
            myRooms[i][ENDPOINT].setDoor(BOTTOM_DOOR);
            myRooms[i][ENDPOINT].setDoor(LEFT_DOOR);
        }

    }

    /**
     * Method that reassigns the doors of each room to properly reference each other.
     * Ex. Right door in [0][0] should be the same as left door in [0][1].
     */
    private void assignDoors() {

        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {

                // assigns all rooms that have both left and top doors.
                if (j > 0 && i > 0) {
                    myRooms[i][j].assignLeftDoor(myRooms[i][j - 1].getRightDoor());
                    myRooms[i][j].assignTopDoor(myRooms[i - 1][j].getBottomDoor());
                }

                // assigns all rooms that have both right and bottom doors.
                if (j < myHeight - 2 && i < myWidth - 2) {
                    myRooms[i][j].assignRightDoor(myRooms[i][j + 1].getLeftDoor());
                    myRooms[i][j].assignBottomDoor(myRooms[i + 1][j].getTopDoor());
                }

                // assigns left col top doors
                if (i > 0 && j == 0) {
                    myRooms[i][j].assignTopDoor(myRooms[i - 1][j].getBottomDoor());

                }

                // assigns top row left doors
                if (i == 0 && j > 0) {
                    myRooms[i][j].assignLeftDoor(myRooms[i][j - 1].getRightDoor());

                }

                // assigns right doors of last row.
                if (i == ENDPOINT && j < myHeight - 2) {
                    myRooms[i][j].assignRightDoor(myRooms[i][j + 1].getLeftDoor());
                }

                // assigns bottom doors of right column.
                if (i < myWidth - 2 && j == ENDPOINT) {
                    myRooms[i][j].assignBottomDoor(myRooms[i + 1][j].getTopDoor());
                }

            }
        }

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

        final String info = "This is information";

        return info;
    }

    /**
     * Evaluates the answer given by the Character to a trivia Question.
     */
    public boolean answerQuestion(final String theInput) {

        final boolean validity = false;

        return validity;
    }

    /**
     * Evaluates if Character can move in Maze.
     * @return boolean if Character can move.
     */
    public boolean canMove() {

        return myCanMove;
    }

    /**
     * Sets it to where the Character can move.
     */
    private void setMoveTrue() {
        myCanMove = true;

        //notifies that just has been unfrozen, usually after answering a question.
        myPcs.firePropertyChange(PROPERTY_FREEZE, null, myCanMove);
    }

    /**
     * Sets it to where the Character can't move.
     */
    private void setMoveFalse() {
        myCanMove = false;
        //notifies that the user is frozen in place, usually when they are answering a question.
        myPcs.firePropertyChange(PROPERTY_FREEZE, null, myCanMove);

    }


    /**
     * Door holding current Question to be locked if answered incorrectly.
     */
    private void lockDoor(final Door theDoor) {

    }

    /**
     * Returns if the game is lost or not.
     */
    public boolean isGameLost() {

        return myGameOverStatus;
    }

    /**
     * Sets the game to true or false based on the status passed.
     * @param theStatus of the game you are trying to set.
     */
    private void setMyGameOverStatus(final boolean theStatus) {
        myGameOverStatus = theStatus;
        //notifies pcs that it changed
        myPcs.firePropertyChange(PROPERTY_GAME_OVER, null, myGameOverStatus);
    }


    @Override
    public void newGame() {
        // Calculate the initial position for the Character to be in the middle of the screen.
        // since it is represented within the top left corner of a pixel, you have to subtract
        // the tile size.
        final int startX = (MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE) / 2;
        final int startY = (MazeControls.MY_SCREEN_HEIGHT - MazeControls.MY_TILE_SIZE) / 2;

        // Instantiate the Character with the calculated initial position.
        myCharacter = new Character(startX, startY, MazeControls.MY_SCREEN_WIDTH,
                MazeControls.MY_SCREEN_HEIGHT);
        setMoveTrue();
        myCorrectAnswers = 0;
        createMaze();
        // Print the door status
        printMazeDoorStatus();
        setMyGameOverStatus(false);


        myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);
        myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);

    }


    /**
     * Checks if the character is at the right door.
     *
     * @return true if the character is at the right door, false otherwise.
     */
    private boolean checkIfAtRightDoor() {


        // Check if the character's position is at the right door
        // and if the Y coordinate is within the vertical range of the right door.
        return myCharacter.getCurrentPosition().getX()
                == MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE
                && myCharacter.getCurrentPosition().getY()
                >= (MazeControls.MY_SCREEN_HEIGHT / 2.0)
                - (MazeControls.MY_TILE_SIZE / 2.0)
                && myCharacter.getCurrentPosition().getY()
                <= (MazeControls.MY_SCREEN_HEIGHT / 2.0)
                + (MazeControls.MY_TILE_SIZE / 2.0);
    }
    /**
     * Checks if the character is at the left door.
     *
     * @return true if the character is at the left door, false otherwise.
     */
    private boolean checkIfAtLeftDoor() {
        // Check if the character's position is at the left door
        // and if the Y coordinate is within the vertical range of the left door.
        return myCharacter.getCurrentPosition().getX() == 0
                && myCharacter.getCurrentPosition().getY()
                >= (MazeControls.MY_SCREEN_HEIGHT / 2.0)
                - (MazeControls.MY_TILE_SIZE / 2.0)
                && myCharacter.getCurrentPosition().getY()
                <= (MazeControls.MY_SCREEN_HEIGHT / 2.0)
                + (MazeControls.MY_TILE_SIZE / 2.0);
    }

    /**
     * Checks if the character is at the top door.
     *
     * @return true if the character is at the top door, false otherwise.
     */
    private boolean checkIfAtTopDoor() {
        // Check if the character's position is at the top door
        // and if the X coordinate is within the horizontal range of the top door.
        return myCharacter.getCurrentPosition().getY() == 0
                && myCharacter.getCurrentPosition().getX()
                >= (MazeControls.MY_SCREEN_WIDTH / 2.0)
                - (MazeControls.MY_TILE_SIZE / 2.0)
                && myCharacter.getCurrentPosition().getX()
                <= (MazeControls.MY_SCREEN_WIDTH / 2.0)
                + (MazeControls.MY_TILE_SIZE / 2.0);
    }
    /**
     * Checks if the character is at the bottom door.
     *
     * @return true if the character is at the bottom door, false otherwise.
     */
    private boolean checkIfAtBottomDoor() {
        // Check if the character's position is at the bottom door
        // and if the X coordinate is within the horizontal range of the right door.
        return myCharacter.getCurrentPosition().getY() == MazeControls.MY_SCREEN_HEIGHT
                - MazeControls.MY_TILE_SIZE
                && myCharacter.getCurrentPosition().getX()
                >= (MazeControls.MY_SCREEN_WIDTH / 2.0)
                - (MazeControls.MY_TILE_SIZE / 2.0)
                && myCharacter.getCurrentPosition().getX()
                <= (MazeControls.MY_SCREEN_WIDTH / 2.0)
                + (MazeControls.MY_TILE_SIZE / 2.0);
    }
    /**
     * Moves the character down.
     */
    @Override
    public void moveDown() {
        // Moves the character
        if (canMove()) {
            myCharacter.moveDown();
            myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);
        }

        /*
         Checks if the character's position is at the bottom door if it exists.
        Will prompt for a question if the game isn't over and if the doors question isn't
        answered.
        */
        if (myCurrentRoom.getBottomDoor() != null && checkIfAtBottomDoor() && !isGameLost()) {

            // if the question hasn't been prompted for the question and
            // freeze the character in place, so they can answer.
            if (!myCurrentRoom.getBottomDoor().isMyQuestionPrompted()) {
                setMoveFalse();


            }

            System.out.println("At bottom door");

            // Character is at the left door position
            // Your code here...

            // Fire property change support and change into the next room

            //myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);

            // Freeze until otherwise
            // Add your code for freezing here...

        }
    }


    /**
     * Moves the character up.
     *
     */
    @Override
    public void moveUp() {

        // Moves the character
        if (canMove()) {
            myCharacter.moveUp();
            myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);
        }


        /*
         Checks if the character's position is at the top door if it exists.
        Will prompt for a question if the game isn't over and if the doors question isn't
        answered.
        */
        if (myCurrentRoom.getTopDoor() != null && checkIfAtTopDoor() && !isGameLost()) {
            // if the question hasn't been prompted for the question and
            // freeze the character in place, so they can answer.
            if (!myCurrentRoom.getTopDoor().isMyQuestionPrompted()) {
                setMoveFalse();


            }

            System.out.println("At Top door");

            // Character is at the left door position
            // Your code here...

            // Fire property change support and change into the next room

            //myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);

            // Freeze until otherwise
            // Add your code for freezing here...

        }

    }

    /**
     * Moves the character left.
     *
     */
    @Override
    public void moveLeft() {

        // Moves the character
        if (canMove()) {
            myCharacter.moveLeft();
            myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);
        }

        /*
         Checks if the character's position is at the top door if it exists.
        Will prompt for a question if the game isn't over and if the doors question isn't
        answered.
        */
        if (myCurrentRoom.getLeftDoor() != null && checkIfAtLeftDoor() && !isGameLost()) {


            // if the question hasn't been prompted for the question and
            // freeze the character in place, so they can answer.
            if (!myCurrentRoom.getLeftDoor().isMyQuestionPrompted()) {
                setMoveFalse();


            }
            System.out.println("At Left door");

            // Character is at the left door position
            // Your code here...

            // Fire property change support and change into the next room

           // myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);

            // Freeze until otherwise
            // Add your code for freezing here...

        }

    }

    /**
     * Moves the character right.
     *
     */
    @Override
    public void moveRight() {

        // Moves the character
        if (canMove()) {
            myCharacter.moveRight();
            myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);
        }

           /*
         Checks if the character's position is at the top door if it exists.
        Will prompt for a question if the game isn't over and if the doors question isn't
        answered.
        */
        if (myCurrentRoom.getRightDoor() != null && checkIfAtRightDoor() && !isGameLost()) {
            // if the question hasn't been prompted for the question and
            // freeze the character in place, so they can answer.
            if (!myCurrentRoom.getLeftDoor().isMyQuestionPrompted()) {
                setMoveFalse();
            }
            System.out.println("At Right door");

            // Character is at the left door position
            // Your code here...

            // Fire property change support and change into the next room

           // myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);

            // Freeze until otherwise
            // Add your code for freezing here...

        }

    }

    /**
     * Prints the door status of each room in the maze.
     */
    public void printMazeDoorStatus() {
        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                System.out.println("Door status for Room [" + i + "][" + j + "]:");
                System.out.println("Left Door: " + (myRooms[i][j].getLeftDoor() != null
                        ? "Exists" : "Not Exists"));
                System.out.println("Right Door: " + (myRooms[i][j].getRightDoor() != null
                        ? "Exists" : "Not Exists"));
                System.out.println("Top Door: " + (myRooms[i][j].getTopDoor() != null
                        ? "Exists" : "Not Exists"));
                System.out.println("Bottom Door: " + (myRooms[i][j].getBottomDoor() != null
                        ? "Exists" : "Not Exists"));
                System.out.println("------------------------");
            }
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
        myPcs.addPropertyChangeListener(theListener);
    }

    /**
     * adds an object as a listener to the propertyChangeSupport object.
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }

    /**
     * removes an object as a listener to the propertyChangeSupport object.
     * @param theListener The PropertyChangeListener to be removed
     */
    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

    /**
     * removes an object as a listener to the propertyChangeSupport object.
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }
}
