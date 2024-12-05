package Helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Helper class for creating and displaying alerts in a JavaFX application.
 * <p>
 * This class provides reusable methods for showing various types of alerts such as confirmation,
 * information, warning, and error messages. It is designed to simplify alert management throughout
 * the application, promoting consistent alert style and behavior.
 * </p>
 */
public class AlertHelper {

    /**
     * Displays a confirmation alert with the given title, header, and content.
     * <p>
     * This method is used when an action requires user confirmation, such as deleting a record or
     * saving changes. It returns an {@link Optional} containing the {@link ButtonType} indicating
     * the user's response (e.g., OK or CANCEL). This allows the calling code to take action based
     * on whether the user confirmed or cancelled the operation.
     * </p>
     *
     * @param title   The title of the alert, displayed in the alert window's title bar.
     * @param header  The header text of the alert, providing a short description of the alert purpose.
     * @param content The content text of the alert, providing more detailed information about what is being confirmed.
     * @return An {@link Optional} containing the {@link ButtonType} that represents the user's response to the alert.
     */
    public static Optional<ButtonType> showConfirmationAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait(); // Returns Optional<ButtonType> which can be used for handling user's decision.
    }

    /**
     * Displays an information alert with the given title, header, and content.
     * <p>
     * This method is used to inform the user of something, such as the successful completion
     * of an operation (e.g., data has been successfully saved). Information alerts are non-critical
     * and provide positive feedback to the user.
     * </p>
     *
     * @param title   The title of the alert, displayed in the alert window's title bar.
     * @param header  The header text of the alert (can be null if no header is needed), providing a short summary of the information.
     * @param content The content text of the alert, providing the detailed information.
     */
    public static void showInformationAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait(); // Shows the alert and waits for the user to acknowledge it before proceeding.
    }

    /**
     * Displays a warning alert with the given title, header, and content.
     * <p>
     * This method is used to warn the user of a potential issue, such as entering invalid data
     * or attempting an unsupported action. Warning alerts help guide the user towards appropriate
     * actions and prevent mistakes without stopping the entire application.
     * </p>
     *
     * @param title   The title of the alert, displayed in the alert window's title bar.
     * @param header  The header text of the alert (can be null if no header is needed), providing a short summary of the warning.
     * @param content The content text of the alert, providing detailed warning information.
     */
    public static void showWarningAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait(); // Shows the warning alert to the user.
    }

    /**
     * Displays an error alert with the given title and content.
     * <p>
     * This method is used when a critical error occurs in the application, such as failing to load
     * important data or an unexpected exception. The error alert informs the user of the issue and
     * prompts them to take appropriate action or retry.
     * </p>
     *
     * @param title   The title of the alert, displayed in the alert window's title bar.
     * @param content The content text of the alert, providing detailed information about the error.
     */
    public static void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // Set header to null for simplicity, as error alerts often do not need a header.
        alert.setContentText(content);
        alert.showAndWait(); // Shows the error alert and waits for the user to acknowledge it before proceeding.
    }

    /**
     * Displays a warning alert with the given title and content.
     * <p>
     * This is an overloaded version of {@link #showWarningAlert(String, String, String)} which
     * provides a simpler interface when no header is required. It helps to reduce repetitive
     * code when a header is not needed for a particular warning.
     * </p>
     *
     * @param title   The title of the alert, displayed in the alert window's title bar.
     * @param content The content text of the alert, providing detailed warning information.
     */
    public static void showWarningAlert(String title, String content) {
        showWarningAlert(title, null, content); // Calls the other showWarningAlert method with a null header.
    }
}
