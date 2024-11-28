package com.example.project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Ticket Sales view.
 */
//ToDo
public class TicketSaleViewController {

    /**
     * Button to navigate back to the previous screen.
     */
    @FXML
    public Button backButton;


    /**
     * ListView to display the list of ticket sales.
     */



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
