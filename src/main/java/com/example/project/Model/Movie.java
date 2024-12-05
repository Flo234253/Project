package com.example.project.Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Movie.
 * <p>
 * This class encapsulates the details of a movie, such as its title, genres, release date,
 * duration, actors, director, and description. It implements {@link Serializable} to allow
 * movies to be serialized, enabling them to be saved to or loaded from files. The {@link Movie}
 * class is used throughout the application in classes such as {@link com.example.project.Controllers.MovieListController}
 * to manage movie data, display it to manager, and persist changes.
 * </p>
 */
public class Movie implements Serializable {
    /**
     * The serial version UID for ensuring compatibility during deserialization.
     * <p>
     * This UID is necessary for ensuring that during the deserialization of an object,
     * the version of the class is compatible with the version that was serialized.
     * </p>
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The title of the movie.
     * <p>
     * This field stores the name of the movie and must not be null or empty.
     * It uniquely identifies a movie in the application.
     * </p>
     */
    private String aTitle;

    /**
     * The list of genres associated with the movie.
     * <p>
     * This list represents the different genres that categorize the movie (e.g., Action, Comedy).
     * It helps in searching and filtering movies based on genres.
     * </p>
     */
    private List<Genre> aGenres;

    /**
     * The release date of the movie in YYYY-MM-DD format.
     * <p>
     * The release date represents when the movie was first made available to the public.
     * It helps sort or display movies chronologically.
     * </p>
     */
    private String aReleaseDate;

    /**
     * The duration of the movie, e.g., "148 min".
     * <p>
     * The duration provides information on the total length of the movie.
     * It is used in displaying movie details to manager.
     * </p>
     */
    private String aDuration;

    /**
     * The main actors in the movie, typically represented as a comma-separated string.
     * <p>
     * This field stores the key actors who played major roles in the movie.
     * It is useful for searching or displaying cast information.
     * </p>
     */
    private String aActors;

    /**
     * The director of the movie.
     * <p>
     * This field holds the name of the person who directed the movie. It is used to display
     * movie details and can also be used for searching movies by director.
     * </p>
     */
    private String aDirector;

    /**
     * A brief description or synopsis of the movie.
     * <p>
     * The description provides an overview or storyline of the movie to give manager an idea of what the movie is about.
     * </p>
     */
    private String aDescription;

    /**
     * Constructs a Movie instance with the specified details.
     * <p>
     * This constructor initializes a new {@link Movie} object by setting its title, genres, release date,
     * duration, actors, director, and description. This constructor is used in different parts of the
     * application, such as when adding a new movie in the {@link com.example.project.Controllers.AddMovieController}
     * or when initializing the default movie list.
     * </p>
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
     * <p>
     * The title must be non-null and non-empty. It is an essential identifier for a movie.
     * This method is used when constructing or modifying a {@link Movie} object, such as in
     * the {@link com.example.project.Controllers.ModifyMovieController}.
     * </p>
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
     * @return A list of genres. Returns a defensive copy to protect the original list.
     */
    public List<Genre> getGenres() {
        // Defensive copy to prevent external modification of the internal genre list
        return new ArrayList<>(aGenres);
    }

    /**
     * Sets the list of genres associated with the movie.
     * <p>
     * The genre list must not be null or empty. Genres are important for categorizing and filtering movies.
     * This method is used when constructing or updating the {@link Movie} object.
     * </p>
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
     * <p>
     * The release date must be in the format YYYY-MM-DD and not be null or empty.
     * This information is used for displaying movie details and sorting movies by release date.
     * </p>
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
     * <p>
     * The duration represents how long the movie runs and must not be null or empty.
     * This method is used when creating or updating movie details, such as in the
     * {@link com.example.project.Controllers.ModifyMovieController}.
     * </p>
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
     * <p>
     * The actors field is a comma-separated list of actors who played major roles in the movie.
     * This information is essential for displaying movie details and can be used in search features.
     * </p>
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
     * <p>
     * The director's name must not be null or empty. This field is used for displaying movie details
     * and can also be used in search or filtering operations based on the director.
     * </p>
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
     * <p>
     * This description gives an overview of the storyline of the movie.
     * It is used in multiple parts of the application, such as in the {@link com.example.project.Controllers.MovieListController}
     * to display movie details to manager.
     * </p>
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
