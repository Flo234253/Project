package com.example.project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyShowTimeViewController {


    public DatePicker dateField;
    @FXML
    private TextField showTimeIdField;





    @FXML
    private TextField timeField;



    @FXML
    private TextField roomIdField;


    @FXML
    private TextField movieIdField;


    @FXML
    private Button modifyShowTimeButton;


    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;


    @FXML
    private CheckBox fullCheckBox;

    public void handleModifyButton(ActionEvent actionEvent) {



    }

    public void handleCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();


    }

    public void handleSaveButton(ActionEvent actionEvent) {

        
    }
}
