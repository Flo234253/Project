module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.Controllers;
    opens com.example.project.Controllers to javafx.fxml;
}