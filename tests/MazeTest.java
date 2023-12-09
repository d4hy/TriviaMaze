package tests;

import model.Maze;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myTestMaze.getCurrentRoom().getRightDoor().setMyQuestionNotPromptedStatus(false);
        final int moveRightToRightDoor = 54;
        for (int i = 0; i < moveRightToRightDoor; i++) {
            myTestMaze.moveRight();
        }
        final int moveDownToRightDoor = 15 ;
        for (int i = 0; i < moveDownToRightDoor; i++) {
            myTestMaze.moveDown();
        }
        final String expected = """
                Current Room's row and column:[0][1], the game is lost:false, the game is won:false
                Top Door Status:null
                Bottom Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Right Door Status:exists, isLocked:false, Question has not been prompted:true, Question has been answered correctly:false
                Left Door Status:null""";
        assertEquals(expected, myTestMaze.toString(), SHOULD_BE_SAME);

    }

}