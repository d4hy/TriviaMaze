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
     * The sprite to use for the left door.
     */
    private BufferedImage myLeftDoorImg;

    /**
     * The sprite to use for the right door.
     */
    private BufferedImage myRightDoorImg;
    /**
     * The sprite to use for the top door.
     */
    private BufferedImage myTopDoorImg;
    /**
     * The sprite to use for the bottom door.
     */
    private BufferedImage myBottomDoorImg;



    /**
     * Constructor for Room.
     */

    public Room() { }

    public void setDoor(final String theDoor) {

        if (Objects.equals(theDoor, "Left")) {
            myLeftDoor = new Door("Left Door");
        } else if (Objects.equals(theDoor, "Right")) {
            myRightDoor = new Door("Right Door");
        } else if (Objects.equals(theDoor, "Top")) {
            myTopDoor = new Door("Top Door");
        } else if (Objects.equals(theDoor, "Bottom")) {
            myBottomDoor = new Door("Bottom Door");
        }

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
     * Will reassign reference of door in this Room to share with adjacent room's
     * door.
     * @param theDoor door of adjacent room.
     */
    public void assignLeftDoor(final Door theDoor) {
        myLeftDoor = theDoor;
    }

    /**
     * Will reassign reference of door in this Room to share with adjacent room's
     * door.
     * @param theDoor door of adjacent room.
     */
    public void assignRightDoor(final Door theDoor) {
        myRightDoor = theDoor;
    }

    /**
     * Will reassign reference of door in this Room to share with adjacent room's
     * door.
     * @param theDoor door of adjacent room.
     */
    public void assignTopDoor(final Door theDoor) {
        myTopDoor = theDoor;
    }

    /**
     * Will reassign reference of door in this Room to share with adjacent room's
     * door.
     * @param theDoor door of adjacent room.
     */
    public void assignBottomDoor(final Door theDoor) {
        myBottomDoor = theDoor;
    }

    /**
     * Depending on validity of answered Question, will unlock specified Door
     * in this Room.
     */
    public void unlockDoors() {

    }

    /**
     * Draw the room and their doors.
     */
    public void draw(final Graphics2D g2) {

        drawRoomsFloor(g2);
        drawRoomsDoors(g2);






    }

    /**
     * Draws the rooms doors.
     */
    private void drawRoomsDoors(final Graphics2D g2) {

        // Draw the left door if it exists
        if (myLeftDoor != null) {
            final BufferedImage leftDoorImg = getLeftDoorImg();
            // Calculate the position to draw the left door in the middle of the left border
            final int leftDoorImgX = 0;
            final int leftDoorImgY = (MazeControls.MY_SCREEN_HEIGHT / 2)
                    - (MazeControls.MY_TILE_SIZE / 2);
            //draws the door that is to the left of the door if it exists.
            g2.drawImage(leftDoorImg, leftDoorImgX , leftDoorImgY, MazeControls.MY_TILE_SIZE,
                    MazeControls.MY_TILE_SIZE, null);
        }

        // Draw the right door if it exists
        if (myRightDoor != null) {
            final BufferedImage rightDoorImg = getRightDoorImg();
            // Calculate the position to draw the right door on the
            // right side of the room's border.
            final int rightDoorImgX = MazeControls.MY_SCREEN_WIDTH
                    - MazeControls.MY_TILE_SIZE;
            final int rightDoorImgY = (MazeControls.MY_SCREEN_HEIGHT / 2)
                    - (MazeControls.MY_TILE_SIZE / 2);
            // Draw the right door.
            g2.drawImage(rightDoorImg, rightDoorImgX, rightDoorImgY,
                    MazeControls.MY_TILE_SIZE, MazeControls.MY_TILE_SIZE, null);
        }

        // Draw the bottom door if it exists
        if (myBottomDoor != null) {
            final BufferedImage bottomDoorImg = getBottomDoorImg();
            // Calculate the position to draw the right door on
            // the bottom side of the room's border.
            final int bottomDoorImgX = (MazeControls.MY_SCREEN_WIDTH / 2)
                    - (MazeControls.MY_TILE_SIZE / 2);
            final int bottomDoorImgY = MazeControls.MY_SCREEN_HEIGHT
                    - MazeControls.MY_TILE_SIZE;
            // Draw the right door.
            g2.drawImage(bottomDoorImg, bottomDoorImgX, bottomDoorImgY,
                    MazeControls.MY_TILE_SIZE, MazeControls.MY_TILE_SIZE, null);
        }
    }


    /**
     * Draws the current rooms floor.
     *
     */
    private void drawRoomsFloor(final Graphics2D g2) {
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
     * This method will load the left door images.
     */
    private BufferedImage getLeftDoorImg() {
        try {
            //the string is the path of where the file for the left door img.
            myLeftDoorImg = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/left_door.png")));

        } catch (final IOException e) {
            e.printStackTrace();

        }
        return myLeftDoorImg;
    }

    /**
     * This method will load the right door images.
     */
    private BufferedImage getRightDoorImg() {
        try {
            //the string is the path of where the file for the right door img.
            myRightDoorImg = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/right_door.png")));

        } catch (final IOException e) {
            e.printStackTrace();

        }
        return myRightDoorImg;
    }

    /**
     * This method will load the bottom door images.
     */
    private BufferedImage getBottomDoorImg() {
        try {
            //the string is the path of where the file for the bottom door img.
            myBottomDoorImg = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/bottom_door.png")));

        } catch (final IOException e) {
            e.printStackTrace();

        }
        return myBottomDoorImg;
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
