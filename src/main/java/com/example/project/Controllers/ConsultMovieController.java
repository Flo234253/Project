package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for the Consult Movie view.
 * <p>
 * This class manages the display of detailed movie information, allowing the manager
 * to view the title, genre, release date, duration, actors, director, and description
 * of a movie. It also provides the functionality to close the view.
 * </p>
 */
public class ConsultMovieController {
    /**
     * TextField for displaying the title of the movie.
     */
    @FXML
    private TextField aTitleField;

    /**
     * TextField for displaying the genre of the movie.
     */
    @FXML
    private TextField aGenreField;

    /**
     * TextField for displaying the release date of the movie.
     */
    @FXML
    private TextField aReleaseDateField;

    /**
     * TextField for displaying the duration of the movie.
     */
    @FXML
    private TextField aDurationField;

    /**
     * TextField for displaying the main actor(s of the movie.
     */
    @FXML
    private TextField aActorsField;

    /**
     * TextField for displaying the director of the movie.
     */
    @FXML
    private TextField aDirectorField;

    /**
     * TextField for displaying the description or synopsis of the movie.
     */
    @FXML
    private TextField aDescriptionArea;

    /**
     * The stage representing the current window.
     */
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
     * Closes the Consult Movie view.
     * <p>
     * This method is triggered by the Close button in the view, allowing the manager
     * to close the current view.
     * </p>
     */
    @FXML
    private void onCloseButtonClicked() {
        if (aStage != null) {
            aStage.close();
        }
    }
}
