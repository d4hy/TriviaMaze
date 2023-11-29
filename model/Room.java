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
     * Field that will tell us if a room is dead end or not.
     */
    private Boolean myDeadEndStatus;



    /**
     * Constructor for Room.
     */

    public Room() {
        myDeadEndStatus = false;
    }

    /**
     * Retrieves the status if a room is dead end.
     * @return true if this room is a dead end, false otherwise.
     */
    public boolean isDeadEnd() {
        return myDeadEndStatus;
    }

    /**
     * Sets this room as a dead end.
     */
    public void setAsDeadEnd() {
        myDeadEndStatus = true;
    }

    /**
     * When Maze is created, this method is called to flag what rooms should have
     * what doors existing.
     * @param theDoor the Door that's created in Room according to direction.
     */
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
     * Will reassign the reference of room's left door to specified door.
     * @param theDoor door of adjacent room.
     */
    public void assignLeftDoor(final Door theDoor) {
        myLeftDoor = theDoor;
    }

    /**
     * Will reassign the reference of room's right door to specified door.
     * @param theDoor door of adjacent room.
     */
    public void assignRightDoor(final Door theDoor) {
        myRightDoor = theDoor;
    }

    /**
     * Will reassign the reference of room's top door to specified door.
     * @param theDoor door of adjacent room.
     */
    public void assignTopDoor(final Door theDoor) {
        myTopDoor = theDoor;
    }

    /**
     * Will reassign the reference of room's bottom door to specified door.
     * @param theDoor door of adjacent room.
     */
    public void assignBottomDoor(final Door theDoor) {
        myBottomDoor = theDoor;
    }


}
