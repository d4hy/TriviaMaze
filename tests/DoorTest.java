package tests;
/*
 * TriviaMaze
 * Fall 2023
 */
import controller.Question;
import model.Door;
import model.QuestionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * This program will be used to test the methods in
 * the Door class.
 * @author David Hoang
 * @author Faith Capito
 * @version Fall 2023
 *
 */
class DoorTest {
    /**
     * A test fixture for the true results.
     */
    private static final String SHOULD_BE_TRUE = "This should be true";

    /**
     * Test fixture of a Door to be used for tests.
     */
    private Door myTestDoor;


    @BeforeEach
    void setUp() {
        myTestDoor = new Door();
    }
    @Test
    void testTrueForLocked()  {
        myTestDoor.lock();
        assertTrue(myTestDoor.isLocked(), SHOULD_BE_TRUE);


    }
    @Test
    void testFalseForLocked()  {

        assertFalse(myTestDoor.isLocked(), SHOULD_BE_TRUE);


    }
}