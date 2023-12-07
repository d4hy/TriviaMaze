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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
     * A test fixture for the false results.
     */
    private static final String SHOULD_BE_FALSE = "This should be False";

    /**
     * A test fixture for the same results.
     */
    private static final String SHOULD_BE_SAME = "This should be the same";


    /**
     * A test question text fixture.
     */
    private static final String BEA_BEST = "Is Beabadoobee the best?";

    /**
     * The true or false question type.
     */
    private static final String TRUE_FALSE = "True False";

    /**
     * The true option type.
     */
    private static final String TRUE = "True";

    /**
     * The false option type.
     */
    private static final String FALSE = "False";




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

    @Test
    void testTrueHasMyQuestionBeenNotPrompted()  {
        myTestDoor.setMyQuestionNotPromptedStatus(true);
        assertTrue(myTestDoor.hasMyQuestionBeenNotPrompted(), SHOULD_BE_TRUE);


    }


    @Test
    void testFalseHasMyQuestionBeenNotPrompted()  {
        myTestDoor.setMyQuestionNotPromptedStatus(false);
        assertFalse(myTestDoor.hasMyQuestionBeenNotPrompted(), SHOULD_BE_FALSE);


    }

    @Test
    void testTrueHasMyQuestionBeenAnsweredCorrectly()  {
        myTestDoor.setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        assertTrue(myTestDoor.hasMyQuestionBeenAnsweredCorrectly(), SHOULD_BE_TRUE);


    }
    @Test
    void testFalseHasMyQuestionBeenAnsweredCorrectly()  {
        myTestDoor.setMyQuestionHasBeenAnsweredCorrectlyStatus(false);
        assertFalse(myTestDoor.hasMyQuestionBeenAnsweredCorrectly(), SHOULD_BE_FALSE);


    }

    @Test
    void testSetQuestionOfDoor()  {
        final Question trueFalseQuestion =
                QuestionFactory.createQuestion(BEA_BEST, TRUE_FALSE,
                        TRUE, FALSE, null, null);
        myTestDoor.setQuestion(trueFalseQuestion);
        assertEquals(myTestDoor.getMyQuestion().getQuestionText(), BEA_BEST , SHOULD_BE_SAME);


    }


}