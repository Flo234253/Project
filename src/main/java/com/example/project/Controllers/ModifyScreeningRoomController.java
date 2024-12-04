package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Helpers.AlertHelper;
import javafx.scene.control.ButtonType;

import java.util.List; // Import List from java.util
import java.util.Optional;

/**
 * Controller for the "Modify Screening Room" view.
 * This class handles user input for modifying the details of a screening room.
 */
public class ModifyScreeningRoomController {

    @FXML
    private TextField aNameField;

    @FXML
    private TextField aCapacityField;

    @FXML
    private TextField aTypeField;

    private Stage aStage;

    private boolean isSaved = false;

    /**
     * Sets the stage for the view. The stage is used to control the window.
     *
     * @param pStage The stage to set.
     */
    public void setStage(Stage pStage) {
        this.aStage = pStage;
    }

    /**
     * Populates the fields with the details of the screening room.
     *
     * @param pName     The name of the screening room.
     * @param pCapacity The capacity of the screening room.
     * @param pType     The type of the screening room.
     */
    public void setRoomDetails(String pName, int pCapacity, String pType) {
        aNameField.setText(pName);
        aCapacityField.setText(String.valueOf(pCapacity));
        aTypeField.setText(pType);
    }

    /**
     * Saves the changes made to the screening room and closes the view.
     * <p>
     * This method validates user inputs for the screening room details,
     * including the name, capacity, and type. If the inputs are valid,
     * the changes are saved and the view is closed. If any input is invalid,
     * an error alert is displayed.
     * </p>
     *
     * <p>
     * Triggered by the Save button.
     * </p>
     *
     * @throws IllegalArgumentException if any input field contains invalid data.
     */
    @FXML
    private void onSaveButtonClicked() {
        try {
            String newName = aNameField.getText().trim();
            String newCapacityText = aCapacityField.getText().trim();
            String newType = aTypeField.getText().trim();

            // Validate input fields
            validateName(newName);
            int newCapacity = validateCapacity(newCapacityText);
            validateFeature(newType);

            // Show confirmation alert before saving
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Save", null,
                    "Are you sure you want to save the changes to this screening room?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Save the updated data
                isSaved = true;

                // Show confirmation that the screening room has been saved
                AlertHelper.showInformationAlert("Screening Room Saved", null, "The screening room has been successfully saved.");

                // Close the window
                closeWindow();
            }

        } catch (IllegalArgumentException e) {
            // Show an error alert for invalid inputs
            AlertHelper.showErrorAlert("Invalid Input", e.getMessage());
        }
    }

    /**
     * Cancels the operation and closes the view.
     * Triggered by the Cancel button.
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
     * Validates the room name.
     *
     * @param name The name to validate.
     * @throws IllegalArgumentException if the name is invalid.
     */
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty.");
        }
    }

    /**
     * Parses and validates the capacity input.
     *
     * @param capacityStr The capacity string to parse and validate.
     * @return The parsed capacity as an integer.
     * @throws IllegalArgumentException if the capacity is not valid.
     */
    private int validateCapacity(String capacityStr) {
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
     * Validates the feature input.
     *
     * @param feature The feature to validate.
     * @throws IllegalArgumentException if the feature is invalid.
     */
    private void validateFeature(String feature) {
        List<String> allowedFeatures = List.of("IMAX", "3D", "Standard");
        if (feature == null || !allowedFeatures.contains(feature.trim())) {
            throw new IllegalArgumentException("Invalid feature. Allowed values are: IMAX, 3D, Standard.");
        }
    }

    /**
     * Closes the current window.
     */
    private void closeWindow() {
        if (aStage != null) {
            aStage.close();
        }
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
     * Gets the updated room name.
     *
     * @return The updated room name.
     */
    public String getUpdatedName() {
        return aNameField.getText().trim();
    }

    /**
     * Gets the updated room capacity.
     *
     * @return The updated room capacity.
     */
    public int getUpdatedCapacity() {
        return Integer.parseInt(aCapacityField.getText().trim());
    }

    /**
     * Gets the updated room features.
     *
     * @return The updated room features.
     */
    public String getUpdatedFeatures() {
        return aTypeField.getText().trim();
    }
}
