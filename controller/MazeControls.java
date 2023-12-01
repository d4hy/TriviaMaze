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

    //Screen settings.
    /**
     *  16x 16 tiles, will represent size of characters,and parts of the map.
     */
    int MY_ORIGINAL_TILE_SIZE = 16;
    /**
     *  This variable will be used to upscale the tile size otherwise it's too small to see.
     */
    int MY_SCALE = 3;

    /**
     * The tile size up-scaled to fit on the screen.
     */
    int MY_TILE_SIZE = MY_ORIGINAL_TILE_SIZE * MY_SCALE;

    /**
     *The amount of tiles that will be represented as the rows of the screen.
     *
     */
    int MY_MAX_SCREEN_ROW = 20;
    /**
     *The amount of tiles that will be represented as the columns of the screen.
     *
     */
    int MY_MAX_SCREEN_COL = 12;

    /**
     * The up-scaled amount of tiles that will be represented as the width of the screen.
     */
    int MY_SCREEN_WIDTH = MY_TILE_SIZE  * MY_MAX_SCREEN_ROW; //960 pixels.

    /**
     * The up-scaled amount of tiles that will be represented as the height of the screen.
     */
    int MY_SCREEN_HEIGHT = MY_TILE_SIZE * MY_MAX_SCREEN_COL;
    // 576 pixels.
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
