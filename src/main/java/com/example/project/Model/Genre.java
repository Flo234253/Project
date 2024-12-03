package com.example.project.Model;

import java.io.Serial;
import java.io.Serializable;

public class Genre implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The name of the genre.
     */
    private final String aName;

    /**
     * Constructs a Genre instance with a specified name.
     *
     * @param pName The name of the genre. Must not be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public Genre(String pName) {
        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre name cannot be null or empty.");
        }
        this.aName = pName.trim();
    }

    /**
     * Gets the name of the genre.
     *
     * @return The name of the genre.
     */
    public String getName() {
        return aName;
    }

    /**
     * Determines whether this Genre is equal to another object.
     * Two genres are considered equal if their names are the same,
     * regardless of case.
     *
     * @param object The object to compare this Genre against.
     * @return {@code true} if the given object is equal to this Genre,
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        Genre genre = (Genre) object;
        return aName.equalsIgnoreCase(genre.aName);
    }

    /**
     * Returns the hash code for this Genre.
     * The hash code is case-insensitive.
     *
     * @return The hash code of this Genre.
     */
    @Override
    public int hashCode() {
        return aName.toLowerCase().hashCode();
    }

    /**
     * Returns a string representation of this Genre.
     *
     * @return The name of the genre as a string.
     */
    @Override
    public String toString() {
        return aName;
    }
}
