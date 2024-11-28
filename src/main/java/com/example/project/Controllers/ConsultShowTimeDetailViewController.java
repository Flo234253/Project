package com.example.project.Controllers;


import com.example.project.Model.ShowTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Controller class for the Consult ShowTime Details view.
 *
 */
public class ConsultShowTimeDetailViewController {

    /**
     * TableView displaying the list of showtimes.
     * Each row represents a single showtime entry.
     */
    @FXML
    private TableView<ShowTime> showTimeTableView;

    /**
     * TableColumn displaying the ShowTime ID for each showtime.
     * Maps to the `showtimeId` property in the ShowTime object.
     */
    @FXML
    private TableColumn<ShowTime, Integer> showTimeIdColumn;

    /**
     * TableColumn displaying the Date for each showtime.
     * Maps to the `date` property in the ShowTime object.
     */
    @FXML
    private TableColumn<ShowTime, String> dateColumn;

    /**
     * TableColumn displaying the Time for each showtime.
     * Maps to the `time` property in the ShowTime object.
     */
    @FXML
    private TableColumn<ShowTime, String> timeColumn;

    /**
     * TableColumn displaying the Room ID for each showtime.
     * Maps to the `roomId` property in the ShowTime object.
     */
    @FXML
    private TableColumn<ShowTime, Integer> roomIdColumn;

    /**
     * TableColumn displaying the Movie ID for each showtime.
     * Maps to the `movieId` property in the ShowTime object.
     */
    @FXML
    private TableColumn<ShowTime, Integer> movieIdColumn;

    /**
     * TableColumn displaying whether the showtime is Full or not.
     * Maps to the `full` property in the ShowTime object.
     */
    @FXML
    private TableColumn<ShowTime, Boolean> fullColumn;

    /**
     * Button that allows the user to go back to the previous screen.
     * This button can be used to navigate or perform other actions.
     */
    @FXML
    private Button backButton;

    /**
     * Handles the back button click event.
     * This method can be used to navigate back to the previous screen
     * or to perform other actions associated with the back button.
     *
     * @param actionEvent The event that triggered the method.
     */
    @FXML
    private void handleBackButton(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
