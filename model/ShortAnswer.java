package model;

/**
 * ShortAnswer is a subclass of Question object that will be used to display
 * through doors in maze.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class ShortAnswer extends AbstractQuestion {

    /**
     * Constructor that takes question and answer Strings from database for a
     * short answer Question.
     * @param theQuestion Question for trivia.
     * @param theAnswer Answer to this question.
     */
    public ShortAnswer(final String theQuestion, final String theAnswer) {

        super(theQuestion, theAnswer, "", "", "");

    }


}
