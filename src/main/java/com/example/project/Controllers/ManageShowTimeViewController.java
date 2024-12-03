package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.ShowTime;

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


import java.io.IOException;

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
