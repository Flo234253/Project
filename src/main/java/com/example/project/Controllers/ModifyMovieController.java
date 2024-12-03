package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.Genre;
import com.example.project.Model.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller class for the Modify Movie view.
 * <p>
 * This class allows users to modify the information of an existing movie, including its title,
 * genre, release date, duration, actors, director, and description.
 * It also provides functionality to save or cancel the modifications.
 * </p>
 */
public class ModifyMovieController {

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

    private Stage aStage;
    private Movie aMovie;

    /**
     * Sets the stage for the view.
     *
     * @param pStage The stage to set.
     */
    public void setStage(Stage pStage) {
        this.aStage = pStage;
    }

    /**
     * Populates the fields with the details of the movie.
     *
     * @param movie The movie to modify.
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

    // Todo:add confirmation message
    /**
     * Handles the Save button click event.
     * Validates input fields and saves the modified movie details.
     */
    @FXML
    private void onSaveButtonClicked() {
        try {
            // Extract and validate inputs using a loop
            Movie updatedMovie = extractMovieFromInputFields();

            // Show confirmation alert before saving
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Save", null,
                    "Are you sure you want to save the changes to this movie?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Update movie details
                updateMovieDetails(updatedMovie);

                // Todo: Implement persistence (e.g., saving to a database, CSV, or serialization)

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

    // Todo:add confirmation message
    /**
     * Handles the Cancel button click event.
     * Cancels the operation and closes the view.
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
     * Extracts and validates the input fields to create a Movie object.
     *
     * @return A Movie object based on the input fields.
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
