package com.example.project.Controllers;

import Helpers.AlertHelper;
import Helpers.MovieCell;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for managing the movie list view.
 * Provides functionality for searching, consulting, adding, editing, and deleting movies.
 */

public class MovieListController {

    @FXML
    private TextField aSearchField;

    @FXML
    private ListView<Movie> aMovieListView;

    @FXML
    private Button aConsultButton;

    @FXML
    private Button aAddButton;

    @FXML
    private Button aEditButton;

    @FXML
    private Button aDeleteButton;

    private ObservableList<Movie> aMovies;

    /**
     * Initializes the controller.
     * Loads the list of movies, sets up the ListView, and configures button behavior.
     */
    //Todo
    @FXML
    public void initialize() {

        loadMovies();
        // Custom cell factory for better ListView display
        aMovieListView.setItems(aMovies);
        aMovieListView.setCellFactory(listView -> new MovieCell());

        // Disable buttons when no movie is selected
        aMovieListView.getSelectionModel().selectedItemProperty().addListener((pObs, pOldVal, pNewVal) -> {
            boolean movieSelected = pNewVal != null;
            aConsultButton.setDisable(!movieSelected);
            aEditButton.setDisable(!movieSelected);
            aDeleteButton.setDisable(!movieSelected);
        });
    }

    /**
     * Loads movies into the list.
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
     * Filters the movie list based on the search query.
     */
    //Todo
    @FXML
    private void onSearchButtonClicked() {
        String pQuery = aSearchField.getText().toLowerCase();

        // Filter movies based on the query
        FilteredList<Movie> pFilteredMovies = new FilteredList<>(aMovies, pMovie ->
                pMovie.getTitle().toLowerCase().contains(pQuery) || pMovie.getGenre().toLowerCase().contains(pQuery));

        // Check if the filtered list is empty
        if (pFilteredMovies.isEmpty()) {
            // Show an error message if no movies match the query
            AlertHelper.showWarningAlert(
                    "No Results Found",
                    "No movies match your search query.",
                    "Please check your input and try again."
            );
        } else {
            // Update the ListView with the filtered movies
            aMovieListView.setItems(pFilteredMovies);
        }
    }


    /**
     * Opens the consult movie view to display the details of the selected movie.
     */
    //Todo
    @FXML
    private void onConsultButtonClicked() {
        Movie pSelectedMovie = aMovieListView.getSelectionModel().getSelectedItem();
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
                stage.setScene(new Scene(root));
                stage.setTitle("Movie Details");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Opens the add movie view to allow the user to add a new movie.
     */
    @FXML
    private void onAddButtonClicked() {
        // TODO: Implement logic to open the Add Movie view
    }

    /**
     * Opens the edit movie view to allow the user to edit the selected movie.
     */
    @FXML
    private void onEditButtonClicked() {
        Movie pSelectedMovie = aMovieListView.getSelectionModel().getSelectedItem();
        if (pSelectedMovie != null) {
            // TODO: Implement logic to open the Edit Movie view and pre-fill data
        }
    }

    /**
     * Deletes the selected movie from the list.
     */
    //Todo
    @FXML
    private void onDeleteButtonClicked() {
        Movie selectedMovie = aMovieListView.getSelectionModel().getSelectedItem();

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

    /**
     * Gets the "Add" button.
     *
     * @return The "Add" button.
     */
    public Button getaAddButton() {
        return aAddButton;
    }

    /**
     * Sets the "Add" button.
     *
     * @param aAddButton The "Add" button to set.
     */
    public void setaAddButton(Button aAddButton) {
        this.aAddButton = aAddButton;
    }
}
