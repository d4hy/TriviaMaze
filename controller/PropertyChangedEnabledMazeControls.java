package controller;
import model.Maze;

import java.beans.PropertyChangeListener;

/**
 *
 * Defines behaviors allowing PropertyChangeListeners to be added or removed from a
 * Maze object. Implementing classes should inform PropertyChangeListeners
 * when methods defined in MazeControls mutate the state of the simulation.
 *
 * @author David Hoang
 * @author Faith Capito
 *
 * @version Winter 2023
 */
public interface PropertyChangedEnabledMazeControls extends MazeControls {

    /**
     * A property name for the current GAME_OVER field in the game.
     */
    String PROPERTY_GAME_OVER = "GAME_OVER";
    /**
     * A property name for the current game won status.
     */
    String PROPERTY_GAME_WON ="GAME_WON";

    /**
     * A property name for when we load the status of the game.
     */

    String PROPERTY_LOAD ="LOADED";

    /**
     * A property name for the current position of the character status.
     */
    String PROPERTY_CHARACTER_MOVE = "CHAR_MOVED";



    /**
     *  String to fire when the current room has changed.
     */
    String PROPERTY_ROOM_CHANGE = "room has changed";


    /**
     * A property name for the user to prompt the question when at the bottom door.
     */
    String PROPERTY_PROMPT_QUESTION_BOT_DOOR = "PROMPTQuestionFromBottomDoor";

    /**
     * A property name for the user to prompt the question when at the top door.
     */
    String PROPERTY_PROMPT_QUESTION_TOP_DOOR = "PROMPTQuestionFromTopDoor";

    /**
     * A property name for the user to prompt the question when at the left door.
     */
    String PROPERTY_PROMPT_QUESTION_LEFT_DOOR = "PROMPTQuestionFromLeftDoor";

    /**
     * A property name for the user to prompt the question when at the right door.
     */
    String PROPERTY_PROMPT_QUESTION_RIGHT_DOOR = "PROMPTQuestionFromRightDoor";



    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for
     * all properties. The same listener object may be added more than once, and will be
     * called as many times as it is added. If listener is null, no exception is thrown and
     * no action is taken.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);




    /**
     * Remove a PropertyChangeListener from the listener list. This removes a
     * PropertyChangeListener that was registered for all properties. If listener was added
     * more than once to the same event source, it will be notified one less time after being
     * removed. If listener is null, or was never added, no exception is thrown and no action
     * is taken.
     *
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);


}
