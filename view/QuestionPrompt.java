package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import model.AbstractQuestion;
import model.Door;
import model.Maze;
import model.Room;
import model.TrueOrFalse;


/**
 * A  class for displaying a question prompt to the user using JOptionPane.
 * This class listens for property changes in the Maze class and displays a question prompt
 * when the freeze property is triggered.
 *
 * @author David Hoang
 * @author Faith Capito
 * @version Fall 2023
 */
public class QuestionPrompt implements PropertyChangeListener {

    /**
     * The Maze object to be referenced.
     */
    private final Maze myMaze;

    /**
     * The current room to reference.
     */
    private Room myRoom;

    /**
     * Constructs a QuestionPrompt with a reference to the Maze object.
     *
     * @param theMaze The Maze object to be referenced.
     */
    public QuestionPrompt(final Maze theMaze)  {
        myMaze = theMaze;
    }
    /**
     * Displays a question prompt to the user and processes their answer.
     * If the answer is correct, it sets the bottom door as prompted and unlocks it.
     * If the dialog is closed or the answer is incorrect, it sets the question as not prompted.
     * Updates the game state accordingly.
     *
     * @param theDoor The door for which the question prompt is displayed.
     */
    private void displayQuestionBottomPrompt(Door theDoor) {

        // Retrieve the associated question
        AbstractQuestion abstractQuestion = theDoor.getMyQuestion(theDoor);
        // Display the question prompt using JOptionPane

        String userAnswer = JOptionPane.showInputDialog(null, abstractQuestion.getQuestionText());

        // Process the user's answer (validate, etc.)
        // If the answer is "OK", set the door as prompted and unlock it
        if ("OK".equalsIgnoreCase(userAnswer.trim())) {
            theDoor.setMyQuestionNotPromptedStatus(false);
            theDoor.setMyQuestionHasBeenAnsweredCorrectlyStatus(true);

            // Set the character to move again
            myMaze.setMoveTrue();
        } else {
            // If the dialog is closed or the answer is incorrect, set the question as not prompted
            theDoor.setMyQuestionNotPromptedStatus(false);
            theDoor.lock();
            // Set the character to move again
            myMaze.setMoveTrue();
        }
    }
    /**
     * Displays a question prompt to the user and processes their answer.
     * If the answer is correct, it sets the bottom door as prompted and unlocks it.
     * If the dialog is closed or the answer is incorrect, it sets the question as not prompted.
     * Updates the game state accordingly.
     *
     * @param theDoor The door for which the question prompt is displayed.
     */
    private void displayQuestionPrompt(Door theDoor) {
        // Retrieve the associated question
        AbstractQuestion abstractQuestion = theDoor.getMyQuestion(theDoor);

        if (abstractQuestion instanceof TrueOrFalse) {
            handleTrueOrFalseQuestion(theDoor, (TrueOrFalse) abstractQuestion);
        } else {
            handleOtherQuestion(theDoor, abstractQuestion);
        }
    }

    /**
     * Handles True/False type questions.
     *
     * @param theDoor         The door for which the question prompt is displayed.
     * @param trueOrFalseQuestion The True/False question.
     */
    private void handleTrueOrFalseQuestion(final Door theDoor, final TrueOrFalse trueOrFalseQuestion) {
        final String[] options = {"True", "False"};
        final int userAnswer = JOptionPane.showOptionDialog(
                null,
                trueOrFalseQuestion.getQuestionText(),
                "Answer to play!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        // Process the user's answer

        //if the answer is true, check if the user chose true
        if (trueOrFalseQuestion.getAnswerText().equals(options[0])) {
            if (userAnswer == JOptionPane.YES_OPTION) {
                handleCorrectAnswer(theDoor);
            } else if (userAnswer == JOptionPane.NO_OPTION) {
                handleIncorrectAnswer(theDoor);
            }
            //if the answer is false, check if the answer chose false
        }  else if (trueOrFalseQuestion.getAnswerText().equals(options[1])) {
            if (userAnswer == JOptionPane.NO_OPTION) {
                handleCorrectAnswer(theDoor);
            } else if (userAnswer == JOptionPane.YES_OPTION) {
                handleIncorrectAnswer(theDoor);
            }
        }

    }

    /**
     * Handles questions other than True/False type.
     *
     * @param theDoor           The door for which the question prompt is displayed.
     * @param otherQuestion The non True/False question.
     */
    private void handleOtherQuestion(final Door theDoor, final AbstractQuestion otherQuestion) {
        final String userInput = JOptionPane.showInputDialog(null, otherQuestion.getQuestionText());

        // Process the user's answer (validate, etc.)
        if ("OK".equalsIgnoreCase(userInput.trim())) {
            handleCorrectAnswer(theDoor);
        } else {
            handleIncorrectAnswer(theDoor);
        }
    }

    /**
     * Handles the case where the user answered the question correctly.
     *
     * @param theDoor The door for which the question was displayed.
     */
    private void handleCorrectAnswer(final Door theDoor) {
        theDoor.setMyQuestionNotPromptedStatus(false);
        theDoor.setMyQuestionHasBeenAnsweredCorrectlyStatus(true);
        myMaze.setMoveTrue();
    }

    /**
     * Handles the case where the user answered the question incorrectly or closed the dialog.
     *
     * @param theDoor The door for which the question was displayed.
     */
    private void handleIncorrectAnswer(Door theDoor) {
        theDoor.setMyQuestionNotPromptedStatus(false);
        theDoor.lock();
        myMaze.setMoveTrue();
    }

    /**
     * Displays a question prompt to the user and processes their answer.
     * If the answer is correct, it sets the Top door as prompted and unlocks it.
     * Updates the game state accordingly.
     * @param theDoor The door for which the question prompt is displayed.
     */
    private void displayQuestionTopPrompt(Door theDoor) {
        // Display the question prompt using JOptionPane
        String userAnswer = JOptionPane.showInputDialog(null, "Your question goes here");

        // Process the user's answer (validate, etc.)
        // If the answer is "OK", set the door as prompted and unlock it
        if ("OK".equalsIgnoreCase(userAnswer.trim())) {
            theDoor.setMyQuestionNotPromptedStatus(false);
            theDoor.setMyQuestionHasBeenAnsweredCorrectlyStatus(true);

            // Set the character to move again
            myMaze.setMoveTrue();
        } else {
            // If the dialog is closed or the answer is incorrect, set the question as not prompted
            theDoor.setMyQuestionNotPromptedStatus(false);
            theDoor.lock();
            // Set the character to move again
            myMaze.setMoveTrue();
        }
    }

    /**
     * Displays a question prompt to the user and processes their answer.
     * If the answer is correct, it sets the Left door as prompted and unlocks it.
     * Updates the game state accordingly.
     * @param theDoor The door for which the question prompt is displayed.
     */
    private void displayQuestionLeftPrompt(Door theDoor) {
        // Display the question prompt using JOptionPane
        String userAnswer = JOptionPane.showInputDialog(null, "Your question goes here");

        // Process the user's answer (validate, etc.)
        // If the answer is "OK", set the door as prompted and unlock it
        if ("OK".equalsIgnoreCase(userAnswer.trim())) {
            theDoor.setMyQuestionNotPromptedStatus(false);
            theDoor.setMyQuestionHasBeenAnsweredCorrectlyStatus(true);

            // Set the character to move again
            myMaze.setMoveTrue();
        } else {
            // If the dialog is closed or the answer is incorrect, set the question as not prompted
            theDoor.setMyQuestionNotPromptedStatus(false);
            theDoor.lock();
            // Set the character to move again
            myMaze.setMoveTrue();
        }
    }

    /**
     * Displays a question prompt to the user and processes their answer.
     * If the answer is correct, it sets the Right door as prompted and unlocks it.
     * Updates the game state accordingly.
     * @param theDoor The door for which the question prompt is displayed.
     */
    private void displayQuestionRightPrompt(Door theDoor) {
        // Display the question prompt using JOptionPane
        String userAnswer = JOptionPane.showInputDialog(null, "Your question goes here");

        // Process the user's answer (validate, etc.)
        // If the answer is "OK", set the door as prompted and unlock it
        if ("OK".equalsIgnoreCase(userAnswer.trim())) {
            theDoor.setMyQuestionNotPromptedStatus(false);
            theDoor.setMyQuestionHasBeenAnsweredCorrectlyStatus(true);

            // Set the character to move again
            myMaze.setMoveTrue();
        } else {
            // If the dialog is closed or the answer is incorrect, set the question as not prompted
            theDoor.setMyQuestionNotPromptedStatus(false);
            theDoor.lock();
            // Set the character to move again
            myMaze.setMoveTrue();
        }
    }





    /**
     * Listens for property changes in the Maze class.
     * When the freeze property is triggered, it displays a question prompt.
     *
     * @param theEvt The property change event.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        // Listen for changes in the model, if needed.
        final String propertyName = theEvt.getPropertyName();
        if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_BOT_DOOR)) {
            myRoom = (Room) theEvt.getNewValue();
            displayQuestionBottomPrompt(myRoom.getBottomDoor());
        } else if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_TOP_DOOR)) {
            myRoom = (Room) theEvt.getNewValue();
            displayQuestionPrompt(myRoom.getTopDoor());
        } else if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_LEFT_DOOR)) {
            myRoom = (Room) theEvt.getNewValue();
            displayQuestionLeftPrompt(myRoom.getLeftDoor());
        } else if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_RIGHT_DOOR)) {
            myRoom = (Room) theEvt.getNewValue();
            displayQuestionRightPrompt(myRoom.getRightDoor());
        }
    }
}