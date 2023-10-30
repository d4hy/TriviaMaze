/*
 * TriviaMaze
 * Fall 2023
 */
package controller;

/**
 * MazeController defines all the methods that will be implemented and used in
 * TriviaMaze.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version 23 October 2023
 */
public interface MazeControls {

    /**
     * Resets the maze for a new game.
     * This method must be called before the first game
     * and before each new game.
     */
    void newGame();

    /**
     * Try to move the character down
     * If the character tries to move outside the map they won't be able to move down.
     */
    void moveDown();

    /**
     * Try to move the character up
     * If the character tries to move  outside the map they won't be able to move up.
     */
    void moveUp();

    /**
     * Try to move the character left
     * If the character tries to move  outside the map they won't be able to move left.
     */
    void moveLeft();

    /**
     * Try to move the character right
     * If the character tries to move  outside the map they won't be able to move right.
     */
    void moveRight();

    /**
     * Pauses the game, freezes the character in place, stops the timer from advancing.
     */
    void pauseGame();



}
