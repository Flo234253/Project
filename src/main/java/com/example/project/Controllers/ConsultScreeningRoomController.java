package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the Consult Screening Room view.
 * <p>
 * This class is responsible for managing the view of detailed information about a specific screening room.
 * It allows the manager to consult information about a room's name, capacity, and type.
 * The class also provides functionality for closing the view when the manager finishes consulting the details.
 * </p>
 */
public class ConsultScreeningRoomController {

    /**
     * TextField for displaying the name of the screening room.
     * <p>
     * This field shows the name of the screening room, such as "Room A".
     * The value is set via the {@link #setRoomDetails} method.
     * </p>
     */
    @FXML
    private TextField aNameField;

    /**
     * TextField for displaying the capacity of the screening room.
     * <p>
     * This field shows the maximum number of people that the screening room can accommodate.
     * The value is set via the {@link #setRoomDetails} method.
     * </p>
     */
    @FXML
    private TextField aCapacityField;

    /**
     * TextField for displaying the type of the screening room.
     * <p>
     * This field displays the type of the screening room, which could be "IMAX", "3D", or "Standard".
     * The value is set via the {@link #setRoomDetails} method.
     * </p>
     */
    @FXML
    private TextField aTypeField;

    /**
     * The JavaFX Stage representing the current window.
     * <p>
     * This stage is used to control the current window, such as closing it when needed.
     * The stage is set via the {@link #setStage} method.
     * </p>
     */
    private Stage aStage;

    /**
     * Sets the stage for the Consult Screening Room view.
     * <p>
     * This method assigns the JavaFX Stage object that represents the current window.
     * The stage is used to perform actions such as closing the window.
     * It must be set before any operation involving closing the window.
     * </p>
     *
     * @param pStage The Stage object to set. It must not be null.
     */
    public void setStage(Stage pStage) {
        this.aStage = pStage;
    }

    /**
     * Populates the fields with the details of the screening room.
     * <p>
     * This method is used to populate the fields of the view with the details of a specific screening room.
     * It is typically called by another controller when the manager selects a screening room to consult.
     * The method will fill in the name, capacity, and type information for the room.
     * </p>
     *
     * @param pName     The name of the screening room. This should be a non-null and non-empty value,
     *                  typically descriptive of the room (e.g., "Room A").
     * @param pCapacity The capacity of the screening room. It must be a positive number indicating the number
     *                  of seats available in the room.
     * @param pType     The type of the screening room. This should be a non-null and non-empty value,
     *                  specifying the type of experience the room offers (e.g., "IMAX", "Standard").
     */
    public void setRoomDetails(String pName, int pCapacity, String pType) {
        aNameField.setText(pName);
        aCapacityField.setText(String.valueOf(pCapacity));
        aTypeField.setText(pType);
    }

    /**
     * Handles the event when the Close button is clicked.
     * <p>
     * This method is called when the manager clicks on the "Close" button in the Consult Screening Room view.
     * It checks if the stage has been set and, if so, closes the current window, allowing the manager to exit
     * the screening room details view.
     * </p>
     */
    @FXML
    private void onCloseButtonClicked() {
        if (aStage != null) {
            aStage.close();
        }
    }
}
