package com.example.project.Controllers;

import Helpers.AlertHelper;
import Helpers.SerializationHelper;
import com.example.project.Model.Movie;
import com.example.project.Model.Genre;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for the "Add Movie" view.
 * <p>
 * This class handles the manager interactions in the "Add Movie" view, including managing form input fields
 * for capturing movie details, performing data validation, and saving the movie data persistently.
 * It is responsible for all operations related to adding a new movie, including creating Movie objects,
 * confirming save operations, and handling error alerts when inputs are incorrect.
 * </p>
 */
public class AddMovieController implements Serializable {

    private static final String MOVIES_FILE_PATH = "../data/movies.ser";

    /**
     * TextField for the movie's title input.
     */
    @FXML
    private TextField aTitleField;

    /**
     * TextField for the movie's genres input.
     */
    @FXML
    private TextField aGenreField;

    /**
     * TextField for the movie's release date input.
     */
    @FXML
    private TextField aReleaseDateField;

    /**
     * TextField for the movie's duration input.
     */
    @FXML
    private TextField aDurationField;

    /**
     * TextField for the main actors in the movie.
     */
    @FXML
    private TextField aActorsField;

    /**
     * TextField for the movie's director input.
     */
    @FXML
    private TextField aDirectorField;

    /**
     * TextField for a brief description of the movie.
     */
    @FXML
    private TextField aDescriptionField;

    /**
     * A newly created Movie object, representing the movie that is being added.
     */
    private Movie newMovie;

    /**
     * A boolean flag indicating whether the movie has been successfully saved.
     */
    private boolean isSaved = false;

    /**
     * The current JavaFX Stage instance representing the Add Movie view.
     */
    private Stage stage;

    /**
     * Sets the stage for this controller.
     * <p>
     * This method is used to set the JavaFX Stage object representing the Add Movie window.
     * It allows the controller to manage the window, such as closing it when an action is completed.
     * </p>
     *
     * @param stage The stage to set, which represents the Add Movie view.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets the newly created Movie object.
     * <p>
     * This method returns the Movie object that has been added through the form inputs.
     * It can be used to access the movie's details after the form is successfully saved.
     * </p>
     *
     * @return The new Movie object.
     */
    public Movie getNewMovie() {
        return newMovie;
    }

    /**
     * Checks if the movie was successfully saved.
     * <p>
     * This method checks whether the movie has been successfully saved to the list.
     * It helps determine if the form submission was successful.
     * </p>
     *
     * @return True if the movie was saved successfully, false otherwise.
     */
    public boolean isSaved() {
        return isSaved;
    }

    /**
     * Handles the action when the "Save" button is clicked.
     * <p>
     * This method captures the manager input from the form fields and performs the following actions:
     * <ul>
     *     <li>Validates the input data to ensure all fields are filled and formatted correctly.</li>
     *     <li>Checks for duplicate movie titles to avoid adding a movie with an existing title.</li>
     *     <li>Parses the genre input into a list of Genre objects and confirms the action with the manager.</li>
     *     <li>Saves the new Movie object to the list and persists it to a serialized file.</li>
     *     <li>Closes the window if the movie is successfully saved.</li>
     * </ul>
     * If any validation fails, an alert is displayed to the manager, and the saving process is halted.
     */
    @FXML
    private void onSaveButtonClicked() {
        try {
            // Extract data from input fields
            String title = aTitleField.getText().trim();
            String genresInput = aGenreField.getText().trim();
            String releaseDate = aReleaseDateField.getText().trim();
            String duration = aDurationField.getText().trim();
            String actors = aActorsField.getText().trim();
            String director = aDirectorField.getText().trim();
            String description = aDescriptionField.getText().trim();

            // Validate that all fields are not empty
            if (title.isEmpty() || genresInput.isEmpty() || releaseDate.isEmpty() || duration.isEmpty()
                    || actors.isEmpty() || director.isEmpty() || description.isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            // Validate that the movie title is unique
            if (isTitleDuplicate(title)) {
                throw new IllegalArgumentException("Movie title must be unique.");
            }

            // Validate that the release date is in the correct format (YYYY-MM-DD)
            if (!releaseDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new IllegalArgumentException("Release date must be in the format YYYY-MM-DD.");
            }

            // Validate that the duration is in the correct format (e.g., "120 min")
            if (!duration.matches("\\d+ min")) {
                throw new IllegalArgumentException("Duration must be in the format 'XX min' (e.g., '120 min').");
            }

            // Parse the genre input into a list of Genre objects
            List<Genre> genres = parseGenres(genresInput);

            // Show confirmation alert before proceeding to save
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Save", null,
                    "Are you sure you want to save this movie?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Create the new Movie object and mark it as saved
                newMovie = new Movie(title, genres, releaseDate, duration, actors, director, description);
                isSaved = true;

                // Add the new movie to the existing list and save to the serialized file
                List<Movie> existingMovies = getExistingMovies();
                existingMovies.add(newMovie);
                SerializationHelper.saveData(MOVIES_FILE_PATH, existingMovies);

                // Show success message and close the window
                AlertHelper.showInformationAlert("Movie Saved", null, "The movie has been successfully saved.");
                closeWindow();
            }

        } catch (IllegalArgumentException e) {
            // Show alert for invalid input
            AlertHelper.showWarningAlert("Invalid Input", null, e.getMessage());
        }
    }

    /**
     * Parses the genre input into a list of Genre objects.
     * <p>
     * This method takes a comma-separated string of genres, validates each genre,
     * and returns a list of Genre objects. The genres must match predefined valid genres.
     * If any genre is invalid, an IllegalArgumentException is thrown.
     * </p>
     *
     * @param genresInput The input string containing genres (comma-separated).
     * @return A list of Genre objects representing the movie's genres.
     * @throws IllegalArgumentException if any genre is not in the list of allowed genres.
     */
    private List<Genre> parseGenres(String genresInput) {
        // Predefined list of valid genres
        List<String> validGenres = Arrays.asList(
                "action", "comedy", "drama", "horror", "sci-fi", "romance", "science fiction",
                "thriller", "adventure", "fantasy", "mystery", "animation",
                "documentary", "biography", "musical", "western"
        );

        String[] genresArray = genresInput.split(",");

        // Validate each genre
        for (String genre : genresArray) {
            if (!validGenres.contains(genre.trim().toLowerCase())) {
                throw new IllegalArgumentException(
                        "Invalid genre: " + genre.trim() + ". Allowed genres: " + validGenres
                );
            }
        }

        // Convert genres to a list of Genre objects
        return Arrays.stream(genresArray)
                .map(genre -> new Genre(genre.trim()))
                .toList();
    }

    /**
     * Checks if the movie title is already used in the list of existing movies.
     * <p>
     * This method checks if a movie with the given title already exists in the movie list,
     * ensuring that each movie added has a unique title.
     * </p>
     *
     * @param title The movie title to check.
     * @return True if the title is already used, false otherwise.
     */
    private boolean isTitleDuplicate(String title) {
        List<Movie> existingMovies = getExistingMovies();
        return existingMovies.stream().anyMatch(movie -> movie.getTitle().equalsIgnoreCase(title));
    }

    /**
     * Loads the existing list of movies from persistent storage.
     * <p>
     * This method reads the serialized data from the file that stores all the movies.
     * If the file does not exist or cannot be read, it returns an empty list.
     * </p>
     *
     * @return A list of existing Movie objects.
     */
    private List<Movie> getExistingMovies() {
        List<Movie> existingMovies = SerializationHelper.loadData(MOVIES_FILE_PATH);
        return existingMovies != null ? new ArrayList<>(existingMovies) : new ArrayList<>();
    }

    /**
     * Handles the action when the "Cancel" button is clicked.
     * <p>
     * This method clears the form fields or closes the Add Movie view without saving any changes.
     * It confirms with the manager before discarding unsaved changes.
     * </p>
     */
    @FXML
    private void onCancelButtonClicked() {
        // Show confirmation alert before canceling
        Optional<ButtonType> result = AlertHelper.showConfirmationAlert("Confirm Cancel", null,
                "Are you sure you want to cancel? Any unsaved changes will be lost.");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            closeWindow();
        }
    }

    /**
     * Closes the current window.
     * <p>
     * This method closes the JavaFX Stage that represents the current Add Movie view.
     * It is used when the manager completes an action or decides to cancel.
     * </p>
     */
    private void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }
}
