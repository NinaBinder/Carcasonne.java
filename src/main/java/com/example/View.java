package com.example;


//TODO: 3x3 Feld gefüllt mit Random Bildern aus unserem Ressource Ordner


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Random;


public class View {

    TileLibrary library= new TileLibrary();
    BorderPane border = new BorderPane();

    TilePane root = new TilePane();
    ScrollPane scrollPane= new ScrollPane();
    HBox playerInterface= new HBox();
    ImageView buttonImageView = new ImageView();
    Button drawCardButton = new Button("draw new card");
    ImageView cardBack= new ImageView("file:src/fields/back.png");

    Image newButtonImage;
    Button rotateRight= new Button("rotate right");
    Label points = new Label("POINTS");
    int rotate=0;

    public Image getButtonImage(){
        return newButtonImage;
    }

    public View() {
        border.setCenter(scrollPane);
        scrollPane.setContent(root);
        scrollPane.setPrefSize(400, 400);

        border.setBottom(playerInterface);
        playerInterface.setPadding(new Insets(15, 12, 15, 12));
        playerInterface.setSpacing(10);
        playerInterface.getChildren().add(rotateRight);
        playerInterface.getChildren().add(points);

        playerInterface.getChildren().add(drawCardButton);
        drawCardButton.setGraphic(cardBack);
        drawCardButton.setContentDisplay(ContentDisplay.BOTTOM);
        cardBack.setFitWidth(100);
        cardBack.setFitHeight(100);

        root.setPrefColumns(3);
        initView();
        countPoints();

        Image[] buttonimage = {library.getImage(library.map.get("D")),
                library.getImage(library.map.get("H")),
                library.getImage(library.map.get("V")),
                library.getImage(library.map.get("J")),
                };

        Random rand = new Random();
        int[] numbers={0,1,2,3};

        drawCardButton.setOnAction(event->{
            buttonImageView.setFitWidth(100);
            buttonImageView.setFitHeight(100);
            int rnd = rand.nextInt(numbers.length);
            newButtonImage=buttonimage[rnd];
            buttonImageView.setImage(newButtonImage);
            drawCardButton.setGraphic(buttonImageView);

        });

    }

    public void initView() {
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(0,-1,0,"EMPTY",false));
        allTiles.add(new Tile(1,1,0,"OG",false));
        allTiles.add(new Tile(1,-1,0,"EMPTY",false));


        for (Tile tile : allTiles) {
            ImageView imageview = new ImageView(tile.getImage());
            imageview.setFitWidth(150);
            imageview.setFitHeight(150);

            rotateRight.setOnAction(event -> {
                rotate= rotate + 90;

                if (rotate == 360) {
                    rotate = 0;
                }
                System.out.println(rotate);

            });
            root.getChildren().addAll(imageview);
            imageview.setRotate(rotate);
        }

    }
    public void newCard(){

    }

    public void countPoints() {

        int score = 0;
        points.setText("Score: " + score);
        //if ...
        score++;
            }

    }