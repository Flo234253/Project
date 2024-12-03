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
 * Controller class for managing the movie list view.
 * Provides functionality for searching, consulting, adding, editing, and deleting movies.
 */
public class MovieListController {

    /**
     * Path to the serialized file for saving/loading movie data.
     */
    private static final String MOVIES_FILE_PATH = "data/movies.dat";

    @FXML
    private TextField aSearchField;

    @FXML
    private Button aRefreshButton;

    @FXML
    private TableView<Movie> aMovieTableView;

    @FXML
    private TableColumn<Movie, String> titleColumn;

    @FXML
    private TableColumn<Movie, String> genreColumn;

    @FXML
    private Button aConsultButton;

    @FXML
    private Button aAddButton;

    @FXML
    private Button aEditButton;

    @FXML
    private Button aDeleteButton;

    /**
     * List for storing the movies (serializable).
     */
    private List<Movie> aMovieList;

    /**
     * ObservableList for UI operations.
     */
    private ObservableList<Movie> aMovies;

    /**
     * Initializes the controller.
     * <p>
     * This method is called after the FXML file has been loaded. It sets up the TableView,
     * adds the columns to the movies properties, and disables buttons when no movie is selected.
     * </p>
     */
    @FXML
    public void initialize() {
        loadMovies();

        // Adds the Title column to the Movie model's title property
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));

        genreColumn.setCellValueFactory(data -> {
            String genres = data.getValue().getGenres().stream()
                    .map(Genre::getName)
                    .collect(Collectors.joining(", "));
            return new javafx.beans.property.SimpleStringProperty(genres);
        });

        // Populate the TableView
        aMovieTableView.setItems(aMovies);

        // Disable buttons when no movie is selected
        aMovieTableView.getSelectionModel().selectedItemProperty().addListener((_, _, newVal) -> {
            boolean isSelected = newVal != null;
            aConsultButton.setDisable(!isSelected);
            aEditButton.setDisable(!isSelected);
            aDeleteButton.setDisable(!isSelected);
        });

        // Disable Refresh button by default
        aRefreshButton.setDisable(true);
    }

    /**
     * Loads movies from the serialized file or a predefined list if no file exists.
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
     * @return the list of default movies
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
     * Triggered by the Refresh button.
     */
    @FXML
    private void onRefreshButtonClicked() {
        aMovieTableView.setItems(aMovies);
    }

    /**
     * Filters movies based on the input from the search field.
     * <p>
     * If no movies match the input, an alert is displayed to the manager.
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
     * Opens a new window to display the information about the selected movie.
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
     * After the user saves a new movie, it is added to the observable movie list.
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
     */
    private void saveMovies() {
        ensureDataDirectoryExists(); // Ensure the "data" directory exists before saving
        SerializationHelper.saveData(MOVIES_FILE_PATH, aMovieList);
    }

    /**
     * Opens a new window for editing the details of the selected movie.
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
     * Displays a confirmation alert before deletion.
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
