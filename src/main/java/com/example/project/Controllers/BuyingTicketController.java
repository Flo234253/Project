package com.example.project.Controllers;

import com.example.project.Model.ShowTime;
import com.example.project.Model.Ticket;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Controller class for the buying ticket for a client, so filtering choosing a
 * movie and time, entering the number of ticket and buying it. Also, implement the initializable
 */
public class BuyingTicketController implements Initializable {

    /**
     * Initialize the date picker, list view, text field amd the labe
     */
    @FXML
    public DatePicker datePicker;
    @FXML
    public ListView<String> moveAndShowtimeListView;
    @FXML
    public TextField numberTicketTextField;
    @FXML
    public Label priceLabel1;

    /**
    Set the constance for the ticket price
     */
    private static final double TICKET_PRICE = 12.49;

    /**
     * Create a list that will hold the showtime information
     */
    private List<ShowTime> showtimes;

    /**
     * Override the initialize class, this method will automatically be caalled when this view is called.
     * There's event listener to data picket and price label
     * @param location of the showtime file
     * @param resources resource bundle makes it easier to read data
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load showtimes from the file
        showtimes = loadDataFromFile("data/showtimes.ser");

        // Add listener to update the movie list based on selected date
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null) {
                    onDateSelected();
                }
            }
        });

        // Add listener to update the price label dynamically
        numberTicketTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateTotalPrice();
            }
        });
    }

    /**
     * This method loads the showtime information from a serialized file.
     * It reads a list of `ShowTime` objects from the file and returns it.
     * @param fileName the name of the showtime file
     * @return the list of the showtime information from the file
     */
    private List<ShowTime> loadDataFromFile(String fileName) {
        List<ShowTime> showtimesList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            // Read the list of ShowTime objects from the serialized file
            showtimesList = (List<ShowTime>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading file: " + fileName);
            e.printStackTrace();
        }
        return showtimesList;
    }
    /**
     * This method will be called when the user selects a date from the DatePicker.
     * Filters the available showtimes based on the selected date and display the correct format for the list view
     */
    @FXML
    public void onDateSelected() {
        moveAndShowtimeListView.getItems().clear();

        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate == null) {
            return;
        }

        // Filter showtimes using a simple for loop
        List<ShowTime> filteredShowtimes = new ArrayList<>();
        for (ShowTime showtime : showtimes) {
            // Compare the date portion of the ShowTime with the selected date
            if (showtime.getDateTime().toLocalDate().equals(selectedDate)) {
                filteredShowtimes.add(showtime);
            }
        }

        if (filteredShowtimes.isEmpty()) {
            moveAndShowtimeListView.getItems().add("No available showtimes for the selected date.");
            return;
        }

        for (ShowTime showtime : filteredShowtimes) {
            String movieName = showtime.getMovie();
            String time = showtime.getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            // Remove the room number from the display text
            String displayText = String.format("%s - Showtime: %s", movieName, time);
            moveAndShowtimeListView.getItems().add(displayText);
        }
    }

    /**
     * This method will multiply the price to the number of ticket that the client wants to buy
     */
    private void updateTotalPrice() {
        String numberOfTickets = numberTicketTextField.getText();
        try {
            int ticketCount = Integer.parseInt(numberOfTickets);
            double totalPrice = ticketCount * TICKET_PRICE;
            priceLabel1.setText("Total Price: $" + String.format("%.2f", totalPrice));
        } catch (NumberFormatException e) {
            priceLabel1.setText("Total Price: $0.00");
        }
    }

    /**
     * This will be called when the button is press and it will verify if the user selected a date,
     * entered the number of ticket and if the number is valid. Then will show a success alert that the ticket
     * has been bought will all the information
     * @param event when button is pressed
     */
    @FXML
    public void buyButton(ActionEvent event) {
        String selectedMovieTitle = moveAndShowtimeListView.getSelectionModel().getSelectedItem();
        String numberOfTickets = numberTicketTextField.getText();

        if (selectedMovieTitle == null || selectedMovieTitle.isEmpty()) {
            Helpers.AlertHelper.showErrorAlert("No Movie Selected", "Please select a movie before proceeding.");
            return;
        }

        if (numberOfTickets == null || numberOfTickets.isEmpty()) {
            Helpers.AlertHelper.showErrorAlert("No Tickets Entered", "Please enter the number of tickets.");
            return;
        }

        try {
            int ticketCount = Integer.parseInt(numberOfTickets);
            if (ticketCount <= 0) throw new NumberFormatException("Ticket count must be positive.");

            //variable for id
            int uniqueID = (int) (Math.random() * 100000);
            LocalDateTime purchaseDateTime = LocalDateTime.now();

            ShowTime matchedShowtime = showtimes.stream()
                    .filter(showtime -> selectedMovieTitle.startsWith(showtime.getMovie()))
                    .findFirst()
                    .orElse(null);

            if (matchedShowtime == null) {
                Helpers.AlertHelper.showErrorAlert("Showtime Not Found", "Could not find a showtime for the selected movie.");
                return;
            }

            Ticket eTicket = new Ticket(uniqueID, purchaseDateTime, matchedShowtime);

            Helpers.AlertHelper.showInformationAlert(
                    "Ticket Purchase Successful",
                    null,
                    String.format("Ticket ID: %d\nPurchase Date/Time: %s\nMovie: %s\nNumber of Tickets: %d\nTotal Price: $%.2f",
                            eTicket.getID(),
                            eTicket.getPurchaseDateTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")),
                            matchedShowtime.getMovie(),
                            ticketCount,
                            ticketCount * TICKET_PRICE)
            );

        } catch (NumberFormatException e) {
            Helpers.AlertHelper.showErrorAlert("Invalid Input", "Please enter a valid number of tickets.");
        } catch (Exception e) {
            Helpers.AlertHelper.showErrorAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }
}
