package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for the "Consult Movie" view.
 * This class manages the display of detailed movie information
 * and provides functionality to close the view.
 */
public class ConsultMovieController {

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
    private TextField aDescriptionArea;

    private Stage aStage;

    /**
     * Sets the stage for the view. The stage is used to control the window.
     *
     * @param pStage The stage to set.
     */
    public void setStage(Stage pStage) {
        this.aStage = pStage;
    }

    /**
     * Populates the text fields with the details of the movie.
     *
     * @param pTitle       The title of the movie.
     * @param pGenre       The genre of the movie.
     * @param pReleaseDate The release date of the movie.
     * @param pDuration    The duration of the movie.
     * @param pActors      The main actors of the movie.
     * @param pDirector    The director of the movie.
     * @param pDescription The description of the movie.
     */
    public void setMovieDetails(String pTitle, String pGenre, String pReleaseDate, String pDuration, String pActors, String pDirector, String pDescription) {
        aTitleField.setText(pTitle);
        aGenreField.setText(pGenre);
        aReleaseDateField.setText(pReleaseDate);
        aDurationField.setText(pDuration);
        aActorsField.setText(pActors);
        aDirectorField.setText(pDirector);
        aDescriptionArea.setText(pDescription);
    }

    /**
     * Closes the "Consult Movie" view.
     * This method is triggered by the "Close" button in the view.
     */
    @FXML
    private void onCloseButtonClicked() {
        if (aStage != null) {
            aStage.close();
        }
    }
}
