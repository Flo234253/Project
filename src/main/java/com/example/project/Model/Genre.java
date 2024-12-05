package com.example.project.Model;

import com.example.project.Controllers.MovieListController;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class representing a Genre.
 * <p>
 * This class represents a genre associated with a movie, such as "Action", "Comedy", "Drama", etc.
 * It implements the {@link Serializable} interface, which allows the genre to be serialized,
 * meaning it can be saved to a file or transferred over a network.
 * The {@link Genre} class is primarily used in classes like {@link Movie} to categorize movies by genre.
 * </p>
 */
public class Genre implements Serializable {
    /**
     * The serial version UID for ensuring compatibility during the deserialization process.
     * <p>
     * This UID helps verify that the sender and receiver of a serialized object have loaded
     * classes for that object that are compatible with respect to serialization.
     * </p>
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The name of the genre.
     * <p>
     * This field is final because a genre's name cannot be changed after being created.
     * It must not be null or empty, as a genre must always have a valid name.
     * </p>
     */
    private final String aName;

    /**
     * Constructs a Genre instance with a specified name.
     * <p>
     * This constructor initializes a new {@link Genre} object using the provided name.
     * The name must be non-null and non-empty to ensure that all genres are valid and meaningful.
     * This is used in various other parts of the application such as in {@link Movie},
     * where a {@link Genre} instance categorizes movies.
     * </p>
     *
     * @param pName The name of the genre. Must not be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public Genre(String pName) {
        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre name cannot be null or empty.");
        }
        // Trimming the name to ensure no leading or trailing spaces exist
        this.aName = pName.trim();
    }

    /**
     * Gets the name of the genre.
     * <p>
     * This method returns the name of the genre, allowing access to the genre's value.
     * This method is used in multiple parts of the application, such as in controllers
     * when displaying movie genres in the manager interface or when filtering movies by genre.
     * </p>
     *
     * @return The name of the genre.
     */
    public String getName() {
        return aName;
    }

    /**
     * Determines whether this Genre is equal to another object.
     * <p>
     * Two {@link Genre} instances are considered equal if their names match, ignoring case differences.
     * This method is useful for comparing genres in scenarios like checking if a genre already exists
     * in a list of genres (e.g., in {@link MovieListController} for filtering movies).
     * </p>
     *
     * @param object The object to compare this Genre against.
     * @return {@code true} if the given object is equal to this Genre, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // If the objects are the same instance, they are equal
        if (this == object) return true;

        // If the other object is null or is not an instance of Genre, they are not equal
        if (object == null || getClass() != object.getClass()) return false;

        // Cast the object to Genre to compare names
        Genre genre = (Genre) object;
        return aName.equalsIgnoreCase(genre.aName); // Comparison is case-insensitive
    }

    /**
     * Returns the hash code for this Genre.
     * <p>
     * The hash code is generated based on the genre name in a case-insensitive manner.
     * This ensures that genres that are equal in terms of their name (ignoring case) will have the same hash code.
     * This method is important when genres are used in collections like {@link java.util.HashSet}
     * or as keys in {@link java.util.HashMap}.
     * </p>
     *
     * @return The hash code of this Genre.
     */
    @Override
    public int hashCode() {
        // Generate hash code using the lower-cased name for case-insensitive equality
        return aName.toLowerCase().hashCode();
    }

    /**
     * Returns a string representation of this Genre.
     * <p>
     * This method is used for displaying the genre name in the manager interface,
     * such as when showing a list of genres for a movie in a TableView.
     * </p>
     *
     * @return The name of the genre as a string.
     */
    @Override
    public String toString() {
        return aName;
    }
}
