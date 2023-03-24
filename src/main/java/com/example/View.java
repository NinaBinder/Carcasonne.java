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
    Pane root = new Pane();
    ScrollPane scrollPane= new ScrollPane();
    HBox playerInterface= new HBox();
    final double IMAGESIZE= 100;
    final double SCROLLPANESIZE= 500;
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
        Tile originalTile= new Tile(0,0,0,"OG",false);

        Board board= new Board(originalTile);
        border.setCenter(scrollPane);

        scrollPane.setPrefSize(SCROLLPANESIZE, SCROLLPANESIZE);


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


        initBoard(board);
        countPoints();

        //TODO: create stack with all possible Tiles in another class --> Controller
        Image[] buttonimage = {library.getImage(library.map.get("D")),
                library.getImage(library.map.get("H")),
                library.getImage(library.map.get("V")),
                library.getImage(library.map.get("J")),
                };

        //TODO: method for drawing a new card in controller
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
    public void initBoard(Board board){
        Tile originalTile = new Tile(0,0,0,"OG",false);
        board.matrix[0][0]= originalTile;

        ImageView imageView= new ImageView(board.getTile_viaRelative(0,0).getImage());
        imageView.setFitWidth(IMAGESIZE);
        imageView.setFitHeight(IMAGESIZE);


        imageView.setLayoutX(SCROLLPANESIZE/2-IMAGESIZE/2);
        imageView.setLayoutY(SCROLLPANESIZE/2-IMAGESIZE/2);
        root.getChildren().add(imageView);

        scrollPane.setContent(root);
    }

    //when creating the first board which is only filled with the original Tile,
    // a layer of empty tiles is created as well, where new possible tiles will be dragged onto
    /*public void initView() {
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(1,1,0,"OG",false));
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));


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
            //root.getChildren().addAll(imageview);
            imageview.setRotate(rotate);
        }*/




    public void countPoints() {

        int score = 0;
        points.setText("Score: " + score);
        //if ...
        score++;
            }

    }