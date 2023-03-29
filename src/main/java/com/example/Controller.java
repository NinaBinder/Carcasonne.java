package com.example;

import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class Controller {
    View view;
    Model model;
    Tile tile;
    TileLibrary library = new TileLibrary();
    ArrayList<Tile> allTiles;
    HashMap<Position, ImageView> everyTile = new HashMap<Position, ImageView>();

    /** deck: An Array List filled with all possible Entries
     in order to get the images of that entry: deck(index).img
     */
    ArrayList<LibraryEntry> deck;

    public Controller(View view, Model model){
        this.view = view;
        this.model= model;
        this.allTiles = new ArrayList<Tile>();
        this.deck= new ArrayList<LibraryEntry>();

        //updateBoard(board);

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

        changeDrawButtonImage();
        rotateLeft();
        rotateRight();
        DragandDrop();
    }
    public void updateView(Image tileImage, int x, int y){

        // new Hashmap which safes image view of all tiles added to board with key: relx rely
        Position tilePosition= new Position(x,y);

        if(everyTile.containsKey(tilePosition)){
            everyTile.get(tilePosition).setImage(tileImage);

        }else {

            ImageView startimageView = new ImageView(tileImage);

            everyTile.put(tilePosition, startimageView);

            startimageView.setFitWidth(view.getIMAGESIZE());
            startimageView.setFitHeight(view.getIMAGESIZE());
        }
        everyTile.get(tilePosition).setX((x * view.getIMAGESIZE()));
        everyTile.get(tilePosition).setY(y * view.getIMAGESIZE());
        //System.out.println(startimageView);
        view.getRoot().getChildren().add(everyTile.get(tilePosition));
        //view.getScrollPane().setContent(startimageView);
    }
    public void updateBoard(Board board) {

        view.getRoot().getChildren().clear();
        //go through board matrix for every tile do this:
        for (int relX = 0; relX < board.matrix.length; relX++) {
            for (int relY = 0; relY < board.matrix.length; relY++) {


                //get every Tiles Entry and set it to possibly new entry
                //image view für tile x,y
                Image tileImage = board.getTile(relX, relY).getImage();

                everyTile.put(new Position(relX,relY),new ImageView(tileImage));

                updateView(tileImage, relX, relY);
                //model.isPatternClosed(model.getBoard().getTile(1,1));
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

            //System.out.println(deck.get(index).img);
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
        //Source of the drag gesture is the Button
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
                    if(event.getGestureSource() != target && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
            event.consume();
        }
        );

        target.setOnDragDropped( event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            //System.out.println(event.getTarget());
            //target is a specific image view

            Position targetPos;
            if(event.getTarget() instanceof ImageView){

                for(Position pos : everyTile.keySet()){
                    //targetPos= new Position((int)event.getX(), (int)event.getY());

                    if(everyTile.get(pos).equals(event.getTarget())){
                        targetPos = pos;
                        everyTile.put(targetPos,new ImageView(db.getImage()));
                        updateView(db.getImage(), targetPos.getX(), targetPos.getY());

                        //TODO: tiles match?
                        if(tile.tileMatch( getNorthTile(targetPos.getX(),targetPos.getY()),
                                getSouthTile(targetPos.getX(), targetPos.getY()),
                                getEastTile(targetPos.getX(), targetPos.getY()),
                                getWestTile(targetPos.getX(), targetPos.getY())) ) {

                        }
                        else{
                            System.out.println("not matching");
                        }

                    }
                }

                success= true;
            }
            event.setDropCompleted(success);
            event.consume();
        });


            //TODO: where to add the new tile to / maybe use hashmasp
            //everyTile.keySet() all position zu denen es eintrag gibt
            // everyTile.values() alle imageviews in hashmap
            /*for(Tile tile: ){
                //add the Tiles to the hashmap if it is not already added


                // get neighbours (north, east, south, west) of a tile

                //move maybe to model like this
                //if(model.trysetTile(position)== true
               if(Objects.equals(tile.getEntry(), "EMPTY") && tile.TileMatch(getNorthTile(tile.getRelX(),tile.getRelY()),
                                                                getSouthTile(tile.getRelX(),tile.getRelY()),
                                                                getEastTile(tile.getRelX(),tile.getRelY()),
                                                                getWestTile(tile.getRelX(),tile.getRelY()))){
                   EventTarget eventTarget = event.getTarget();
                   tile = (Tile) eventTarget;
                   model.getBoard().set_withRelativeReference(tile.getRelX(),tile.getRelY(),tile.getEntry());
                   updateBoard(model.getBoard());
                   System.out.println("board gets updated");*/

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


    //TODO: ins model
    // get neighbours (north, east, south, west) of a tile
    public Tile getNorthTile(int relX, int relY){
        Tile north = model.getBoard().getTile_viaRelative(relX,relY-1);
        return north;
    }
    public Tile getEastTile(int relX, int relY){
        Tile east = model.getBoard().getTile_viaRelative(relX+1,relY);
        return east;
    }
    public Tile getSouthTile(int relX, int relY){
        Tile south = model.getBoard().getTile_viaRelative(relX,relY+1);
        return south;
    }
    public Tile getWestTile(int relX, int relY){
        Tile west = model.getBoard().getTile_viaRelative(relX-1,relY);
        return west;
    }

}