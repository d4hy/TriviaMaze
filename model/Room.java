/*
 * TriviaMaze
 * Fall 2023
 */
package model;

import controller.MazeControls;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;


/**
 * Room class will contain information about its Doors in response to player actions in Maze.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class Room {

    /**
     * The available Doors within current Room.
     */
    private Door[] myDoors;

    /**
     * Left door in Room.
     */
    private Door myLeftDoor;

    /**
     * Right door in Room.
     */
    private Door myRightDoor;

    /**
     * Top door in Room.
     */
    private Door myTopDoor;

    /**
     * Bottom door in Room.
     */
    private Door myBottomDoor;

    /**
     * The sprite to use for the room.
     */

    private BufferedImage myRoomTile;

    /**
     * Constructor for Room.
     */
    public Room(final Door theLeftDoor, final Door theRightDoor,
                final Door theTopDoor, final Door theBottomDoor) {

        myLeftDoor = theLeftDoor;
        myRightDoor = theRightDoor;
        myTopDoor = theTopDoor;
        myBottomDoor = theBottomDoor;

    }

    public Room() {
        myLeftDoor = new Door("Left Door");
        myRightDoor = new Door("Right Door");
        myTopDoor = new Door("Top Door");
        myBottomDoor = new Door("Bottom Door");
    }

    /**
     * Retrieves left door in specific Room.
     * @return left Door.
     */
    public Door getLeftDoor() {
        return myLeftDoor;
    }

    /**
     * Retrieves right door in specific Room.
     * @return right Door.
     */
    public Door getRightDoor() {
        return myRightDoor;
    }

    /**
     * Retrieves top door in specific Room.
     * @return top Door.
     */
    public Door getTopDoor() {
        return myTopDoor;
    }

    /**
     * Retrieves bottom door in specific Room.
     * @return bottom Door.
     */
    public Door getBottomDoor() {
        return myBottomDoor;
    }

    /**
     * Depending on validity of answered Question, will unlock specified Door
     * in this Room.
     */
    public void unlockDoors() {

    }

    /**
     * Draw the room tiles.
     */
    public void draw(final Graphics2D g2) {






        final BufferedImage image = getRoomTileImage();
        // Draw a grid of room tiles
        for (int i = 0; i < MazeControls.MY_MAX_SCREEN_ROW; i++) {
            for (int j = 0; j < MazeControls.MY_MAX_SCREEN_COL; j++) {
                final int x = i * MazeControls.MY_TILE_SIZE;
                final int y = j * MazeControls.MY_TILE_SIZE;
                g2.drawImage(image, x, y, MazeControls.MY_TILE_SIZE,
                        MazeControls.MY_TILE_SIZE, null);
            }
        }


    }

    /**
     * This method will load the user images.
     */
    private BufferedImage getRoomTileImage() {
        try {
            //the string is the path of where the file for the room tiles
            myRoomTile = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/room_tile_red.png")));

        } catch (final IOException e) {
            e.printStackTrace();

        }
        return myRoomTile;
    }
    /**
     * Returns validity of Character answered Question.
     * @return boolean if question asked was answered.
     */
    public boolean isQuestionAnswered() {

        boolean answer = false;

        return answer;
    }
}
