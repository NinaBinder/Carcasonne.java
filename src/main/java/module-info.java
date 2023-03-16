module com.example.demo_carcassonne {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo_carcassonne to javafx.fxml;
    exports com.example.demo_carcassonne;
}