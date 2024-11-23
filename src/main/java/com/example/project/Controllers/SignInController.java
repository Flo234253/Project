package com.example.project.Controllers;

import com.example.project.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {

    /**
     * Initialize the passwordTextField and the emailTextField
     */
    public TextField passwordTextField;
    public TextField emailTextField;

    /**
     * When you click the sign in button it can open two different views
     * depending on the email and the password. It can open either the manager view of the client view.
     * It will need to go through a list of the user to see if their a manager or a client or some time they aren't even in the list
     * when that happens it will give you an error message
     * @param event this will happen when the sign in button is click
     */
    @FXML
    private void SignInButton(ActionEvent event) {

        /**
         * Get the email and password from their textField
         */
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        /**
         *Call the authenticate method from the user class to see
         *if the email and password are already in the user list
         */
        User user = User.authenticate(email, password);

        /**
         * After the authenticate of the email and password if its not null then
         * It will continue the following if statement: Verify if teh role of the user is a manger or
         * if its a client and will open two different views depending on which user
         * they are and if the user is null then it will throw an error
         */
        if (user != null) {
            if ("Manager".equals(user.getRole())) {
                openManagerView(event);
            } else if ("Client".equals(user.getRole())) {
                openBuyingTicketView(event);
            }
        } else {
            showError("No user found. Please try again.");
        }
    }

    /**
     *This method is will open a new JavaFX window, making it a model so you can
     * only focus on the new window
     * @param fxmlPath the path where the fxml is situated so it can open the right one
     * @param title the title of the fxml
     * @param width how big the page is
     * @param height how long the page is
     * @param event when the button is click it will open and get the source window as the owner
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
     * This method will open the BuyingTicketView
     * @param event if the user is a client then this method will be called
     */
    private void openBuyingTicketView(ActionEvent event) {
        openView("/com/example/project/BuyingTicketView.fxml", "Buying Ticket", 400, 450, event);
}

    /**
     * This method will open the manager dashboard
     * @param event will be called if the user is a manager
     */
    private void openManagerView(ActionEvent event) {
        openView("/com/example/project/manager-dashboard-view.fxml", "Manager Dashboard", 600, 600, event);
    }

    /**
     * This message will show an error message
     * @param message the message is what is wrong with the system
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sign In Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * When the creatAccountButton is click it will call the open Viw method which open the CreatingAccountView.fxml
     * @param event will be call when the button is clicked
     */
    public void createAccountButton(ActionEvent event) {
        openView("/com/example/project/CreatingAccountView.fxml", "Buying Ticket", 390, 340, event);
    }
}
