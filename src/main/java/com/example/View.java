package com.example;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
//TODO: meeple display

public class View {
    BorderPane border = new BorderPane();
    ScrollPane scrollPane = new ScrollPane();
    Pane root = new Pane();
    HBox hBox = new HBox();
    final double IMAGESIZE = 100;
    final double SCROLLPANESIZE = 500;
    ImageView buttonImageView = new ImageView();
    Button drawCardButton = new Button("draw new card");
    ImageView cardBack = new ImageView("file:src/fields/back.png");
    Image newButtonImage;
    Button rotateRight = new Button("rotate to right");
    Button rotateLeft = new Button("rotate to left");
    Button computerTurn = new Button("computerTurn");
    Label points = new Label("POINTS");




    public View() {
        border.setCenter(scrollPane);
        scrollPane.setPrefSize(SCROLLPANESIZE, SCROLLPANESIZE);
        scrollPane.setContent(root);
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        border.setBottom(hBox);
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.getChildren().add(rotateRight);
        hBox.getChildren().add(rotateLeft);
        hBox.getChildren().add(points);
        hBox.getChildren().add(drawCardButton);
        hBox.getChildren().add(computerTurn);

        drawCardButton.setGraphic(cardBack);
        drawCardButton.setContentDisplay(ContentDisplay.BOTTOM);
        cardBack.setFitWidth(100);
        cardBack.setFitHeight(100);

        countPoints();
    }

    public void rotateRight(){

            buttonImageView.setRotate(buttonImageView.getRotate()+90);
            if(buttonImageView.getRotate() == 360){
                buttonImageView.setRotate(0);
            }
            System.out.println(buttonImageView.getRotate());
    }

    public void rotateLeft(){

        if(buttonImageView.getRotate() == 0){
            buttonImageView.setRotate(360);
        }
        buttonImageView.setRotate(buttonImageView.getRotate()-90);

        System.out.println(buttonImageView.getRotate());
    }


    //TODO: update of Point Label
    public void countPoints() {

        int score = 0;
        points.setText("Score: " + score);
        //if ...
        score++;
    }


    /** getter and setter **/
    public Button getDrawCardButton() {
        return drawCardButton;
    }
    public Image getNewButtonImage() {
        return newButtonImage;
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
}