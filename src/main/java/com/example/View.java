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
import java.util.Random;


public class View {

    TileLibrary library= new TileLibrary();
    BorderPane border = new BorderPane();
    TilePane root = new TilePane();
    ScrollPane scrollPane= new ScrollPane();
    HBox playerInterface= new HBox();
    ImageView buttonImageView = new ImageView();
    Button drawCard = new Button("draw new card");
    ImageView cardBack= new ImageView("file:src/fields/back.png");

    Button rotateRight= new Button("rotate right");
    Label points = new Label("POINTS");
    int rotate=0;

    public ImageView getButtonImageView(){
        return buttonImageView;
    }

    public Button getDrawCard(){
        return drawCard;
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

        playerInterface.getChildren().add(drawCard);
        drawCard.setGraphic(cardBack);
        drawCard.setContentDisplay(ContentDisplay.BOTTOM);
        cardBack.setFitWidth(100);
        cardBack.setFitHeight(100);

        root.setPrefColumns(3);
        initView();
        countPoints();

        drawCard.setOnAction(event->{

            //Pei I think this is the source of the drag and drop :)
            drawCard.setGraphic(buttonImageView);

        });

    }
    public void initView() {
        Image[] imageName = {library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("OG")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY")),
                library.getImage(library.map.get("EMPTY"))};

        for (int i = 0; i < 9; i++) {
            ImageView imageview = new ImageView(imageName[i]);
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
        Image[] buttonimage = {library.getImage(library.map.get("D")),
                library.getImage(library.map.get("H")),
                library.getImage(library.map.get("V")),
                library.getImage(library.map.get("J")),
        };

        Random rand = new Random();
        int[] numbers={0,1,2,3};
        buttonImageView.setFitWidth(100);
        buttonImageView.setFitHeight(100);
        int rnd = rand.nextInt(numbers.length);
        buttonImageView.setImage(buttonimage[rnd]);
    }

    public void countPoints() {

        int score = 0;
        points.setText("Score: " + score);
        //if ...
        score++;
            }

    }