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
public class Question {

    /**
     * Question to be answered in Maze.
     */
    private String myQuestion;

    private String myQuestionType;

    /**
     * Correct answer to Question that is to be expected.
     */
    private String myCorrectAnswer;

    private String mySecondOption;

    private String myThirdOption;

    private String myFourthOption;

    /**
     * Constructor for Question.
     */
    public Question() {

    }

    public Question(final String theQuestionText, final String theQuestionType,
                    final String theCorrectAnswer, final String theSecondOption,
                    final String theThirdOption, final String theFourthOption) {

        myQuestion = theQuestionText;
        myQuestionType = theQuestionType;
        myCorrectAnswer = theCorrectAnswer;
        mySecondOption = theSecondOption;
        myThirdOption = theThirdOption;
        myFourthOption = theFourthOption;

    }

    /**
     * Gets text of current Question.
     * @return String text of the question.
     */
    public String getQuestionText() {
        return myQuestion;
    }

    public String getMyQuestionType() {
        return myQuestionType;
    }

    /**
     * Gets expected answer as String text.
     * @return String answer
     */
    public String getAnswer() {

        myCorrectAnswer = "This is the answer.";

        return myCorrectAnswer;
    }

    /**
     * Returns the type of current Question.
     * @return String type of question.
     */
    public String getType() {

        return "This is the type of question.";
    }

    /**
     * Dummy question to use for testing purposes.
     * @return String of explicit question.
     */
    public String getDummyQuestion() {
        myQuestion = "Who is Beabadoobee?";
        return myQuestion;
    }

    /**
     * Dummy answer to use for testing purposes.
     * @return String of explicit answer.
     */
    public String getDummyAnswer() {
        myCorrectAnswer = "UK Indie Rock Artist";
        return myCorrectAnswer;
    }

    /**
     * Dummy question type to use for testing purposes.
     * @return String type of question.
     */
    public String getDummyType() {
        return "Short Answer";
    }
}
