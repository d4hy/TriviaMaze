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

    /**
     * Correct answer to Question that is to be expected.
     */
    private String myAnswer;

    /**
     * Constructor for Question.
     */
    public Question() {

    }

    /**
     * Gets text of current Question.
     * @return String text of the question.
     */
    public String getQuestionText() {

        myQuestion = "This is a question.";

        return myQuestion;
    }

    /**
     * Gets expected answer as String text.
     * @return String answer
     */
    public String getAnswer() {

        String myAnswer = "This is the answer.";

        return myAnswer;
    }

    /**
     * Returns the type of current Question.
     * @return String type of question.
     */
    public String getType() {

        String type = "This is the type of question.";

        return type;
    }
}
