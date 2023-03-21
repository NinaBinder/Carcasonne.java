package com.example;


//TODO: 3x3 Feld gefüllt mit Random Bildern aus unserem Ressource Ordner


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
//import javafx.scene.layout.GridPane.com.example.demo_carcassonne
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends BorderPane {

    TileLibrary library= new TileLibrary();
    BorderPane border = new BorderPane();
    TilePane root = new TilePane();
    VBox vBox= new VBox();
    Button karteZiehen = new Button("neue Karte ziehen");

    public View() {

        //border.setMargin (root, new Insets (12,12,12,12));
        border.setLeft(vBox);
        //root.setAlignment(Pos.CENTER);
        //vBox.setAlignment(Pos.TOP_RIGHT);
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setSpacing(10);
        vBox.getChildren().add(karteZiehen);
        //setAlignment (karteZiehen, Pos.BOTTOM_RIGHT);

        //initView(3,3);
        root.setPrefColumns(3);
        border.setCenter(root);
        setAlignment (root, Pos.CENTER);
        initView();
    }

    public void initView() {
        Image[] imageName = {library.getImage(library.map.get("K")),
                library.getImage(library.map.get("D")),
                library.getImage(library.map.get("V")),
                library.getImage(library.map.get("W")),
                library.getImage(library.map.get("H")),
                library.getImage(library.map.get("V")),
                library.getImage(library.map.get("J")),
                library.getImage(library.map.get("D")),
                library.getImage(library.map.get("K"))};

        for (int i = 0; i < 9; i++) {
            ImageView imageview = new ImageView(imageName[i]);
            root.getChildren().addAll(imageview);
        }
    }


        /*public void initView(int width, int height) {

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

    }*/
}
