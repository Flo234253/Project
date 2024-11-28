package com.example.project.Model;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a showtime for a movie in a specific screening room.
 */
public class ShowTime {

    /** The unique identifier for the showtime. */
    private int aID;

    /** Attribute for the date and time of the showtime. */
    private LocalDateTime aDateTime;

    /** Attribute for the movie being shown. */
    private String aMovie;

    /** Attribute for the screening room where the movie is shown. */
    private String aScreeningRoom;

    /**
     * Constructor to create a new showtime with all attributes.
     *
     * @param pID            The unique identifier for the showtime.
     * @param pDateTime      The date and time of the showtime.
     * @param pMovie         The movie being shown.
     * @param pScreeningRoom The screening room where the movie is shown.
     */
    public ShowTime(int pID, LocalDateTime pDateTime, String pMovie, String pScreeningRoom) {
        this.aID = pID;
        this.aDateTime = pDateTime;
        this.aMovie = pMovie;
        this.aScreeningRoom = pScreeningRoom;
    }

    /**
     * Constructor with minimal attributes (for testing or quick display purposes).
     *
     * @param pID       The unique identifier for the showtime.
     * @param pDateTime The date and time of the showtime (as String, for simplicity).
     * @param pMovie    The movie being shown.
     */
    public ShowTime(int pID, String pDateTime, String pMovie) {
        this.aID = pID;
        // Define a custom DateTimeFormatter to match the string format "yyyy-MM-dd HH:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.aDateTime = LocalDateTime.parse(pDateTime, formatter); // Convert string to LocalDateTime using formatter
        this.aMovie = pMovie;
        this.aScreeningRoom = ""; // Default value
    }
    // Getters and Setters
    public int getaID() {
        return aID;
    }

    public void setID(int aID) {
        this.aID = aID;
    }

    public LocalDateTime getDateTime() {
        return aDateTime;
    }

    public void setDateTime(LocalDateTime aDateTime) {
        this.aDateTime = aDateTime;
    }

    public String getMovie() {
        return aMovie;
    }

    public void setMovie(String aMovie) {
        this.aMovie = aMovie;
    }

    public String getScreeningRoom() {
        return aScreeningRoom;
    }

    public void setScreeningRoom(String aScreeningRoom) {
        this.aScreeningRoom = aScreeningRoom;
    }

}
