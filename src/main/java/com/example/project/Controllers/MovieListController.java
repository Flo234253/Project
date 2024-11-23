package com.example.project.Controllers;

import com.example.project.Helpers.MovieCell;
import com.example.project.Model.Movie;

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
    //Todo
    @FXML
    public void initialize() {
        // Load movies into the list
        loadMovies();

        // Populate ListView with movies
        aMovieListView.setItems(aMovies);

        // Custom cell factory for better ListView display
        aMovieListView.setCellFactory(listView -> new MovieCell());


        // Disable buttons when no movie is selected
        aMovieListView.getSelectionModel().selectedItemProperty().addListener((pObs, pOldVal, pNewVal) -> {
            boolean movieSelected = pNewVal != null;
            aConsultButton.setDisable(!movieSelected);
            aEditButton.setDisable(!movieSelected);
            aDeleteButton.setDisable(!movieSelected);
        });
    }
//TODO:change this method in a class (importhelper or movieservice)
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
    //Todo
    @FXML
    private void onSearchButtonClicked() {
        String pQuery = aSearchField.getText().toLowerCase();
        FilteredList<Movie> pFilteredMovies = new FilteredList<>(aMovies, pMovie ->
                pMovie.getTitle().toLowerCase().contains(pQuery) || pMovie.getGenre().toLowerCase().contains(pQuery));
        aMovieListView.setItems(pFilteredMovies);
    }
    //Todo
    @FXML
    private void onConsultButtonClicked() {
        Movie pSelectedMovie = aMovieListView.getSelectionModel().getSelectedItem();
        if (pSelectedMovie != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/consult-movie-view.fxml"));
                Parent root = loader.load();

                // Access the controller
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

                // Create and show a new stage
                Stage stage = new Stage();
                controller.setStage(stage);
                stage.setTitle("Movie Details");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//Todo
    @FXML
    private void onAddButtonClicked() {
        System.out.println("Add Movie button clicked!");
        // Logic to open the Add Movie view
    }
    //Todo
    @FXML
    private void onEditButtonClicked() {
        Movie pSelectedMovie = aMovieListView.getSelectionModel().getSelectedItem();
        if (pSelectedMovie != null) {
            System.out.println("Editing movie: " + pSelectedMovie.getTitle());
            // Logic to open the Edit Movie view and pre-fill data
        }
    }
    //Todo
    @FXML
    private void onDeleteButtonClicked() {
        Movie pSelectedMovie = aMovieListView.getSelectionModel().getSelectedItem();
        if (pSelectedMovie != null) {
            aMovies.remove(pSelectedMovie);
            System.out.println("Deleted movie: " + pSelectedMovie.getTitle());
        }
    }

    public Button getaAddButton() {
        return aAddButton;
    }

    public void setaAddButton(Button aAddButton) {
        this.aAddButton = aAddButton;
    }
}
