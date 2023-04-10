package com.example;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
//TODO: meeple display

public class View {
    BorderPane border = new BorderPane();
    ScrollPane scrollPane = new ScrollPane();
    Pane root = new Pane();
    HBox hBox = new HBox();
    HBox hBoxTop = new HBox();
    final double IMAGESIZE = 100;
    final double SCROLLPANESIZE = 500;
    ImageView buttonImageView = new ImageView();
    Button drawCardButton = new Button("draw new card");
    ImageView cardBack = new ImageView("file:src/fields/back.png");
    Image newButtonImage;
    Button computerTurn = new Button("computerTurn");
    Label points = new Label("POINTS");
    Label pointsPlayer = new Label("POINTS PLAYER:");
    Label pointsComputer = new Label("POINTS ENEMY:");
    ToggleButton rotateRight = new ToggleButton();
    ToggleButton rotateLeft = new ToggleButton();
    Image right = new Image("file:src/fields/rotateR.png");
    Image left = new Image("file:src/fields/rotateL.png");




    public View() {
        border.setCenter(scrollPane);
        scrollPane.setPrefSize(SCROLLPANESIZE, SCROLLPANESIZE);
        scrollPane.setContent(root);
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        rotateRight.setPrefSize(40,80);
        rotateRight.setBackground(null);
        rotateRight.setBorder(null);
        final ImageView toggleImage = new ImageView();
        rotateRight.setGraphic(toggleImage);
        toggleImage.setFitHeight(120);
        toggleImage.setFitWidth(160);
        toggleImage.imageProperty().bind(Bindings
                .when(rotateRight.selectedProperty())
                .then(right)
                .otherwise(right)
        );
        rotateLeft.setPrefSize(40,80);
        rotateLeft.setBackground(null);
        rotateLeft.setBorder(null);
        final ImageView    toggleImageLeft = new ImageView();
        rotateLeft.setGraphic(toggleImage);
        toggleImageLeft.setFitHeight(120);
        toggleImageLeft.setFitWidth(160);
        toggleImageLeft.imageProperty().bind(Bindings
                .when(rotateLeft.selectedProperty())
                .then(left)
                .otherwise(left)
        );

        border.setTop(hBoxTop);
        hBoxTop.setSpacing(25);
        hBoxTop.setPadding(new Insets(15, 12, 15, 12));
        hBoxTop.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
        hBoxTop.getChildren().add(pointsPlayer);
        hBoxTop.getChildren().add(pointsComputer);
        border.setBottom(hBox);
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(35);
        hBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
        hBox.getChildren().add(rotateLeft);
        hBox.getChildren().add(drawCardButton);
        hBox.getChildren().add(rotateRight);
        hBox.getChildren().add(computerTurn);

        drawCardButton.setGraphic(cardBack);
        drawCardButton.setContentDisplay(ContentDisplay.BOTTOM);
        cardBack.setFitWidth(100);
        cardBack.setFitHeight(100);

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