package Model;

/**
 * Represents a movie with detailed information such as title, genre, release date,
 * duration, actors, director, and description.
 */
public class Movie {
    private String aTitle;
    private String aGenre;
    private String aReleaseDate;
    private String aDuration;
    private String aActors;
    private String aDirector;
    private String aDescription;

    /**
     * Constructs a Movie instance with the specified details.
     *
     * @param pTitle        The title of the movie.
     * @param pGenre        The genre of the movie.
     * @param pReleaseDate  The release date of the movie (in YYYY-MM-DD format).
     * @param pDuration     The duration of the movie (e.g., "148 min").
     * @param pActors       A comma-separated list of the main actors in the movie.
     * @param pDirector     The director of the movie.
     * @param pDescription  A brief description or synopsis of the movie.
     */
    public Movie(String pTitle, String pGenre, String pReleaseDate, String pDuration, String pActors, String pDirector, String pDescription) {
        this.aTitle = pTitle;
        this.aGenre = pGenre;
        this.aReleaseDate = pReleaseDate;
        this.aDuration = pDuration;
        this.aActors = pActors;
        this.aDirector = pDirector;
        this.aDescription = pDescription;
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
     * Gets the genre of the movie.
     *
     * @return The genre of the movie.
     */
    public String getGenre() {
        return aGenre;
    }

    /**
     * Gets the release date of the movie.
     *
     * @return The release date of the movie in YYYY-MM-DD format.
     */
    public String getReleaseDate() {
        return aReleaseDate;
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
     * Gets the list of actors in the movie.
     *
     * @return A comma-separated list of the main actors in the movie.
     */
    public String getActors() {
        return aActors;
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
     * Gets the description or synopsis of the movie.
     *
     * @return A brief description of the movie.
     */
    public String getDescription() {
        return aDescription;
    }
}
