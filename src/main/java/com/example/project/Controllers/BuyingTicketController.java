package com.example.project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * This class will implement the initializable and will control the action of the user that planning to buy movie ticjet.
 * So selected a date, then a movie, the number of ticket and then buying a ticket and validating the data.
 */
public class BuyingTicketController implements Initializable {

    /**
     * Initialize the datePicker, the moveAndShowtime, numberTicketTextField and the price priceLabel1
     */
    @FXML
    public DatePicker datePicker;
    @FXML
    public ListView moveAndShowtimeListView; // Keep this as is
    @FXML
    public TextField numberTicketTextField;
    @FXML
    public Label priceLabel1;

    // Hardcoded movie data (in place of reading from a CSV file)
    private final List<Map<String, String>> movies = List.of(
            Map.of("movie_id", "10", "Title", "The Brave Journey", "Director", "John Smith", "Year", "2022", "Duration", "120 min"),
            Map.of("movie_id", "12", "Title", "Laughter Unlimited", "Director", "Jane Doe", "Year", "2021", "Duration", "95 min"),
            Map.of("movie_id", "14", "Title", "Love and Tears", "Director", "Emily Clark", "Year", "2023", "Duration", "130 min"),
            Map.of("movie_id", "15", "Title", "Space Adventures", "Director", "Mark Lee", "Year", "2024", "Duration", "140 min")
    );

    // Hardcoded showtime data (in place of reading from a CSV file)
    private final List<Map<String, String>> showtimes = List.of(
            Map.of("showtime_id", "1", "date", "11/19/2024", "time", "18:00", "room_id", "1", "movie_id", "10", "full", "FALSE"),
            Map.of("showtime_id", "2", "date", "11/19/2024", "time", "21:00", "room_id", "2", "movie_id", "12", "full", "TRUE"),
            Map.of("showtime_id", "3", "date", "11/20/2024", "time", "14:30", "room_id", "3", "movie_id", "14", "full", "FALSE"),
            Map.of("showtime_id", "4", "date", "11/20/2024", "time", "19:00", "room_id", "1", "movie_id", "10", "full", "TRUE"),
            Map.of("showtime_id", "5", "date", "11/21/2024", "time", "16:00", "room_id", "4", "movie_id", "15", "full", "FALSE")
    );

    /**
     * Set the constance for the price of one ticket
     */
    private static final double TICKET_PRICE = 12.49;

    /**
     * Creating a list that contains the showtimeDetails
     */
    private List<String> showtimeDetails;

    /**
     * Initializes the controller class.
     *
     * Initializes the showtimeDetails list. Then add a listener to the date pick so it will automatically
     * know when you put a date and the list view will be updated.Also add a listener to the numberTicketField so
     * when you put a number it will automatically know and add the price to the price label
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize showtimeDetails list
        showtimeDetails = new ArrayList<>();

        // Add a listener to handle date selection
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                onDateSelected();
            }
        });

        // Add a listener to the numberTicketTextField to update the price as the user types
        numberTicketTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateTotalPrice();
        });
    }

    /**
     * This method is what happens when you press the Date picker. First it will clear the list view.
     * Then get the selected date and format. Filter the showtime so only the day of will show the movie.
     * Then display the information on the list view.
     */
    @FXML
    public void onDateSelected() {
        // Clear the ListView for new results
        moveAndShowtimeListView.getItems().clear();
        showtimeDetails.clear(); // Clear previous selections from list

        // Get the selected date and format it
        LocalDate selectedDate = datePicker.getValue();
        String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        // Filter showtimes by selected date
        List<Map<String, String>> filteredShowtimes = showtimes.stream()
                .filter(showtime -> showtime.get("date").equals(formattedDate) && !showtime.get("full").equals("TRUE"))
                .collect(Collectors.toList());

        if (filteredShowtimes.isEmpty()) {
            moveAndShowtimeListView.getItems().add("No available showtimes for the selected date.");
            return;
        }

        // Display movies and showtimes in the ListView and save to list
        for (Map<String, String> showtime : filteredShowtimes) {
            String movieId = showtime.get("movie_id");
            String time = showtime.get("time");

            // Find the movie title based on movie_id
            String movieTitle = movies.stream()
                    .filter(movie -> movie.get("movie_id").equals(movieId))
                    .map(movie -> movie.get("Title"))
                    .findFirst()
                    .orElse("Unknown Movie");

            // Add the movie and showtime to the ListView
            String displayText = movieTitle + " at " + time;
            moveAndShowtimeListView.getItems().add(displayText);

            // Add to the showtimeDetails list for processing later
            showtimeDetails.add(displayText);
        }
    }
    /**
     *  This method will updates the total price label based on the number of tickets entered by the user.
     *  So it will get the number from the user in the textfield, then calculate the price by multiplying
     *  the number that the user gave us with the constance of the ticket price. Then display the price on the price label
     */
    // Method to update the total price in the label
    private void updateTotalPrice() {
        String numberOfTickets = numberTicketTextField.getText();
        try {
            // Parse the number of tickets
            int ticketCount = Integer.parseInt(numberOfTickets);

            // Calculate the total price
            double totalPrice = ticketCount * TICKET_PRICE;

            // Update the price label with the total price
            priceLabel1.setText("Total Price: $" + String.format("%.2f", totalPrice));
        } catch (NumberFormatException e) {
            // If the input is not a valid number, clear the price label
            priceLabel1.setText("Total Price: $0.00");
        }
    }

    /**
     *When you press the buy button it will check if the date is selecete, see if the user has picked out a movie,
     * then entered the number of ticket and if the information is all correct it will show an alert success. If not it will show warning
     * @param event when the buy button is pressed
     */
    @FXML
    public void buyButton(ActionEvent event) {
        String selectedShowtime = (String) moveAndShowtimeListView.getSelectionModel().getSelectedItem();
        String numberOfTickets = numberTicketTextField.getText();

        // Handle the buying logic here (e.g., validation, processing)
        if (selectedShowtime != null && !numberOfTickets.isEmpty()) {
            try {
                // Parse the number of tickets
                int ticketCount = Integer.parseInt(numberOfTickets);
                // Calculate the total price
                double totalPrice = ticketCount * TICKET_PRICE;

                // Process the purchase (this is just a simulation)
                Helpers.AlertHelper.showInformationAlert("Bought tickets", null, "Buying " + ticketCount + " tickets for: " + selectedShowtime);
            } catch (NumberFormatException e) {
                Helpers.AlertHelper.showWarningAlert("Error", null, "Invalid number of tickets.");

            }
        } else {
            Helpers.AlertHelper.showWarningAlert("Error", null, "Please select a showtime and enter the number of tickets.");

        }
    }
}
