package com.example.project.Controllers;

import com.example.project.Model.ShowTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

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





    private ObservableList<ShowTime> showTimeList = FXCollections.observableArrayList();  // This should be initialized

    @FXML
    public void initialize() throws IOException {
        // Bind the movie column to the movie property
        movieColumn.setCellValueFactory(cellData -> cellData.getValue().movieProperty());

        // Bind the date column to the date part (without time)
        dateColumn.setCellValueFactory(cellData -> {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            return new SimpleStringProperty(dateFormatter.format(cellData.getValue().getDateTime()));
        });

        // Bind the time column to the time part (without date)
        timeColumn.setCellValueFactory(cellData -> {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            return new SimpleStringProperty(timeFormatter.format(cellData.getValue().getDateTime()));
        });

        // Load data into the TableView
        loadShowTimes();

        // Enable consult button only when a row is selected
        showTimeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            consultShowTimeButton.setDisable(newValue == null);
        });

        // Set the items of the TableView to the showTimeList
        showTimeTableView.setItems(showTimeList);

    }

    /**
     * Method to load sample data for showtimes.
     */
    private void loadShowTimes() {
        String file = "data/SampleData(Showtime).csv";
        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            // Skip the header row
            String header = reader.readLine();
            if (header != null) {
                // You can keep this line if you still want to log the header being skipped.
            }

            // Read the data rows
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                if (row.length != 6) {
                    // Skip invalid rows with the wrong column count
                    continue;
                }

                try {
                    int showTimeId = Integer.parseInt(row[0].trim());
                    String date = row[1].trim();
                    String time = row[2].trim();
                    int roomId = Integer.parseInt(row[3].trim());
                    String movie = row[4].trim();
                    boolean isFull = Boolean.parseBoolean(row[5].trim());

                    // Create a ShowTime object and add it to the list
                    ShowTime showTime = new ShowTime(showTimeId, date, time, roomId, movie, isFull);
                    showTimeList.add(showTime);

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
