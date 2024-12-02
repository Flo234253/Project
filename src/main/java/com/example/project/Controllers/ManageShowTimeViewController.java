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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
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
     * ObservableList to store the showtime data.
     */

    private final ObservableList<ShowTime> aShowTimeList = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     * <p>
     * Binds table columns to the properties of the ShowTime class, loads data into the table,
     * and sets up event listeners for user interactions.
     * </p>
     *
     * @throws IOException if there is an error during initialization
     */
    @FXML
    public void initialize() throws IOException {
        // Bind the movie column to the movie property
        movieColumn.setCellValueFactory(cellData -> cellData.getValue().aMovieProperty());

        // Bind the date column to the date part (without time)
        dateColumn.setCellValueFactory(cellData -> {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            return new SimpleStringProperty(dateFormatter.format(cellData.getValue().getaDateTime()));
        });

        // Bind the time column to the time part (without date)
        timeColumn.setCellValueFactory(cellData -> {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            return new SimpleStringProperty(timeFormatter.format(cellData.getValue().getaDateTime()));
        });

        // Load data into the TableView
        loadShowTimes();

        // Enable consult button only when a row is selected
        showTimeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            consultShowTimeButton.setDisable(newValue == null);
        });

        // Set the items of the TableView to the showTimeList
        showTimeTableView.setItems(aShowTimeList);

    }

     /**
     * Loads sample data for showtimes from a CSV file.
     * <p>
     * The method reads data from a file, parses each row, and populates the ObservableList.
     * Invalid rows are skipped without raising exceptions.
     * </p>
     */
    private void loadShowTimes() {
        String file = "data/SampleData.csv";
        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            // Skip the header row
            String header = reader.readLine();
            // You can keep this line if you still want to log the header being skipped.

            // Read the data rows
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                if (row.length != 6) {
                    // Skip invalid rows with the wrong column count
                    continue;
                }

                try {
                    ShowTime showTime = getShowTime(row);
                    aShowTimeList.add(showTime);

                } catch (NumberFormatException e) {
                    // Handle invalid number formats without printing the stack trace
                } catch (Exception e) {
                    // Handle any other exceptions without printing the stack trace
                }
            }

        } catch (IOException e) {
            // Handle I/O errors without printing the stack trace
        }
    }

    /**
     * Creates a ShowTime object from a parsed CSV row.
     *
     * @param row the array of string values from a CSV row
     * @return a ShowTime object created from the row data
     */

    private static ShowTime getShowTime(String[] row) {
        int showTimeId = Integer.parseInt(row[0].trim());
        String date = row[1].trim();
        String time = row[2].trim();
        int roomId = Integer.parseInt(row[3].trim());
        String movie = row[4].trim();
        boolean isFull = Boolean.parseBoolean(row[5].trim());

        // Create a ShowTime object and add it to the list
        return new ShowTime(showTimeId, date, time, roomId, movie, isFull);
    }


    /**
     * Opens a new window for adding a new showtime to the list.
     *
     * @param actionEvent the event triggered by clicking the add button
     */
    @FXML
    private void handleAddButton(ActionEvent actionEvent) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/add-showtime-view.fxml"));
            Parent root = loader.load();

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
     * Opens a new window for modifying the selected showtime.
     *
     * @param actionEvent the event triggered by clicking the modify button
     */

    public void handleModifyButton(ActionEvent actionEvent) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/modify-showtime-view.fxml"));
            Parent root = loader.load();

            // Create a new stage (window)
            Stage stage = new Stage();
            stage.setTitle("Modify Showtime");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Optionally show an alert if the FXML file could not be loaded
        }


    }


    /**
     * Opens a new window to display details about the selected showtime.
     *
     * @param actionEvent the event triggered by clicking the consult button
     */
    @FXML
    private void handleConsultButton(ActionEvent actionEvent) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/consult-showtime-details-view.fxml"));
            Parent root = loader.load();

            // Create a new stage (window)
            Stage stage = new Stage();
            stage.setTitle("Modify Showtime");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Optionally show an alert if the FXML file could not be loaded
        }

    }





    /**
     * Deletes the selected showtime from the list.
     * <p>
     * Displays a confirmation alert before deletion. If confirmed, the showtime is removed
     * from the ObservableList, and an information alert is displayed.
     * </p>
     *
     * @param actionEvent the event triggered by clicking the delete button
     */
    @FXML
    private void handleDeleteButton(ActionEvent actionEvent) {

       // Get the selected ShowTime from the TableView
        ShowTime selectedShowTime = showTimeTableView.getSelectionModel().getSelectedItem();

        // Check if a ShowTime is selected
        if (selectedShowTime != null) {
            // Show confirmation alert
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert(
                    "Confirm Deletion",
                    "Are you sure you want to delete this showtime?",
                    "Showtime: " + selectedShowTime.getaMovie() + " at " + selectedShowTime.getaDateTime()
            );

            // If the user confirms the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the selected ShowTime from the ObservableList
                aShowTimeList.remove(selectedShowTime);

                // Show success information alert
                AlertHelper.showInformationAlert(
                        "Showtime Deleted",
                        null,
                        "The showtime for '" + selectedShowTime.getaMovie() + "' was successfully deleted."
                );
            }
        } else {
            // If no ShowTime is selected, show a warning alert
            AlertHelper.showWarningAlert(
                    "No Showtime Selected",
                    null,
                    "Please select a showtime to delete."
            );
        }

    }







    /**
     * Handles the action of the "Back" button.
     * <p>
     * This method switches the current view back to the previous manager dashboard view.
     * It retrieves the current stage, loads the FXML file for the manager dashboard, and updates the scene.
     * </p>
     *
     * @param actionEvent the event triggered by clicking the "Back" button
     * @throws IOException if the FXML file for the manager dashboard cannot be loaded
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
