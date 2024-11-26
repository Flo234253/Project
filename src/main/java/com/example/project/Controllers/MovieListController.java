package com.example.project.Controllers;

import Helpers.AlertHelper;
import com.example.project.Model.Movie;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for managing the movie list view.
 * Provides functionality for searching, consulting, adding, editing, and deleting movies.
 */

public class MovieListController {
    /**
     * TextField for searching movies in the list by title or genre.
     */
    @FXML
    private TextField aSearchField;

    /**
     * TableView for displaying the list of movies.
     */
    @FXML
    private TableView<Movie> aMovieTableView;

    /**
     * TableColumn for displaying movie titles in the TableView.
     */
    @FXML
    private TableColumn<Movie, String> titleColumn;

    /**
     * TableColumn for displaying movie genres in the TableView.
     */
    @FXML
    private TableColumn<Movie, String> genreColumn;

    /**
     * Button for consulting detailed information about the selected movie.
     */
    @FXML
    private Button aConsultButton;

    /**
     * Button for adding a new movie to the list.
     */
    @FXML
    private Button aAddButton;

    /**
     * Button for editing the selected movie.
     */
    @FXML
    private Button aEditButton;

    /**
     * Button for deleting the selected movie from the list.
     */
    @FXML
    private Button aDeleteButton;


    private ObservableList<Movie> aMovies;

    /**
     * Initializes the controller.
     * <p>
     * This method is called after the FXML file has been loaded. It sets up the TableView,
     * adds the columns to the Movies properties, and disables buttons when no room is selected.
     * </p>
     */
    //Todo
    @FXML
    public void initialize() {
        loadMovies();

        // Bind the "Title" column to the Movie model's title property
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));

        // Bind the "Genre" column to the Movie model's genre property
        genreColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenre()));

        // Populate the TableView
        aMovieTableView.setItems(aMovies);

        // Disable buttons when no movie is selected
        aMovieTableView.getSelectionModel().selectedItemProperty().addListener((_, _, newVal) -> {
            boolean isSelected = newVal != null;
            aConsultButton.setDisable(!isSelected);
            aEditButton.setDisable(!isSelected);
            aDeleteButton.setDisable(!isSelected);
        });
    }

    /**
     * Loads a predefined list of Movies and genre into the TableView.
     * This is temporary logic for demonstration purposes
     * TODO: Move this logic to an external helper or service class
     */
private void loadMovies() {
    aMovies = FXCollections.observableArrayList(
            new Movie("Inception", "Sci-Fi/Suspense/Action", "2010-07-16", "148 min", "Leonardo DiCaprio",
                    "Christopher Nolan", "A mind-bending thriller by Christopher Nolan."),
            new Movie("The Dark Knight", "Action/Sci-Fi/Thriller", "2008-07-18", "152 min", "Christian Bale",
                    "Christopher Nolan", "A superhero movie exploring the psyche of Batman."),
            new Movie("Titanic", "Romance/Drama/Historical", "1997-12-19", "195 min", "Leonardo DiCaprio, Kate Winslet",
                    "James Cameron", "A romantic tragedy aboard the ill-fated Titanic.")
    );
}

    /**
     * Filters Movies and Genre list based on the input from the search field.
     * <p>
     * If no Movie match the input, an alert is displayed to the manager.
     * </p>
     */
    //Todo
    @FXML
    private void onSearchButtonClicked() {
        String pInput = aSearchField.getText().toLowerCase();

        // Filter movies based on the input
        FilteredList<Movie> pFilteredMovies = new FilteredList<>(aMovies, pMovie ->
                pMovie.getTitle().toLowerCase().contains(pInput) || pMovie.getGenre().toLowerCase().contains(pInput));

        // Check if the filtered list is empty
        if (pFilteredMovies.isEmpty()) {
            // Show an error message if no movies match the input
            AlertHelper.showWarningAlert(
                    "No Results Found",
                    "No movies match your search input.",
                    "Please check your input and try again."
            );
        } else {
            // Update the ListView with the filtered movies
            aMovieTableView.setItems(pFilteredMovies);
        }
    }


    /**
     * Opens a new window to display detailed information about the selected movie.
     * <p>
     * The new window uses the ConsultMovieController to populate fields
     * with the movie's details. This method ensures the Consult button
     * is only enabled when a movie is selected.
     * </p>
     */
    //Todo
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
                        pSelectedMovie.getGenre(),
                        pSelectedMovie.getReleaseDate(),
                        pSelectedMovie.getDuration(),
                        pSelectedMovie.getActors(),
                        pSelectedMovie.getDirector(),
                        pSelectedMovie.getDescription()
                );


                Stage stage = new Stage();
                controller.setStage(stage); // Pass the stage to the ConsultMovieController
                stage.setScene(new Scene(root, 700, 550));
                stage.setTitle("Movie Details");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Opens a new window for adding a new movie to the list.
     * <p>
     * The implementation for this functionality is pending.
     * </p>
     */
    @FXML
    private void onAddButtonClicked() {
        // TODO: Implement logic to open the Add Movie view
    }

    /**
     * Opens a new window for editing the details of the selected movie.
     * <p>
     * This method should pre-fill the fields with the current movie details,
     * allowing the manager to make modifications.
     * The implementation for this functionality will be done later.
     * </p>
     */
    @FXML
    private void onEditButtonClicked() {
        Movie pSelectedMovie = aMovieTableView.getSelectionModel().getSelectedItem();
        if (pSelectedMovie != null) {
            // TODO: Implement logic to open the Edit Movie view and pre-fill data
        }
    }

    /**
     * Deletes the selected movie from the list.
     * <p>
     * Displays a confirmation alert before deletion. If confirmed,
     * the movie is removed from the TableView and an alert notifies the user of success.
     * </p>
     */
    //Todo
    @FXML
    private void onDeleteButtonClicked() {
        Movie selectedMovie = aMovieTableView.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            // Show confirmation alert
            Optional<ButtonType> result = AlertHelper.showConfirmationAlert(
                    "Confirm Deletion",
                    "Are you sure you want to delete this movie?",
                    "Movie: " + selectedMovie.getTitle()
            );

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the selected movie
                aMovies.remove(selectedMovie);

                // Show success message
                AlertHelper.showInformationAlert(
                        "Movie Deleted",
                        null,
                        "The movie '" + selectedMovie.getTitle() + "' was successfully deleted."
                );
            }
        } else {
            // Show warning if no movie is selected
            AlertHelper.showWarningAlert(
                    "No Movie Selected",
                    null,
                    "Please select a movie to delete."
            );
        }
    }
}

