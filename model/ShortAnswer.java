package model;

/**
 * ShortAnswer is a subclass of Question object that will be used to display
 * through doors in maze.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class ShortAnswer extends Question {

    /**
     * Question's text.
     */
    private static String myQuestion;

    /**
     * Text of correct answer to this Question.
     */
    private static String myAnswer;

    public ShortAnswer(final String theQuestion, final String theAnswer) {
        super();

        myQuestion = theQuestion;
        myAnswer = theAnswer;
    }
}
