package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.ScreeningRoom;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing the "Add Screening Room" view.
 * <p>
 * This class is responsible for handling manager interactions for adding a new screening room,
 * including capturing form inputs, validating the inputs, checking for duplicates,
 * and handling both saving and cancellation actions.
 * The newly created room can be accessed after saving.
 * </p>
 */
public class AddScreenRoomController {

    /**
     * TextField for entering the name of the screening room.
     * <p>
     * This field allows the manager to input a unique name for the screening room.
     * It is used as the identifier and must be validated to be unique.
     * </p>
     */
    @FXML
    private TextField aRoomNameField;

    /**
     * TextField for entering the seating capacity of the screening room.
     * <p>
     * This field allows the manager to enter the total capacity of the screening room.
     * The value must be a positive integer, within acceptable limits.
     * </p>
     */
    @FXML
    private TextField aCapacityField;

    /**
     * TextField for entering features of the screening room.
     * <p>
     * This field allows the manager to input available features of the screening room,
     * such as "IMAX", "3D", or "Standard". The value must match allowed types.
     * </p>
     */
    @FXML
    private TextField aFeaturesField;

    /**
     * A newly created ScreeningRoom object that will hold the details of the added room.
     */
    private ScreeningRoom newRoom;

    /**
     * A boolean flag indicating if the room was successfully saved.
     * This is used to check whether the add operation was successful.
     */
    private boolean isSaved = false;

    /**
     * The JavaFX Stage object representing the current "Add Screening Room" window.
     * This is used for closing the window when required.
     */
    private Stage stage;

    /**
     * List of existing screening rooms, used for validation purposes.
     * <p>
     * This list is used to check if the new room name is unique compared to the existing rooms.
     * </p>
     */
    private ObservableList<ScreeningRoom> existingRooms;

    /**
     * Sets the stage for this controller.
     * <p>
     * This method assigns the JavaFX Stage object representing the current view.
     * It is used to manage the window, such as closing it after saving.
     * </p>
     *
     * @param stage The Stage object to set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the existing rooms list for validation purposes.
     * <p>
     * This method sets the list of existing screening rooms, allowing the controller
     * to verify if the new room name is unique when adding a new room.
     * </p>
     *
     * @param existingRooms The list of existing screening rooms.
     */
    public void setExistingRooms(ObservableList<ScreeningRoom> existingRooms) {
        this.existingRooms = existingRooms;
    }

    /**
     * Gets the newly created ScreeningRoom object.
     * <p>
     * This method provides the newly created ScreeningRoom after the room is saved successfully.
     * It can be used to access the details of the added room.
     * </p>
     *
     * @return The newly created ScreeningRoom object.
     */
    public ScreeningRoom getNewRoom() {
        return newRoom;
    }

    /**
     * Checks if the screening room was successfully saved.
     * <p>
     * This method indicates if the new screening room has been saved successfully,
     * and is used to confirm if the add operation was completed without errors.
     * </p>
     *
     * @return True if the screening room was saved, false otherwise.
     */
    public boolean isSaved() {
        return isSaved;
    }

    /**
     * Handles the Save button click event.
     * <p>
     * This method captures input data from the form fields, performs validation on the fields,
     * checks for room name uniqueness, parses and validates the capacity, validates the feature types,
     * and finally creates a new ScreeningRoom object.
     * <br><br>
     * If any field validation fails, an appropriate error alert is shown. If all validations pass,
     * the manager is asked for confirmation before saving the new room.
     * </p>
     */
    @FXML
    private void onSaveButtonClicked() {
        try {
            // Extract input values from text fields
            String roomName = aRoomNameField.getText().trim();
            String capacityStr = aCapacityField.getText().trim();
            String features = aFeaturesField.getText().trim();

            // Validate that all fields are filled
            if (roomName.isEmpty() || capacityStr.isEmpty() || features.isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            // Validate room name uniqueness against the existing list of rooms
            if (existingRooms != null && isRoomNameDuplicate(roomName, existingRooms)) {
                throw new IllegalArgumentException("Room name must be unique.");
            }

            // Parse and validate the capacity input
            int capacity = parseAndValidateCapacity(capacityStr);

            // Validate that the provided feature is one of the allowed options
            if (!isFeatureValid(features)) {
                throw new IllegalArgumentException("Invalid feature. Allowed values are: IMAX, 3D, Standard.");
            }

            // Show confirmation alert before proceeding to save the screening room
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Save", null,
                    "Are you sure you want to save this screening room?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Create a new ScreeningRoom object with the provided details
                newRoom = new ScreeningRoom(roomName, capacity, features);
                isSaved = true; // Mark as saved

                // Show success message and close the window
                AlertHelper.showInformationAlert("Room Saved", null, "The screening room has been successfully saved.");
                closeWindow();
            }

        } catch (IllegalArgumentException e) {
            // Show an alert if there are validation errors
            AlertHelper.showWarningAlert("Invalid Input", null, e.getMessage());
        }
    }

    /**
     * Parses and validates the capacity input.
     * <p>
     * This method takes the capacity input as a string, attempts to parse it as an integer,
     * and performs validation to ensure the capacity is within acceptable limits.
     * If the value is invalid, an IllegalArgumentException is thrown.
     * </p>
     *
     * @param capacityStr The capacity string to parse and validate.
     * @return The parsed capacity as an integer.
     * @throws IllegalArgumentException if the capacity is not a valid positive number or exceeds allowed limits.
     */
    private int parseAndValidateCapacity(String capacityStr) {
        int capacity;
        try {
            // Attempt to parse the input string as an integer
            capacity = Integer.parseInt(capacityStr);
        } catch (NumberFormatException e) {
            // If parsing fails, throw an error
            throw new IllegalArgumentException("Capacity must be a numeric value.");
        }

        // Ensure the capacity is positive and does not exceed 255
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive number.");
        }
        if (capacity > 255) {
            throw new IllegalArgumentException("Capacity cannot exceed 255.");
        }

        return capacity;
    }

    /**
     * Checks if the room name is already used in the list of existing rooms.
     * <p>
     * This method ensures that each screening room has a unique name.
     * It checks the given room name against the list of existing rooms.
     * </p>
     *
     * @param roomName      The room name to check.
     * @param existingRooms The list of existing screening rooms.
     * @return True if the room name is already used, false otherwise.
     */
    private boolean isRoomNameDuplicate(String roomName, ObservableList<ScreeningRoom> existingRooms) {
        // Use a stream to check if any room in the list has the same name as the new room
        return existingRooms.stream().anyMatch(room -> room.getName().equalsIgnoreCase(roomName));
    }

    /**
     * Validates that the provided feature is allowed.
     * <p>
     * This method checks if the provided feature is one of the allowed types.
     * It is used to ensure that only valid features are added to the screening room.
     * </p>
     *
     * @param feature The feature to validate.
     * @return True if the feature is valid, false otherwise.
     */
    private boolean isFeatureValid(String feature) {
        // Define a list of allowed features for screening rooms
        List<String> allowedFeatures = List.of("IMAX", "3D", "Standard");
        return allowedFeatures.contains(feature);
    }

    /**
     * Handles the Cancel button click event.
     * <p>
     * This method prompts the manager to confirm if they want to cancel without saving.
     * If confirmed, the window is closed, and any unsaved changes are discarded.
     * </p>
     */
    @FXML
    private void onCancelButtonClicked() {
        // Show confirmation alert before canceling the operation
        Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Cancel", null,
                "Are you sure you want to cancel? Any unsaved changes will be lost.");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            closeWindow();
        }
    }

    /**
     * Closes the current window.
     * <p>
     * This method closes the JavaFX Stage that represents the current "Add Screening Room" view.
     * It is called when the manager chooses to cancel the action or after a successful save.
     * </p>
     */
    private void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }
}
