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
public abstract class AbstractQuestion {

    /**
     * Question to be answered in Maze.
     */
    private String myQuestion;

    /**
     * String describing the type of Question listed.
     */
    private String myQuestionType;

    /**
     * Correct answer to Question that is to be expected.
     */
    private String myCorrectAnswer;

    private String mySecondOption;

    private String myThirdOption;

    private String myFourthOption;

    /**
     * Dummy constructor for Question.
     */
    public AbstractQuestion() {    }

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
