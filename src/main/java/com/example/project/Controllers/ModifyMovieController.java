package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.Genre;
import com.example.project.Model.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller class for the Modify Movie view.
 * <p>
 * This class allows manager to modify the information of an existing movie, including its title,
 * genre, release date, duration, actors, director, and description. It also provides
 * functionality to save or cancel modifications made to the movie details.
 * </p>
 */
public class ModifyMovieController implements Serializable {

    /**
     * TextField for the title of the movie.
     * <p>
     * This field allows the manager to view and modify the movie title.
     * The updated title will be saved upon confirmation.
     * </p>
     */
    @FXML
    private TextField aTitleField;

    /**
     * TextField for the genres of the movie.
     * <p>
     * This field allows the manager to view and modify the genres associated with the movie.
     * Genres are expected to be input as a comma-separated list.
     * </p>
     */
    @FXML
    private TextField aGenreField;

    /**
     * TextField for the release date of the movie.
     * <p>
     * This field allows the manager to view and modify the release date.
     * It should be provided in the format "YYYY-MM-DD".
     * </p>
     */
    @FXML
    private TextField aReleaseDateField;

    /**
     * TextField for the duration of the movie.
     * <p>
     * This field allows the manager to view and modify the duration of the movie.
     * Duration is expected to be in the format "XX min" (e.g., "120 min").
     * </p>
     */
    @FXML
    private TextField aDurationField;

    /**
     * TextField for the actors in the movie.
     * <p>
     * This field allows the manager to view and modify the actors involved in the movie.
     * It should be a comma-separated list of actors.
     * </p>
     */
    @FXML
    private TextField aActorsField;

    /**
     * TextField for the director of the movie.
     * <p>
     * This field allows the manager to view and modify the director of the movie.
     * </p>
     */
    @FXML
    private TextField aDirectorField;

    /**
     * TextField for the description or synopsis of the movie.
     * <p>
     * This field allows the manager to view and modify the description of the movie.
     * </p>
     */
    @FXML
    private TextField aDescriptionField;

    /**
     * The stage representing the current window of the Modify Movie view.
     * <p>
     * This stage is used for window management, such as closing the current view after saving or cancelling.
     * </p>
     */
    private Stage aStage;

    /**
     * The movie object that is being modified.
     * <p>
     * This represents the movie whose details are being modified. It is updated and saved upon confirmation.
     * </p>
     */
    private Movie aMovie;

    /**
     * Sets the stage for the Modify Movie view.
     * <p>
     * The stage is used to control the window operations, such as closing it after modifications.
     * </p>
     *
     * @param pStage The Stage object representing the current window.
     */
    public void setStage(Stage pStage) {
        this.aStage = pStage;
    }

    /**
     * Populates the text fields with the current details of the movie.
     * <p>
     * This method sets the fields to display the current information of the movie being modified.
     * It allows the manager to see the existing values before making changes.
     * </p>
     *
     * @param movie The movie whose details are being modified.
     */
    public void setMovieDetails(Movie movie) {
        this.aMovie = movie;

        aTitleField.setText(movie.getTitle());
        aGenreField.setText(String.join(", ", movie.getGenres().stream().map(Genre::getName).toList()));
        aReleaseDateField.setText(movie.getReleaseDate());
        aDurationField.setText(movie.getDuration());
        aActorsField.setText(movie.getActors());
        aDirectorField.setText(movie.getDirector());
        aDescriptionField.setText(movie.getDescription());
    }

    /**
     * Handles the Save button click event.
     * <p>
     * This method extracts the manager inputs, validates them, and saves the modifications
     * to the movie details if they are valid. It then displays a confirmation alert
     * and closes the view if the manager confirms the changes.
     * </p>
     */
    @FXML
    private void onSaveButtonClicked() {
        try {
            // Extract and validate inputs
            Movie updatedMovie = extractMovieFromInputFields();

            // Show confirmation alert before saving
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Save", null,
                    "Are you sure you want to save the changes to this movie?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Update movie details
                updateMovieDetails(updatedMovie);

                // Show confirmation that the movie has been saved
                AlertHelper.showInformationAlert("Movie Saved", null, "The movie has been successfully saved.");

                // Close the view
                if (aStage != null) {
                    aStage.close();
                }
            }
        } catch (IllegalArgumentException e) {
            AlertHelper.showErrorAlert("Invalid Input", e.getMessage());
        }
    }

    /**
     * Handles the Cancel button click event.
     * <p>
     * This method cancels the current operation, discards any unsaved changes,
     * and closes the Modify Movie view upon manager confirmation.
     * </p>
     */
    @FXML
    private void onCancelButtonClicked() {
        Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Cancel", null,
                "Are you sure you want to cancel? Any unsaved changes will be lost.");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (aStage != null) {
                aStage.close();
            }
        }
    }

    /**
     * Extracts and validates the input fields to create a new Movie object.
     * <p>
     * This method reads data from the input fields, performs validation checks,
     * and creates a new Movie object with the updated information.
     * If any field is invalid, it throws an IllegalArgumentException with the appropriate error message.
     * </p>
     *
     * @return A Movie object with the updated details.
     * @throws IllegalArgumentException if any input field is invalid.
     */
    private Movie extractMovieFromInputFields() throws IllegalArgumentException {
        // List of input fields and corresponding validation messages
        Map<TextField, String> fieldValidationMap = Map.of(
                aTitleField, "Title is required.",
                aGenreField, "Genres are required.",
                aReleaseDateField, "Release date must be in the format YYYY-MM-DD.",
                aDurationField, "Duration must be in the format 'XX min' (e.g., '120 min').",
                aActorsField, "Actors are required.",
                aDirectorField, "Director is required.",
                aDescriptionField, "Description is required."
        );

        // Extract and validate each field
        for (Map.Entry<TextField, String> entry : fieldValidationMap.entrySet()) {
            String value = entry.getKey().getText().trim();
            if (value.isEmpty()) {
                throw new IllegalArgumentException(entry.getValue());
            }
        }

        // Validate specific formats for release date and duration
        String releaseDate = aReleaseDateField.getText().trim();
        if (!releaseDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Release date must be in the format YYYY-MM-DD.");
        }

        String duration = aDurationField.getText().trim();
        if (!duration.matches("\\d+ min")) {
            throw new IllegalArgumentException("Duration must be in the format 'XX min' (e.g., '120 min').");
        }

        // Parse genres
        String genresInput = aGenreField.getText().trim();
        List<Genre> genres = parseGenres(genresInput);

        // Create and return the Movie object
        return new Movie(
                aTitleField.getText().trim(),
                genres,
                releaseDate,
                duration,
                aActorsField.getText().trim(),
                aDirectorField.getText().trim(),
                aDescriptionField.getText().trim()
        );
    }

    /**
     * Updates the movie details with the provided Movie object.
     * <p>
     * This method takes a Movie object with updated details and replaces the current movie's
     * details with these new values.
     * </p>
     *
     * @param updatedMovie The updated movie information.
     */
    private void updateMovieDetails(Movie updatedMovie) {
        aMovie.setTitle(updatedMovie.getTitle());
        aMovie.setGenres(updatedMovie.getGenres());
        aMovie.setReleaseDate(updatedMovie.getReleaseDate());
        aMovie.setDuration(updatedMovie.getDuration());
        aMovie.setActors(updatedMovie.getActors());
        aMovie.setDirector(updatedMovie.getDirector());
        aMovie.setDescription(updatedMovie.getDescription());
    }

    /**
     * Parses the genre input into a list of Genre objects.
     * <p>
     * This method takes a comma-separated list of genres and converts it into a list of Genre objects.
     * It validates that each genre is allowed and throws an exception if any genre is invalid.
     * </p>
     *
     * @param genresInput The input string containing genres (comma-separated).
     * @return A list of Genre objects.
     * @throws IllegalArgumentException if any genre is invalid.
     */
    private List<Genre> parseGenres(String genresInput) {
        List<String> validGenres = Arrays.asList(
                "Action", "Comedy", "Drama", "Horror", "Sci-Fi", "Romance", "Science Fiction",
                "Thriller", "Adventure", "Fantasy", "Mystery", "Animation",
                "Documentary", "Biography", "Musical", "Western"
        );

        String[] genresArray = genresInput.split(",");
        for (String genre : genresArray) {
            if (!validGenres.contains(genre.trim())) {
                throw new IllegalArgumentException("Invalid genre: " + genre.trim());
            }
        }

        return Arrays.stream(genresArray).map(genre -> new Genre(genre.trim())).toList();
    }
}
