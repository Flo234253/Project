package com.example.project.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
     * Checkbox to indicate whether the show is full.
     */
    @FXML
    private CheckBox fullCheckBox;

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
     * Handles the action event triggered by the "Add Showtime" button.
     * This method will process the inputs provided by the user and add a new showtime.
     *
     * @param actionEvent the event triggered when the "Add Showtime" button is clicked
     */
    @FXML
    private void handleAddShowTimeButton(ActionEvent actionEvent) {
        // Implementation for adding a new showtime
    }

    /**
     * Handles the action event triggered by the "Cancel" button.
     * This method will clear the input fields or return to the previous screen.
     *
     * @param actionEvent the event triggered when the "Cancel" button is clicked
     */
    @FXML
    private void handleCancelButton(ActionEvent actionEvent) {
        // Implementation for canceling the operation
    }
}
