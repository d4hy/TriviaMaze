package model;

/**
 * This program is a  parent class to be reused
 * for item child classes.
 * @author David Hoang
 * @version fall 2023
 *
 */
abstract class AbstractItem {
    /**
     * The name of the item.
     */
    private final String myName;

    /**
     * The history of the item.
     */
    private final String myHistory;

    /**
     * This method constructs the item and initializes its fields.
     * @param theName of the item.
     * @param theHistory of the item.
     */
    AbstractItem(final String theName, final String theHistory) {

        myName = theName;
        myHistory = theHistory;

    }

}
