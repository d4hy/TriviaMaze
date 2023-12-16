/*
 * TriviaMaze
 * Fall 2023
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.MazeControls;
import model.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




/**
 *
 * This program will be used to test the methods in
 * the Character class.
 * @author David Hoang
 * @author Faith Capito
 * @version Fall 2023
 *
 */
class CharacterTest {
    /**
     * A test fixture for the false results.
     */
    private static final String SHOULD_BE_FALSE = "This should be False";

    /**
     * A test fixture for the true results.
     */
    private static final String SHOULD_BE_TRUE = "This should be true";
    /**
     * A test fixture for the same results.
     */
    private static final String SHOULD_BE_SAME = "This should be the same";
    /**
     * The starting x coordinate where the user will spawn.
     */
    private static final  int MY_START_X = 0;

    /**
     * The starting y coordinate where the user will spawn.
     */
    private static final int MY_START_Y = 0;
    /**
     * The boundaries of the character fixture.
     */
    private static final int  MY_WIDTH_HEIGHT = 100;

    /**
     * A test fixture for the Character class.
     */
    private Character myTestCharacter;

    @BeforeEach
    void setUp() {

        myTestCharacter = new Character(MY_START_X,
                MY_START_Y,  MY_WIDTH_HEIGHT ,  MY_WIDTH_HEIGHT);
    }

    @Test
    void testMoveRight()  {

        assertEquals(MY_START_X,
                myTestCharacter.getCurrentPosition().x, SHOULD_BE_SAME);
        myTestCharacter.moveRight();
        assertEquals(MazeControls.MY_ORIGINAL_TILE_SIZE,
               myTestCharacter.getCurrentPosition().x , SHOULD_BE_SAME);
        //moving right until the max boundary of the right side.
        final int move3Times = 3;
        for (int i = 0; i < move3Times; i++) {
            myTestCharacter.moveRight();
        }
        final String expectedDirection = "right";
        assertEquals(expectedDirection,
                myTestCharacter.getMyDirection() , SHOULD_BE_SAME);

        //because we have to boundary check the boundary of x and y will always be
        //(0,0) - (WIDTH-TILE-SIZE, HEIGHT-TILE-SIZE)
        final int expected = MY_WIDTH_HEIGHT - MazeControls.MY_TILE_SIZE;
        assertEquals(expected,
                myTestCharacter.getCurrentPosition().x , SHOULD_BE_SAME);

    }
    @Test
    void testMoveLeft()  {

        assertEquals(MY_START_X,
                myTestCharacter.getCurrentPosition().x, SHOULD_BE_SAME);
        myTestCharacter.moveLeft();
        assertEquals(0,
                myTestCharacter.getCurrentPosition().x , SHOULD_BE_SAME);
        //moving right until the max boundary of the right side.
        final int move4Times = 4;
        for (int i = 0; i < move4Times; i++) {
            myTestCharacter.moveRight();
        }
        final int expected = MY_WIDTH_HEIGHT - MazeControls.MY_TILE_SIZE;
        assertEquals(expected,
                myTestCharacter.getCurrentPosition().x , SHOULD_BE_SAME);
        for (int i = 0; i < move4Times; i++) {
            myTestCharacter.moveLeft();
        }
        final String expectedDirection = "left";
        assertEquals(expectedDirection,
                myTestCharacter.getMyDirection() , SHOULD_BE_SAME);
        //going back to the left boundary after moving to the right boundary
        assertEquals(0,
                myTestCharacter.getCurrentPosition().x , SHOULD_BE_SAME);

    }
    @Test
    void testMoveDown()  {

        assertEquals(MY_START_Y,
                myTestCharacter.getCurrentPosition().y, SHOULD_BE_SAME);
        myTestCharacter.moveDown();
        assertEquals(MazeControls.MY_ORIGINAL_TILE_SIZE,
                myTestCharacter.getCurrentPosition().y , SHOULD_BE_SAME);
        //moving down until the max boundary of the  bottom side.
        final int move4Times = 4;
        for (int i = 0; i < move4Times; i++) {
            myTestCharacter.moveDown();
        }
        final String expectedDirection = "down";
        assertEquals(expectedDirection,
                myTestCharacter.getMyDirection() , SHOULD_BE_SAME);
        //because we have to boundary check the boundary of x and y will always be
        //(0,0) - (WIDTH-TILE-SIZE, HEIGHT-TILE-SIZE)
        final int expected = MY_WIDTH_HEIGHT - MazeControls.MY_TILE_SIZE;
        assertEquals(expected,
                myTestCharacter.getCurrentPosition().y , SHOULD_BE_SAME);

    }

    @Test
    void testMoveUp()  {

        assertEquals(MY_START_X,
                myTestCharacter.getCurrentPosition().y, SHOULD_BE_SAME);
        myTestCharacter.moveDown();
        assertEquals(MazeControls.MY_ORIGINAL_TILE_SIZE,
                myTestCharacter.getCurrentPosition().y , SHOULD_BE_SAME);
        //moving right until the max boundary of the right side.
        final int move4Times = 4;
        for (int i = 0; i < move4Times; i++) {
            myTestCharacter.moveDown();
        }
        //because we have to boundary check the boundary of x and y will always be
        //(0,0) - (WIDTH-TILE-SIZE, HEIGHT-TILE-SIZE)
        final int expected = MY_WIDTH_HEIGHT - MazeControls.MY_TILE_SIZE;
        assertEquals(expected,
                myTestCharacter.getCurrentPosition().y , SHOULD_BE_SAME);
        for (int i = 0; i < move4Times; i++) {
            myTestCharacter.moveUp();
        }
        final String expectedDirection = "up";
        assertEquals(expectedDirection,
                myTestCharacter.getMyDirection() , SHOULD_BE_SAME);
        assertEquals(0,
                myTestCharacter.getCurrentPosition().y , SHOULD_BE_SAME);


    }
    @Test
    void testResetToSpawn() {
        assertEquals(MY_START_X,
                myTestCharacter.getCurrentPosition().y, SHOULD_BE_SAME);
        myTestCharacter.moveDown();
        assertEquals(MazeControls.MY_ORIGINAL_TILE_SIZE,
                myTestCharacter.getCurrentPosition().y , SHOULD_BE_SAME);
        //moving right until the max boundary of the right side.
        final int move4Times = 4;
        for (int i = 0; i < move4Times; i++) {
            myTestCharacter.moveDown();
        }
        //because we have to boundary check the boundary of x and y will always be
        //(0,0) - (WIDTH-TILE-SIZE, HEIGHT-TILE-SIZE)
        final int expected = MY_WIDTH_HEIGHT - MazeControls.MY_TILE_SIZE;
        assertEquals(expected,
                myTestCharacter.getCurrentPosition().y , SHOULD_BE_SAME);
        myTestCharacter.resetToSpawn();
        assertEquals(0,
                myTestCharacter.getCurrentPosition().y , SHOULD_BE_SAME);

    }



}