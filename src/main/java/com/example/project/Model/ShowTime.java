package com.example.project.Model;



import javafx.beans.property.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a showtime for a movie in a specific screening room.
 * <p>
 * This class includes details such as the showtime ID, movie name, date and time of the show,
 * room ID, and whether the screening is fully booked.
 * </p>
 */
public class ShowTime implements Serializable {
    /**
     * A unique identifier for the serialized version of this class.
     * <p>
     * Ensures compatibility during deserialization. If the class structure changes,
     * the {@code serialVersionUID} should be updated to avoid {@link java.io.InvalidClassException}.
     * </p>
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /** The unique identifier for the showtime. */
    private int aID;

    /** Attribute  for The date and time of the showtime. */
    private LocalDateTime aDateTime;

    /** Attribute  for  The movie being shown. */
    private String aMovie;

    /** Attribute  for  The screening room where the movie is shown. */
    private String aScreeningRoom;





    /**
     * Constructor to create a new showtime with a unique ID, date and time, movie, and screening room.
     *
     * @param pID The unique identifier for the showtime.
     * @param pDateTime The date and time of the showtime.
     * @param pMovie The movie being shown.
     * @param pScreeningRoom The screening room where the movie is shown.
     *
     */
    public ShowTime(int pID, LocalDateTime pDateTime, String pMovie, String pScreeningRoom) {
        this.aID = pID;
        this.aDateTime = pDateTime;
        this.aMovie = pMovie;
        this.aScreeningRoom = pScreeningRoom;

    }


    /**
     * Gets the unique identifier for the showtime.
     *
     * @return The unique identifier for the showtime.
     */
    public int getaID() {
        return aID;
    }


    /**
     * Sets the unique identifier for the showtime.
     *
     * @param aID The unique identifier for the showtime.
     */
    public void setID(int aID) {
        this.aID = aID;
    }


    /**
     * Gets the date and time of the showtime.
     *
     * @return The date and time of the showtime.
     */
    public LocalDateTime getDateTime() {
        return aDateTime;
    }


    /**
     * Sets the date and time of the showtime.
     *
     * @param aDateTime The date and time of the showtime.
     */
    public void setDateTime(LocalDateTime aDateTime) {
        this.aDateTime = aDateTime;
    }

    /**
     * Gets the movie being shown.
     *
     * @return The movie being shown.
     */
    public String getMovie() {
        return aMovie;
    }

    /**
     * Sets the movie being shown.
     *
     * @param aMovie The movie being shown.
     */
    public void setMovie(String aMovie) {
        this.aMovie = aMovie;
    }


    /**
     * Gets the screening room where the movie is shown.
     *
     * @return The screening room.
     *
     */
    public String getScreeningRoom() {
        return aScreeningRoom;
    }

    /**
     * Sets the screening room where the movie is shown.
     *
     * @param aScreeningRoom The screening room.
     */
    public void setScreeningRoom(String aScreeningRoom) {
        this.aScreeningRoom = aScreeningRoom;
    }



    /**
     * Gets the formatted date for the showtime.
     *
     * @return The formatted date (MM/dd/yyyy).
     */
    public String getFormattedDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return aDateTime.format(dateFormatter);
    }

    /**
     * Gets the formatted time for the showtime.
     *
     * @return The formatted time (HH:mm).
     */
    public String getFormattedTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return aDateTime.format(timeFormatter);
    }


}
