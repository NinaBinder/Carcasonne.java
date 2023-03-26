package com.example;

import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Controller {
    View view;
    Model model;
    TileLibrary library = new TileLibrary();
    ArrayList<Tile> allTiles;
    Board board= new Board();

    /** deck: An Array List filled with all possible Entries
     in order to get the images of that entry: deck(index).img
     */
    ArrayList<LibraryEntry> deck;

    public Controller(View view, Model model){
        this.view = view;
        this.model= model;
        //this.allTiles = new ArrayList<Tile>();
        this.deck= new ArrayList<LibraryEntry>();

        //Filling the deck with all possible cards (right amount of each card included)
        deck.add(library.map.get("C"));
        deck.add(library.map.get("G"));
        deck.add(library.map.get("Q"));
        deck.add(library.map.get("T"));
        deck.add(library.map.get("X"));

        for (int i = 0; i < 2; i++) {
            deck.add(library.map.get("A"));
            deck.add(library.map.get("F"));
            deck.add(library.map.get("I"));
            deck.add(library.map.get("M"));
            deck.add(library.map.get("O"));
            deck.add(library.map.get("S"));
        }
        for (int i = 0; i < 3; i++) {
            deck.add(library.map.get("H"));
            deck.add(library.map.get("J"));
            deck.add(library.map.get("K"));
            deck.add(library.map.get("L"));
            deck.add(library.map.get("N"));
            deck.add(library.map.get("P"));
            deck.add(library.map.get("R"));
            deck.add(library.map.get("D"));
        }
        for (int i = 0; i < 4; i++) {
            deck.add(library.map.get("B"));
            deck.add(library.map.get("W"));
        }
        for (int i = 0; i < 5; i++) {
            deck.add(library.map.get("E"));
        }
        for (int i = 0; i < 8; i++) {
            deck.add(library.map.get("U"));
        }
        for (int i = 0; i < 9; i++) {
            deck.add(library.map.get("V"));
        }

        updateBoard(board);
        changeDrawButtonImage();
        rotateLeft();
        rotateRight();
        DragandDrop();
    }
    public void updateBoard(Board board) {
        //go through board matrix for every tile do this:
        for (int x = 0; x < board.matrix.length; x++) {
            for (int y = 0; y < board.matrix.length; y++) {

                //get every Tiles Entry and set it to possibly new entry
                //image view für tile x,y
                Image tileImage = board.getTile(x, y).getImage();
                ImageView updateImageView = new ImageView(tileImage);
                updateImageView.setFitWidth(view.getIMAGESIZE());
                updateImageView.setFitHeight(view.getIMAGESIZE());

                // wo soll es hinzugefügt werden?
                updateImageView.setX((x * view.getIMAGESIZE()));
                updateImageView.setY(y * view.getIMAGESIZE());

                view.getRoot().getChildren().add(updateImageView);

            }
        }
    }
    public void changeDrawButtonImage(){
        //TODO: nochmal ziehen, falls tile nicht passt
        // TODO: nach dem ziehen, soll tile aus deck gelöscht werden
        //put image on label nicht auf button / disbale button after on drück es sei denn es passt nicht dann mach noch mal
        view.getDrawCardButton().setOnAction(event->{

            view.buttonImageView.setFitWidth(100);
            view.buttonImageView.setFitHeight(100);

            int index = (int)(Math.random() * deck.size());

            System.out.println(deck.get(index).img);
            view.newButtonImage = deck.get(index).img;

            view.getButtonImageView().setImage(view.newButtonImage);
            view.getDrawCardButton().setGraphic(view.getButtonImageView());
            //TODO: disable button after use (enable again wenn es passt nicht)
            //view.getDrawCardButton().disarm();
        });
    }

    // drag the tile which is shown on the button to the board
    //TODO: finish drop
    public void DragandDrop(){
        //Source of the drag gesture
        Button source = view.drawCardButton;
        //react of drag
        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            System.out.println(view.getNewButtonImage());
            content.putImage(view.getNewButtonImage());
            db.setContent(content);
            event.consume();
        });
        // target of drag gesture = drop
        final BorderPane target = view.getBorder();

        // accept possible drop
        target.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        }
        );

        target.setOnDragDropped( event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            for(Tile tile: allTiles){
                // get neighbours (north, east, south, west) of a tile

               if(tile.getEntry() == "EMPTY" && tile.TileMatch(getNorthTile(tile.getRelX(),tile.getRelY()),
                                                                getSouthTile(tile.getRelX(),tile.getRelY()),
                                                                getEastTile(tile.getRelX(),tile.getRelY()),
                                                                getWestTile(tile.getRelX(),tile.getRelY()))){
                   EventTarget eventTarget = event.getTarget();
                   tile = (Tile) eventTarget;
                   board.set_withRelativeReference(tile.getRelX(),tile.getRelY(),tile.getEntry());
                   updateBoard(board);

               }
            }
            event.setDropCompleted(success);
            event.consume();
        });

    }




    //set the event handler on the rotateRight button
    public void rotateRight(){
        view.rotateRight.setOnAction(event-> {
            //rotate the sockets of the ButtonTile
            //ButtonTile.rotateRight();
            //rotate the image
            view.rotateRight();
        });
    }

    //set the event handler on the rotateLeft button
    public void rotateLeft(){
        view.rotateLeft.setOnAction(event-> {
            //rotate the sockets of the ButtonTile
            //ButtonTile.rotateLeft();
            //rotate the image
            view.rotateLeft();

        });
    }


    // get neighbours (north, east, south, west) of a tile
    public Tile getNorthTile(int relX, int relY){
        Tile north = board.getTile_viaRelative(relX,relY-1);
        return north;
    }
    public Tile getEastTile(int relX, int relY){
        Tile east = board.getTile_viaRelative(relX+1,relY);
        return east;
    }
    public Tile getSouthTile(int relX, int relY){
        Tile south = board.getTile_viaRelative(relX,relY+1);
        return south;
    }
    public Tile getWestTile(int relX, int relY){
        Tile west = board.getTile_viaRelative(relX-1,relY);
        return west;
    }



    }




