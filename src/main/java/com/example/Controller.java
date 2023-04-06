package com.example;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.util.*;

public class Controller {
    View view;
    Model model;
    TileLibrary library = new TileLibrary();
    HashMap<Position, ImageView> everyTile = new HashMap<Position, ImageView>();
    double rotation;


    /** deck: An Array List filled with all possible Entries
     in order to get the images of that entry: deck(index).img
     */
    ArrayList<LibraryEntry> deck;

    public Controller(View view, Model model){
        this.view = view;
        this.model= model;
        this.deck= new ArrayList<LibraryEntry>();

        fillDeck();
        changeDrawButtonImage();
        rotateLeft();
        rotateRight();
        computer();
        DragandDrop();
    }


    public void updateView(Tile currentTile, int absX, int absY){

        // new Hashmap which safes image view of all tiles added to board with key: relx rely
        Position tilePosition= new Position(model.getBoard().convertToRelativeX(absX), model.getBoard().convertToRelativeY(absY));

        if(everyTile.containsKey(tilePosition)){
            everyTile.get(tilePosition).setImage(currentTile.getImage());

        }else {

            ImageView startimageView = new ImageView(currentTile.getImage());

            startimageView.setRotate(currentTile.getRotation());
            everyTile.put(tilePosition, startimageView);

            startimageView.setFitWidth(view.getIMAGESIZE());
            startimageView.setFitHeight(view.getIMAGESIZE());

        }
        everyTile.get(tilePosition).setX((absX * view.getIMAGESIZE()));
        everyTile.get(tilePosition).setY(absY * view.getIMAGESIZE());
        //System.out.println(startimageView);
        view.getRoot().getChildren().add(everyTile.get(tilePosition));
        //view.getScrollPane().setContent(startimageView);
    }
    public void updateBoard(Board board) {

        System.out.println("updateBoard");
        view.getRoot().getChildren().clear();
        //go through board matrix for every tile do this:
        for (int absX = 0; absX < board.getWidth(); absX++) {
            for (int absY = 0; absY < board.getHeight(); absY++) {

                //current Tile we are working with rn
                Tile currentTile = board.getAbsoluteTile(absX,absY);
                if(currentTile== null){
                    continue;
                }

                updateView(currentTile, absX, absY);
            }
        }
    }
    public LibraryEntry pickACardAnyCard(){

        int index = (int)(Math.random() * deck.size());
        LibraryEntry nextEntry = deck.get(index);
        //removing the card from deck
        deck.remove(index);

        return nextEntry;
    }
    public void changeDrawButtonImage(){
        //TODO: nochmal ziehen, falls tile nicht passt
        view.getDrawCardButton().setOnAction(event->{

            view.buttonImageView.setFitWidth(100);
            view.buttonImageView.setFitHeight(100);



            view.newButtonImage =  pickACardAnyCard().image;
            model.setNextEntry( pickACardAnyCard());
            view.getButtonImageView().setImage(view.newButtonImage);
            view.getDrawCardButton().setGraphic(view.getButtonImageView());
        });
    }

    // drag the tile which is shown on the button to the board
    public void DragandDrop(){
        //Source of the drag gesture is the Button
        Button source = view.drawCardButton;
        //react of drag
        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(view.getNewButtonImage());
            db.setContent(content);
            event.consume();
        });
        // target of drag gesture = drop
        final Pane target = view.getRoot();

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

            Position targetPos = null;
            if(event.getTarget() instanceof ImageView){

                for(Position pos : everyTile.keySet()){
                    if(everyTile.get(pos).equals(event.getTarget())){
                        targetPos = pos;

                    }
                }
                if(targetPos!=null && model.tryPlaceTile(targetPos.getX(), targetPos.getY())){
                    updateBoard(model.getBoard());
                }
            }
            event.setDropCompleted(true);
            event.consume();
            updateBoard(model.getBoard());
        });
    }

    public void computer(){

        view.coputerTurn.setOnAction( actionEvent -> {
             model.computerTurn();
                }
        );
    }

    //set the event handler on the rotateRight button
    public void rotateRight(){
        view.rotateRight.setOnAction(event-> {
            //rotate the sockets of the ButtonTile
            //ButtonTile.rotateRight();
            //rotate the image
            //view.getButtonImageView().setRotate(getRotation()+90);
            view.rotateRight();
            this.rotation= view.getButtonImageView().getRotate();
        });
    }
    public Double getRotation(){
        return rotation;
    }

    //set the event handler on the rotateLeft button
    public void rotateLeft(){
        //TODO: fix getRotation
        view.rotateLeft.setOnAction(event-> {
            //rotate the sockets of the ButtonTile
            //ButtonTile.rotateLeft();
            //rotate the image
            view.rotateLeft();

        });
    }
    public void fillDeck(){
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

    }

    public TileLibrary getLibrary() {
        return library;
    }

}