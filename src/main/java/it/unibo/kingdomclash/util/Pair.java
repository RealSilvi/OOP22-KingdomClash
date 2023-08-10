package it.unibo.kingdomclash.util;

import java.io.Serializable;

/**
 * A pair class to contain data.
 * @param <X> the first serializable element type
 * @param <Y> the second serializable element type
 */
public final class Pair<X extends Serializable, Y extends Serializable> implements Serializable {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 14724610L;

    private X first;
    private Y second;
    /**
     * Constructs a pair composed of 2 elements.
     * @param first     the first element
     * @param second    the second element
     */
    public Pair(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }
    /**
     * Sets the first element.
     * @param first the first element to set
     */
    public void setFirst(final X first) {
        this.first = first;
    }
    /**
     * Sets the second element.
     * @param second the second element to set
     */
    public void setSecond(final Y second) {
        this.second = second;
    }
    /**
     * Gets the first element.
     * @return the first element
     */
    public X getFirst() {
        return this.first;
    }
    /**
     * Gets the second element.
     * @return the second element
     */
    public Y getSecond() {
        return this.second;
    }
    /**
     * Prints in a formatted way the content of the pair.
     * @return a formatted string with the content of the pair
     */
    @Override
    public String toString() {
        return "<" + this.first + "," + this.second + ">";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }

    /*This behaviour is intended and necessary for the function*/
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (first == null) {
            if (other.first != null) {
                return false;
            }
        } else if (!first.equals(other.first)) {
            return false;
        }
        if (second == null) {
            if (other.second != null) {
                return false;
            }
        } else if (!second.equals(other.second)) {
            return false;
        }
        return true;
    }
}
