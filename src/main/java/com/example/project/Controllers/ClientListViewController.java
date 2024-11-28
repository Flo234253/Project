package com.example.project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Controller class for the Clients view.
 *
 */
//ToDO
public class ClientListViewController {


    /**
     * Button to navigate back to the previous screen.
     */

    @FXML
    public Button backButton;

    /**
     * ListView to display the list of clients.
     */
    public ListView clientListView;


    /**
     * Handles the action triggered by clicking the back button.
     * <p>
     * This method closes the current window and returns to the previous screen.
     * </p>
     *
     * @param actionEvent the event triggered by clicking the back button
     */
    public void handleBackButton(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
