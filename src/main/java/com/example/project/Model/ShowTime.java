package com.example.project.Model;



import javafx.beans.property.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a showtime for a movie in a specific screening room.
 */
public class ShowTime {

    private final IntegerProperty showTimeId;
    private final StringProperty movie;
    private final ObjectProperty<LocalDateTime> dateTime;
    private final IntegerProperty roomId;
    private final BooleanProperty isFull;

    // Constructor
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

    // Getter methods (with observable properties)
    public IntegerProperty showTimeIdProperty() {
        return showTimeId;
    }

    public StringProperty movieProperty() {
        return movie;
    }

    public ObjectProperty<LocalDateTime> dateTimeProperty() {
        return dateTime;
    }

    public IntegerProperty roomIdProperty() {
        return roomId;
    }

    public BooleanProperty isFullProperty() {
        return isFull;
    }

    public String getMovie() {
        return movie.get();
    }

    public LocalDateTime getDateTime() {
        return dateTime.get();
    }

    public boolean isFull() {
        return isFull.get();
    }
}
