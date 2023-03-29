package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        View view= new View();
        Model model= new Model();

        Controller controller = new Controller(view, model);
        model.setController(controller);
        model.initBoard();

        Scene scene = new Scene(view.border);
        stage.setScene(scene);
        stage.show();




    }

    public static void main(String[] args) {
        launch();
    }
}