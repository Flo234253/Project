package com.example.project.Model;

/**
 * Represents a movie with detailed information such as title, genre, release date,
 * duration, actors, director, and description.
 * @implNote This class validates its inputs to ensure data integrity.
 */
public class Movie {
    /**
     * The title of the movie.
     */
    private String aTitle;

    /**
     * The genre of the movie.
     */
    private String aGenre;

    /**
     * The release date of the movie in yyyy-MM-dd format.
     */
    private String aReleaseDate;

    /**
     * The duration of the movie (ex: "148 min").
     */
    private String aDuration;

    /**
     * A comma-separated list of the main actors in the movie.
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
     * @param pGenre        The genre of the movie. Must not be null or empty.
     * @param pReleaseDate  The release date of the movie (in YYYY-MM-DD format). Must not be null or empty.
     * @param pDuration     The duration of the movie (Ex., "148 min"). Must not be null or empty.
     * @param pActors       A comma-separated list of the main actors in the movie. Must not be null or empty.
     * @param pDirector     The director of the movie. Must not be null or empty.
     * @param pDescription  A brief description or synopsis of the movie. Must not be null or empty.
     * @throws IllegalArgumentException if any of the input parameters are invalid.
     */
    public Movie(String pTitle, String pGenre, String pReleaseDate, String pDuration, String pActors, String pDirector, String pDescription) {
        setTitle(pTitle);
        setGenre(pGenre);
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
        this.aTitle = pTitle;
    }

    /**
     * Gets the genre of the movie.
     *
     * @return The genre of the movie.
     */
    public String getGenre() {
        return aGenre;
    }

    /**
     * Sets the genre of the movie.
     *
     * @param pGenre The genre of the movie. Must not be null or empty.
     * @throws IllegalArgumentException if the genre is null or empty.
     */
    public void setGenre(String pGenre) {
        if (pGenre == null || pGenre.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty.");
        }
        this.aGenre = pGenre;
    }

    /**
     * Gets the release date of the movie.
     *
     * @return The release date of the movie in yyyy-mm-dd format.
     */
    public String getReleaseDate() {
        return aReleaseDate;
    }

    /**
     * Sets the release date of the movie.
     *
     * @param pReleaseDate The release date of the movie. Must not be null or empty.
     * @throws IllegalArgumentException if the release date is null or empty.
     */
    public void setReleaseDate(String pReleaseDate) {
        if (pReleaseDate == null || pReleaseDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Release date cannot be null or empty.");
        }
        this.aReleaseDate = pReleaseDate;
    }

    /**
     * Gets the duration of the movie.
     *
     * @return The duration of the movie (e.g., "148 min").
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
        this.aDuration = pDuration;
    }

    /**
     * Gets the list of actors in the movie.
     *
     * @return A comma-separated list of the main actors in the movie.
     */
    public String getActors() {
        return aActors;
    }

    /**
     * Sets the list of actors in the movie.
     *
     * @param pActors A comma-separated list of the main actors in the movie. Must not be null or empty.
     * @throws IllegalArgumentException if the actors list is null or empty.
     */
    public void setActors(String pActors) {
        if (pActors == null || pActors.trim().isEmpty()) {
            throw new IllegalArgumentException("Actors cannot be null or empty.");
        }
        this.aActors = pActors;
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
        this.aDirector = pDirector;
    }

    /**
     * Gets the description or synopsis of the movie.
     *
     * @return A brief description of the movie.
     */
    public String getDescription() {
        return aDescription;
    }

    /**
     * Sets the description or synopsis of the movie.
     *
     * @param pDescription A brief description of the movie. Must not be null or empty.
     * @throws IllegalArgumentException if the description is null or empty.
     */
    public void setDescription(String pDescription) {
        if (pDescription == null || pDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.aDescription = pDescription;
    }
}
