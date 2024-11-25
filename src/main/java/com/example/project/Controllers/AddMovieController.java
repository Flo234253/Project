package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
//Todo
public class AddMovieController {

    @FXML
    private TextField aTitleField;

    @FXML
    private TextField aGenreField;

    @FXML
    private TextField aReleaseDateField;

    @FXML
    private TextField aDurationField;

    @FXML
    private TextField aActorsField;

    @FXML
    private TextField aDirectorField;

    @FXML
    private TextField aDescriptionField;

    @FXML
    private Button aSaveButton;

    @FXML
    private Button aCancelButton;

    @FXML
    private void onSaveButtonClicked() {

        // TODO: Logic to save the movie details
    }

    @FXML
    private void onCancelButtonClicked() {

        // TODO:Logic to close the view or clear fields
    }
}
