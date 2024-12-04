package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.Client;
import com.example.project.Model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import static com.example.project.Model.User.saveClientData;

/**
 * Controller class for managing the client table view and related actions in the application.
 *
 */
public class ClientListViewController {


    /**
     * TableView for displaying the list of clients.
     */
    @FXML
    private TableView<Client> clientsTableView;

    /**
     * TableColumn for displaying client IDs.
     */
    @FXML
    private TableColumn<Client, Integer> idColumn;

    /**
     * TableColumn for displaying client names.
     */
    @FXML
    private TableColumn<Client, String> nameColumn;

    /**
     * TableColumn for displaying client email addresses.
     */
    @FXML
    private TableColumn<Client, String> emailColumn;

    /**
     * TableColumn for displaying client registration dates.
     */
    @FXML
    private TableColumn<Client, String> registrationDateColumn;

    /**
     * Button to navigate back to the previous screen.
     */
    @FXML
    public Button backButton;


    /**
     * Button to delete the selected client from the table.
     */
    @FXML
    public Button deleteButton;


    /**
     * Observable list that holds all clients to be displayed in the TableView.
     */

    private ObservableList<Client> clientList;


    /**
     * Initializes the controller by configuring the TableColumns and loading client data.
     */
    @FXML
    public void initialize() {
        // Configure TableColumns
        // Configure TableColumns
        idColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getID()).asObject());
        nameColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        emailColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));

        // Format the registration date to display only the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        registrationDateColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getRegistrationDateTime().format(formatter)
                ));

        // Load client data from the deserialized file
        loadClientData();

        // Set the data into the TableView
        clientsTableView.setItems(clientList);
    }


    /**
     * Loads client data from the serialized file into the clientList.
     * This method reads a serialized list of users and filters out only the clients.
     */
    private void loadClientData() {
        clientList = FXCollections.observableArrayList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"))) {
            // Read the deserialized object (userList) and filter for clients
            ArrayList<User> userList = (ArrayList<User>) ois.readObject();

            for (User user : userList) {
                if (user instanceof Client) {
                    clientList.add((Client) user);  // Adding Client objects to the list
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Handles the delete button action.
     * Confirms the deletion with the user, removes the selected client from the list, and updates the serialized data.
     *
     * @param actionEvent The action event triggered by the delete button.
     */
    public void handleDeleteButton(ActionEvent actionEvent) {
        Client selectedClient = clientsTableView.getSelectionModel().getSelectedItem();

        // Check if a client is selected
        if (selectedClient != null) {
            // Confirm deletion with the user using AlertHelper
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert(
                    "Confirm Deletion",
                    "Are you sure you want to delete this client?",
                    "This action cannot be undone."
            );

            // If the user confirms the deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the client from the ObservableList
                clientList.remove(selectedClient);

                // Save the updated client list to the serialized file
                saveClientData();

                // Show a success message using AlertHelper
                AlertHelper.showInformationAlert(
                        "Client Deleted",
                        null,
                        "The client was successfully deleted."
                );
            }
        } else {
            // If no client is selected, show an error message using AlertHelper
            AlertHelper.showErrorAlert(
                    "No Client Selected",
                    "Please select a client to delete."
            );
        }
    }


    /**
     * Saves the updated client list back to the serialized file after deletion.
     * This method writes the remaining clients to the file.
     */
    private void saveClientData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            // Create a new list of users that includes only the remaining clients
            ArrayList<User> userList = new ArrayList<>(clientList);

            // Write the updated list to the serialized file
            oos.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Handles the action triggered by clicking the back button.
     *      * <p>
     *      * This method closes the current window and returns to the previous screen by loading
     *      * the specified FXML view.
     *      * </p>
     *      *
     *      * @param actionEvent the event triggered by clicking the back button
     *      * @throws IOException if the FXML file for the previous screen cannot be loaded
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
