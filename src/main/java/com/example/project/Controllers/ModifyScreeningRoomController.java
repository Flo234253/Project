package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Helpers.AlertHelper;
import javafx.scene.control.ButtonType;

import java.util.Optional;

// Todo:javadoc

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

    // Todo:add confirmation message
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
            // Validate input fields
            String newName = aNameField.getText();
            String newCapacityText = aCapacityField.getText();
            String newType = aTypeField.getText();

            // Check if name or type fields are empty
            if (newName == null || newName.trim().isEmpty()) {
                throw new IllegalArgumentException("Room name cannot be empty.");
            }
            if (newType == null || newType.trim().isEmpty()) {
                throw new IllegalArgumentException("Room type cannot be empty.");
            }

            // Validate and parse capacity
            int newCapacity;
            try {
                newCapacity = Integer.parseInt(newCapacityText);
                if (newCapacity <= 0) {
                    throw new IllegalArgumentException("Capacity must be a positive number.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Capacity must be a valid integer.");
            }

            // Show confirmation alert before saving
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Save", null,
                    "Are you sure you want to save the changes to this screening room?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Save the updated data
                // TODO: Add logic to save the updated data to the model or database

                // Show confirmation that the screening room has been saved
                AlertHelper.showInformationAlert("Screening Room Saved", null, "The screening room has been successfully saved.");

                // Close the window
                if (aStage != null) {
                    aStage.close();
                }
            }

        } catch (IllegalArgumentException e) {
            // Show an error alert for invalid inputs
            AlertHelper.showErrorAlert("Invalid Input", e.getMessage());
        }
    }

    // Todo:add confirmation message
    /**
     * Cancels the operation and closes the view.
     * Triggered by the Cancel button.
     */
    @FXML
    private void onCancelButtonClicked() {
        Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Cancel", null,
                "Are you sure you want to cancel? Any unsaved changes will be lost.");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (aStage != null) {
                aStage.close();
            }
        }
    }
}
