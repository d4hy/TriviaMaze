package model;

import java.util.Objects;

/**
 * QuestionFactory will create specific Questions depending on its type and
 * information it carries.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public final class QuestionFactory {

    /**
     * Private constructor to ensure it will not be instantiated.
     */
    private QuestionFactory() { }

    /**
     * Creates a Question based on its parameters to be used in Maze.
     * @param theQuestionText The text of the Question to be asked.
     * @param theQuestionType The type of Question (multiple choice, short answer, true or
     *                        false).
     * @param theCorrectAnswer The text of the answer to this Question.
     * @param theSecondOption The incorrect second option to use in view.
     * @param theThirdOption The incorrect third option to use in view.
     * @param theFourthOption The incorrect fourth option to use in view.
     * @return The Question that is created.
     */
    public static Question createQuestion(final String theQuestionText,
                                          final String theQuestionType,
                                          final String theCorrectAnswer,
                                          final String theSecondOption,
                                          final String theThirdOption,
                                          final String theFourthOption) {
        Question question = null;

        if (theQuestionType.equals("Short Answer")) {
            question = new ShortAnswer(theQuestionText, theCorrectAnswer);
        } else if (theQuestionType.equals("True False")) {
            question = new TrueOrFalse(theQuestionText, theCorrectAnswer, theSecondOption);
        } else if (theQuestionType.equals("Multiple Choice")) {
            question = new MultipleChoice(theQuestionText, theCorrectAnswer, theSecondOption,
                    theThirdOption, theFourthOption);
        }

        return question;
    }
}
