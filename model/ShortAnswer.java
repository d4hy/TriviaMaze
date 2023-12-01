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
     * Question's text.
     */
    private static String myQuestion;

    /**
     * Text of correct answer to this Question.
     */
    private static String myAnswer;

    /**
     * Text of incorrect second option.
     */
    private static String mySecondOption;

    /**
     * Text of incorrect third option.
     */
    private static String myThirdOption;

    /**
     * Text of incorrect fourth option.
     */
    private static String myFourthOption;

    /**
     * Constructor that takes question and answer Strings from database for a
     * short answer Question.
     * @param theQuestion Question for trivia.
     * @param theAnswer Answer to this question.
     */
    public ShortAnswer(final String theQuestion, final String theAnswer) {

        super(theQuestion, theAnswer, "", "", "");

        myQuestion = theQuestion;
        myAnswer = theAnswer;
    }

    /**
     * Returns the text of the specific question that will be displayed in view.
     * @return String Text of the question.
     */
    public String getQuestionText() {
        return myQuestion;
    }

    /**
     * Returns the text of the answer to this question that will be displayed in view.
     * @return String Text of the answer.
     */
    public String getAnswerText() {
        return myAnswer;
    }
}
