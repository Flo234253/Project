package com.example.project.Controllers;

import Helpers.AlertHelper;
import Helpers.SerializationHelper;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for the "Screening Room List" view.
 * This class manages the form for adding, consulting, editing, deleting, and saving a screening room.
 */
public class ScreenRoomListController {

    private static final String SCREEN_ROOMS_FILE_PATH = "data/screening_rooms.ser";

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

    private List<ScreeningRoom> aScreenRoomList;
    private ObservableList<ScreeningRoom> aScreenRooms;

    /**
     * Initializes the controller.
     * This method is called after the FXML file has been loaded. It sets up the TableView,
     * adds the columns to the screening room properties, and disables buttons when no room is selected.
     */
    @FXML
    public void initialize() {
        loadScreenRooms();

        // Set the cell value factory for the name column to bind the data
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
        aRefreshButton.setDisable(true);
    }

    /**
     * Loads screening rooms from the serialized file or a predefined list if no file exists.
     */
    private void loadScreenRooms() {
        ensureDataDirectoryExists(); // Ensure the "data" directory exists before loading

        File dataFile = new File(SCREEN_ROOMS_FILE_PATH);
        if (dataFile.exists()) {
            // Load serialized data if available
            aScreenRoomList = SerializationHelper.loadData(SCREEN_ROOMS_FILE_PATH);
            if (aScreenRoomList == null) {
                aScreenRoomList = createDefaultScreeningRooms();
            } else {
                aScreenRoomList = new ArrayList<>(aScreenRoomList); // Ensure the list is mutable
            }
        } else {
            aScreenRoomList = createDefaultScreeningRooms();
            saveScreenRooms(); // Save default screening rooms for next time
        }

        // Convert to ObservableList for UI operations
        aScreenRooms = FXCollections.observableArrayList(aScreenRoomList);
    }

    /**
     * Saves the current list of screening rooms to the serialized file.
     */
    private void saveScreenRooms() {
        ensureDataDirectoryExists(); // Ensure the "data" directory exists before saving
        SerializationHelper.saveData(SCREEN_ROOMS_FILE_PATH, aScreenRoomList);
    }

    /**
     * Ensures that the data directory exists.
     */
    private void ensureDataDirectoryExists() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    /**
     * Creates a predefined list of screening rooms to be used when no serialized data exists.
     *
     * @return the list of default screening rooms
     */
    private List<ScreeningRoom> createDefaultScreeningRooms() {
        return new ArrayList<>(List.of(
                new ScreeningRoom("Room 1", 100, "Standard"),
                new ScreeningRoom("Room 2", 150, "IMAX"),
                new ScreeningRoom("Room 3", 200, "3D")
        ));
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
     * If no room name matches the input, an alert is displayed to the manager.
     */
    @FXML
    private void onSearchButtonClicked() {
        String pInput = aSearchField.getText().toLowerCase();

        // Filter screening rooms based on the input
        FilteredList<ScreeningRoom> filteredRooms = new FilteredList<>(aScreenRooms, room ->
                room.getName().toLowerCase().contains(pInput) || room.getFeatures().toLowerCase().contains(pInput)
        );

        if (filteredRooms.isEmpty()) {
            AlertHelper.showWarningAlert(
                    "No Results Found",
                    "No screening rooms match your search input.",
                    "Please check your input and try again."
            );
        } else {
            // Update the TableView with the filtered screening rooms
            aScreenRoomTableView.setItems(filteredRooms);
        }

        // Enable the Refresh button if the search was performed
        aRefreshButton.setDisable(false);
    }

    /**
     * Opens a new window to display the information about the selected screening room.
     */
    @FXML
    private void onConsultButtonClicked() {
        ScreeningRoom selectedRoom = aScreenRoomTableView.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/consult-screening-room-view.fxml"));
                Parent root = loader.load();

                ConsultScreeningRoomController controller = loader.getController();
                controller.setRoomDetails(selectedRoom.getName(), selectedRoom.getCapacity(), selectedRoom.getFeatures());

                Stage stage = new Stage();
                controller.setStage(stage);
                stage.setScene(new Scene(root));
                stage.setTitle("Screening Room Details");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                AlertHelper.showErrorAlert("Error", "Unable to open the Consult Screening Room view.");
            }
        } else {
            AlertHelper.showWarningAlert("No Selection", "Please select a screening room to consult.", null);
        }
    }

    /**
     * Opens a new window for adding a new room to the list.
     */
    @FXML
    private void onAddButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/add-screening-room-view.fxml"));
            Parent root = loader.load();

            AddScreenRoomController controller = loader.getController();
            Stage stage = new Stage();
            controller.setStage(stage);
            controller.setExistingRooms(aScreenRooms); // Provide the existing rooms for validation
            stage.setScene(new Scene(root));
            stage.setTitle("Add Screening Room");
            stage.showAndWait();

            if (controller.isSaved()) {
                ScreeningRoom newRoom = controller.getNewRoom();
                aScreenRoomList.add(newRoom);
                aScreenRooms.add(newRoom);
                saveScreenRooms(); // Save after adding a new room
                aScreenRoomTableView.refresh(); // Refresh the TableView after adding a new room
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a new window for editing the details of the selected room.
     */
    @FXML
    private void onEditButtonClicked() {
        ScreeningRoom selectedRoom = aScreenRoomTableView.getSelectionModel().getSelectedItem();

        if (selectedRoom != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/modify-screening-room-view.fxml"));
                Parent root = loader.load();

                ModifyScreeningRoomController controller = loader.getController();
                controller.setRoomDetails(selectedRoom.getName(), selectedRoom.getCapacity(), selectedRoom.getFeatures());

                Stage stage = new Stage();
                controller.setStage(stage);
                stage.setScene(new Scene(root));
                stage.setTitle("Modify Screening Room");
                stage.showAndWait();

                // Update the selected room with new values
                if (controller.isSaved()) {
                    selectedRoom.setName(controller.getUpdatedName());
                    selectedRoom.setCapacity(controller.getUpdatedCapacity());
                    selectedRoom.setFeatures(controller.getUpdatedFeatures());
                    saveScreenRooms(); // Save after editing the room
                    aScreenRoomTableView.refresh(); // Refresh the TableView after editing
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            AlertHelper.showWarningAlert("No Selection", "Please select a screening room to edit.", null);
        }
    }

    /**
     * Deletes the selected room from the list.
     */
    @FXML
    private void onDeleteButtonClicked() {
        ScreeningRoom selectedRoom = aScreenRoomTableView.getSelectionModel().getSelectedItem();

        if (selectedRoom != null) {
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert(
                    "Confirm Deletion",
                    "Are you sure you want to delete this screening room?",
                    "Room: " + selectedRoom.getName()
            );

            if (result.isPresent() && result.get() == ButtonType.OK) {
                aScreenRoomList.remove(selectedRoom);
                aScreenRooms.remove(selectedRoom);
                saveScreenRooms(); // Save after deleting the room
                aScreenRoomTableView.refresh(); // Refresh the TableView after deletion
                AlertHelper.showInformationAlert(
                        "Screening Room Deleted",
                        null,
                        "The room '" + selectedRoom.getName() + "' was successfully deleted."
                );
            }
        } else {
            AlertHelper.showWarningAlert("No Room Selected", null, "Please select a room to delete.");
        }
    }
}
