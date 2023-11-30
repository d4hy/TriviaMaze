package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This program implements a Bag as an ArrayList that lets you put elements in it
 * and take them out.
 *
 * @author David Hoang
 * @version Fall 2023
 * @param <E> any generic type of Object.
 */

public class ArrayListBag<E> {
    /**
     * A Random that we use for grabbing random Elements.
     */
    private static final Random RANDOM_ELEMENT = new Random();

    /**
     * A list of Elements that have been stored.
     */
    private final  List<E> myElements;



    /**
     * Constructor that has an empty list of elements.
     */
    public ArrayListBag() {
        myElements = new ArrayList<E>();

    }

    /**
     * Adds an element into the bag, will not allow duplicate elements.
     *
     * @param theE the element that is being added.

     */
    public void add(final E theE) {
        for (int i = 0; i < myElements.size(); i++) {
            if (theE.equals(myElements.get(i))) {
                myElements.set(i, theE);
                return;
            }


        }
        myElements.add(theE);
    }
    /**
     * Grabs a random element from the bag, while removing what's inside.
     *
     * @throws IllegalArgumentException if there is nothing in the bag
     */
    public E grab() {
        final int randomIntOfElement = RANDOM_ELEMENT.nextInt(myElements.size());
        final E e = myElements.get(randomIntOfElement);
        myElements.remove(randomIntOfElement);
        return  e;

    }

    /**
     * Checks to see if the bag is empty.
     * @return if the Bag is empty or not.
     */
    public boolean isBagEmpty() {
        return myElements.isEmpty();
    }

}