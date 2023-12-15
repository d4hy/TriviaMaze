/*
 * TriviaMaze
 * Fall 2023
 */
package model;

/**
 * Multiple Choice is a subclass of Question object that will be used to display
 * through doors in maze.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class MultipleChoice extends AbstractQuestion {
    /**
     * Multiple choice constructor calls super to Question and takes parameters based
     * on the information pertaining to this specific type of question.
     * @param theQuestion String text of the question to be displayed.
     * @param theAnswer String text of the answer to be displayed.
     * @param theSecondOption String text of the second, incorrect option to be displayed.
     * @param theThirdOption String text of the third, incorrect option to be displayed.
     * @param theFourthOption String text of the fourth, incorrect option to be displayed.
     */
    public MultipleChoice(final String theQuestion, final String theAnswer,
                          final String theSecondOption, final String theThirdOption,
                          final String theFourthOption) {
        super(theQuestion, theAnswer, theSecondOption, theThirdOption, theFourthOption);


    }
}
