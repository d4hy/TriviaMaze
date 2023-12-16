/*
 * TriviaMaze
 * Fall 2023
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * This program will be used to test the methods in
 *  the Room class.
 *@author David Hoang
 *@author Faith Capito
 *@version Fall 2023
 */

class RoomTest {

    /**
     * A test fixture for the false results.
     */
    private static final String SHOULD_BE_FALSE = "This should be False";

    /**
     * A test fixture for the true results.
     */
    private static final String SHOULD_BE_TRUE = "This should be true";

    /**
     * A test fixture for the Left door.
     */
    private static final String LEFT_DOOR = "Left";


    /**
     * A test fixture for the Right door.
     */
    private static final String RIGHT_DOOR = "Right";
    /**
     * A test fixture for the Bottom door.
     */
    private static final String BOTTOM_DOOR = "Bottom";

    /**
     * A test fixture for the Top Door.
     */
    private static final String TOP_DOOR = "Top";

    /**
     * Test fixture for room to be used for tests.
     */
    private Room myTestRoom;
    @BeforeEach
    void setUp() {
        myTestRoom = new Room();

    }

    @Test
    void testDeadEndStatusWithoutSetting() {
        assertFalse(myTestRoom.isDeadEnd(), SHOULD_BE_FALSE);
    }

    @Test
    void testDeadEndStatusWithSettingTrue() {
        myTestRoom.setAsDeadEnd();
        assertTrue(myTestRoom.isDeadEnd(), SHOULD_BE_TRUE);
    }
    @Test
    void testSetDoorLeft() {
        myTestRoom.setDoor(LEFT_DOOR);
        myTestRoom.getLeftDoor().lock();
        assertTrue(myTestRoom.getLeftDoor().isLocked(), SHOULD_BE_TRUE);
        myTestRoom.setDoor(RIGHT_DOOR);
        myTestRoom.assignLeftDoor(myTestRoom.getRightDoor());
        assertFalse(myTestRoom.getLeftDoor().isLocked(), SHOULD_BE_FALSE);

    }

    @Test
    void testSetDoorRight() {
        myTestRoom.setDoor(RIGHT_DOOR);
        myTestRoom.getRightDoor().lock();
        assertTrue(myTestRoom.getRightDoor().isLocked(), SHOULD_BE_TRUE);
        myTestRoom.setDoor(BOTTOM_DOOR);
        myTestRoom.assignRightDoor(myTestRoom.getBottomDoor());
        assertFalse(myTestRoom.getRightDoor().isLocked(), SHOULD_BE_FALSE);


    }
    @Test
    void testSetDoorBottom() {
        myTestRoom.setDoor(BOTTOM_DOOR);
        myTestRoom.getBottomDoor().lock();
        assertTrue(myTestRoom.getBottomDoor().isLocked(), SHOULD_BE_TRUE);
        myTestRoom.setDoor(TOP_DOOR);
        myTestRoom.assignBottomDoor(myTestRoom.getTopDoor());
        assertFalse(myTestRoom.getBottomDoor().isLocked(), SHOULD_BE_FALSE);

    }
    @Test
    void testSetDoorTop() {
        myTestRoom.setDoor(TOP_DOOR);
        myTestRoom.getTopDoor().lock();
        assertTrue(myTestRoom.getTopDoor().isLocked(), SHOULD_BE_TRUE);
        myTestRoom.setDoor(LEFT_DOOR);
        myTestRoom.assignTopDoor(myTestRoom.getLeftDoor());
        assertFalse(myTestRoom.getTopDoor().isLocked(), SHOULD_BE_FALSE);

    }
}