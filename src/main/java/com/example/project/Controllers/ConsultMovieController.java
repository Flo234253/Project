package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for the Consult Movie view.
 * <p>
 * This class is responsible for managing the display of detailed movie information.
 * It allows the manager to view specific details of a movie, such as its title,
 * genre, release date, duration, actors, director, and a brief description.
 * The class also includes functionality for closing the movie details view.
 * </p>
 */
public class ConsultMovieController {

    /**
     * TextField for displaying the title of the movie.
     * <p>
     * This field shows the name of the movie selected by the manager.
     * The value is set via the {@link #setMovieDetails} method.
     * </p>
     */
    @FXML
    private TextField aTitleField;

    /**
     * TextField for displaying the genre of the movie.
     * <p>
     * This field shows the genres associated with the movie, such as "Action, Comedy".
     * The value is set via the {@link #setMovieDetails} method.
     * </p>
     */
    @FXML
    private TextField aGenreField;

    /**
     * TextField for displaying the release date of the movie.
     * <p>
     * This field shows the release date of the movie, formatted as "YYYY-MM-DD".
     * The value is set via the {@link #setMovieDetails} method.
     * </p>
     */
    @FXML
    private TextField aReleaseDateField;

    /**
     * TextField for displaying the duration of the movie.
     * <p>
     * This field shows the duration of the movie in the format "XXX min".
     * The value is set via the {@link #setMovieDetails} method.
     * </p>
     */
    @FXML
    private TextField aDurationField;

    /**
     * TextField for displaying the main actor(s) of the movie.
     * <p>
     * This field shows the list of main actors in the movie, typically formatted as a comma-separated string.
     * The value is set via the {@link #setMovieDetails} method.
     * </p>
     */
    @FXML
    private TextField aActorsField;

    /**
     * TextField for displaying the director of the movie.
     * <p>
     * This field shows the name of the director of the movie.
     * The value is set via the {@link #setMovieDetails} method.
     * </p>
     */
    @FXML
    private TextField aDirectorField;

    /**
     * TextField for displaying the description or synopsis of the movie.
     * <p>
     * This field provides a brief description or synopsis of the movie, offering insight into the storyline.
     * The value is set via the {@link #setMovieDetails} method.
     * </p>
     */
    @FXML
    private TextField aDescriptionArea;

    /**
     * The JavaFX Stage representing the current window for the Consult Movie view.
     * <p>
     * This stage is used to manage and control the current window, such as closing it when required.
     * </p>
     */
    private Stage aStage;

    /**
     * Sets the stage for the Consult Movie view.
     * <p>
     * This method assigns the JavaFX Stage object representing the current window.
     * The stage is used to perform actions such as closing the window when the manager
     * clicks on the "Close" button.
     * </p>
     *
     * @param pStage The Stage object to set.
     */
    public void setStage(Stage pStage) {
        this.aStage = pStage;
    }

    /**
     * Populates the text fields with the details of the selected movie.
     * <p>
     * This method is used to populate all the text fields of the view with the
     * provided movie details. The manager can use this information to consult
     * the details of a specific movie. It is typically called by another controller
     * that handles the event of selecting a movie to consult.
     * </p>
     *
     * @param pTitle       The title of the movie to be displayed.
     * @param pGenre       The genre(s) of the movie to be displayed.
     * @param pReleaseDate The release date of the movie in "YYYY-MM-DD" format.
     * @param pDuration    The duration of the movie in "XXX min" format.
     * @param pActors      The main actor(s) in the movie, typically as a comma-separated string.
     * @param pDirector    The name of the director of the movie.
     * @param pDescription A brief description or synopsis of the movie.
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
     * Handles the action when the "Close" button is clicked.
     * <p>
     * This method is triggered when the manager clicks on the "Close" button in the Consult Movie view.
     * It is used to close the current window, allowing the manager to exit the view after consulting
     * the details of the movie.
     * </p>
     */
    @FXML
    private void onCloseButtonClicked() {
        if (aStage != null) {
            aStage.close();
        }
    }
}
