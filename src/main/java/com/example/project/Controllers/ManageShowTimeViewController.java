package com.example.project.Controllers;

import com.example.project.Model.ShowTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller class for managing the movie list view.
 * Provides functionality for searching, consulting, adding, editing, and deleting movies.
 */
public class ManageShowTimeViewController {


    /**
     * Button for refreshing the movie list.
     */
    @FXML
    private Button searchButton;

    /**
     * TextField for searching movies in the list by title or genre.
     */
    @FXML
    private TextField searchField;


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
    private TableColumn<ShowTime, String> showTimeColumn;


    /**
     * Button for adding a showtime to the list.
     */
    @FXML
    private Button addShowTimeButton;


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

    @FXML
    public void initialize() {
        // Bind columns to ShowTime properties
        movieColumn.setCellValueFactory(new PropertyValueFactory<>("movie"));
        showTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        // Load data into the TableView
        loadShowTimes();

        // Enable consult button only when a row is selected
        showTimeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            consultShowTimeButton.setDisable(newValue == null);
        });
    }

    /**
     * Method to load sample data for showtimes.
     */
    private void loadShowTimes() {
        ObservableList<ShowTime> showTimeList = FXCollections.observableArrayList(
                new ShowTime(1, "2024-11-19 18:00", "Inception"),
                new ShowTime(2, "2024-11-19 21:00", "Titanic"),
                new ShowTime(3, "2024-11-20 14:30", "Avatar"),
                new ShowTime(4, "2024-11-20 19:00", "Inception"),
                new ShowTime(5, "2024-11-21 16:00", "Interstellar")
        );

        // Bind the TableView
        showTimeTableView.setItems(showTimeList);
    }

    /**
     * Filters movies based on the input from the search field.
     * <p>
     * If no movies match the input, an alert is displayed to the manager.
     * </p>
     */
    @FXML
    private void handleSearchButton(ActionEvent actionEvent) {



    }


    /**
     * Opens a new window for adding a new showtime to the list.
     */
    @FXML
    private void handleAddButton(ActionEvent actionEvent) {



    }



    /**
     * Deletes the selected showtime from the list.
     * <p>
     * Displays a confirmation alert before deletion.
     * </p>
     */
    @FXML
    private void handleDeleteButton(ActionEvent actionEvent) {



    }



    /**
     * Opens a new window to display the information about the selected showtime.
     */
    @FXML
    private void handleConsultButton(ActionEvent actionEvent) {


    }


    /**
     *
     * to witch back to the previous view
     */
    @FXML
    private void handleBackButton(ActionEvent actionEvent) {


    }

}
