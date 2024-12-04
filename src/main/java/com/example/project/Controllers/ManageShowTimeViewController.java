package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.ShowTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing the movie list view.
 * Provides functionality for searching, consulting, adding, editing, and deleting movies.
 */
public class ManageShowTimeViewController {


    /**
     * TableView for displaying the list of showtime.
     */
    @FXML
    private TableView<ShowTime> showTimeTableView;


    /**
     * TableColumn for displaying movie titles in the TableView.
     */
    @FXML
    private TableColumn<ShowTime, String> movieColumn;


    /**
     * TableColumn for displaying showtime  in the TableView.
     */
    @FXML
    private TableColumn<ShowTime, String> dateColumn;

    @FXML private TableColumn<ShowTime, String> timeColumn;


    /**
     * Button for adding a showtime to the list.
     */
    @FXML
    private Button addButton;

    @FXML
    private Button modifyButton;

    /**
     * Button for deleting the selected Showtime from the table.
     */
    @FXML
    private Button deleteShowTimeButton;

    /**
     * Button for consulting detailed information about Showtime.
     */
    @FXML
    private Button consultShowTimeButton;

    /**
     * Button for going back to previous page.
     */
    @FXML
    private Button backButton;



    /**
     * Initializes the controller and sets up the TableView and columns.
     */
    @FXML
    public void initialize() {
        // Load showtimes and display them
        ObservableList<ShowTime> showTimeList = loadShowTimes();

        // Set the movie name column
        movieColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMovie()));

        // Set the date and time columns
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormattedDate()));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormattedTime()));

        // Populate the TableView with the loaded showtimes
        showTimeTableView.setItems(showTimeList);
    }


    /**
     * Loads showtimes from a predefined list (simulating data load).
     *
     * @return ObservableList of showtimes
     */

    private ObservableList<ShowTime> loadShowTimes() {
        ObservableList<ShowTime> showTimeList = FXCollections.observableArrayList();

        // Attempt to load saved showtimes from file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/showtimes.ser"))) {
            // Deserialize the List<ShowTime>
            List<ShowTime> deserializedList = (List<ShowTime>) ois.readObject();
            if (deserializedList != null && !deserializedList.isEmpty()) {
                // If file contains showtimes, add them to the list
                showTimeList.addAll(deserializedList);
            }
            // If the file is empty or no showtimes, showTimeList will remain empty
        } catch (IOException | ClassNotFoundException e) {
            // If no saved file or error, showTimeList will remain empty
        }

        // Return the ObservableList to bind to the TableView
        return showTimeList;
    }



    /**
     * Deletes the selected showtime from the list.
     * <p>
     * Displays a confirmation alert before deletion.
     * </p>
     */
    @FXML
    private void handleDeleteButton(ActionEvent actionEvent) {

// Get the selected showtime from the TableView
        ShowTime selectedShowTime = showTimeTableView.getSelectionModel().getSelectedItem();

        if (selectedShowTime != null) {
            // Display a confirmation alert
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert(
                    "Confirm Deletion",
                    "Are you sure you want to delete this showtime?",
                    selectedShowTime.getMovie() + " at " + selectedShowTime.getFormattedTime()  // Display movie name and time only
            );

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the selected showtime from the ObservableList
                showTimeTableView.getItems().remove(selectedShowTime);

                // Save the updated list to the file
                saveShowTimes(showTimeTableView.getItems());

                // Refresh the TableView UI
                showTimeTableView.refresh();

                // Display success message
                AlertHelper.showInformationAlert("Success", null, "Showtime deleted successfully!");
            }
        } else {
            // Display warning if no showtime is selected
            AlertHelper.showWarningAlert("No Selection", null, "Please select a showtime to delete.");
        }
    }




    /**
     * Opens a new window to display the information about the selected showtime.
     */
    @FXML
    private void handleConsultButton(ActionEvent actionEvent) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/consult-showtime-details-view.fxml"));
            Parent root = loader.load();

            // Create a new stage (window)
            Stage stage = new Stage();
            stage.setTitle("Consult Showtime");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Optionally show an alert if the FXML file could not be loaded
        }

    }




    /**
     * Opens a new window for adding a new showtime to the list.
     */
    @FXML
    private void handleAddButton(ActionEvent actionEvent) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/add-showtime-view.fxml"));
            Parent root = loader.load();

            // Get the controller for AddShowTimeViewController
            AddShowTimeViewController addController = loader.getController();

            // Pass the showtime list to the AddShowTimeViewController
            addController.setShowTimeList(showTimeTableView.getItems());

            // Create a new stage (window)
            Stage stage = new Stage();
            stage.setTitle("Add Showtime");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Optionally show an alert if the FXML file could not be loaded
        }

    }









    /**
     * Saves the showtimes to a file.
     *
     * @param showTimeList The list of showtimes to save
     */
    private void saveShowTimes(ObservableList<ShowTime> showTimeList) {
        // Convert ObservableList to a serializable List
        List<ShowTime> serializableList = new ArrayList<>(showTimeList);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/showtimes.ser"))) {
            // Serialize the List of ShowTimes (not the ObservableList)
            oos.writeObject(serializableList);
        } catch (IOException e) {
            e.printStackTrace();
            AlertHelper.showErrorAlert("Save Error", "An error occurred while saving showtimes.");
        }
    }







    /**
     *
     * to witch back to the previous view
     */
    @FXML
    private void handleBackButton(ActionEvent actionEvent) throws IOException {
        // Get the current stage (window)
        Stage stage = (Stage) backButton.getScene().getWindow();

        // Load the previous scene (or the desired view)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/manager-dashboard-view.fxml"));
        Parent root = loader.load();

        // Set the scene for the current stage (window)
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Optionally, you can show the window again
        stage.show();
    }





}
