package Helpers;

import com.example.project.Model.Movie;

/**
 * Custom ListCell implementation for displaying movie details in a ListView.
 * This class customizes the appearance of each cell in the ListView to show the
 * title and genre of a movie.
 */
public class MovieCell extends javafx.scene.control.ListCell<Movie> {


    /**
     * Updates the content of a ListCell for the given Movie object.
     *
     * @param pMovie The Movie object to display in the cell. If null, the cell is cleared.
     * @param pEmpty A boolean indicating whether the cell is empty. If true, the cell is cleared.
     */
    @Override
    protected void updateItem(Movie pMovie, boolean pEmpty) {
        super.updateItem(pMovie, pEmpty);

        if (pEmpty || pMovie == null) {
            // Clear the cell content if empty or no movie is provided
            setText(null);
        } else {
            // Display the movie title and genre in the cell
            setText(pMovie.getTitle() + " - " + pMovie.getGenre());
        }
    }

}
