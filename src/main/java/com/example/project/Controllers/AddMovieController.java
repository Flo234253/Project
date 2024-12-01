package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.Movie;
import com.example.project.Model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for the Add Movie view.
 * This class manages the form for adding a new movie,
 * including capturing input fields and handling the manager actions.
 */
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

    private Movie newMovie;
    private boolean isSaved = false;
    private Stage stage;

    /**
     * Sets the stage for this controller.
     *
     * @param stage The stage to set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets the newly created Movie object.
     *
     * @return The new Movie.
     */
    public Movie getNewMovie() {
        return newMovie;
    }

    /**
     * Checks if the movie was successfully saved.
     *
     * @return True if saved, false otherwise.
     */
    public boolean isSaved() {
        return isSaved;
    }

    /**
     * Handles the action when the "Save" button is clicked.
     * Captures the manager input from the form fields and processes the data to save the movie.
     */
    @FXML
    private void onSaveButtonClicked() {
        try {
            String title = aTitleField.getText().trim();
            String genresInput = aGenreField.getText().trim();
            String releaseDate = aReleaseDateField.getText().trim();
            String duration = aDurationField.getText().trim();
            String actors = aActorsField.getText().trim();
            String director = aDirectorField.getText().trim();
            String description = aDescriptionField.getText().trim();

            // Validate fields are not empty
            if (title.isEmpty() || genresInput.isEmpty() || releaseDate.isEmpty() || duration.isEmpty()
                    || actors.isEmpty() || director.isEmpty() || description.isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            // Validate title uniqueness
            if (isTitleDuplicate(title)) {
                throw new IllegalArgumentException("Movie title must be unique.");
            }

            // Validate release date format (YYYY-MM-DD)
            if (!releaseDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new IllegalArgumentException("Release date must in numeric value and  be in the format YYYY-MM-DD.");
            }

            // Validate duration format (e.g., "120 min")
            if (!duration.matches("\\d+ min")) {
                throw new IllegalArgumentException("Duration must be in the format 'XX min' (e.g., '120 min').");
            }

            // Parse genres
            List<Genre> genres = parseGenres(genresInput);

            // Show confirmation alert before saving
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Save", null,
                    "Are you sure you want to save this movie?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Create new Movie object
                newMovie = new Movie(title, genres, releaseDate, duration, actors, director, description);
                isSaved = true; // Mark as saved

                // Show confirmation that the movie has been saved
                AlertHelper.showInformationAlert("Movie Saved", null, "The movie has been successfully saved.");

                closeWindow();
            }

        } catch (IllegalArgumentException e) {
            AlertHelper.showWarningAlert("Invalid Input", null, e.getMessage());
        }
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
                "action", "comedy", "drama", "horror", "sci-fi", "romance", "science fiction",
                "thriller", "adventure", "fantasy", "mystery", "animation",
                "documentary", "biography", "musical", "western"
        );

        String[] genresArray = genresInput.split(",");

        for (String genre : genresArray) {
            if (!validGenres.contains(genre.trim().toLowerCase())) {
                throw new IllegalArgumentException(
                        "Invalid genre: " + genre.trim() + ". Allowed genres: " + validGenres
                );
            }
        }

        return Arrays.stream(genresArray)
                .map(genre -> new Genre(genre.trim()))
                .toList();
    }

    /**
     * Checks if the movie title is already used in the list of existing movies.
     *
     * @param title The movie title to check.
     * @return True if the title is a duplicate, false otherwise.
     */
    private boolean isTitleDuplicate(String title) {
        ObservableList<Movie> existingMovies = FXCollections.observableArrayList(
                new Movie("Inception", Arrays.asList(new Genre("Sci-Fi"), new Genre("Action")),
                        "2010-07-16", "148 min", "Leonardo DiCaprio", "Christopher Nolan", "A mind-bending thriller."),
                new Movie("Titanic", Arrays.asList(new Genre("Romance"), new Genre("Drama")),
                        "1997-12-19", "195 min", "Leonardo DiCaprio, Kate Winslet", "James Cameron", "A romantic tragedy.")
        );

        return existingMovies.stream().anyMatch(movie -> movie.getTitle().equalsIgnoreCase(title));
    }

    /**
     * Handles the action when the Cancel button is clicked.
     * Clears the form fields or closes the Add Movie view, discarding any unsaved changes.
     */
    @FXML
    private void onCancelButtonClicked() {
        Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Cancel", null,
                "Are you sure you want to cancel? Any unsaved changes will be lost.");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            closeWindow();
        }
    }

    /**
     * Closes the current window.
     */
    private void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }
}
