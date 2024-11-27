package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Todo:verify

/**
 * Controller for the Consult Screening Room view.
 * <p>
 * This class handles the display of detailed information about a specific screening room.
 * It allows manager to view the room's details, such as name, capacity, and type,
 * and provides functionality to close the view.
 * </p>
 */
public class ConsultScreeningRoomController {

    /**
     * TextField for displaying the name of the screening room.
     */
    @FXML
    private TextField aNameField;

    /**
     * TextField for displaying the capacity of the screening room.
     */
    @FXML
    private TextField aCapacityField;

    /**
     * TextField for displaying the type of the screening room.
     */
    @FXML
    private TextField aTypeField;

    /**
     * The stage associated with this view.
     */
    private Stage aStage;

    /**
     * Sets the stage for the view. The stage is used to control the window.
     *
     * @param pStage The stage to set. It must not be null.
     */
    public void setStage(Stage pStage) {
        this.aStage = pStage;
    }

    /**
     * Populates the fields with the details of the screening room.
     *
     * @param pName     The name of the screening room. Must not be null or empty.
     * @param pCapacity The capacity of the screening room. Must be greater than zero.
     * @param pType     The type of the screening room. Must not be null or empty.
     */
    public void setRoomDetails(String pName, int pCapacity, String pType) {
        aNameField.setText(pName);
        aCapacityField.setText(String.valueOf(pCapacity));
        aTypeField.setText(pType);
    }

    /**
     * Handles the event when the Close button is clicked.
     * <p>
     * This method closes the current view if the stage is set.
     * </p>
     */
    @FXML
    private void onCloseButtonClicked() {
        if (aStage != null) {
            aStage.close();
        }
    }
}
