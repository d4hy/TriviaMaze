package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import model.Maze;
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
     * If the answer is correct, it sets the associated door as prompted and unlocks it.
     * Updates the game state accordingly.
     */
    public void displayQuestionPrompt() {
        // Display the question prompt using JOptionPane
        String userAnswer = JOptionPane.showInputDialog(null, "Your question goes here");
        // Process the user's answer (validate, etc.)
        // If correct, set the door as prompted and unlock it
        // Update the game state accordingly
        myMaze.getCurrentRoom().getBottomDoor().setMyQuestionPromptedStatus(true);

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
        if (propertyName.equals(myMaze.PROPERTY_FREEZE)) {
            myRoom = (Room) theEvt.getNewValue();
            // If the freeze property is triggered, display the question prompt
            displayQuestionPrompt();
        }
    }
}