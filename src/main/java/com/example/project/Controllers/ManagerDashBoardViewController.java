package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for managing the manager dashboard view.
 *
 */
public class ManagerDashBoardViewController {

    /**
     * Button to navigate to the Movies view.
     */
    @FXML
    private Button viewMoviesButton;

    /**
     * Button to navigate to the ShowTimes view.
     */
    @FXML
    private Button viewShowTimesButton;

    /**
     * Button to navigate to the Rooms view.
     */
    @FXML
    private Button viewRoomsButton;

    /**
     * Button to navigate to the Ticket Sales view.
     */
    @FXML
    private Button viewTicketSalesButton;

    /**
     * Button to navigate to the Clients view.
     */
    @FXML
    private Button viewClientsListButton;

    /**
     * Handles the action triggered by clicking the Movies button.
     * <p>
     * Navigates to the Movies view.
     * </p>
     */
    @FXML
    private void handleMoviesButton() {
        navigateToView("/com/example/project/movie-list-view.fxml", "Movies List", viewMoviesButton);
    }

    /**
     * Handles the action triggered by clicking the ShowTimes button.
     * <p>
     * Navigates to the ShowTimes view.
     * </p>
     */
    @FXML
    private void handleShowTimesButton() {
        navigateToView("/com/example/project/manage-showtime-view.fxml", "Show Time List", viewShowTimesButton);
    }

    /**
     * Handles the action triggered by clicking the Rooms button.
     * <p>
     * Navigates to the screen Rooms list view.
     * </p>
     */
    @FXML
    private void handleRoomsButton() {
        navigateToView("/com/example/project/screen-room-list-view.fxml", "Screening Rooms List", viewRoomsButton);
    }

    /**
     * Handles the action triggered by clicking the Ticket Sales button.
     * <p>
     * Navigates to the Ticket Sales view.
     * </p>
     */
    @FXML
    private void handleTicketSalesButton() {
        navigateToView("/com/example/project/ticket-sale-view.fxml", "Ticket Sales List", viewTicketSalesButton);
    }

    /**
     * Handles the action triggered by clicking the Clients button.
     * <p>
     * Navigates to the Clients view.
     * </p>
     */
    @FXML
    private void handleClientsListButton() {
        navigateToView("/com/example/project/client-list-view.fxml", "Clients List", viewClientsListButton);
    }

    /**
     * Utility method to navigate to a specified view.
     *
     * @param fxmlPath     The path to the FXML file for the view.
     * @param title        The title of the window to display.
     * @param sourceButton The button that triggered the navigation (used to close the current stage).
     */
    private void navigateToView(String fxmlPath, String title, Button sourceButton) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 560, 550));
            newStage.setTitle(title);
            newStage.show();

            // Close the current stage
            Stage currentStage = (Stage) sourceButton.getScene().getWindow();
            currentStage.close();

            // Reopen the dashboard when the new view is closed
            newStage.setOnCloseRequest(event -> reopenDashboard());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Re-opens the manager dashboard after a view is closed.
     */
    private void reopenDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/manager-dashboard-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 550));
            stage.setTitle("Manager Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
