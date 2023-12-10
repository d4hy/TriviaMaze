/*
 * TriviaMaze
 * Fall 2023
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



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
    void testAnsweringCorrectlyAndPromptingQuestionToRightRoom() {
        myTestMaze.getCurrentRoom().getRightDoor().
                setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        final int moveRightToRightDoor = 26;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }
//        final int moveDownToRightDoor = 15 ;
//        for (int i = 0; i < moveDownToRightDoor; i++) {
//            myTestMaze.moveDown();
//        }
        final String expected = """
                Current Room's row and column:[0][1], the game is lost:false, the game is won:false
                Top Door Status:null
                Bottom Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:exists, isLocked:false, Question has not been prompted:false, Question has been answered correctly:true""";
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
    void testAnsweringIncorrectlyRoomsSquareGameOver() {
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
                Current Room's row and column:[0][1], the game is lost:true, the game is won:false
                Top Door Status:null
                Bottom Door Status:exists, isLocked:true, Question has not been prompted:false, Question has been answered correctly:false
                Right Door Status:exists, isLocked:true, Question has not been prompted:false, Question has been answered correctly:false
                Left Door Status:null""";

        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);
    }

    @Test
    void testSetMoveFalse() {
        final int moveRightToRightDoor = 26;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }

        assertFalse(myTestMaze.canMove(), SHOULD_BE_FALSE);

    }





}