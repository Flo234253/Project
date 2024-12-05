package com.example.project.Controllers;

import com.example.project.Model.ShowTime;
import com.example.project.Model.Ticket;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for the Ticket Sales view.
 */

public class TicketSaleViewController {

    /**
     * Button to navigate back to the previous screen.
     */
    @FXML
    public Button backButton;

    /**
     * The table that displays the list of tickets sold.
     */
    @FXML
    public TableView<Ticket> ticketSalesTable;



    /**
     * The table that displays the list of tickets sold.
     */
    @FXML
    public TableColumn<Ticket, String> movieNameColumn;


    /**
     * The table column that displays the number of tickets purchased.
     */
    @FXML
    public TableColumn<Ticket, Integer> ticketColumn;


    /**
     * The observable list holding the tickets to be displayed in the table.
     */
    private ObservableList<Ticket> ticketList;


    /**
     * Initializes the controller by configuring the TableColumns and loading ticket data.
     */
    @FXML
    public void initialize() {
        // Configure TableColumns
        ticketColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTicketsPurchased()).asObject());

        // Load ticket data from the serialized file
        loadTicketData();

        // Set the data into the TableView
        ticketSalesTable.setItems(ticketList);
    }

    /**
     * Loads ticket data from the serialized file into the ticketList.
     * This method reads a serialized list of tickets.
     */
    private void loadTicketData() {
        ticketList = FXCollections.observableArrayList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/tickets.ser"))) {
            // Read the deserialized object (ticketList)
            List<Ticket> loadedTickets = (List<Ticket>) ois.readObject();

            // Add all tickets to the list (could filter if needed)
            ticketList.addAll(loadedTickets);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<ShowTime> loadShowTimesFromFile() {
        List<ShowTime> showTimeList = new ArrayList<>();
        File showTimeFile = new File("data/showtime.ser");

        // Check if the file exists
        if (!showTimeFile.exists()) {
            System.err.println("ShowTime file does not exist: " + showTimeFile.getAbsolutePath());
            return showTimeList; // Return an empty list if file doesn't exist
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(showTimeFile))) {
            showTimeList = (List<ShowTime>) ois.readObject();
            System.out.println("ShowTimes loaded successfully: " + showTimeList.size());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading ShowTimes from file: " + e.getMessage());
            e.printStackTrace();
        }

        return showTimeList;
    }


    /**
     * Handles the action triggered by clicking the back button.
     * <p>
     * This method closes the current window and returns to the previous screen.
     * </p>
     *
     * @param actionEvent the event triggered by clicking the back button
     */
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        // Get the current stage (window)
        Stage stage = (Stage) backButton.getScene().getWindow();

        // Load the previous scene (or the desired view)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/manager-dashboard-view.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}
