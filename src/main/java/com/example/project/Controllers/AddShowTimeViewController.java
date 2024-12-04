package com.example.project.Controllers;


import com.example.project.Model.ShowTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for handling the "Add Showtime" view.
 * It manages user interactions with the UI components such as text fields, checkboxes, and buttons.
 */
public class AddShowTimeViewController {

    /**
     * Text field for entering the ShowTime ID.
     */
    @FXML
    private TextField showTimeIdField;

    /**
     * Text field for entering the time of the show.
     */
    @FXML
    private TextField timeField;

    /**
     * Text field for entering the room ID where the show will take place.
     */
    @FXML
    private TextField roomIdField;

    /**
     * Text field for entering the movie ID associated with the show.
     */
    @FXML
    private TextField movieIdField;



    /**
     * Button for adding a new showtime.
     */
    @FXML
    private Button addShowTimeButton;

    /**
     * Button for canceling the operation and returning to the previous view.
     */
    @FXML
    private Button cancelButton;

    /**
    * date picker for selecting a proper date.
    * */
    @FXML
    private DatePicker datePicker;



    @FXML
    private ObservableList<ShowTime> showTimeList = FXCollections.observableArrayList(); // Initialize the list

    /**
     * Method to set the showTimeList, ensuring that it is initialized if null.
     * @param showTimeList the list to set
     */
    public void setShowTimeList(ObservableList<ShowTime> showTimeList) {
        // Ensure that showTimeList is initialized if it's null
        if (showTimeList == null) {
            this.showTimeList = FXCollections.observableArrayList();
        } else {
            this.showTimeList = showTimeList;
        }
    }

    /**
     * Handles the action event triggered by the "Add Showtime" button.
     * <p>
     * This method processes the user inputs for movie and room IDs, validates them,
     * generates a unique showtime ID, and adds the new showtime to the list.
     * </p>
     * @param actionEvent the event triggered when the "Add Showtime" button is clicked
     */
    @FXML
    private void handleAddShowTimeButton(ActionEvent actionEvent) {
        if (showTimeList == null) {
            System.err.println("ShowTime list is not initialized.");
            return;  // Prevent further processing if the list is null
        }

        try {
            // Get the input from the user for movie and room IDs
            String movie = movieIdField.getText().trim();
            String room = roomIdField.getText().trim();

            // Check if either movie or room input is empty
            if (movie.isEmpty() || room.isEmpty()) {
                throw new IllegalArgumentException("Movie or room cannot be empty.");
            }

            // Generate a unique showtime ID based on the size of the list
            int showTimeId = showTimeList.size() + 1;
            LocalDateTime showDateTime = LocalDateTime.now();  // You can adjust this as needed

            // Create a new ShowTime object with the provided details
            ShowTime newShowTime = new ShowTime(showTimeId, showDateTime, movie, room);

            // Add the new showtime to the list
            showTimeList.add(newShowTime);

            // Save the updated showtime list
            saveShowTimes(showTimeList);

        } catch (IllegalArgumentException e) {
            // Handle case when movie or room is empty
            System.err.println(e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
        }
    }

    /**
     * Saves the showtimes list to a file for persistence.
     * <p>
     * The showtimes are serialized and written to the specified file for later retrieval.
     * </p>
     * @param showTimeList the list of showtimes to be saved
     */

    private void saveShowTimes(ObservableList<ShowTime> showTimeList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/showtimes.ser"))) {
            // Convert ObservableList to List before serialization
            List<ShowTime> showTimeListToSave = new ArrayList<>(showTimeList);
            oos.writeObject(showTimeListToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * Handles the action event triggered by the "Cancel" button.
     * This method will clear the input fields or return to the previous screen.
     *
     * @param actionEvent the event triggered when the "Cancel" button is clicked
     */
    @FXML
    private void handleCancelButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
