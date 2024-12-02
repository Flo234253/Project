package com.example.project.Model;



import javafx.beans.property.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a showtime for a movie in a specific screening room.
 * <p>
 * This class includes details such as the showtime ID, movie name, date and time of the show,
 * room ID, and whether the screening is fully booked.
 * </p>
 */
public class ShowTime {

    private final IntegerProperty showTimeId;
    private final StringProperty movie;
    private final ObjectProperty<LocalDateTime> dateTime;
    private final IntegerProperty roomId;
    private final BooleanProperty isFull;

    /**
     * Constructs a new ShowTime object with the specified details.
     *
     * @param showTimeId the ID of the showtime
     * @param date       the date of the showtime in "MM/dd/yyyy" format
     * @param time       the time of the showtime in "HH:mm" format
     * @param roomId     the ID of the screening room
     * @param movie      the name of the movie
     * @param isFull     whether the screening is fully booked
     */
    public ShowTime(int showTimeId, String date, String time, int roomId, String movie, boolean isFull) {
        this.showTimeId = new SimpleIntegerProperty(showTimeId);
        this.movie = new SimpleStringProperty(movie);
        this.roomId = new SimpleIntegerProperty(roomId);
        this.isFull = new SimpleBooleanProperty(isFull);

        // Combine date and time into a LocalDateTime object
        String dateTimeStr = date + " " + time; // Combine date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        this.dateTime = new SimpleObjectProperty<>(LocalDateTime.parse(dateTimeStr, formatter));
    }

    /**
     * Returns the observable property for the showtime ID.
     *
     * @return the IntegerProperty representing the showtime ID
     */
    public IntegerProperty showTimeIdProperty() {
        return showTimeId;
    }

    /**
     * Returns the observable property for the movie name.
     *
     * @return the StringProperty representing the movie name
     */
    public StringProperty movieProperty() {
        return movie;
    }


    /**
     * Returns the observable property for the date and time of the showtime.
     *
     * @return the ObjectProperty representing the date and time of the showtime
     */
    public ObjectProperty<LocalDateTime> dateTimeProperty() {
        return dateTime;
    }


    /**
     * Returns the observable property for the screening room ID.
     *
     * @return the IntegerProperty representing the screening room ID
     */
    public IntegerProperty roomIdProperty() {
        return roomId;
    }


    /**
     * Returns the observable property indicating whether the screening is fully booked.
     *
     * @return the BooleanProperty representing the "full" status of the screening
     */
    public BooleanProperty isFullProperty() {
        return isFull;
    }


    /**
     * Returns the name of the movie.
     *
     * @return the movie name
     */
    public String getMovie() {
        return movie.get();
    }

    /**
     * Returns the date and time of the showtime.
     *
     * @return the LocalDateTime object representing the showtime
     */
    public LocalDateTime getDateTime() {
        return dateTime.get();
    }

    /**
     * Returns whether the screening is fully booked.
     *
     * @return {@code true} if the screening is fully booked, otherwise {@code false}
     */
    public boolean isFull() {
        return isFull.get();
    }
}
