package com.example.project.Controllers;

import com.example.project.Model.Client;
import com.example.project.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreatingAccountController {

    /**
     * Assign the text field
     */
    public TextField IdTextField;
    public TextField nameTextField;
    public TextField emailTextField;
    public TextField passwordTextField;

    /**
     * When the button is press it will verify the information and save the client in the file
     * @param event
     */
    public void enterButton(ActionEvent event) {

        /**
         * Get the email, password, id and the name from their text fields and assign them a variable
         */
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String ID = IdTextField.getText();
        String name = nameTextField.getText();

        /**
         * Check if the text field are not empty and if they are then you show an error message
         */
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || ID.isEmpty()) {
            showError("All text fields must be filled. Please try again.");
            return;
        }

        /**
         * Check if the email format is correct so example@example.ca
         */
        if (!email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            showError("Invalid email format!");
            return;
        }

        /* Validate and add the new client*/
        try {
            int clientId = Integer.parseInt(ID);
            Client newClient = new Client(name, email, password, clientId);
            User.getUserList().add(newClient);

            /*Save user data to file*/
            User.saveClientData();

            /*If successful it will open the buy ticket view*/
            openBuyingTicketView(event);
        } catch (NumberFormatException e) {
            showError("ID must be a valid integer!");
        }
    }

    /**
     * This method to display error messages.
     *
     * @param message The error message to display.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Generalized method to open a new view.
     *
     * @param fxmlPath The path to the FXML file.
     * @param title    The title of the new stage.
     * @param width    The width of the new scene.
     * @param height   The height of the new scene.
     * @param event    The triggering action event.
     */
    private void openView(String fxmlPath, String title, double width, double height, ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = fxmlLoader.load();
            Scene nextScene = new Scene(view, width, height);

            Stage nextStage = new Stage();
            nextStage.setScene(nextScene);
            nextStage.setTitle(title);
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            nextStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *This method will open the buying ticket view
     *
     * @param event The triggering action event.
     */
    private void openBuyingTicketView(ActionEvent event) {
        openView("/com/example/project/BuyingTicketView.fxml", "Buying Ticket", 400, 450, event);
    }
}
