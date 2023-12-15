package model;

/**
 * TrueOrFalse is a subclass of Question object that will be used to display
 * through doors in maze.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class TrueOrFalse extends AbstractQuestion {



    /**
     * Multiple choice constructor calls super to Question and takes parameters based
     * on the information pertaining to this specific type of question.
     * @param theQuestion String text of the question to be displayed.
     * @param theAnswer String text of the answer to be displayed.
     * @param theIncorrectAnswer String text of the incorrect option to be displayed.
     */
    public TrueOrFalse(final String theQuestion, final String theAnswer,
                       final String theIncorrectAnswer) {
        super(theQuestion, theAnswer, theIncorrectAnswer, "", "");

    }



}
