package tests;

import controller.MazeControls;
import org.junit.jupiter.api.BeforeEach;
import model.Character;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



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

    }

}