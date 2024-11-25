package com.example.project.Controllers;

import Helpers.AlertHelper;
import Helpers.ScreeningRoomCell;
import com.example.project.Model.ScreeningRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

/**
 * Controller class for managing the screening room list view.
 * Provides functionality for searching, consulting, adding, editing, and deleting screening rooms.
 */
public class ScreenRoomListController {

    @FXML
    private TextField aSearchField;

    @FXML
    private ListView<ScreeningRoom> aScreenRoomListView;

    @FXML
    private Button aConsultButton;

    @FXML
    private Button aAddButton;

    @FXML
    private Button aEditButton;

    @FXML
    private Button aDeleteButton;

    private ObservableList<ScreeningRoom> aScreenRooms;

    /**
     * Initializes the controller.
     * Loads the list of screening rooms, sets up the ListView, and configures button behavior.
     */
    //TODO
    @FXML
    public void initialize() {
        loadScreenRooms();

        // Populate the ListView
        aScreenRoomListView.setItems(aScreenRooms);

        // Custom cell factory for better ListView display
        aScreenRoomListView.setCellFactory(listView -> new ScreeningRoomCell());

        // Disable buttons when no screening room is selected
        aScreenRoomListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean isSelected = newVal != null;
            aConsultButton.setDisable(!isSelected);
            aEditButton.setDisable(!isSelected);
            aDeleteButton.setDisable(!isSelected);
        });
    }

    /**
     * Loads screening rooms into the list.
     *  TODO: Move this logic to an external helper or service class
     */
    private void loadScreenRooms() {
        aScreenRooms = FXCollections.observableArrayList(
                new ScreeningRoom("Room 1", 100, "Standard"),
                new ScreeningRoom("Room 2", 150, "IMAX"),
                new ScreeningRoom("Room 3", 200, "3D")
        );
    }

    /**
     * Filters the screening room list based on the search input.
     */
    //Todo
    @FXML
    private void onSearchButtonClicked() {
        String query = aSearchField.getText().toLowerCase();

        // Filter screening rooms based on the input
        FilteredList<ScreeningRoom> filteredRooms = new FilteredList<>(aScreenRooms, room ->
                room.getName().toLowerCase().contains(query) || room.getFeatures().toLowerCase().contains(query)
        );
        // Check if the filtered list is empty
        if (filteredRooms.isEmpty()) {
            // Show an error message if no screening room match the input
            AlertHelper.showWarningAlert(
                    "No Results Found",
                    "No screening rooms match your search input.",
                    "Please check your input and try again."
            );
        } else {
            // Update the ListView with the filtered screening room
            aScreenRoomListView.setItems(filteredRooms);
        }
    }

    /**
     * Displays the details of the selected screening room.
     */
    //Todo
    @FXML
    private void onConsultButtonClicked() {
        ScreeningRoom selectedRoom = aScreenRoomListView.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            // TODO: Implement logic to open the Consult Screening Room view
        }
    }

    /**
     * Opens the add screening room view.
     */
    @FXML
    private void onAddButtonClicked() {
        // TODO: Implement logic to open the Add Screening Room view
    }

    /**
     * Opens the edit screening room view.
     */
    @FXML
    private void onEditButtonClicked() {
        ScreeningRoom selectedRoom = aScreenRoomListView.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            // TODO: Implement logic to open the Edit Screening Room view and pre-fill data
        }
    }

    /**
     * Deletes the selected screening room from the list.
     */
    //Todo
    @FXML
    private void onDeleteButtonClicked() {
        ScreeningRoom selectedRoom = aScreenRoomListView.getSelectionModel().getSelectedItem();

        // Show confirmation alert
        if (selectedRoom != null) {
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert(
                    "Confirm Deletion",
                    "Are you sure you want to delete this screening room?",
                    "Room: " + selectedRoom.getName()
            );

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the selected screening room
                aScreenRooms.remove(selectedRoom);
                // Show success message
                AlertHelper.showInformationAlert(
                        "Screening Room Deleted",
                        null,
                        "The room '" + selectedRoom.getName() + "' was successfully deleted."
                );
            }
        } else {
            // Show warning if no screening room is selected
            AlertHelper.showWarningAlert(
                    "No Room Selected",
                    null,
                    "Please select a room to delete."
            );
        }
    }
}
