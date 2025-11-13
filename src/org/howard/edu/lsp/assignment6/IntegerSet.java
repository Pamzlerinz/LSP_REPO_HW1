package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IntegerSet represents a mathematical set of integers with no duplicates.
 * This class provides standard set operations including union, intersection,
 * difference, and complement, as well as basic set operations like add, remove,
 * and membership testing.
 * 
 * @author Kafilat Sarki-Umar
 */
public class IntegerSet {
    /** Internal list storing the set elements */
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears the internal representation of the set, removing all elements.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * 
     * @return the size of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if the two sets are equal, false otherwise.
     * Two sets are equal if they contain all of the same values in ANY order.
     * This overrides the equals method from the Object class.
     * 
     * @param o the object to compare with this set
     * @return true if the sets contain the same elements, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof IntegerSet)) return false;
        
        IntegerSet other = (IntegerSet) o;
        
        // Sets are equal if they have same size and this contains all elements of other
        if (this.length() != other.length()) return false;
        
        return set.containsAll(other.set);
    }

    /**
     * Returns true if the set contains the specified value, otherwise false.
     * 
     * @param value the value to search for
     * @return true if value is in the set, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     * 
     * @return the maximum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest item in the set.
     * 
     * @return the minimum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set. If the item is already present, does nothing.
     * 
     * @param item the value to add to the set
     */
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set. If the item is not present, does nothing.
     * 
     * @param item the value to remove from the set
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Performs set union operation.
     * Modifies this set to contain all unique elements in this set or the other set.
     * 
     * @param other the set to union with this set
     */
    public void union(IntegerSet other) {
        for (Integer item : other.set) {
            if (!contains(item)) {
                set.add(item);
            }
        }
    }

    /**
     * Performs set intersection operation.
     * Modifies this set to contain only elements that are in both this set and the other set.
     * 
     * @param other the set to intersect with this set
     */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /**
     * Performs set difference operation (this \ other).
     * Modifies this set to remove all elements that are found in the other set.
     * 
     * @param other the set whose elements will be removed from this set
     */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /**
     * Performs set complement operation.
     * Modifies this set to become (other \ this), containing elements in other that are not in this.
     * 
     * @param other the set to take the complement from
     */
    public void complement(IntegerSet other) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer item : other.set) {
            if (!contains(item)) {
                result.add(item);
            }
        }
        set = result;
    }

    /**
     * Returns true if the set is empty, false otherwise.
     * 
     * @return true if the set has no elements, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a String representation of the set in the format [element1, element2, ...].
     * This overrides the toString method from the Object class.
     * 
     * @return a string representation of the set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}
