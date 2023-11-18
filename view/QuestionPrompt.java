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
     * The current question to prompt
     */
    private Question myQuestionToPrompt;

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
     * If the answer is correct, it sets the associated door as prompted and unlocks it.
     * Updates the game state accordingly.
     */
    public void displayQuestionPrompt() {
        if (!myRoom.getBottomDoor().isMyQuestionPrompted() || !myRoom.getBottomDoor().isMyQuestionAnswered()) {
            handleQuestionPrompt(myRoom.getBottomDoor());
        } else if (!myRoom.getLeftDoor().isMyQuestionPrompted() || !myRoom.getLeftDoor().isMyQuestionAnswered()) {
            handleQuestionPrompt(myRoom.getLeftDoor());
        } else if (!myRoom.getRightDoor().isMyQuestionPrompted() || !myRoom.getRightDoor().isMyQuestionAnswered()) {
            handleQuestionPrompt(myRoom.getRightDoor());
        } else if (!myRoom.getTopDoor().isMyQuestionPrompted() || !myRoom.getTopDoor().isMyQuestionAnswered()) {
            handleQuestionPrompt(myRoom.getTopDoor());
        }
    }

    /**
     * Handles the question prompt for a specific door.
     * @param theDoor The door for which the question prompt is displayed.
     */
    private void handleQuestionPrompt(Door theDoor) {
        // Display the question prompt using JOptionPane
        String userAnswer = JOptionPane.showInputDialog(null, "Your question goes here");

        // Process the user's answer (validate, etc.)
        // If the answer is "OK", set the door as prompted and unlock it
        if ("OK".equalsIgnoreCase(userAnswer.trim())) {
            theDoor.setMyQuestionPromptedStatus(true);
            theDoor.setMyQuestionAnsweredStatus(true);

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
    public void propertyChange(PropertyChangeEvent theEvt) {
        // Listen for changes in the model, if needed.
        final String propertyName = theEvt.getPropertyName();
        if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_BOT_DOOR)) {
            myQuestionToPrompt = (Question) theEvt.getNewValue();
            // If the freeze property is triggered, display the question prompt
            displayQuestionPrompt();
        }
    }
}