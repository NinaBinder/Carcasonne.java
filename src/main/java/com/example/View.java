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
    Button rotateRight= new Button("rotate to right");
    Button rotateLeft= new Button("rotate to left");
    Label points = new Label("POINTS");
    int rotate=0;

    public Image getButtonImage(){
        return newButtonImage;
    }
    public Button getDrawCardButton(){ return drawCardButton;}
    public ImageView getButtonImageView(){return buttonImageView;}

    public View() {
        Tile originalTile= new Tile(0,0,0,"OG",false);

        Board board= new Board();
        border.setCenter(scrollPane);

        scrollPane.setPrefSize(SCROLLPANESIZE, SCROLLPANESIZE);
        scrollPane.setContent(root);


        border.setBottom(playerInterface);
        playerInterface.setPadding(new Insets(15, 12, 15, 12));
        playerInterface.setSpacing(10);
        playerInterface.getChildren().add(rotateRight);
        playerInterface.getChildren().add(rotateLeft);
        playerInterface.getChildren().add(points);
        playerInterface.getChildren().add(drawCardButton);

        drawCardButton.setGraphic(cardBack);
        drawCardButton.setContentDisplay(ContentDisplay.BOTTOM);
        cardBack.setFitWidth(100);
        cardBack.setFitHeight(100);
        initBoard(board);
        countPoints();

        //TODO: create stack with all possible Tiles in another class --> Controller


        //TODO: method for drawing a new card in controller
        Random rand = new Random();
        int[] numbers={0,1,2,3};




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
    }

    //rotate the image of the tile in the button
    public void rotate(int direction){
        buttonImageView.setRotate(direction);
        }




    public void countPoints() {

        int score = 0;
        points.setText("Score: " + score);
        //if ...
        score++;
            }

    }