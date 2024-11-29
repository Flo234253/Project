package com.example.project.Controllers;

import com.example.project.Model.Client;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Controller class for the Clients view.
 *
 */
//ToDO
public class ClientListViewController {




    @FXML
    private TableView<Client> clientsTableView;

    @FXML
    private TableColumn<Client, Integer> idColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;

    @FXML
    private TableColumn<Client, String> registrationDateColumn;


    /**
     * Button to navigate back to the previous screen.
     */

    @FXML
    public Button backButton;


    @FXML
    public void initialize() {
        // Correct PropertyValueFactory keys to match getters
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID")); // Matches getID()
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // Matches getName()
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        registrationDateColumn.setCellValueFactory(client ->
                new SimpleStringProperty(
                        client.getValue().getRegistrationDateTime()
                                .format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"))
                )
        );

        // Populate table
        ObservableList<Client> clientData = getClientList();
        clientsTableView.setItems(clientData);
    }

    /**
     * Fetches the list of clients to be displayed.
     *
     * @return an ObservableList of Client objects.
     */
    private ObservableList<Client> getClientList() {
        // Replace this with actual data fetching logic if needed
        ObservableList<Client> clients = FXCollections.observableArrayList();
        clients.add(new Client("Henry Robert", "henryRobert@gmail.ca", "1234", 1));
        clients.add(new Client("Ava Chapman", "avachapman@hotmail.ca", "4321", 2));
        clients.add(new Client("Ethan Price", "ethanPrice@gmail.ca", "2134", 3));
        return clients;
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

        // Set the scene for the current stage (window)
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Optionally, you can show the window again
        stage.show();
    }
}
