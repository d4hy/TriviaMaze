/*
 * TriviaMaze
 * Fall 2023
 */
package model;

import controller.MazeControls;
import controller.PropertyChangedEnabledMazeControls;
import controller.Question;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Maze class contains data that will be responsible for current data of game.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class Maze implements PropertyChangedEnabledMazeControls, Serializable {

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
     * Constant to be used to check if the current room has these types of doors.
     */
    private static final String[]
            DOORS_TO_CHECK = {RIGHT_DOOR, LEFT_DOOR, TOP_DOOR, BOTTOM_DOOR};

    /**
     * Constant to use for reaching the furthest rooms in perimeter of maze.
     */
    private static final int ENDPOINT = 3;

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
     * Array of all Doors within the Maze.
     */
    private ArrayList<Door> myDoors;

    /**
     * Columns in Maze.
     */
    private final int myColumns;

    /**
     * Rows in Maze.
     */
    private final int myRows;

    /**
     * The status of the game if it is over.
     */
    private boolean  myGameOverStatus;

    /**
     * The status of the game if the user won.
     */
    private boolean myGameWonStatus;

    /**
     * Character that is in Maze.
     */
    private Character myCharacter;

    /**
     * Signals change from the model to the view.
     */
    private PropertyChangeSupport myPcs;

    /**
     * Arraylist of Questions to be used throughout setup of Maze.
     */
    private ArrayList<Question> myQuestions = new ArrayList<>();

    /**
     *
     * Constructor for new game of Maze, creating starting point of a Character and Rooms.
     *
     * @param theColumns of how many rooms there are wide.
     * @param theRows of many rooms there are tall.
     */
    public Maze(final int theRows, final int theColumns) {
        super();


        // Calculate the initial position for the Character to be in the middle of the screen.
        // since it is represented within the top left corner of a pixel, you have to subtract
        // the tile size.
        final int startX = (MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE) / 2;
        final int startY = (MazeControls.MY_SCREEN_HEIGHT - MazeControls.MY_TILE_SIZE) / 2;

        // Instantiate the Character with the calculated initial position.
        myCharacter = new Character(startX, startY, MazeControls.MY_SCREEN_WIDTH,
                MazeControls.MY_SCREEN_HEIGHT);

        myColumns = theColumns;
        myRows = theRows;
        myPcs = new PropertyChangeSupport(this);
        setMoveTrue();
        setMyGameOverStatus(false);





    }

    /**
     * Fills the maze with Rooms and sets initial game values.
     */
    public void createMaze() {

        myRooms = new Room[myRows][myColumns];
        myDoors = new ArrayList<>();

        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myColumns; j++) {
                myRooms[i][j] = new Room();
            }
        }
        // Establishes connection to database and creates questions.
        QuestionDatabase.connectToDatabase();

        // Retrieves list of questions to use when creating Maze.
        myQuestions = QuestionDatabase.getQuestions();

        // Shuffles the list of Question each time a new game is
        // instantiated to have different questions to doors.
        Collections.shuffle(myQuestions);

        createRooms();
        assignDoors();
        assignQuestionsToDoors();
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

        for (int i = 1; i < myRows - 1; i++) {
            for (int j = 1; j < myColumns - 1; j++) {
                myRooms[i][j].setDoor(RIGHT_DOOR);
                myRooms[i][j].setDoor(LEFT_DOOR);
                myRooms[i][j].setDoor(TOP_DOOR);
                myRooms[i][j].setDoor(BOTTOM_DOOR);
            }
        }

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

        for (int i = 1; i < myColumns - 1; i++) {
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

        for (int i = 1; i < myRows - 1; i++) {
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

        for (int i = 1; i < myColumns - 1; i++) {
            myRooms[ENDPOINT][i].setDoor(TOP_DOOR);
            myRooms[ENDPOINT][i].setDoor(RIGHT_DOOR);
            myRooms[ENDPOINT][i].setDoor(LEFT_DOOR);
        }

    }

    /**
     * Sets the doors of right column in maze.
     */
    private void createRightColDoors() {

        for (int i = 1; i < myRows - 1; i++) {
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

        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myColumns; j++) {

                // assigns all rooms that have both left and top doors.
                if (j > 0 && i > 0) {
                    myRooms[i][j].assignLeftDoor(myRooms[i][j - 1].getRightDoor());
                    myRooms[i][j].assignTopDoor(myRooms[i - 1][j].getBottomDoor());
                }

                // assigns all rooms that have both right and bottom doors.
                if (j < myRows - 2 && i < myColumns - 2) {
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
                if (i == ENDPOINT && j < myColumns - 2) {
                    myRooms[i][j].assignRightDoor(myRooms[i][j + 1].getLeftDoor());
                }

                // assigns bottom doors of right column.
                if (i < myRows - 2 && j == ENDPOINT) {
                    myRooms[i][j].assignBottomDoor(myRooms[i + 1][j].getTopDoor());
                }

            }
        }

    }

    /**
     * Method to save the current state of the game into an object file.
     */
    public void save(final String theFileName) throws IOException {

        final FileOutputStream file = new FileOutputStream(theFileName);
        final ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this);
        out.close();
        file.close();
        System.out.println("State has been saved successfully.");

    }

    /**
     * Method that can load a previous state of the game from a file.
     * @return Maze with saved state.
     */
    public Maze load(final String theFileName) throws IOException, ClassNotFoundException {



        final FileInputStream file = new FileInputStream(theFileName);
        final ObjectInputStream in = new ObjectInputStream(file);
        final Maze maze = (Maze) in.readObject();
        in.close();
        file.close();
        System.out.println("State has been loaded.");
        myPcs.firePropertyChange(PROPERTY_LOAD, null,  maze);
        return maze;
    }

    /**
     * Method that gets the character object of the maze.
     * @return a character object that the maze class is referencing.
     */
    public Character getCharacter() {
        return myCharacter;
    }

    /**
     * Adds each Door object into a list of Doors that the Maze can then
     * distribute Questions to from the database.
     */
    private void assignQuestionsToDoors() {

        // Adds all active doors to list of doors after maze creation.
        // This will only check for bottom and right doors in all rooms,
        // avoiding adding doubles when some doors should be using a "shared" question.
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myColumns; j++) {
                if (myRooms[i][j].getRightDoor() != null) {
                    myDoors.add(myRooms[i][j].getRightDoor());
                }
                if (myRooms[i][j].getBottomDoor() != null) {
                    myDoors.add(myRooms[i][j].getBottomDoor());
                }
            }
        }

        for (int i = 0; i < myQuestions.size() && i < myDoors.size(); i++) {
            myDoors.get(i).setQuestion(myQuestions.get(i));
            System.out.println(myDoors.get(i).getMyQuestion().getQuestionText());
        }

    }

    public ArrayList<Door> getMyDoors() {
        return myDoors;
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
    public void setMoveTrue() {
        myCanMove = true;


    }

    /**
     * Sets it to where the Character can't move.
     */
    private void setMoveFalse() {
        myCanMove = false;


    }

    /**
     * Returns if the game is lost or not.
     * @return true if the game is lost, false if the game is not lost.
     */
    public boolean isGameLost() {

        return myGameOverStatus;
    }

    /**
     * Returns if the game is won or not.
     * @return True if the game is won, false if the game is not won.
     */
    public boolean isMyGameWon() {
        return myGameWonStatus;
    }

    /**
     * Method that gets the current room of the maze.
     * @return the current room of the maze.
     */
    public Room getCurrentRoom() {
        return myCurrentRoom;
    }

    /**
     * Sets the game to true or false based on the status passed.
     * @param theStatus of the game you are trying to set.
     */
    private void setMyGameOverStatus(final boolean theStatus) {
        myGameOverStatus = theStatus;
        if (theStatus) {

            System.out.println("GameOver");
        }
        //notifies pcs that it changed
        myPcs.firePropertyChange(PROPERTY_GAME_OVER, null, myGameOverStatus);
    }

    /**
     * Method starts and initializes the variables needed in order for a new game to start.
     */
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
        createMaze();

        setMyGameOverStatus(false);
        myGameWonStatus = false;

        System.out.println(this);
        myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);
        myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);

    }

    /**
     * Is meant to be a cheat to spawn in the room [3][2].
     */

    public void cheatSpawnInRoomLeftOfBottomRight() {
        final int row = 3;
        final int col = 2;
        myCurrentRoom = myRooms[row][col];
        myCharacter.resetToSpawn();
        myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);
        myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);
    }


    /**
     * Handles the interaction when the character is near a specific door.
     *
     * @param theDoorType The door with which the character is interacting.
     */
    private void handleDoorInteraction(final String theDoorType) {
        final int currentRow = getCurrentRoomRow();
        final int currentCol = getCurrentRoomCol();

        final boolean isValidMove;

        // Determine the new room coordinates based on the door's direction
        int newRow = currentRow;
        int newCol = currentCol;

        switch (theDoorType) {
            case RIGHT_DOOR:
                isValidMove = currentCol < ENDPOINT && myRooms[newRow][++newCol] != null;
                break;
            case LEFT_DOOR:
                isValidMove = currentCol > 0 && myRooms[newRow][--newCol] != null;
                break;
            case TOP_DOOR:
                isValidMove = currentRow > 0 && myRooms[--newRow][newCol] != null;
                break;
            case BOTTOM_DOOR:
                isValidMove = currentRow < ENDPOINT && myRooms[++newRow][newCol] != null;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + theDoorType);
        }

        // Move to the new room if the move is valid
        if (isValidMove) {
            myCurrentRoom = myRooms[newRow][newCol];
            System.out.println(this);
            //If we are at the bottom right room fire the property that the user won
            // and set the myGameWonn status to true
            if (newRow == ENDPOINT && newCol == ENDPOINT) {
                myGameWonStatus = true;
                myPcs.firePropertyChange(PROPERTY_GAME_WON, null, true);
            }
            myCharacter.resetToSpawn();
            myPcs.firePropertyChange(PROPERTY_CHARACTER_MOVE, null, myCharacter);
            myPcs.firePropertyChange(PROPERTY_ROOM_CHANGE, null, myCurrentRoom);
        }
    }
    /**
     * Checks if the character is near a specific door.
     *
     * @param theDoorType The type of door to check (e.g., "Right", "Left", "Top", "Bottom").
     * @return True if the character is near the specified door, false otherwise.
     */
    private boolean isCharacterNearDoor(final String theDoorType) {


        double doorX = 0;
        double doorY = 0;

        // Set door positions based on the door type
        switch (theDoorType) {
            case RIGHT_DOOR -> {
                doorX = MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE;
                doorY = MazeControls.MY_SCREEN_HEIGHT / 2.0;
            }
            case LEFT_DOOR -> {
                doorX = 0.0;
                doorY = MazeControls.MY_SCREEN_HEIGHT / 2.0;
            }
            case TOP_DOOR -> {
                doorX = MazeControls.MY_SCREEN_WIDTH / 2.0;
                doorY = 0.0;
            }
            case BOTTOM_DOOR -> {
                doorX = MazeControls.MY_SCREEN_WIDTH / 2.0;
                doorY = MazeControls.MY_SCREEN_HEIGHT - MazeControls.MY_TILE_SIZE;
            }
            default -> throw new IllegalStateException("Unexpected value:  " + theDoorType);
        }


        // Check if the character's position is near the specified door
        final boolean isNearDoor = myCharacter.getCurrentPosition().getX()
                >= doorX - MazeControls.MY_TILE_SIZE
                && myCharacter.getCurrentPosition().getX() <= doorX + MazeControls.MY_TILE_SIZE
                && myCharacter.getCurrentPosition().getY()
                >= doorY - MazeControls.MY_TILE_SIZE
                && myCharacter.getCurrentPosition().getY()
                <= doorY + MazeControls.MY_TILE_SIZE;

        // If the character is near a door, check if all doors have been incorrectly answered
        if (isNearDoor) {
            checkIfAllDoorsIncorrectlyAnswered();
            canReachBottomRight();

            // Additional actions to perform if the game is not over
            // You can add more code here based on your requirements
        }

        return isNearDoor;
    }
    /**
     * Checks if it is possible to reach the bottom-right room of the maze,
     * considering the question answers in each room. Sets the gameOverStatusToTrue
     * if we are unable to reach the bottom right room.
     */
    private void canReachBottomRight() {
        final boolean[][] visited = new boolean[myRooms.length][myRooms[0].length];

        if (!move(visited, myRooms, 0, 0)) {

            setMyGameOverStatus(true);
        }

    }

    /**
     * Recursive algorithm to see if there is a valid path to the exit.
     * @param theVisited array of rooms as a 2d array of boolean values,
     *      *  true if it is already visited false otherwise.
     * @param theRooms the 2d array of rooms to reference to
     *      *                      see if it is already a dead end or
     *                to check if its doors are locked.
     * @param theRow of the current room
     * @param theCol of the current room
     * @return true if there is valid path, false otherwise
     */
    private boolean move(final boolean[][] theVisited, final Room[][] theRooms,
                         final int theRow, final int theCol) {
        boolean success = false;
        final Room currentRoomCheck = theRooms[theRow][theCol];
        if (validMove(theVisited, theRooms, theRow, theCol)) {
            markVisited(theVisited, theRow, theCol);
            if (atExit(theVisited, theRow, theCol)) {
                return true;
            }
            if (currentRoomCheck.getBottomDoor() != null
                    && !currentRoomCheck.getBottomDoor().isLocked()) {
                success = move(theVisited, theRooms, theRow + 1, theCol); // down
            }
            if (!success && currentRoomCheck.getRightDoor() != null
                    && !currentRoomCheck.getRightDoor().isLocked()) {
                success = move(theVisited, theRooms, theRow, theCol + 1); // right
            }
            if (!success && currentRoomCheck.getTopDoor() != null
                    && !currentRoomCheck.getTopDoor().isLocked()) {
                success = move(theVisited, theRooms, theRow - 1, theCol); // up
            }
            if (!success && currentRoomCheck.getLeftDoor() != null
                    && !currentRoomCheck.getLeftDoor().isLocked()) {
                success = move(theVisited, theRooms, theRow, theCol - 1); // left
            }
            if (!success) { // Is a dead end so go to other options
                theRooms[theRow][theCol].setAsDeadEnd();
            }
        }
        return success;
    }

    /**
     *  Helper method to set if the  room is visited already.
     * @param theVisited array of rooms as a 2d array of boolean values,
     *  true if it is already visited false otherwise.
     * @param theRow of the current room
     * @param theCol of the current room
     */
    private  void markVisited(final boolean[][] theVisited,
                                    final int theRow, final int theCol) {
        theVisited[theRow][theCol] = true;
    }

    /**
     * Helper method to see if it as the exit.
     * @param theVisited array of rooms as a 2d array of boolean values,
     *      *                   true if it is already visited false otherwise.
     * @param theRow of the current room
     * @param theCol of the current room.
     * @return true if the room is at the exit, false otherwise.
     */
    private  boolean atExit(final boolean[][] theVisited, final int theRow, final int theCol) {

        return theRow == theVisited.length - 1 && theCol == theVisited[theRow].length - 1;
    }

    /**
     * helper method to check if a room is valid move to within, it is valid if
     * it isn't visited already or a dead end.
     * @param theVisited array of rooms as a 2d array of boolean values,
     *                   true if it is already visited false otherwise.
     * @param theCopyOfRooms the 2d array of rooms to reference to
     *                      see if it is already a dead end.
     * @param theRow of the current room
     * @param theCol of the current room.
     * @return true if the room is valid to move into false otherwise.
     */
    private  boolean validMove(final boolean[][] theVisited,
                               final Room[][]theCopyOfRooms,
                               final int theRow, final int theCol) {
        return theRow >= 0 && theRow < myRooms.length
                && theCol >= 0 && theCol < myRooms[theRow].length
                //check  within bounds if the room has not been visited yet, is not a dead end.
                && !theVisited[theRow][theCol] && !theCopyOfRooms[theRow][theCol].isDeadEnd();
    }

    /**
     * Checks if all doors in the current room have been prompted and answered incorrectly.
     * If so, sets myGameOverStatus to true and fires the corresponding property change.
     */
    private void checkIfAllDoorsIncorrectlyAnswered() {
        // Initialize a boolean variable to track if all doors have been incorrectly answered.
        boolean allDoorsIncorrectlyAnswered = true;

        // Iterate through each door direction in the array of doors to check.
        for (String doorDirection : DOORS_TO_CHECK) {
            // Get the Door object for the current direction.
            final Door door = getDoorForDirection(doorDirection);

            // Check if the door is not null (exists).
            if (door != null) {
                // Update the boolean variable based on the conditions.
                allDoorsIncorrectlyAnswered = allDoorsIncorrectlyAnswered
                        && !door.hasMyQuestionBeenNotPrompted()
                        && !door.hasMyQuestionBeenAnsweredCorrectly();
            }
        }

        // If all doors have been incorrectly answered, set game over status to true.
        if (allDoorsIncorrectlyAnswered) {
            setMyGameOverStatus(true);
        }
    }
    /**
     * Method that returns the current row that is the current room.
     * @return -1 if the room doesn't exist, otherwise a value >= 0
     */
    public int getCurrentRoomRow() {
        int res = 0;
        for (int i = 0; i < myRooms.length; i++) {
            for (int j = 0; j < myRooms[i].length; j++) {
                if (myRooms[i][j] == myCurrentRoom) {
                    res = i;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Method that returns the current column that is the current room.
     * @return -1 if the room doesn't exist, otherwise a value >= 0
     */
    public int getCurrentRoomCol() {
        int res = 0;
        for (int i = 0; i < myRooms.length; i++) {
            for (int j = 0; j < myRooms[i].length; j++) {
                if (myRooms[i][j] == myCurrentRoom) {
                    res = j;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Gets the property name for prompting a question based on the door's direction.
     *
     * @param theDoorType  The door for which to get the property name.
     * @return The property name for prompting a question.
     */
    private String getPromptQuestionPropertyName(final String theDoorType) {
        final String result;
        switch (theDoorType) {
            case RIGHT_DOOR:
                result = PROPERTY_PROMPT_QUESTION_RIGHT_DOOR;
                break;
            case LEFT_DOOR:
                result = PROPERTY_PROMPT_QUESTION_LEFT_DOOR;
                break;
            case TOP_DOOR:
                result = PROPERTY_PROMPT_QUESTION_TOP_DOOR;
                break;
            case BOTTOM_DOOR:
                result = PROPERTY_PROMPT_QUESTION_BOT_DOOR;
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    /**
     * Checks all doors in the current room for interaction.
     */
    private void checkDoors() {
        for (String doorDirection : DOORS_TO_CHECK) {
            final Door door = getDoorForDirection(doorDirection);
            // Check if the door should be handled based on conditions
            if (shouldHandleDoorInteraction(door, doorDirection)) {
                System.out.println("At " + doorDirection + " door");


                // Handle unprompted door
                if (door.hasMyQuestionBeenNotPrompted()) {
                    handleUnpromptedDoor(door, doorDirection);

                    // Handle answered door
                } else if (!door.hasMyQuestionBeenNotPrompted()
                        && door.hasMyQuestionBeenAnsweredCorrectly()) {
                    handleAnsweredDoor(doorDirection);

                }

            }
        }
    }

    /**
     * Gets the corresponding door based on the direction.
     *
     * @param theDoorDirection The direction of the door.
     * @return The corresponding door.
     */
    private Door getDoorForDirection(final String theDoorDirection) {
        final Door result;
        switch (theDoorDirection) {
            case RIGHT_DOOR:
                result = myCurrentRoom.getRightDoor();
                break;
            case LEFT_DOOR:
                result = myCurrentRoom.getLeftDoor();
                break;
            case TOP_DOOR:
                result = myCurrentRoom.getTopDoor();
                break;
            case BOTTOM_DOOR:
                result = myCurrentRoom.getBottomDoor();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + theDoorDirection);
        }
        return result;
    }

    /**
     * Checks if the door should be handled for interaction.
     *
     * @param theDoor           The door to check.
     * @param theDoorDirection  The direction of the door.
     * @return True if the door should be handled, false otherwise.
     */
    private boolean shouldHandleDoorInteraction(final Door theDoor,
                                                final String theDoorDirection) {
        return theDoor != null && isCharacterNearDoor(theDoorDirection);
    }

    /**
     * Handles unprompted door interaction.
     * @param theDoor The Door object.
     * @param theDoorDirection The direction of the door.
     */
    private void handleUnpromptedDoor(final Door theDoor, final String theDoorDirection) {
        if (theDoor.hasMyQuestionBeenNotPrompted()) {
            System.out.println(theDoor.getMyQuestion().getQuestionText());
            setMoveFalse();
            // Fire property change event to prompt the question
            myPcs.firePropertyChange(getPromptQuestionPropertyName(theDoorDirection),
                    null, myCurrentRoom);

        }
    }

    /**
     * Handles door interaction when the question is answered.
     *
     * @param theDoorDirection The direction of the door.
     */
    private void handleAnsweredDoor(final String theDoorDirection) {
        // Move within the corresponding room
        handleDoorInteraction(theDoorDirection);

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
        // Check doors for interaction
        checkDoors();
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
        // Check doors for interaction
        checkDoors();



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
        // Check doors for interaction
        checkDoors();


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
        // Check doors for interaction
        checkDoors();

    }

    /**
     * Will be  in the format:
     * Current Room's row and column:[][], the game is lost: ,the game is won:
     * Top door status:
     * Bottom door status:
     * Right Door Status:
     * Left Door status:
     * .
     * @return a String with the state of the maze.
     */
    @Override
    public String toString() {
        final StringBuilder stats = new StringBuilder(30);
        final String leftBracket = "[";
        final String rightBracket = "]";
        final String comma = ", ";
        stats.append("Current Room's row and column:").
                append(leftBracket).append(getCurrentRoomRow()).
                append(rightBracket).
                append(leftBracket).append(getCurrentRoomCol()).append(rightBracket).
                append(comma).append("the game is lost:").append(isGameLost()).
                append(comma).append("the game is won:").append(isMyGameWon());
        appendDoorStatus(stats, TOP_DOOR, myCurrentRoom.getTopDoor(),
                comma);
        appendDoorStatus(stats, BOTTOM_DOOR, myCurrentRoom.getBottomDoor(),
                comma);
        appendDoorStatus(stats, RIGHT_DOOR, myCurrentRoom.getRightDoor(),
                comma);
        appendDoorStatus(stats, LEFT_DOOR, myCurrentRoom.getLeftDoor(),
                comma);
        return stats.toString();
    }

    /**
     * helper method to use within toString.
      * @param theStats theStringBuilder Object we are referencing.
     * @param theDoorName to add within the toString.
     * @param theDoor to use to add for the toString.
     * @param theComma string to add within the toString.
     */
    private void appendDoorStatus(final StringBuilder theStats, final String theDoorName,
                                  final Door theDoor, final String theComma) {
        theStats.append("\n").append(theDoorName).append(" Door Status:");
        if (theDoor == null) {
            theStats.append("null");
        } else {
            theStats.append("exists").
                    append(theComma).append("isLocked:").append(theDoor.isLocked()).
                    append(theComma).append("Question has not been prompted:").
                    append(theDoor.hasMyQuestionBeenNotPrompted()).
                    append(theComma).append("Question has been answered correctly:").
                    append(theDoor.hasMyQuestionBeenAnsweredCorrectly());
        }
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
     * removes an object as a listener to the propertyChangeSupport object.
     * @param theListener The PropertyChangeListener to be removed
     */
    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }
}
