package model;

import java.util.Locale;

/**
 * This program is a  parent class to be reused
 * for item child classes.
 * @author David Hoang
 * @author Faith Capito
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

    /**
     * This method returns the name of the item.
     * @return the name of the item as a String.
     */
    public String getName() {
        return myName;
    }

    /**
     * This method returns the history of the item.
     * @return the history of the item as a String.
     */
    public String getHistory() {
        return myHistory;
    }
    /**
     * Prompt that prints to the console when collecting item.
     */
    public void collect() {
        System.out.println("Collected " + myName + "!");
    }

    /**
     * {@inheritDoc}
     * A String representation of the item, and it's history
     *
     */
    @Override
    public String toString() {
        final StringBuilder stats = new StringBuilder();
        stats.append("Type of item:");
        stats.append(getClass().getSimpleName().toLowerCase(Locale.US));
        stats.append(", Name of item:");
        stats.append(myName);
        stats.append("\n History of them:");
        stats.append(myHistory);
        return stats.toString();
    }
}
