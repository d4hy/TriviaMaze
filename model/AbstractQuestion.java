/*
 * TriviaMaze
 * Fall 2023
 */
package model;

/**
 * Class will hold the list of Questions to be answered and evaluated during game.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public abstract class AbstractQuestion implements Question {

    /**
     * Question to be answered in Maze.
     */
    private final String myQuestion;

    /**
     * Correct answer to Question that is to be expected.
     */
    private final String myCorrectAnswer;

    /**
     * Text of incorrect second option.
     */
    private final String mySecondOption;

    /**
     * Text of incorrect third option.
     */
    private final String myThirdOption;

    /**
     * Text of incorrect fourth option.
     */
    private final String myFourthOption;

    /**
     * Dummy constructor for Question.
     */
    public AbstractQuestion(final String theQuestion, final String theAnswer,
                            final String theSecondOption, final String theThirdOption,
                            final String theFourthOption) {

        this.myQuestion = theQuestion;
        this.myCorrectAnswer = theAnswer;
        this.mySecondOption = theSecondOption;
        this.myThirdOption = theThirdOption;
        this.myFourthOption = theFourthOption;

    }

    public String getQuestionText() {
        return myQuestion;
    }

    public String getAnswerText() {
        return myCorrectAnswer;
    }

    public String getSecondOption() {
        return mySecondOption;
    }

    public String getThirdOption() {
        return myThirdOption;
    }

    public String getFourthOption() {
        return myFourthOption;
    }
}
