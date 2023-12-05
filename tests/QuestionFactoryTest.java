package tests;
/*
 * TriviaMaze
 * Fall 2023
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import controller.Question;
import model.QuestionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




/**
 * This program will be used to test the methods in
 *  the AbstractionQuestion class and it's subclasses.
 *@author David Hoang
 *@author Faith Capito
 *@version Fall 2023
 */


class QuestionFactoryTest {

    /**
     * The true or false question type.
     */
    private static final String TRUE_FALSE = "True False";
    /**
     * The multiple choice question type.
     */
    private static final String MULTIPLE_CHOICE = "Multiple Choice";


    /**
     * The true option type.
     */
    private static final String TRUE = "True";



    /**
     * The false option type.
     */
    private static final String FALSE = "False";

    /**
     * A test question text fixture.
     */
    private static final String BEA_BEST = "Is Beabadoobee the best?";

    /**
     * A test question text fixture.
     */

    private static final String BEA_HEIGHT = "How tall is Bea?";
    /**
     * A test fixture for the same results.
     */
    private static final String SHOULD_BE_SAME = "This should be the same";

    /**
     * A test fixture for different results.
     */
    private static final String SHOULD_NOT_BE_SAME = "This should not be the same";



    /**
     * Text fixture of a true false question type.
     */
    private  Question myTrueFalseQuestionTest;

    /**
     * A test question fixture.
     */
    private String FIVE_FOUR = "5 feet 4 inches";


    /**
     * Text fixture of a Multiple Choice Question type.
     */
    private  Question myMultipleChoiceQuestionTest;
    @BeforeEach
  void setUp() throws Exception {

        myTrueFalseQuestionTest =
                QuestionFactory.createQuestion(BEA_BEST, TRUE_FALSE,
                        TRUE, FALSE, FALSE, FALSE);
        myMultipleChoiceQuestionTest = QuestionFactory.createQuestion(BEA_HEIGHT,
                MULTIPLE_CHOICE, FIVE_FOUR, FALSE, FALSE, FALSE );

    }
    @Test
    void testTrueFalseSameQuestionTestAndAnswer()  {
        final Question trueFalseQuestion =
                QuestionFactory.createQuestion(BEA_BEST, TRUE_FALSE,
                        TRUE, FALSE, null, null);
        assertEquals(trueFalseQuestion.getQuestionText(),
                myTrueFalseQuestionTest.getQuestionText(), SHOULD_BE_SAME);
        assertEquals(trueFalseQuestion.getAnswerText(),
                myTrueFalseQuestionTest.getAnswerText(), SHOULD_BE_SAME);
        assertEquals(trueFalseQuestion.getSecondOption(),
                myTrueFalseQuestionTest.getSecondOption(), SHOULD_BE_SAME);

    }
    @Test
    void testTrueFalseNotSameQuestionTestAndAnswer()  {
        final Question trueFalseQuestion =
                QuestionFactory.createQuestion("Beabadobee is not the best", TRUE_FALSE,
                        FALSE, TRUE, null, null);
        assertNotEquals(trueFalseQuestion.getQuestionText(),
                myTrueFalseQuestionTest.getQuestionText(), SHOULD_NOT_BE_SAME);
        assertNotEquals(trueFalseQuestion.getAnswerText(),
                myTrueFalseQuestionTest.getAnswerText(), SHOULD_NOT_BE_SAME);
        assertNotEquals(trueFalseQuestion.getSecondOption(),
                myTrueFalseQuestionTest.getSecondOption(), SHOULD_NOT_BE_SAME);


    }
    @Test
    void testTMultipleChoiceSameQuestionTestAndAnswer()  {
        final Question multipleChoiceQuestion =
                QuestionFactory.createQuestion(BEA_HEIGHT, MULTIPLE_CHOICE,
                        FIVE_FOUR, FALSE, FALSE, FALSE);
        assertEquals(multipleChoiceQuestion.getQuestionText(),
                myMultipleChoiceQuestionTest.getQuestionText(), SHOULD_BE_SAME);
        assertEquals(multipleChoiceQuestion.getAnswerText(),
                myMultipleChoiceQuestionTest.getAnswerText(), SHOULD_BE_SAME);
        assertEquals(multipleChoiceQuestion.getSecondOption(),
                myMultipleChoiceQuestionTest.getSecondOption(), SHOULD_BE_SAME);
        assertEquals(multipleChoiceQuestion.getThirdOption(),
                myMultipleChoiceQuestionTest.getThirdOption(), SHOULD_BE_SAME);
        assertEquals(multipleChoiceQuestion.getFourthOption(),
                myMultipleChoiceQuestionTest.getFourthOption(), SHOULD_BE_SAME);


    }

}