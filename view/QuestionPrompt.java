package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import model.AbstractQuestion;
import model.Door;
import model.Maze;
import model.MultipleChoice;
import model.Question;
import model.Room;
import model.ShortAnswer;
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
    private void displayQuestionPrompt(final Door theDoor) {
        // Retrieve the associated question
        final Question question = theDoor.getMyQuestion(theDoor);

        if (question instanceof TrueOrFalse) {
            handleTrueOrFalseQuestion(theDoor, (TrueOrFalse) question);
        } else if (question instanceof MultipleChoice) {
            handleMultipleChoiceQuestion(theDoor, (MultipleChoice) question);
        } else if (question instanceof ShortAnswer) {
            handleShortAnswerQuestion(theDoor, (ShortAnswer) question);
        }
    }

    /**
     * Handles Multiple Choice type questions.
     *
     * @param theDoor The door for which the question prompt is displayed.
     * @param theMultipleChoiceQuestion The multiple choice type question.
     */
    private void  handleMultipleChoiceQuestion(final Door theDoor, final MultipleChoice theMultipleChoiceQuestion) {
        final ArrayList<String> options = new ArrayList<>();
        options.add(theMultipleChoiceQuestion.getAnswerText());
        options.add(theMultipleChoiceQuestion.getSecondOption());
        options.add(theMultipleChoiceQuestion.getThirdOption());
        options.add(theMultipleChoiceQuestion.getFourthOption());
        Collections.shuffle(options);
        final int userAnswerIndex = JOptionPane.showOptionDialog(
                null,
                theMultipleChoiceQuestion.getQuestionText(),
                "Answer to play!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options.toArray(),
                options.get(0)
        );
        // Check if the user's answer is correct
        if (userAnswerIndex >= 0 && userAnswerIndex < options.size()
                && options.get(userAnswerIndex).
                equals(theMultipleChoiceQuestion.getAnswerText())) {
            // Handle correct answer
            handleCorrectAnswer(theDoor);
        } else {
            // Handle incorrect answer
            handleIncorrectAnswer(theDoor);
        }

    }
    /**
     * Handles Short Answer type questions.
     *
     * @param theDoor The door for which the question prompt is displayed.
     * @param theShortAnswerQuestion The  Short Answer question.
     */
    private void  handleShortAnswerQuestion(final Door theDoor,
                                            final ShortAnswer theShortAnswerQuestion) {
        final String userInput = JOptionPane.showInputDialog(null,
                theShortAnswerQuestion.getQuestionText());
        System.out.println(theShortAnswerQuestion.getAnswerText());
        // Check if the user's answer is correct
        if (theShortAnswerQuestion.getAnswerText().trim().equals(userInput.trim())) {
            // Handle correct answer
            handleCorrectAnswer(theDoor);
        } else {
            // Handle incorrect answer
            handleIncorrectAnswer(theDoor);
        }

    }


    /**
     * Handles True/False type questions.
     *
     * @param theDoor         The door for which the question prompt is displayed.
     * @param theTrueOrFalseQuestion The True/False question.
     */
    private void handleTrueOrFalseQuestion(final Door theDoor,
                                           final TrueOrFalse theTrueOrFalseQuestion) {
        final String[] options = {"True", "False"};
        final int userAnswer = JOptionPane.showOptionDialog(
                null,
                theTrueOrFalseQuestion.getQuestionText(),
                "Answer to play!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        // Process the user's answer

        //if the answer is true, check if the user chose true
        if (theTrueOrFalseQuestion.getAnswerText().equals(options[0])) {
            if (userAnswer == JOptionPane.YES_OPTION) {
                handleCorrectAnswer(theDoor);
            } else if (userAnswer == JOptionPane.NO_OPTION) {
                handleIncorrectAnswer(theDoor);
            }
            //if the answer is false, check if the answer chose false
        }  else if (theTrueOrFalseQuestion.getAnswerText().equals(options[1])) {
            if (userAnswer == JOptionPane.NO_OPTION) {
                handleCorrectAnswer(theDoor);
            } else if (userAnswer == JOptionPane.YES_OPTION) {
                handleIncorrectAnswer(theDoor);
            }
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
    private void handleIncorrectAnswer(final Door theDoor) {
        theDoor.setMyQuestionNotPromptedStatus(false);
        theDoor.lock();
        myMaze.setMoveTrue();
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
            displayQuestionPrompt(myRoom.getBottomDoor());
        } else if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_TOP_DOOR)) {
            myRoom = (Room) theEvt.getNewValue();
            displayQuestionPrompt(myRoom.getTopDoor());
        } else if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_LEFT_DOOR)) {
            myRoom = (Room) theEvt.getNewValue();
            displayQuestionPrompt(myRoom.getLeftDoor());
        } else if (propertyName.equals(myMaze.PROPERTY_PROMPT_QUESTION_RIGHT_DOOR)) {
            myRoom = (Room) theEvt.getNewValue();
            displayQuestionPrompt(myRoom.getRightDoor());
        }
    }
}