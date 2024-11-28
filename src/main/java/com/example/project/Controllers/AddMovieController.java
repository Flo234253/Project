package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.Movie;
import com.example.project.Model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

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
     * <p>
     * Captures the manager input from the form fields and processes the data to
     * save the movie.
     * </p>
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
                throw new IllegalArgumentException("Release date must be in the format YYYY-MM-DD.");
            }

            // Validate duration format (e.g., "120 min")
            if (!duration.matches("\\d+ min")) {
                throw new IllegalArgumentException("Duration must be in the format 'XX min' (e.g., '120 min').");
            }

            // Parse genres
            List<Genre> genres = parseGenres(genresInput);

            // Create new Movie object
            newMovie = new Movie(title, genres, releaseDate, duration, actors, director, description);
            isSaved = true; // Mark as saved
            closeWindow();
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
        // Updated list of valid genres (normalized to lowercase for case-insensitive comparison)
        List<String> validGenres = Arrays.asList(
                "action", "comedy", "drama", "horror", "sci-fi", "romance", "science fiction",
                "thriller", "adventure", "fantasy", "mystery", "animation",
                "documentary", "biography", "musical", "western"
        );

        // Split the input genres string by commas
        String[] genresArray = genresInput.split(",");

        // Validate each genre in the input against the valid genres list
        for (String genre : genresArray) {
            if (!validGenres.contains(genre.trim().toLowerCase())) {
                throw new IllegalArgumentException(
                        "Invalid genre: " + genre.trim() + ". Allowed genres: " + validGenres
                );
            }
        }

        // Convert valid genre strings to Genre objects and return as a list
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
     * <p>
     * Clears the form fields or closes the Add Movie view, discarding any
     * unsaved changes.
     * </p>
     */
    @FXML
    private void onCancelButtonClicked() {
        closeWindow();
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
