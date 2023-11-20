package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;

import model.Door;
import model.Maze;
import model.Question;
import model.Room;


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
            // Set the character to move again
            myMaze.setMoveTrue();
        }
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
            displayQuestionTopPrompt(myRoom.getTopDoor());
        } else if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_LEFT_DOOR)) {
            myRoom = (Room) theEvt.getNewValue();
            displayQuestionLeftPrompt(myRoom.getLeftDoor());
        } else if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_RIGHT_DOOR)) {
            myRoom = (Room) theEvt.getNewValue();
            displayQuestionRightPrompt(myRoom.getRightDoor());
        }
    }
}