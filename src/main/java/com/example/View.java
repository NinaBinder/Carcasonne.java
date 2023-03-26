package com.example;

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
/**
    public void updateBoard(Board board){
        //go through board matrix for every tile do this:
        for(int x=0; x<board.matrix.length; x++){
            for (int y= 0; y<board.matrix.length; y++){

                //get every Tiles Entry and set it to possibly new entry
                board.matrix[x][y].getEntry();

                //check the neighbor tile if null set Empty tile, is this the right approach?
                if (getNorthTile(x,y) == null){
                    getNorthTile(x,y).setTile(x,y,0,"EMPTY",false);
                }
            }
        }
    }
*/
    //rotate the image of the tile in the button according to the direction
    public void rotateRight(){
        buttonImageView.setRotate(buttonImageView.getRotate()+90);
        }

    public void rotateLeft(){
        buttonImageView.setRotate(buttonImageView.getRotate()-90);
    }

    public BorderPane getBorder() {
        return border;
    }

    public void countPoints() {

        int score = 0;
        points.setText("Score: " + score);
        //if ...
        score++;
            }

    }