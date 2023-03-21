package com.example;


//TODO: 3x3 Feld gefüllt mit Random Bildern aus unserem Ressource Ordner


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
//import javafx.scene.layout.GridPane.com.example.demo_carcassonne


public class View extends BorderPane {

    TileLibrary library= new TileLibrary();
    BorderPane border = new BorderPane();
    TilePane root = new TilePane();
    ScrollPane scrollPane= new ScrollPane();
    VBox vBox= new VBox();
    Button drawCard = new Button("draw new card");
    Button rotateRight= new Button("rotate right");
    Label points = new Label("POINTS");

    public View() {
        scrollPane.setContent(root);
        border.setCenter(scrollPane);
        scrollPane.setPrefSize(400, 400);
        /*AnchorPane.setTopAnchor(scrollPane, 0.);
        AnchorPane.setRightAnchor(scrollPane, 0.);
        AnchorPane.setBottomAnchor(scrollPane, 0.);
        */AnchorPane.setLeftAnchor(scrollPane, 0.);
        //border.setMargin (root, new Insets (12,12,12,12));
        border.setLeft(vBox);
        //root.setAlignment(Pos.CENTER);
        //vBox.setAlignment(Pos.TOP_RIGHT);
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setSpacing(10);
        vBox.getChildren().add(drawCard);
        vBox.getChildren().add(points);
        //setAlignment (karteZiehen, Pos.BOTTOM_RIGHT);

        //initView(3,3);
        root.setPrefColumns(3);
        //border.setCenter(root);
        //setAlignment (root, Pos.CENTER);
        initView();
        countPoints();
    }

    public void initView() {
        Image[] imageName = {library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("H")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY"))};

        for (int i = 0; i < 9; i++) {
            ImageView imageview = new ImageView(imageName[i]);
            imageview.setFitWidth(150);
            imageview.setFitHeight(150);
            root.getChildren().addAll(imageview);
        }}
        public void countPoints() {

                int score = 0;
                points.setText("Score: " + score);
                //if ...
                score++;
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

