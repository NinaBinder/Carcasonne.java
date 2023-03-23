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
    VBox vBox= new VBox();
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
        drawCard.setGraphic(buttonImageView);

        Image[] buttonimage = {library.getImage(library.map.get("D")),
                library.getImage(library.map.get("H")),
                library.getImage(library.map.get("V")),
                library.getImage(library.map.get("J")),
                };

        Random rand = new Random();
        int[] numbers={0,1,2,3};

        drawCard.setOnAction(event->{
            buttonImageView.setFitWidth(100);
            buttonImageView.setFitHeight(100);
            int rnd = rand.nextInt(numbers.length);
            buttonImageView.setImage(buttonimage[rnd]);

            //Pei I think this is the source of the drag and drop :)
            drawCard.setGraphic(buttonImageView);
            drawCard.setContentDisplay(ContentDisplay.BOTTOM);

        });

        scrollPane.setContent(root);
        border.setCenter(scrollPane);
        scrollPane.setPrefSize(400, 400);

        border.setLeft(vBox);
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setSpacing(10);
        vBox.getChildren().add(drawCard);
        vBox.getChildren().add(rotateRight);
        vBox.getChildren().add(points);


        root.setPrefColumns(3);
        initView();
        countPoints();


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


        public void countPoints() {

                int score = 0;
                points.setText("Score: " + score);
                //if ...
                score++;
            }

    }

