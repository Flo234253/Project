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
 * Controller class for managing the "Screening Room List" view.
 * <p>
 * This class provides the functionality to manage a list of screening rooms, including
 * adding, consulting, editing, deleting, and saving the screening room details.
 * It interacts with the TableView component to display data and manages manager interactions
 * such as adding or deleting screening rooms.
 * </p>
 */
public class ScreenRoomListController {

    /**
     * Path to the serialized file for saving/loading screening room data.
     * <p>
     * This constant is used to specify the file path where screening room data is serialized
     * and saved to persist the information between application restarts.
     * </p>
     */
    private static final String SCREEN_ROOMS_FILE_PATH = "data/screening_rooms.ser";

    /**
     * TextField for searching screening rooms.
     * <p>
     * Allows the manager to input keywords to filter screening rooms by their names or features.
     * </p>
     */
    @FXML
    private TextField aSearchField;

    /**
     * Button to refresh the list of screening rooms.
     * <p>
     * This button resets the search filter to display all screening rooms again.
     * </p>
     */
    @FXML
    private Button aRefreshButton;

    /**
     * TableView for displaying the list of screening rooms.
     * <p>
     * This table shows the screening rooms in a list format, with each room represented by a row.
     * </p>
     */
    @FXML
    private TableView<ScreeningRoom> aScreenRoomTableView;

    /**
     * TableColumn for displaying the name of each screening room in the TableView.
     * <p>
     * This column binds to the "name" property of the ScreeningRoom objects to display their names.
     * </p>
     */
    @FXML
    private TableColumn<ScreeningRoom, String> nameColumn;

    /**
     * Button to consult the details of the selected screening room.
     */
    @FXML
    private Button aConsultButton;

    /**
     * Button to edit the selected screening room.
     */
    @FXML
    private Button aEditButton;

    /**
     * Button to delete the selected screening room from the list.
     */
    @FXML
    private Button aDeleteButton;

    /**
     * List for storing the screening rooms.
     * <p>
     * This list holds the ScreeningRoom objects for persistence purposes.
     * </p>
     */
    private List<ScreeningRoom> aScreenRoomList;

    /**
     * ObservableList for UI operations.
     * <p>
     * This list is used to manage the screening rooms within the TableView,
     * allowing automatic updates to the UI when the data changes.
     * </p>
     */
    private ObservableList<ScreeningRoom> aScreenRooms;

    /**
     * Initializes the controller.
     * <p>
     * This method is called after the FXML file has been loaded.
     * It sets up the TableView by adding the columns for screening room properties
     * and disables buttons when no room is selected.
     * </p>
     */
    @FXML
    public void initialize() {
        loadScreenRooms();

        // Set the cell value factory for the name column to bind the data
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        // Populate the TableView with the list of screening rooms
        aScreenRoomTableView.setItems(aScreenRooms);

        // Disable buttons when no screening room is selected
        aScreenRoomTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newVal) -> {
            boolean isSelected = newVal != null;
            aConsultButton.setDisable(!isSelected);
            aEditButton.setDisable(!isSelected);
            aDeleteButton.setDisable(!isSelected);
        });

        // Disable the Refresh button by default until a search is performed
        aRefreshButton.setDisable(true);
    }

    /**
     * Loads screening rooms from the serialized file or creates a default list if no file exists.
     * <p>
     * The method first ensures that the data directory exists, then attempts to load screening rooms
     * from the serialized file. If the file is not found, it creates a default set of screening rooms.
     * </p>
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
     * <p>
     * This method persists the list of screening rooms to a file, ensuring that
     * data is saved between application sessions.
     * </p>
     */
    private void saveScreenRooms() {
        ensureDataDirectoryExists(); // Ensure the "data" directory exists before saving
        SerializationHelper.saveData(SCREEN_ROOMS_FILE_PATH, aScreenRoomList);
    }

    /**
     * Ensures that the "data" directory exists.
     * <p>
     * If the directory does not exist, it creates it to provide a location to save serialized data.
     * </p>
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
     * @return The list of default screening rooms.
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
     * <p>
     * This method is triggered by the "Refresh" button and resets the TableView to display
     * the complete list of screening rooms, removing any filters that were applied.
     * </p>
     */
    @FXML
    private void onRefreshButtonClicked() {
        // Reset the TableView to display the original list of screening rooms
        aScreenRoomTableView.setItems(aScreenRooms);
    }

    /**
     * Filters the screening room list based on the input from the search field.
     * <p>
     * This method filters the screening rooms displayed in the TableView based on the manager's
     * input in the search field. If no rooms match the search input, an alert is displayed.
     * Triggered by the "Search" button.
     * </p>
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
     * <p>
     * This method is triggered by the "Consult" button and allows the manager to view
     * the details of the selected screening room in a new window.
     * </p>
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
     * <p>
     * This method allows the manager to add a new screening room to the list by opening
     * a new window with an input form. After the manager saves the new room, it is added
     * to the ObservableList and the serialized file.
     * Triggered by the "Add" button.
     * </p>
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
     * <p>
     * This method is triggered by the "Edit" button and allows the manager to modify
     * the details of a selected screening room in a new window. Once editing is complete,
     * the changes are saved.
     * </p>
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
     * <p>
     * This method removes the selected screening room from the list and updates the serialized data.
     * A confirmation alert is displayed to the manager before deletion.
     * Triggered by the "Delete" button.
     * </p>
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
