package com.example;

import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Controller {
    View view= new View();
    TileLibrary library = new TileLibrary();
    ArrayList<Tile> allTiles;
    Board board= new Board( );
    Tile ButtonTile;


    /** deck: An Array List filled with all possible Entries
     in order to get the images of that entry: deck(index).img
     */
    ArrayList<LibraryEntry> deck= new ArrayList<LibraryEntry>();

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

        deck.add(library.map.get("A"));
        deck.add(library.map.get("B"));


        changeDrawButtonImage();

    }




    public void changeDrawButtonImage(){
//TODO: nochmal ziehen, falls tile nicht passt
        //gerade noch random, nicht gelöscht/rotiert
        //put image on label nicht auf button / disbale button after on drück es sei denn es passt nicht dann mach noch mal
        view.getDrawCardButton().setOnAction(event->{

            view.buttonImageView.setFitWidth(100);
            view.buttonImageView.setFitHeight(100);

            int index = (int)(Math.random() * deck.size());

            System.out.println(deck.get(index).img);
            Image newButtonImage = deck.get(index).img;

            view.getButtonImageView().setImage(newButtonImage);

            view.getDrawCardButton().setGraphic(view.getButtonImageView());

        });
    }

    // drag the tile which is shown on the button to the board
    public void DragandDrop(){
        //Source of the drag gesture
        Button source = view.drawCardButton;
        //react of drag
        source.setOnMouseClicked(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            System.out.println(view.getButtonImage());
            content.putImage(view.getButtonImage());
            db.setContent(content);
            event.consume();
        });
        // target of drag gesture = drop


        for(Tile tile: allTiles){
            Tile target = tile;
            // accept possible drop


        }
    }


    //set the event handler on the rotateRight button
    public void rotateRight(){
        view.rotateRight.setOnAction(event-> {
            //rotate the sockets of the ButtonTile
            ButtonTile.rotateRight();
            // the rotation of the ButtonTile will be updated
            // rotate the image with the new rotation value
            view.rotate(ButtonTile.getRotation());
        });
    }

    //set the event handler on the rotateLeft button
    public void rotateLeft(){
        view.rotateLeft.setOnAction(event-> {
            //rotate the sockets of the ButtonTile
            ButtonTile.rotateLeft();
            // the rotation of the ButtonTile will be updated
            // rotate the image with the new rotation value
            view.rotate(ButtonTile.getRotation());

        });
    }






    // get neighbours (north, east, south, west) of a tile
    public Tile getNorthTile(int x, int y){
        Tile north = board.matrix[x][y-1];
        return north;
    }
    public Tile getEastTile(int x, int y){
        Tile east = board.matrix[x+1][y];
        return east;
    }
    public Tile getSouthTile(int x, int y){
        Tile south = board.matrix[x][y+1];
        return south;
    }
    public Tile getWestTile(int x, int y){
        Tile west = board.matrix[x-1][y];
        return west;
    }



    }


}