package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Helpers.AlertHelper;
import javafx.scene.control.ButtonType;

import java.util.List;
import java.util.Optional;

/**
 * Controller for the "Modify Screening Room" view.
 * <p>
 * This class allows manager to modify the details of an existing screening room, such as its name,
 * capacity, and type. It provides functionality for saving changes or cancelling the operation.
 * </p>
 * <p>
 * The view is typically used by managers who want to edit screening room details.
 * After modification, changes can be persisted for future use.
 * </p>
 */
public class ModifyScreeningRoomController {

    /**
     * TextField for the name of the screening room.
     * <p>
     * This field is used to modify the name of the screening room.
     * It should be filled with a non-empty and unique value before saving.
     * </p>
     */
    @FXML
    private TextField aNameField;

    /**
     * TextField for the capacity of the screening room.
     * <p>
     * This field is used to modify the capacity of the screening room.
     * The value must be a positive integer, with a maximum allowed value of 255.
     * </p>
     */
    @FXML
    private TextField aCapacityField;

    /**
     * TextField for the type of the screening room.
     * <p>
     * This field is used to modify the type of the screening room, such as "IMAX", "3D", or "Standard".
     * The value must match one of the allowed types.
     * </p>
     */
    @FXML
    private TextField aTypeField;

    /**
     * The current stage representing the Modify Screening Room window.
     * <p>
     * This stage object is used to control the lifecycle of the current window,
     * such as closing the window upon saving or cancelling.
     * </p>
     */
    private Stage aStage;

    /**
     * A boolean flag indicating whether the screening room was successfully saved.
     * <p>
     * This flag helps track whether the save operation was successful,
     * allowing other parts of the application to determine whether updates were made.
     * </p>
     */
    private boolean isSaved = false;

    /**
     * Sets the stage for the view.
     * <p>
     * The stage is used to manage the window's lifecycle, such as closing it when necessary.
     * It must be set before attempting any window-related operations.
     * </p>
     *
     * @param pStage The stage to set for the view.
     */
    public void setStage(Stage pStage) {
        this.aStage = pStage;
    }

    /**
     * Populates the fields with the current details of the screening room.
     * <p>
     * This method initializes the form fields with the existing screening room information
     * that the manager wants to modify.
     * </p>
     *
     * @param pName     The name of the screening room.
     * @param pCapacity The capacity of the screening room.
     * @param pType     The type of the screening room (e.g., "IMAX", "3D", "Standard").
     */
    public void setRoomDetails(String pName, int pCapacity, String pType) {
        aNameField.setText(pName);
        aCapacityField.setText(String.valueOf(pCapacity));
        aTypeField.setText(pType);
    }

    /**
     * Saves the changes made to the screening room and closes the view.
     * <p>
     * This method validates manager inputs, including the screening room name, capacity, and type.
     * If validation passes, it prompts the manager for confirmation and saves the changes.
     * Upon successful save, the view is closed.
     * </p>
     *
     * <p>
     * Triggered by the "Save" button in the view.
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
     * <p>
     * Triggered by the "Cancel" button in the view, this method prompts the manager
     * for confirmation before discarding any unsaved changes.
     * </p>
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
     * <p>
     * The room name cannot be empty or null. If the validation fails,
     * an IllegalArgumentException is thrown with an appropriate error message.
     * </p>
     *
     * @param name The room name to validate.
     * @throws IllegalArgumentException if the room name is empty or null.
     */
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty.");
        }
    }

    /**
     * Parses and validates the capacity input.
     * <p>
     * This method ensures that the capacity value is a numeric value, greater than zero,
     * and does not exceed 255. If validation fails, an exception is thrown.
     * </p>
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
     * <p>
     * The screening room feature (type) must be one of the allowed values:
     * "IMAX", "3D", or "Standard". If the provided value is invalid, an exception is thrown.
     * </p>
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
     * <p>
     * This method closes the Modify Screening Room view.
     * It is used after saving or cancelling the modification process.
     * </p>
     */
    private void closeWindow() {
        if (aStage != null) {
            aStage.close();
        }
    }

    /**
     * Checks if the screening room was successfully saved.
     * <p>
     * This method returns a boolean value indicating whether the modifications
     * were saved. This can be useful for determining if changes should be persisted.
     * </p>
     *
     * @return True if saved successfully, false otherwise.
     */
    public boolean isSaved() {
        return isSaved;
    }

    /**
     * Gets the updated room name.
     * <p>
     * This method returns the updated name of the screening room as entered by the manager.
     * </p>
     *
     * @return The updated room name.
     */
    public String getUpdatedName() {
        return aNameField.getText().trim();
    }

    /**
     * Gets the updated room capacity.
     * <p>
     * This method returns the updated capacity of the screening room as entered by the manager.
     * </p>
     *
     * @return The updated room capacity.
     */
    public int getUpdatedCapacity() {
        return Integer.parseInt(aCapacityField.getText().trim());
    }

    /**
     * Gets the updated room features.
     * <p>
     * This method returns the updated feature/type of the screening room (e.g., "IMAX").
     * </p>
     *
     * @return The updated room feature/type.
     */
    public String getUpdatedFeatures() {
        return aTypeField.getText().trim();
    }
}
