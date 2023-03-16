package com.example.demo_carcassonne;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane root= new Pane();
        GridPane gridPane= new GridPane();



        Image img = new Image("file:src/fields/kreuzung.png");

        Image img1= new Image("file:src/fields/kurve_nordost.png");
        ImageView imageView= new ImageView();
        ImageView imageView1 = new ImageView();
        imageView1.setImage(img1);
        imageView.setImage(img);

        imageView.setRotate(90);
        imageView1.setLayoutX(img.getWidth());

        for(int i=0; i<50;i++){
            Rectangle rectangle = new Rectangle();
            rectangle.setWidth(img.getWidth());
            rectangle.setStroke(Color.WHITE);
            gridPane.getChildren().add(rectangle);
        }

        root.getChildren().add(gridPane);
        root.getChildren().add(imageView);
        root.getChildren().add(imageView1);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}