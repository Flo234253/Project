package com.example.project.Model;



import javafx.beans.property.*;

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

    /** The unique identifier for the showtime. */
    private int aID;

    /** Attribute  for The date and time of the showtime. */
    private LocalDateTime aDateTime;

    /** Attribute  for  The movie being shown. */
    private String aMovie;

    /** Attribute  for  The screening room where the movie is shown. */
    private String aScreeningRoom;

    /** Attribute for whether the showtime is full or not. */
    private boolean aIsFull;



    /**
     * Constructor to create a new showtime with a unique ID, date and time, movie, and screening room.
     *
     * @param pID The unique identifier for the showtime.
     * @param pDateTime The date and time of the showtime.
     * @param pMovie The movie being shown.
     * @param pScreeningRoom The screening room where the movie is shown.
     * @param pIsFull The status of whether the showtime is full or not.
     */
    public ShowTime(int pID, LocalDateTime pDateTime, String pMovie, String pScreeningRoom, boolean pIsFull) {
        this.aID = pID;
        this.aDateTime = pDateTime;
        this.aMovie = pMovie;
        this.aScreeningRoom = pScreeningRoom;
        this.aIsFull = pIsFull;
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
     * //todo
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
     * Gets the fullness status of the showtime.
     *
     * @return true if the showtime is full, false otherwise.
     */
    public boolean isFull() {
        return aIsFull;
    }

    /**
     * Sets the fullness status of the showtime.
     *
     * @param aIsFull true if the showtime is full, false otherwise.
     */
    public void setFull(boolean aIsFull) {
        this.aIsFull = aIsFull;
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
