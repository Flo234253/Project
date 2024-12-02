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

    private final IntegerProperty aShowTimeId;
    private final StringProperty aMovie;
    private final ObjectProperty<LocalDateTime> aDateTime;
    private final IntegerProperty aRoomId;
    private final BooleanProperty aIsFull;

    /**
     * Constructs a new ShowTime object with the specified details.
     *
     * @param pShowTimeId the ID of the showtime
     * @param PDate       the date of the showtime in "MM/dd/yyyy" format
     * @param PTime       the time of the showtime in "HH:mm" format
     * @param PRoomId     the ID of the screening room
     * @param PMovie      the name of the movie
     * @param pIsFull     whether the screening is fully booked
     */
    public ShowTime(int pShowTimeId, String PDate, String PTime, int PRoomId, String PMovie, boolean pIsFull) {
        this.aShowTimeId = new SimpleIntegerProperty(pShowTimeId);
        this.aMovie = new SimpleStringProperty(PMovie);
        this.aRoomId = new SimpleIntegerProperty(PRoomId);
        this.aIsFull = new SimpleBooleanProperty(pIsFull);

        // Combine date and time into a LocalDateTime object
        String dateTimeStr = PDate + " " + PTime; // Combine date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        this.aDateTime = new SimpleObjectProperty<>(LocalDateTime.parse(dateTimeStr, formatter));
    }

    /**
     * Returns the observable property for the showtime ID.
     *
     * @return the IntegerProperty representing the showtime ID
     */
    public IntegerProperty aShowTimeIdProperty() {
        return aShowTimeId;
    }

    /**
     * Returns the observable property for the movie name.
     *
     * @return the StringProperty representing the movie name
     */
    public StringProperty aMovieProperty() {
        return aMovie;
    }


    /**
     * Returns the observable property for the date and time of the showtime.
     *
     * @return the ObjectProperty representing the date and time of the showtime
     */
    public ObjectProperty<LocalDateTime> aDateTimeProperty() {
        return aDateTime;
    }


    /**
     * Returns the observable property for the screening room ID.
     *
     * @return the IntegerProperty representing the screening room ID
     */
    public IntegerProperty aRoomIdProperty() {
        return aRoomId;
    }


    /**
     * Returns the observable property indicating whether the screening is fully booked.
     *
     * @return the BooleanProperty representing the "full" status of the screening
     */
    public BooleanProperty aIsFullProperty() {
        return aIsFull;
    }


    /**
     * Returns the name of the movie.
     *
     * @return the movie name
     */
    public String getaMovie() {
        return aMovie.get();
    }

    /**
     * Returns the date and time of the showtime.
     *
     * @return the LocalDateTime object representing the showtime
     */
    public LocalDateTime getaDateTime() {
        return aDateTime.get();
    }

    /**
     * Returns whether the screening is fully booked.
     *
     * @return {@code true} if the screening is fully booked, otherwise {@code false}
     */
    public boolean isFull() {
        return aIsFull.get();
    }
}
