package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        View view = new View();
        TileLibrary library= new TileLibrary();

        TilePane root = new TilePane();
        root.setPrefColumns(3);

        Image[] imageName = {library.getImage(library.map.get("K")),
                library.getImage(library.map.get("D")),
                library.getImage(library.map.get("V")),
                library.getImage(library.map.get("W")),
                library.getImage(library.map.get("H")),
                library.getImage(library.map.get("V")),
                library.getImage(library.map.get("J")),
                library.getImage(library.map.get("D")),
                library.getImage(library.map.get("K"))};

        for(int i=0; i< 9 ;i++){
            ImageView imageview = new ImageView(imageName[i]);
            root.getChildren().addAll(imageview);
        }

    Scene scene = new Scene(root, 900, 900);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}