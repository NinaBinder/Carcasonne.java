package com.example;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class View {
    BorderPane border = new BorderPane();
    ScrollPane scrollPane = new ScrollPane();
    Pane root = new Pane();
    HBox hBox = new HBox();
    VBox vBox= new VBox();
    Label drawStack= new Label();
    final double IMAGESIZE = 100;
    final double SCROLLPANESIZE = 500;
    ImageView buttonImageView = new ImageView();
    Button drawCardButton = new Button("draw new card");
    ImageView cardBack = new ImageView("file:src/fields/back.png");
    Image newButtonImage;
    Button rotateRight = new Button("rotate to right");
    Button rotateLeft = new Button("rotate to left");
    Label points = new Label("POINTS");


    public View() {
        //Tile originalTile= new Tile(0,0,0,"OG",false);
        border.setCenter(scrollPane);

        scrollPane.setPrefSize(SCROLLPANESIZE, SCROLLPANESIZE);
        scrollPane.setContent(root);

        border.setBottom(hBox);
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.getChildren().add(rotateRight);
        hBox.getChildren().add(rotateLeft);
        hBox.getChildren().add(points);
        hBox.getChildren().add(drawCardButton);

        drawCardButton.setGraphic(cardBack);
        drawCardButton.setContentDisplay(ContentDisplay.BOTTOM);
        cardBack.setFitWidth(100);
        cardBack.setFitHeight(100);
        countPoints();




    }

    public Image getNewButtonImage() {
        return newButtonImage;
    }

    /** getter and setter **/
    public BorderPane getBorder() {
        return border;
    }

    public double getSCROLLPANESIZE() {
        return SCROLLPANESIZE;
    }

    public Button getDrawCardButton() {
        return drawCardButton;
    }

    public ImageView getButtonImageView() {
        return buttonImageView;
    }

    public Pane getRoot() {
        return root;
    }

    public double getIMAGESIZE() {
        return IMAGESIZE;
    }

    //rotate the image of the tile in the button according to the direction
    public void rotateRight(){
        buttonImageView.setRotate(buttonImageView.getRotate()+90);
        }

    public void rotateLeft(){
        buttonImageView.setRotate(buttonImageView.getRotate()-90);
    }
    //TODO: update of Point Label
    public void countPoints() {

        int score = 0;
        points.setText("Score: " + score);
        //if ...
        score++;
    }

}