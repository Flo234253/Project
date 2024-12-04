package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.ShowTime;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ModifyShowTimeViewController {


    public DatePicker dateField;
    @FXML
    private TextField showTimeIdField;





    @FXML
    private TextField timeField;



    @FXML
    private TextField roomIdField;


    @FXML
    private TextField movieIdField;


    @FXML
    private Button modifyShowTimeButton;


    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;


    private ObservableList<ShowTime> showTimes; // Holds the list of showtimes
    private ShowTime selectedShowTime; // The selected showtime to modify

    // This method is called to initialize the controller with the selected showtime
    public void initialize(ShowTime selectedShowTime, ObservableList<ShowTime> showTimes) {
        this.selectedShowTime = selectedShowTime;
        this.showTimes = showTimes;

        // Populate the fields with the existing data of the selected showtime
        showTimeIdField.setText(String.valueOf(selectedShowTime.getaID()));
        movieIdField.setText(selectedShowTime.getMovie());
        dateField.setValue(selectedShowTime.getDateTime().toLocalDate());
        timeField.setText(selectedShowTime.getFormattedTime());
        roomIdField.setText(selectedShowTime.getScreeningRoom());
    }

    @FXML
    private void handleSaveButton(ActionEvent actionEvent) {
        // Get the updated values from the fields
        int id = Integer.parseInt(showTimeIdField.getText());
        LocalDate newDate = dateField.getValue();
        LocalTime newTime = LocalTime.parse(timeField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
        String newMovie = movieIdField.getText();
        String newScreeningRoom = roomIdField.getText();

        // Create a new LocalDateTime object with the updated values
        LocalDateTime updatedDateTime = LocalDateTime.of(newDate, newTime);

        // Create the updated showtime
        ShowTime updatedShowTime = new ShowTime(id, updatedDateTime, newMovie, newScreeningRoom);

        // Update the list with the modified showtime
        for (int i = 0; i < showTimes.size(); i++) {
            if (showTimes.get(i).getaID() == id) {
                showTimes.set(i, updatedShowTime);
                break;
            }
        }

        // Save the updated list to the file
        saveShowTimes(showTimes);

        // Close the modify showtime window
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

        // Refresh the TableView UI in the main controller
        // No need to call showTimeTableView.refresh() directly here since it's managed in the main controller
        AlertHelper.showInformationAlert("Success", null, "Showtime modified successfully!");
    }

    private void saveShowTimes(ObservableList<ShowTime> showTimeList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/showtimes.ser"))) {
            // Serialize the ObservableList into the file
            oos.writeObject(new ArrayList<>(showTimeList));
        } catch (IOException e) {
            e.printStackTrace();
            AlertHelper.showErrorAlert("Error", "Failed to save showtimes.");

        }
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();


    }

}
