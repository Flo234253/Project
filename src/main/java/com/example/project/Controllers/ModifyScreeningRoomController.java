package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Helpers.AlertHelper;

//Todo:javadoc

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

            // Save the updated data
            // TODO: Add logic to save the updated data to the model or database

            // Close the window
            //Todo
            if (aStage != null) {
                aStage.close();
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
        if (aStage != null) {
            aStage.close();
        }
    }
}
