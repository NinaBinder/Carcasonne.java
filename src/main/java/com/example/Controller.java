package com.example;

import javafx.event.EventTarget;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;



public class Controller {
    View view= new View();
    ArrayList<Tile> allTiles;
    Board board= new Board( );
    Tile ButtonTile;

    public void karteZiehen(){
       // view.getkarteZiehenButton().setOnAction(event -> {
          //  model.neueKarte();
        }

    public Controller(View view){
        this.view = view;
        this.allTiles = new ArrayList<Tile>();
        // generate original tile and new empty tiles in the tile array
        allTiles.add(new Tile(-1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(0,-1,0,"EMPTY",false));
        allTiles.add(new Tile(1,-1,0,"EMPTY",false));
        allTiles.add(new Tile(-1,0,0,"EMPTY",false));
        allTiles.add(new Tile(0,0,0,"OG",false));
        allTiles.add(new Tile(1,0,0,"EMPTY",false));
        allTiles.add(new Tile(-1,1,0,"EMPTY",false));
        allTiles.add(new Tile(0,1,0,"EMPTY",false));
        allTiles.add(new Tile(1,1,0,"EMPTY",false));



    }

    public void init(){
        //set the tiles in the board
        for(Tile tile: allTiles){
            board.set_withRelativeReference(tile.relX, tile.relY, tile.entry);
            ImageView imageView= new ImageView(tile.getImage());
            System.out.println("x");
            view.root.getChildren().add(imageView);
        }
        // initialise the view

    }

    public void rotateRight(){
        view.rotateRight.setOnAction(event-> {
            //rotate the sockets of the ButtonTile
            ButtonTile.rotateRight();
            // rotate the image
            view.rotate(ButtonTile.getRotation());
        });
    }
    public void rotateLeft(){
        view.rotateLeft.setOnAction(event-> {
            //rotate the sockets of the ButtonTile
            ButtonTile.rotateLeft();
            // rotate the image
            view.rotate(ButtonTile.getRotation());

        });
    }



    }



    /** draw one new card and get the imageview of this card
    public void drawCard(){
        view.getDrawCard().setOnMouseClicked(event -> {
        // generate new card image in view class
        });
        ImageView source = view.getButtonImageView();
        //react of drag
        source.setOnMouseClicked(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage();
            db.setContent(content);
            event.consume();
        });

        // accept possible drop
        for(int i = 0; i<9; i++){
            final Tile target = TileArray[i];
            target.setOnDragOver(event -> {
                System.out.println("z");
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                event.consume();
            }
        }

        target.setOnDragDropped( event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            event.setDropCompleted(success);
            event.consume();


        });
     */










