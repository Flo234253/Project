package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.ScreeningRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for the "Add Movie" view.
 * <p>
 * This class manages the form for adding a new movie, including capturing user input
 * and providing functionality to save or cancel the operation.
 * </p>
 */
public class ScreenRoomListController {

    //Todo:javadoc
    @FXML
    private TextField aSearchField;

    @FXML
    private Button aRefreshButton;

    @FXML
    private TableView<ScreeningRoom> aScreenRoomTableView;

    @FXML
    private TableColumn<ScreeningRoom, String> nameColumn;
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
     * <p>
     * This method is called after the FXML file has been loaded. It sets up the TableView,
     * adds the columns to the sreening room properties, and disables buttons when no room is selected.
     * </p>
     */
    //TODO
    @FXML
    public void initialize() {
        loadScreenRooms();

        // Add the Name column to the ScreeningRoom model's name property
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        // Populate the TableView
        aScreenRoomTableView.setItems(aScreenRooms);

        // Disable buttons when no screening room is selected
        aScreenRoomTableView.getSelectionModel().selectedItemProperty().addListener((_, _, newVal) -> {
            boolean isSelected = newVal != null;
            aConsultButton.setDisable(!isSelected);
            aEditButton.setDisable(!isSelected);
            aDeleteButton.setDisable(!isSelected);
        });
        // Disable Refresh button by default
        //todo:javadoc
        aRefreshButton.setDisable(true);
    }

    /**
     * Loads a predefined list of room name into the TableView.
     * This is temporary logic for demonstration purposes
     * TODO: Move this logic to an external helper or service class
     */
    private void loadScreenRooms() {
        aScreenRooms = FXCollections.observableArrayList(
                new ScreeningRoom("Room 1", 100, "Standard"),
                new ScreeningRoom("Room 2", 150, "IMAX"),
                new ScreeningRoom("Room 3", 200, "3D")
        );
    }

    /**
     * Refreshes the TableView to display all screening rooms.
     * Triggered by the "Refresh" button.
     */
    @FXML
    private void onRefreshButtonClicked() {
        // Reset the TableView to display the original list of screening rooms
        aScreenRoomTableView.setItems(aScreenRooms);
    }

    /**
     * Filters the screening room list based on the input from the search field.
     * <p>
     * If no room name match the input, an alert is displayed to the manager.
     * </p>
     */
    //Todo
    @FXML
    private void onSearchButtonClicked() {
        String pInput = aSearchField.getText().toLowerCase();

        // Filter screening rooms based on the input
        FilteredList<ScreeningRoom> filteredRooms = new FilteredList< >( aScreenRooms, room ->
                room.getName().toLowerCase().contains(pInput) || room.getFeatures().toLowerCase().contains(pInput)
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
            // Update the TableView with the filtered screening room
            aScreenRoomTableView.setItems(filteredRooms);
        }

        // Enable the Refresh button if the search was performed
        aRefreshButton.setDisable(false);
    }


    /**
     * Opens a new window to display the information about the selected screening room.
     * <p>
     * This method is triggered when the Consult button is clicked. It retrieves the
     * selected screening room from the TableView, loads the Consult Screening Room view,
     * and passes the room details to the controller for display.
     * If no room is selected, a warning alert is displayed to the user.
     * </p>
     *
     * @throws IllegalStateException if the FXML file cannot be loaded.
     * @throws NullPointerException if the FXMLLoader fails to initialize the controller.
     */
    @FXML
    private void onConsultButtonClicked() {
        // Retrieve the selected screening room from the TableView
        ScreeningRoom selectedRoom = aScreenRoomTableView.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            try {
                // Load the FXML file for the Consult Screening Room view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/consult-screening-room-view.fxml"));
                Parent root = loader.load();

                // Get the controller for the view and pass the selected room details
                ConsultScreeningRoomController controller = loader.getController();
                controller.setRoomDetails(
                        selectedRoom.getName(),
                        selectedRoom.getCapacity(),
                        selectedRoom.getFeatures()
                );

                // Set up and display the new stage
                Stage stage = new Stage();
                controller.setStage(stage); // Pass the stage to the ConsultScreeningRoomController
                stage.setScene(new Scene(root));
                stage.setTitle("Screening Room Details");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                //todo
                // AlertHelper.showErrorAlert("Error", "Unable to open the Modify Screening Room view.");
            }
        } else {
            // Display a warning if no screening room is selected
            AlertHelper.showWarningAlert("No Selection", "Please select a screening room to consult.", null);
        }
    }



    /**
     * Opens a new window for adding a new room to the list.
     * <p>
     * The implementation for this functionality will be done later.
     * </p>
     */
    @FXML
    private void onAddButtonClicked() {
        try {
            // Load the FXML for the Add Screening Room view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/add-screening-room-view.fxml"));
            Parent root = loader.load();

            // Get the controller for the Add Screening Room view
            AddScreenRoomController controller = loader.getController();
            Stage stage = new Stage();
            controller.setStage(stage);

            // Set up and display the new stage
            stage.setScene(new Scene(root));
            stage.setTitle("Add Screening Room");
            stage.showAndWait();

            // Check if a new room was successfully saved and add it to the list
            if (controller.isSaved()) {
                ScreeningRoom newRoom = controller.getNewRoom();
                aScreenRooms.add(newRoom);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Opens a new window for editing the details of the selected room.
     * <p>
     * This method pre-fills the fields with the current room details,
     * allowing the manager to make modifications.
     * </p>
     */
    @FXML
    private void onEditButtonClicked() {
        ScreeningRoom selectedRoom = aScreenRoomTableView.getSelectionModel().getSelectedItem();

        if (selectedRoom != null) {
            try {
                // Load the Modify Screening Room view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/modify-screening-room-view.fxml"));
                Parent root = loader.load();

                // Get the controller for the Modify Screening Room view
                ModifyScreeningRoomController controller = loader.getController();
                controller.setRoomDetails(
                        selectedRoom.getName(),
                        selectedRoom.getCapacity(),
                        selectedRoom.getFeatures()
                );

                // Create a new stage for the Modify Screening Room view
                Stage stage = new Stage();
                controller.setStage(stage);
                stage.setScene(new Scene(root));
                stage.setTitle("Modify Screening Room");
                stage.showAndWait();

                // Update the TableView with the modified data after the window is closed
                aScreenRoomTableView.refresh();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            AlertHelper.showWarningAlert("No Selection", "Please select a screening room to edit.", null);
        }
    }


    /**
     * Deletes the selected room from the list.
     * <p>
     * Displays a confirmation alert before deletion. If confirmed,
     * the room is removed from the TableView and an alert notifies the manager of success.
     * </p>
     */
    @FXML
    private void onDeleteButtonClicked() {
        ScreeningRoom selectedRoom = aScreenRoomTableView.getSelectionModel().getSelectedItem();

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
