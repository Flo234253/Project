package com.example.project.Controllers;


import com.example.project.Model.ShowTime;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
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
    private TableColumn<ShowTime, String> roomIdColumn;

    /**
     * TableColumn displaying the Movie ID for each showtime.
     * Maps to the `movieId` property in the ShowTime object.
     */
    @FXML
    private TableColumn<ShowTime, String> movieNameColumn;



    /**
     * Set the showtime details in the TableView (this will display the selected ShowTime).
     *
     * @param showTimes A list containing the ShowTime(s) to be displayed in the TableView
     */
    public void setShowTimeDetails(ObservableList<ShowTime> showTimes) {
        // Set up the columns for the TableView
        showTimeIdColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getaID()).asObject()); // Correcting the return type to ObservableValue<Integer>

        dateColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFormattedDate())); // Correct return type for String

        timeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFormattedTime())); // Correct return type for String

        roomIdColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getScreeningRoom()));  // Correct return type for Integer

        movieNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMovie())); // Assuming `getMovieName` returns a String


        // Set the TableView's items to the list of showtimes
        showTimeTableView.setItems(showTimes);
    }

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
