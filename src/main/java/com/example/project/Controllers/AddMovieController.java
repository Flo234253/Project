package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
/**
 * Controller class for the Add Movie view.
 * This class manages the form for adding a new movie,
 * including capturing input fields and handling the manager actions.
 */
public class AddMovieController {

    /**
     * TextField for entering the title of the movie.
     */
    @FXML
    private TextField aTitleField;

    /**
     * TextField for entering the genre(s) of the movie.
     */
    @FXML
    private TextField aGenreField;

    /**
     * TextField for entering the release date of the movie.
     */
    @FXML
    private TextField aReleaseDateField;

    /**
     * TextField for entering the duration of the movie.
     */
    @FXML
    private TextField aDurationField;

    /**
     * TextField for entering the main actors of the movie.
     */
    @FXML
    private TextField aActorsField;

    /**
     * TextField for entering the director of the movie.
     */
    @FXML
    private TextField aDirectorField;

    /**
     * TextField for entering a brief description of the movie.
     */
    @FXML
    private TextField aDescriptionField;

    /**
     * Button to save the entered movie details.
     * Clicking this button triggers the logic to save the movie.
     */
    @FXML
    private Button aSaveButton;

    /**
     * Button to cancel the movie addition operation.
     * Clicking this button clears the fields or closes the view.
     */
    @FXML
    private Button aCancelButton;

    /**
     * Handles the action when the "Save" button is clicked.
     * <p>
     * Captures the manager input from the form fields and processes the data to
     * save the movie.
     * This method should save the new movie details to the data structure.
     * The implementation should validate the input fields before saving.
     * </p>
     */
    @FXML
    private void onSaveButtonClicked() {
        // TODO: Logic to save the movie details
    }

    /**
     * Handles the action when the Cancel button is clicked.
     * <p>
     * Clears the form fields or closes the Add Movie view, discarding any
     * unsaved changes.
     * </p>
     */
    @FXML
    private void onCancelButtonClicked() {
        // TODO: Logic to close the view or clear fields
    }
}
