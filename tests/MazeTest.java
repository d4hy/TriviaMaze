/*
 * TriviaMaze
 * Fall 2023
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.Question;
import model.Door;
import model.Maze;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


/**
 * This program will be used to test the methods in
 *  the Maze class.
 *@author David Hoang
 *@author Faith Capito
 *@version Fall 2023
 */
class MazeTest {
    /**
     * Constant to use for constructor.
     */
    private static final int WIDTH_HEIGHT = 4;
    /**
     * A test fixture for the same results.
     */
    private static final String SHOULD_BE_SAME = "This should be the same";
    /**
     * A test fixture for the false results.
     */
    private static final String SHOULD_BE_FALSE = "This should be False";


    /**
     * A test fixture for entering the right room correctly after answering question correctly.
     */
    private static final String ENTER_RIGHT_ROOM_CORRECTLY = """
                Current Room's row and column:[0][1], the game is lost:false, the game is won:false
                Top Door Status:null
                Bottom Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true""";
    /**
     * Test fixture for maze to be used for tests.
     */
    private Maze myTestMaze;





    @BeforeEach
    void setUp() {

        myTestMaze = new Maze(WIDTH_HEIGHT, WIDTH_HEIGHT);
        myTestMaze.newGame();

    }
    @Test
    void testNewGame() {
        final String expected = """
                Current Room's row and column:[0][0], the game is lost:false, the game is won:false
                Top Door Status:null
                Bottom Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:null""";
        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

    }
    @Test
    void testAnsweringIncorrectlySquareRoomGameOver() {
        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        final int moveRightToRightDoor = 26;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }

        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(false);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        myTestMaze.getCurrentRoom().getRightDoor().lock();
        String expected = """
                Current Room's row and column:[0][1], the game is lost:false, the game is won:false
                Top Door Status:null
                Bottom Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Right Door Status:exists, isLocked:true, Question has not been prompted:false, Question has been answered correctly:false
                Left Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true""";
        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

        myTestMaze.getCurrentRoom().getBottomDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getBottomDoor().setMyQuestionNotPromptedStatus(false);

        final int moveDownToBottomDoor = 15;
        for (int i = 0; i < moveDownToBottomDoor; i++) {
            myTestMaze.moveDown();
        }
        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(false);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        myTestMaze.getCurrentRoom().getRightDoor().lock();

        myTestMaze.getCurrentRoom().getBottomDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(false);
        myTestMaze.getCurrentRoom().getBottomDoor().setMyQuestionNotPromptedStatus(false);
        myTestMaze.getCurrentRoom().getBottomDoor().lock();
        expected = """
                Current Room's row and column:[1][1], the game is lost:false, the game is won:false
                Top Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true
                Bottom Door Status:exists, isLocked:true, Question has not been prompted:false, Question has been answered correctly:false
                Right Door Status:exists, isLocked:true, Question has not been prompted:false, Question has been answered correctly:false
                Left Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false""";
        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

        myTestMaze.getCurrentRoom().getLeftDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getLeftDoor().setMyQuestionNotPromptedStatus(false);

        final int moveRightToLeftDoor = 26;
        for (int i = 0; i < moveRightToLeftDoor; i++) {
            myTestMaze.moveLeft();
        }
        myTestMaze.getCurrentRoom().getBottomDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(false);
        myTestMaze.getCurrentRoom().getBottomDoor().setMyQuestionNotPromptedStatus(false);
        myTestMaze.getCurrentRoom().getBottomDoor().lock();

        final int moveUpToTopDoor = 15;
        for (int i = 0; i < moveUpToTopDoor; i++) {
            myTestMaze.moveUp();
        }


        expected = """
                Current Room's row and column:[1][0], the game is lost:true, the game is won:false
                Top Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Bottom Door Status:exists, isLocked:true, Question has not been prompted:false, Question has been answered correctly:false
                Right Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true
                Left Door Status:null""";
        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);



    }
    @Test
    void testAnsweringCorrectlyAndPromptingQuestionToBottomRoom() {
        myTestMaze.getCurrentRoom().getBottomDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getBottomDoor().setMyQuestionNotPromptedStatus(false);

        final int moveDownToRightDoor = 15;
        for (int i = 0; i < moveDownToRightDoor; i++) {
            myTestMaze.moveDown();
        }
        final String expected = """
                Current Room's row and column:[1][0], the game is lost:false, the game is won:false
                Top Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true
                Bottom Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:null""";
        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

    }

    @Test
    void testAnsweringIncorrectlyStartingRoomGameOver() {
        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(false);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        myTestMaze.getCurrentRoom().getRightDoor().lock();
        myTestMaze.getCurrentRoom().getBottomDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(false);
        myTestMaze.getCurrentRoom().getBottomDoor().
                lock();
        myTestMaze.getCurrentRoom().getBottomDoor().setMyQuestionNotPromptedStatus(false);
        final int moveRightToRightDoor = 26;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }
        final String expected = """
                Current Room's row and column:[0][0], the game is lost:true, the game is won:false
                Top Door Status:null
                Bottom Door Status:exists, isLocked:true, Question has not been prompted:false, Question has been answered correctly:false
                Right Door Status:exists, isLocked:true, Question has not been prompted:false, Question has been answered correctly:false
                Left Door Status:null""";

        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);
    }
    @Test
    void testAnsweringCorrectlyAndPromptingQuestionToRightRoom() {
        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        final int moveRightToRightDoor = 26;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }


        assertEquals(ENTER_RIGHT_ROOM_CORRECTLY, myTestMaze.toString(), SHOULD_BE_SAME);
    }

    @Test
    void testSetMoveFalse() {
        final int moveRightToRightDoor = 26;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }

        assertFalse(myTestMaze.canMove(), SHOULD_BE_FALSE);

    }

    @Test
    void testSaveAndLoad() throws IOException, ClassNotFoundException {
        testNewGame();
        testAnsweringCorrectlyAndPromptingQuestionToRightRoom();
        final String saveLoad = "Successful.txt";
        myTestMaze.save(saveLoad);
        myTestMaze.newGame();
        testNewGame();
        myTestMaze = myTestMaze.load(saveLoad);
        assertEquals(ENTER_RIGHT_ROOM_CORRECTLY, myTestMaze.toString(), SHOULD_BE_SAME);



    }
    @Test
    void testGoingToTopRightRoomCorrectly() {
        testAnsweringCorrectlyAndPromptingQuestionToRightRoom();
        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        final int moveRightToRightDoor = 26;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }
        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }
        final String expected = """
                Current Room's row and column:[0][3], the game is lost:false, the game is won:false
                Top Door Status:null
                Bottom Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Right Door Status:null
                Left Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true""";

        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

    }
    @Test
    void testGoingToBottomLeftRoomCorrectly() {
        testAnsweringCorrectlyAndPromptingQuestionToBottomRoom();
        myTestMaze.getCurrentRoom().getBottomDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getBottomDoor().setMyQuestionNotPromptedStatus(false);

        final int moveDownToRightDoor = 15;
        for (int i = 0; i < moveDownToRightDoor; i++) {
            myTestMaze.moveDown();
        }
        myTestMaze.getCurrentRoom().getBottomDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getBottomDoor().setMyQuestionNotPromptedStatus(false);
        for (int i = 0; i < moveDownToRightDoor; i++) {
            myTestMaze.moveDown();
        }

        final String expected = """
                Current Room's row and column:[3][0], the game is lost:false, the game is won:false
                Top Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true
                Bottom Door Status:null
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:null""";

        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        final int moveRightToRightDoor = 26;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }


    }

    @Test
    void testCheatSpawnInRoomLeftOfBottomRightAndWin() {
        myTestMaze.cheatSpawnInRoomLeftOfBottomRight();

        String expected = """
                Current Room's row and column:[3][2], the game is lost:false, the game is won:false
                Top Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Bottom Door Status:null
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false""";
        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        final int moveRightToRightDoor = 26;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }
        expected = """
                Current Room's row and column:[3][3], the game is lost:false, the game is won:true
                Top Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Bottom Door Status:null
                Right Door Status:null
                Left Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true""";

        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);


    }
    @Test
    void testHandleUnPromptedStatusLeftDoor() {
        myTestMaze.cheatSpawnInRoomLeftOfBottomRight();
        final int moveLeftToLeftDoor = 26;
        for (int i = 0; i < moveLeftToLeftDoor; i++) {
            myTestMaze.moveLeft();
        }
        myTestMaze.getCurrentRoom().getLeftDoor().lock();

        final String expected = """
                Current Room's row and column:[3][2], the game is lost:false, the game is won:false
                Top Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Bottom Door Status:null
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:exists, isLocked:true, Question has not been prompted:true, Question has been answered correctly:false""";

        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

    }
    @Test
    void testHandleUnPromptedStatusBottomDoor() {
        final int moveDownToBottomDoor = 26;
        for (int i = 0; i < moveDownToBottomDoor; i++) {
            myTestMaze.moveDown();
        }
        myTestMaze.getCurrentRoom().getBottomDoor().lock();

        final String expected = """
                Current Room's row and column:[0][0], the game is lost:false, the game is won:false
                Top Door Status:null
                Bottom Door Status:exists, isLocked:true, Question has not been prompted:true, Question has been answered correctly:false
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:null""";

        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

    }

    @Test
    void testHandleUnPromptedStatusTopDoor() {
        myTestMaze.cheatSpawnInRoomLeftOfBottomRight();
        myTestMaze.getCurrentRoom().getTopDoor().setMyQuestionNotPromptedStatus(false);
        myTestMaze.getCurrentRoom().getTopDoor().setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        final int moveUpToTopDoor = 26;
        for (int i = 0; i < moveUpToTopDoor; i++) {
            myTestMaze.moveUp();
        }


        final String expected = """
                Current Room's row and column:[2][2], the game is lost:false, the game is won:false
                Top Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Bottom Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false""";

        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

    }
    @Test
    void testDoorsForDifferentQuestions() {

        final ArrayList<Door> gameQuestions = new ArrayList<>(myTestMaze.getMyDoors());
        final HashSet<Question> gameQuestionsNoDupes = new HashSet<>();

        for (Door door : gameQuestions) {
            gameQuestionsNoDupes.add(door.getMyQuestion());
        }

        assertEquals(gameQuestions.size(), gameQuestionsNoDupes.size(), SHOULD_BE_SAME);

    }

    @Test
    void testAddPropertyChangeListener() {
        // Create a flag to check if the listener was called
        final boolean[] listenerCalled = {false};
        // Create a mock listener for testing
        PropertyChangeListener mockListener = new PropertyChangeListener() {

            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                final String propertyName = evt.getPropertyName();
                if(propertyName.equals(myTestMaze.PROPERTY_CHARACTER_MOVE)) {
                    listenerCalled[0] = true;
                }

            }
        };
        myTestMaze.addPropertyChangeListener(mockListener);

        // Perform some operation that triggers a property change
        myTestMaze.moveDown();

        // Check if the listener was called
        assertTrue(listenerCalled[0], "The property change listener was not called.");
    }
    @Test
    void testRemovePropertyChangeListener() {
        // Create a flag to check if the listener was called
        final boolean[] listenerCalled = {false};

        // Create a mock listener for testing
        PropertyChangeListener mockListener = evt -> {
            final String propertyName = evt.getPropertyName();
            if (propertyName.equals(myTestMaze.PROPERTY_CHARACTER_MOVE)) {
                listenerCalled[0] = true;
            }
        };

        // Add the listener
        myTestMaze.addPropertyChangeListener(mockListener);

        // Remove the listener
        myTestMaze.removePropertyChangeListener(mockListener);

        // Perform some operation that triggers a property change
        myTestMaze.moveDown();

        // Check if the listener was not called
        assertFalse(listenerCalled[0], "The property change listener should"
                + " not be called after removal.");
    }





}