package Helpers;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 *  class for creating and displaying alerts.
 */
public class AlertHelper {

    /**
     * Displays a confirmation alert with the given title, header, and content
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param content The content text of the alert.
     * @return An Optional containing the user's response.
     */
    public static Optional<ButtonType> showConfirmationAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    /**
     * Displays an information alert with the given title, header, and content
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert (can be null).
     * @param content The content text of the alert.
     */
    public static void showInformationAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Displays a warning alert with the given title, header, and content.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert (can be null).
     * @param content The content text of the alert.
     */
    public static void showWarningAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}