package Helpers;

import Model.Movie;
import javafx.scene.control.ListCell;

public class MovieCell extends ListCell<Movie> {

    @Override
    protected void updateItem(Movie pMovie, boolean pEmpty) {
        super.updateItem(pMovie, pEmpty);

        if (pEmpty || pMovie == null) {
            setText(null);
        } else {
            setText(pMovie.getTitle() + " - " + pMovie.getGenre());
        }
    }
}
