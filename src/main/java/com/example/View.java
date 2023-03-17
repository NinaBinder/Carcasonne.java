package com.example;


//TODO: 3x3 Feld gefüllt mit Random Bildern aus unserem Ressource Ordner


import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.GridPane.com.example.demo_carcassonne
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View extends BorderPane {

    GridPane gridPane = new GridPane();
    public View() {

        this.setCenter(gridPane);
        initView(3,3);
    }

        public void initView(int width, int height) {

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Tile tile = new Tile ();
                    tile.setTranslateX(x * 200);
                    tile.setTranslateY(y * 200);
                    gridPane.getChildren().add(tile);
                }
            }
        }
    private class Tile extends GridPane {
        public Tile(){
            Rectangle border = new Rectangle(200,200);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);
            getChildren().addAll(border);
        }
    }
    public void addPicture (){

    }
}
