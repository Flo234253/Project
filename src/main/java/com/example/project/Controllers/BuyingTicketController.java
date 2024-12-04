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
     * Initialize the date picker, list view, text field amd the label
     */
    @FXML
    public DatePicker datePicker;
/**
     * The ListView that displays a list of movies and their corresponding showtimes.
     */
    @FXML
    public ListView<String> moveAndShowtimeListView;
/**
     * The TextField where the user inputs the number of tickets they wish to purchase.
     */
    @FXML
    public TextField numberTicketTextField;

    /**
     * The Label that displays the calculated price for the selected number of tickets.
     */
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
     * Override the initialize class, this method will automatically be called when this view is called.
     * There's event listener to data picker and price label. Calls the loadDataFrom file methode that will read the showtimes.
     *Called the onDateSelected method if new value is not null, so if the user selected a date.
     * Calls the updateTotalPrice method is the user enters something in the textField
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
     * So shows the movie and showtime example: Movie-Showtime: 20:30
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
            String time = showtime.getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

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

        // Early returns for input validation
        if (selectedMovieTitle == null || selectedMovieTitle.isEmpty()) {
            Helpers.AlertHelper.showErrorAlert("No Movie Selected", "Please select a movie before proceeding.");
            return;
        }

        if (numberOfTickets == null || numberOfTickets.isEmpty()) {
            Helpers.AlertHelper.showErrorAlert("No Tickets Entered", "Please enter the number of tickets.");
            return;
        }

        int ticketCount;
        try {
            ticketCount = Integer.parseInt(numberOfTickets);
            if (ticketCount <= 0) throw new NumberFormatException("Ticket count must be positive.");
        } catch (NumberFormatException e) {
            Helpers.AlertHelper.showErrorAlert("Invalid Input", "Please enter a valid number of tickets.");
            return;
        }
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

        uniqueID = (int) (Math.random() * 100000);
        purchaseDateTime = LocalDateTime.now();

        // Create and save the ticket
        Ticket eTicket = new Ticket(uniqueID, purchaseDateTime, matchedShowtime, ticketCount);
        saveTicketToFile(eTicket);

        // Show confirmation alert
        Helpers.AlertHelper.showInformationAlert(
                "Ticket Purchase Successful",
                null,
                String.format("Ticket ID: %d\nPurchase Date/Time: %s\nMovie: %s\nNumber of Tickets: %d\nTotal Price: $%.2f",
                        eTicket.getID(),
                        eTicket.getPurchaseDateTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")),
                        matchedShowtime.getMovie(),
                        ticketCount,
                        ticketCount * TICKET_PRICE)
        );
    }

    /**
     * Save the ticket data into a .ser file.
     *
     * @param ticket The Ticket object to be saved.
     */
    private void saveTicketToFile(Ticket ticket) {
        List<Ticket> ticketList = loadTicketsFromFile();

        // Add the new ticket to the list
        ticketList.add(ticket);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/tickets.ser"))) {
            // Serialize the ticket list and write it to the file
            oos.writeObject(ticketList);
        } catch (IOException e) {
            System.err.println("Error saving ticket to file: " + e.getMessage());
        }
    }

    /**
     * Load the list of tickets from the file.
     *
     * @return A list of Ticket objects.
     */
    private List<Ticket> loadTicketsFromFile() {
        List<Ticket> ticketList = new ArrayList<>();
        File ticketFile = new File("data/tickets.ser");

        if (!ticketFile.exists()) {
            System.err.println("Ticket file does not exist: " + ticketFile.getAbsolutePath());
            return ticketList; // Return an empty list if file doesn't exist
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ticketFile))) {
            // Read the list of Ticket objects from the serialized file
            ticketList = (List<Ticket>) ois.readObject();
            System.out.println("Tickets loaded successfully: " + ticketList.size());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading tickets from file: " + e.getMessage());
        }
        return ticketList;
    }

}
