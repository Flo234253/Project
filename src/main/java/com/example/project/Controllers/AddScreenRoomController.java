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

public class AddScreenRoomController {

    @FXML
    private TextField aRoomNameField;

    @FXML
    private TextField aCapacityField;

    @FXML
    private TextField aFeaturesField;

    private ScreeningRoom newRoom;
    private boolean isSaved = false;
    private Stage stage;
    private ObservableList<ScreeningRoom> existingRooms;

    /**
     * Sets the stage for this controller.
     *
     * @param stage The stage to set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the existing rooms list for validation purposes.
     *
     * @param existingRooms The list of existing screening rooms.
     */
    public void setExistingRooms(ObservableList<ScreeningRoom> existingRooms) {
        this.existingRooms = existingRooms;
    }

    /**
     * Gets the newly created ScreeningRoom object.
     *
     * @return The new ScreeningRoom.
     */
    public ScreeningRoom getNewRoom() {
        return newRoom;
    }

    /**
     * Checks if the room was successfully saved.
     *
     * @return True if saved, false otherwise.
     */
    public boolean isSaved() {
        return isSaved;
    }

    /**
     * Handles the Save button click event.
     * Validates the input fields and creates a new ScreeningRoom object.
     */
    @FXML
    private void onSaveButtonClicked() {
        try {
            String roomName = aRoomNameField.getText().trim();
            String capacityStr = aCapacityField.getText().trim();
            String features = aFeaturesField.getText().trim();

            // Validate that fields are not empty
            if (roomName.isEmpty() || capacityStr.isEmpty() || features.isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            // Validate room name uniqueness
            if (existingRooms != null && isRoomNameDuplicate(roomName, existingRooms)) {
                throw new IllegalArgumentException("Room name must be unique.");
            }

            // Validate and parse capacity
            int capacity = parseAndValidateCapacity(capacityStr);

            // Validate features are allowed types
            if (!isFeatureValid(features)) {
                throw new IllegalArgumentException("Invalid feature. Allowed values are: IMAX, 3D, Standard.");
            }

            // Show confirmation alert before saving
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Save", null,
                    "Are you sure you want to save this screening room?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Create new ScreeningRoom object
                newRoom = new ScreeningRoom(roomName, capacity, features);
                isSaved = true; // Mark as saved

                // Todo: Implement persistence (e.g., saving to a database, CSV, or serialization)

                // Show confirmation that the room has been saved
                AlertHelper.showInformationAlert("Room Saved", null, "The screening room has been successfully saved.");

                closeWindow();
            }

        } catch (IllegalArgumentException e) {
            AlertHelper.showWarningAlert("Invalid Input", null, e.getMessage());
        }
    }

    /**
     * Parses and validates the capacity input.
     *
     * @param capacityStr The capacity string to parse and validate.
     * @return The parsed capacity as an integer.
     * @throws IllegalArgumentException if the capacity is not valid.
     */
    private int parseAndValidateCapacity(String capacityStr) {
        int capacity;
        try {
            capacity = Integer.parseInt(capacityStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Capacity must be a numeric value.");
        }

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
     *
     * @param roomName      The room name to check.
     * @param existingRooms The list of existing screening rooms.
     * @return True if the name is a duplicate, false otherwise.
     */
    private boolean isRoomNameDuplicate(String roomName, ObservableList<ScreeningRoom> existingRooms) {
        return existingRooms.stream().anyMatch(room -> room.getName().equalsIgnoreCase(roomName));
    }

    /**
     * Validates that the provided feature is allowed.
     *
     * @param feature The feature to validate.
     * @return True if the feature is valid, false otherwise.
     */
    private boolean isFeatureValid(String feature) {
        List<String> allowedFeatures = List.of("IMAX", "3D", "Standard");
        return allowedFeatures.contains(feature);
    }

    /**
     * Handles the Cancel button click event.
     * Closes the window without saving.
     */
    @FXML
    private void onCancelButtonClicked() {
        Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Cancel", null,
                "Are you sure you want to cancel? Any unsaved changes will be lost.");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            closeWindow();
        }
    }

    /**
     * Closes the current window.
     */
    private void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }
}
