package com.example.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConsultMovieController {

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
    private TextField aDescriptionArea;

    private Stage aStage;


    public void setStage(Stage pStage) {
        this.aStage = pStage;
    }

    public void setMovieDetails(String pTitle, String pGenre, String pReleaseDate, String pDuration, String pActors, String pDirector, String pDescription) {
        aTitleField.setText(pTitle);
        aGenreField.setText(pGenre);
        aReleaseDateField.setText(pReleaseDate);
        aDurationField.setText(pDuration);
        aActorsField.setText(pActors);
        aDirectorField.setText(pDirector);
        aDescriptionArea.setText(pDescription);
    }

    @FXML
    private void onCloseButtonClicked() {
        if (aStage != null) {
            aStage.close();
        }
    }
}
