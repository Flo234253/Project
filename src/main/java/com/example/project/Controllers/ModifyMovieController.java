package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
/**
 * Controller class for the Modify Movie view.
 * <p>
 * This class allows users to modify the information of an existing movie, including its title,
 * genre, release date, duration, actors, director, and description.
 * It also provides functionality to save or cancel the modifications.
 * </p>
 */
public class ModifyMovieController {

    /**
     * TextField for modifying the title of the movie.
     */
    @FXML
    private TextField aTitleField;

    /**
     * TextField for modifying the genre of the movie.
     */
    @FXML
    private TextField aGenreField;

    /**
     * TextField for modifying the release date of the movie.
     */
    @FXML
    private TextField aReleaseDateField;

    /**
     * TextField for modifying the duration of the movie.
     */
    @FXML
    private TextField aDurationField;

    /**
     * TextField for modifying the main actors of the movie.
     */
    @FXML
    private TextField aActorsField;

    /**
     * TextField for modifying the director of the movie.
     */
    @FXML
    private TextField aDirectorField;

    /**
     * TextField for modifying the description or synopsis of the movie.
     */
    @FXML
    private TextField aDescriptionField;

    /**
     * Handles the Save button click event.
     * <p>
     * This method should save the modified movie details to the data structure.
     * The implementation should validate the input fields before saving.
     * </p>
     */
    @FXML
    private void onSaveButtonClicked() {
        // Todo: Add save logic
    }

    /**
     * Handles the Cancel button click event.
     * <p>
     * This method should cancel the modifications and close the view
     * or clear the input fields.
     * </p>
     */
    @FXML
    private void onCancelButtonClicked() {
        // Todo: Add cancel logic
    }
}