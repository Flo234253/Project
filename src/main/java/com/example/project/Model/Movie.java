package com.example.project.Model;

import java.util.ArrayList;
import java.util.List;

//Todo:verify/understand all element
/**
 * Represents a movie with detailed information such as title, genres, release date,
 * duration, actors, director, and description.
 */
public class Movie {

    /**
     * The title of the movie.
     */
    private String aTitle;

    /**
     * The list of genres associated with the movie.
     */
    private List<Genre> aGenres;

    /**
     * The release date of the movie in YYYY-MM-DD format.
     */
    private String aReleaseDate;

    /**
     * The duration of the movie, e.g., "148 min".
     */
    private String aDuration;

    /**
     * The main actors in the movie, typically as a comma-separated string.
     */
    private String aActors;

    /**
     * The director of the movie.
     */
    private String aDirector;

    /**
     * A brief description or synopsis of the movie.
     */
    private String aDescription;

    /**
     * Constructs a Movie instance with the specified details.
     *
     * @param pTitle        The title of the movie. Must not be null or empty.
     * @param pGenres       A list of genres associated with the movie. Must not be null or empty.
     * @param pReleaseDate  The release date of the movie in YYYY-MM-DD format. Must not be null or empty.
     * @param pDuration     The duration of the movie, e.g., "148 min". Must not be null or empty.
     * @param pActors       The main actors in the movie, typically as a comma-separated string. Must not be null or empty.
     * @param pDirector     The director of the movie. Must not be null or empty.
     * @param pDescription  A brief description or synopsis of the movie. Must not be null or empty.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public Movie(String pTitle, List<Genre> pGenres, String pReleaseDate, String pDuration,
                 String pActors, String pDirector, String pDescription) {
        setTitle(pTitle);
        setGenres(pGenres);
        setReleaseDate(pReleaseDate);
        setDuration(pDuration);
        setActors(pActors);
        setDirector(pDirector);
        setDescription(pDescription);
    }

    /**
     * Gets the title of the movie.
     *
     * @return The title of the movie.
     */
    public String getTitle() {
        return aTitle;
    }

    /**
     * Sets the title of the movie.
     *
     * @param pTitle The title of the movie. Must not be null or empty.
     * @throws IllegalArgumentException if the title is null or empty.
     */
    public void setTitle(String pTitle) {
        if (pTitle == null || pTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.aTitle = pTitle.trim();
    }

    /**
     * Gets the list of genres associated with the movie.
     *
     * @return A list of genres. Returns a defensive copy.
     */
    public List<Genre> getGenres() {
        return new ArrayList<>(aGenres);
    }

    /**
     * Sets the list of genres associated with the movie.
     *
     * @param pGenres A list of genres. Must not be null or empty.
     * @throws IllegalArgumentException if the genres list is null or empty.
     */
    public void setGenres(List<Genre> pGenres) {

        if (pGenres == null || pGenres.isEmpty()) {
            throw new IllegalArgumentException("Genres cannot be null or empty.");
        }
        this.aGenres = new ArrayList<>(pGenres);
    }

    /**
     * Gets the release date of the movie.
     *
     * @return The release date in YYYY-MM-DD format.
     */
    public String getReleaseDate() {
        return aReleaseDate;
    }

    /**
     * Sets the release date of the movie.
     *
     * @param pReleaseDate The release date in YYYY-MM-DD format. Must not be null or empty.
     * @throws IllegalArgumentException if the release date is null or empty.
     */
    public void setReleaseDate(String pReleaseDate) {
        if (pReleaseDate == null || pReleaseDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Release date cannot be null or empty.");
        }
        this.aReleaseDate = pReleaseDate.trim();
    }

    /**
     * Gets the duration of the movie.
     *
     * @return The duration of the movie, e.g., "148 min".
     */
    public String getDuration() {
        return aDuration;
    }

    /**
     * Sets the duration of the movie.
     *
     * @param pDuration The duration of the movie. Must not be null or empty.
     * @throws IllegalArgumentException if the duration is null or empty.
     */
    public void setDuration(String pDuration) {
        if (pDuration == null || pDuration.trim().isEmpty()) {
            throw new IllegalArgumentException("Duration cannot be null or empty.");
        }
        this.aDuration = pDuration.trim();
    }

    /**
     * Gets the main actors in the movie.
     *
     * @return A comma-separated string of actors.
     */
    public String getActors() {
        return aActors;
    }

    /**
     * Sets the main actors in the movie.
     *
     * @param pActors A comma-separated string of actors. Must not be null or empty.
     * @throws IllegalArgumentException if the actors string is null or empty.
     */
    public void setActors(String pActors) {
        if (pActors == null || pActors.trim().isEmpty()) {
            throw new IllegalArgumentException("Actors cannot be null or empty.");
        }
        this.aActors = pActors.trim();
    }

    /**
     * Gets the director of the movie.
     *
     * @return The director of the movie.
     */
    public String getDirector() {
        return aDirector;
    }

    /**
     * Sets the director of the movie.
     *
     * @param pDirector The director of the movie. Must not be null or empty.
     * @throws IllegalArgumentException if the director is null or empty.
     */
    public void setDirector(String pDirector) {
        if (pDirector == null || pDirector.trim().isEmpty()) {
            throw new IllegalArgumentException("Director cannot be null or empty.");
        }
        this.aDirector = pDirector.trim();
    }

    /**
     * Gets the description or synopsis of the movie.
     *
     * @return The description of the movie.
     */
    public String getDescription() {
        return aDescription;
    }

    /**
     * Sets the description or synopsis of the movie.
     *
     * @param pDescription The description of the movie. Must not be null or empty.
     * @throws IllegalArgumentException if the description is null or empty.
     */
    public void setDescription(String pDescription) {
        if (pDescription == null || pDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.aDescription = pDescription.trim();
    }
}
