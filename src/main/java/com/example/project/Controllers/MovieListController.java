package com.example.project.Controllers;

import Helpers.AlertHelper;
import Helpers.SerializationHelper;
import com.example.project.Model.Movie;
import com.example.project.Model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller class for managing the Movie List view.
 * <p>
 * This class provides the functionality to manage a list of movies, including:
 * searching, consulting, adding, editing, and deleting movies.
 * It interacts with the TableView UI component to display movies to the manager.
 * </p>
 */
public class MovieListController {

    /**
     * Path to the serialized file for saving/loading movie data.
     * <p>
     * This file is used to persist movie data between sessions.
     * It stores the serialized version of the movie list to allow
     * data to be saved and restored.
     * </p>
     */
    private static final String MOVIES_FILE_PATH = "data/movies.ser";

    /**
     * TextField for searching movies.
     * <p>
     * This field is used by the manager to enter keywords to search for specific movies
     * by their title or genres.
     * </p>
     */
    @FXML
    private TextField aSearchField;

    /**
     * Button to refresh the list of movies.
     * <p>
     * This button resets the search filter to display all movies again.
     * </p>
     */
    @FXML
    private Button aRefreshButton;

    /**
     * TableView component to display the list of movies.
     * <p>
     * This table displays all the movies in the list along with their respective details.
     * It allows manager to select movies for consultation, editing, or deletion.
     * </p>
     */
    @FXML
    private TableView<Movie> aMovieTableView;

    /**
     * TableColumn for displaying the title of each movie in the TableView.
     * <p>
     * This column shows the title attribute of the Movie objects in the TableView.
     * </p>
     */
    @FXML
    private TableColumn<Movie, String> titleColumn;

    /**
     * TableColumn for displaying the genres of each movie in the TableView.
     * <p>
     * This column shows the genres attribute of the Movie objects, represented as a comma-separated string.
     * </p>
     */
    @FXML
    private TableColumn<Movie, String> genreColumn;

    /**
     * Button to consult the selected movie details.
     */
    @FXML
    private Button aConsultButton;

    /**
     * Button to edit the selected movie's details.
     */
    @FXML
    private Button aEditButton;

    /**
     * Button to delete the selected movie from the list.
     */
    @FXML
    private Button aDeleteButton;

    /**
     * List for storing the movies.
     * <p>
     * This list holds Movie objects and is used for serialization purposes.
     * </p>
     */
    private List<Movie> aMovieList;

    /**
     * ObservableList for UI operations.
     * <p>
     * This list is used to manage the movies in the TableView,
     * allowing automatic updates to the UI when the data changes.
     * </p>
     */
    private ObservableList<Movie> aMovies;

    /**
     * Initializes the controller.
     * <p>
     * This method is called after the FXML file has been loaded.
     * It sets up the TableView by adding columns for movie properties and disables buttons when no movie is selected.
     * </p>
     */
    @FXML
    public void initialize() {
        loadMovies();

        // Adds the Title column to the Movie model's title property
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));

        // Adds the Genre column by extracting and joining all genres into a single comma-separated string
        genreColumn.setCellValueFactory(data -> {
            String genres = data.getValue().getGenres().stream()
                    .map(Genre::getName)
                    .collect(Collectors.joining(", "));
            return new javafx.beans.property.SimpleStringProperty(genres);
        });

        // Populate the TableView with the ObservableList
        aMovieTableView.setItems(aMovies);

        // Disable buttons if no movie is selected in the TableView
        aMovieTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean isSelected = newValue != null;
            aConsultButton.setDisable(!isSelected);
            aEditButton.setDisable(!isSelected);
            aDeleteButton.setDisable(!isSelected);
        });

        // Disable Refresh button by default until a search is performed
        aRefreshButton.setDisable(true);
    }

    /**
     * Loads movies from the serialized file or creates a default list if no file exists.
     * <p>
     * The method first ensures the data directory exists, then checks for the serialized data file.
     * If the file is found, it loads the movies; otherwise, it creates a predefined list of default movies.
     * </p>
     */
    private void loadMovies() {
        ensureDataDirectoryExists(); // Ensure the "data" directory exists before loading

        File dataFile = new File(MOVIES_FILE_PATH);

        if (dataFile.exists()) {
            // Load serialized data if available
            aMovieList = SerializationHelper.loadData(MOVIES_FILE_PATH);
            if (aMovieList == null) {
                aMovieList = createDefaultMovies();
            } else {
                aMovieList = new ArrayList<>(aMovieList); // Ensure the list is mutable
            }
        } else {
            aMovieList = createDefaultMovies();
            saveMovies(); // Save default movies for next time
        }

        // Convert to ObservableList for UI operations
        aMovies = FXCollections.observableArrayList(aMovieList);
    }

    /**
     * Ensures that the data directory exists.
     * <p>
     * If the "data" directory does not exist, it creates one.
     * This ensures that there is a place to save the serialized data file.
     * </p>
     */
    private void ensureDataDirectoryExists() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    /**
     * Creates a predefined list of movies to be used when no serialized data exists.
     *
     * @return The list of default Movie objects.
     */
    private List<Movie> createDefaultMovies() {
        return new ArrayList<>(List.of(
                new Movie("Inception", new ArrayList<>(List.of(new Genre("Sci-Fi"), new Genre("Action"))),
                        "2010-07-16", "148 min", "Leonardo DiCaprio", "Christopher Nolan", "A mind-bending thriller."),
                new Movie("Titanic", new ArrayList<>(List.of(new Genre("Romance"), new Genre("Drama"))),
                        "1997-12-19", "195 min", "Leonardo DiCaprio, Kate Winslet", "James Cameron", "A romantic tragedy.")
        ));
    }

    /**
     * Refreshes the TableView to display all movies.
     * <p>
     * This method is triggered by the "Refresh" button.
     * It resets any search filters and displays the complete movie list.
     * </p>
     */
    @FXML
    private void onRefreshButtonClicked() {
        aMovieTableView.setItems(aMovies);
    }

    /**
     * Filters movies based on the input from the search field.
     * <p>
     * If no movies match the input, an alert is displayed to the manager.
     * This method is triggered by the "Search" button.
     * </p>
     */
    @FXML
    private void onSearchButtonClicked() {
        String pInput = aSearchField.getText().toLowerCase();

        // Filter movies based on the input
        FilteredList<Movie> pFilteredMovies = new FilteredList<>(aMovies, pMovie -> {
            if (pMovie.getTitle().toLowerCase().contains(pInput)) {
                return true;
            }
            for (Genre genre : pMovie.getGenres()) {
                if (genre.getName().toLowerCase().contains(pInput)) {
                    return true;
                }
            }
            return false;
        });

        if (pFilteredMovies.isEmpty()) {
            AlertHelper.showWarningAlert(
                    "No Results Found",
                    "No movies or genres match your search input.",
                    "Please check your input and try again."
            );
        } else {
            aMovieTableView.setItems(pFilteredMovies);
        }

        aRefreshButton.setDisable(false);
    }

    /**
     * Opens a new window to display information about the selected movie.
     * <p>
     * This method is triggered by the "Consult" button and allows the manager to view the details
     * of the selected movie in a new window.
     * </p>
     */
    @FXML
    private void onConsultButtonClicked() {
        Movie pSelectedMovie = aMovieTableView.getSelectionModel().getSelectedItem();
        if (pSelectedMovie != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/consult-movie-details-view.fxml"));
                Parent root = loader.load();

                ConsultMovieController controller = loader.getController();
                controller.setMovieDetails(
                        pSelectedMovie.getTitle(),
                        pSelectedMovie.getGenres().stream()
                                .map(Genre::getName)
                                .collect(Collectors.joining(", ")),
                        pSelectedMovie.getReleaseDate(),
                        pSelectedMovie.getDuration(),
                        pSelectedMovie.getActors(),
                        pSelectedMovie.getDirector(),
                        pSelectedMovie.getDescription()
                );

                Stage stage = new Stage();
                controller.setStage(stage);
                stage.setScene(new Scene(root, 700, 550));
                stage.setTitle("Movie Details");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            AlertHelper.showWarningAlert("No Selection", "Please select a movie to consult.", null);
        }
    }

    /**
     * Opens a new window for adding a new movie to the list.
     * <p>
     * After the manager saves a new movie, it is added to the ObservableList.
     * Triggered by the "Add" button.
     * </p>
     */
    @FXML
    private void onAddButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/add-movie-view.fxml"));
            Parent root = loader.load();

            AddMovieController controller = loader.getController();
            Stage stage = new Stage();
            controller.setStage(stage);

            stage.setScene(new Scene(root, 600, 500));
            stage.setTitle("Add New Movie");
            stage.showAndWait();

            if (controller.isSaved()) {
                Movie newMovie = controller.getNewMovie();
                aMovieList.add(newMovie);
                aMovies.add(newMovie);
                saveMovies();
            }

        } catch (IOException e) {
            e.printStackTrace();
            AlertHelper.showErrorAlert("Error", "Failed to open Add Movie view.");
        }
    }

    /**
     * Saves the current list of movies to the serialized file.
     * <p>
     * This method ensures that the movie list persists between sessions.
     * It saves the list to a file defined by the MOVIES_FILE_PATH constant.
     * </p>
     */
    private void saveMovies() {
        ensureDataDirectoryExists(); // Ensure the "data" directory exists before saving
        SerializationHelper.saveData(MOVIES_FILE_PATH, aMovieList);
    }

    /**
     * Opens a new window for editing the details of the selected movie.
     * <p>
     * Triggered by the "Edit" button, this method allows the manager to modify
     * the details of a selected movie. Once editing is complete, it saves the changes.
     * </p>
     */
    @FXML
    private void onEditButtonClicked() {
        Movie pSelectedMovie = aMovieTableView.getSelectionModel().getSelectedItem();
        if (pSelectedMovie != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/modify-movie-view.fxml"));
                Parent root = loader.load();

                ModifyMovieController controller = loader.getController();
                controller.setStage(new Stage());
                controller.setMovieDetails(pSelectedMovie);

                Stage stage = new Stage();
                stage.setScene(new Scene(root, 700, 500));
                stage.setTitle("Modify Movie");
                stage.showAndWait();

                // Refresh the TableView after modifications
                aMovieTableView.refresh();
                saveMovies();

            } catch (IOException e) {
                e.printStackTrace();
                AlertHelper.showErrorAlert("Error", "Failed to open Modify Movie view.");
            }
        } else {
            AlertHelper.showWarningAlert("No Selection", "Please select a movie to modify.", null);
        }
    }

    /**
     * Deletes the selected movie from the list.
     * <p>
     * This method prompts the manager for confirmation before deleting the selected movie.
     * Triggered by the "Delete" button.
     * </p>
     */
    @FXML
    private void onDeleteButtonClicked() {
        Movie selectedMovie = aMovieTableView.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert(
                    "Confirm Deletion",
                    "Are you sure you want to delete this movie?",
                    "Movie: " + selectedMovie.getTitle()
            );

            if (result.isPresent() && result.get() == ButtonType.OK) {
                aMovieList.remove(selectedMovie);
                aMovies.remove(selectedMovie);
                saveMovies();

                AlertHelper.showInformationAlert(
                        "Movie Deleted",
                        null,
                        "The movie '" + selectedMovie.getTitle() + "' was successfully deleted."
                );
            }
        } else {
            AlertHelper.showWarningAlert(
                    "No Movie Selected",
                    null,
                    "Please select a movie to delete."
            );
        }
    }
}
