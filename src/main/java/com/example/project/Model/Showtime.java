package com.example.project.Model;



import java.time.LocalDateTime;


/**
 * Represents a showtime for a movie in a specific screening room.
 */
public class Showtime {

    /** The unique identifier for the showtime. */
    private int id;

    /** Attribute  for The date and time of the showtime. */
    private LocalDateTime dateTime;

    /** Attribute  for  The movie being shown. */
    private String movie;

    /** Attribute  for  The screening room where the movie is shown. */
    private String screeningRoom;


    /**
     * Constructor to create a new showtime with a unique ID, date and time, movie, and screening room.
     *
     * @param id The unique identifier for the showtime.
     * @param dateTime The date and time of the showtime.
     * @param movie The movie being shown.
     * @param screeningRoom The screening room where the movie is shown.
     */
    public Showtime(int id, LocalDateTime dateTime, String movie, String screeningRoom) {
        this.id = id;
        this.dateTime = dateTime;
        this.movie = movie;
        this.screeningRoom = screeningRoom;
    }

    /**
     * Gets the unique identifier for the showtime.
     *
     * @return The unique identifier for the showtime.
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the unique identifier for the showtime.
     *
     * @param id The unique identifier for the showtime.
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the date and time of the showtime.
     *
     * @return The date and time of the showtime.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }


    /**
     * Sets the date and time of the showtime.
     *
     * @param dateTime The date and time of the showtime.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the movie being shown.
     *
     * @return The movie being shown.
     */
    public String getMovie() {
        return movie;
    }

    /**
     * Sets the movie being shown.
     *
     * @param movie The movie being shown.
     */
    public void setMovie(String movie) {
        this.movie = movie;
    }


    /**
     * Gets the screening room where the movie is shown.
     *
     * @return The screening room.
     */
    public String getScreeningRoom() {
        return screeningRoom;
    }

    /**
     * Sets the screening room where the movie is shown.
     *
     * @param screeningRoom The screening room.
     */
    public void setScreeningRoom(String screeningRoom) {
        this.screeningRoom = screeningRoom;
    }


}
