package com.example;

import javafx.scene.control.Button;
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
    HashMap<Position, ImageView> everyImageView = new HashMap<>();
    double rotation;
    Tile currentTile;
    String nextEntry;


    /** deck: An Array List filled with all possible Entries
     in order to get the images of that entry: deck(index).img
     */
    ArrayList<LibraryEntry> deck;

    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
        this.deck = new ArrayList<>();
        this.rotation = 0.0;
        rotation = view.buttonImageView.getRotate();
        this.currentTile = new Tile(0,0,rotation, "EMPTY",false);

        fillDeck();
        changeDrawButtonImage();
        rotateRight();
        rotateLeft();
        computer();
        DragAndDrop();

    }
    public void updateBoard(Board board) {
        System.out.println("updateBoard");
       view.getRoot().getChildren().clear();

        //go through board matrix
        for (int absX = 0; absX < board.getWidth(); absX++) {
            for (int absY = 0; absY < board.getHeight(); absY++) {

                //current Tile at the current position in beard we are at
                Tile currentTile = board.getAbsoluteTile(absX,absY);

                //ignore Tiles that are null
                if(currentTile == null){
                    continue;
                }

                //current Position ( a position safes a relative X and relative Y value
                //which is why we first have to convert the absolute values to relative
                int relX = model.getBoard().convertToRelativeX(absX);
                int relY = model.getBoard().convertToRelativeY(absY);

                Position currentPosition = new Position(relX, relY);

                //everTile is a hashmap which saves all ImageView(value) of all tiles at positions(key)
                if(everyImageView.containsKey(currentPosition)){
                    //if there is an Imageview at the current position already, set the Imageview at the current Position to the Image of the current Tile
                    everyImageView.get(currentPosition).setImage(currentTile.getImage());
                    //add rotation of the tile to the imageview
                    everyImageView.get(currentPosition).setRotate(currentTile.getRotation());

                }else {
                    //if there is no Imageview saved in everTile hashmap at the current Position,
                    //then create a new Imageview, set it to the current Tiles Image and put the new hashmap entry (new Imageview, current Position) in everyTile
                    ImageView newImageView = new ImageView(currentTile.getImage());

                    newImageView.setRotate(currentTile.getRotation());
                    everyImageView.put(currentPosition, newImageView);
                    newImageView.setFitWidth(view.getIMAGESIZE());
                    newImageView.setFitHeight(view.getIMAGESIZE());
                }
                //every ImageView displayed on the board has the same size IMAGE SIZE = 200
                everyImageView.get(currentPosition).setX((absX * view.getIMAGESIZE()));
                everyImageView.get(currentPosition).setY(absY * view.getIMAGESIZE());

                //finally display all the ImageView of everyTile on the board at the right Position in the root Pane in View
                view.getRoot().getChildren().add(everyImageView.get(currentPosition));
            }
        }
    }

    /**
     * represents the act of picking a random card from a deck of cards
     * in our case the deck of cards is an array of LibraryEntries, which are used to display a new Tile on the board
     * @return a random "card" from the possible 71 cards in carcassone
     */
    public LibraryEntry pickACardAnyCard(){
        //index is a random value between 0 and 72 (size of array deck)
        int index = (int)(Math.random() * deck.size());
        //nextEntry is picked from deck at random position(index)
        LibraryEntry nextEntry = deck.get(index);
        String stringNewEntry = library.getNameOfEntry(nextEntry);

        //default mode
        view.getButtonImageView().setRotate(0);
        currentTile = new Tile(0,0, 0 ,stringNewEntry,false);
        rotation = 0;
        System.out.println(currentTile.getEntry());

        //removing the entry from deck
        deck.remove(index);
        return nextEntry;
    }

    /**
     * when pressing the changeDrawButton, the pickACardAnyCard() method activates, providing a random LibraryEntry
     * the image saved in this entry, is now displayed on the button,
     * this is necessary as the image displayed on the button is needed for the drag and drop
     */
    public void changeDrawButtonImage(){
        //TODO: nochmal ziehen, falls tile nicht passt

        view.getDrawCardButton().setOnAction(event->{

            view.buttonImageView.setFitWidth(100);
            view.buttonImageView.setFitHeight(100);
            //a random entry
            LibraryEntry pickedCard = pickACardAnyCard();
            //replace the image displayed on the button
            view.newButtonImage =  pickedCard.image;
            view.getButtonImageView().setImage(view.newButtonImage);
            view.getDrawCardButton().setGraphic(view.getButtonImageView());
            model.setNextEntry(pickedCard);
        });
    }

    /**
     * the drag and drop happens in 3 steps <br>
     * step 1: Drag Detected, where we set the source for the drag and drop,
     * in our case that is the Button "drawCardButton" (more specifically the image displayed on that button) <br>
     * at the end of Drag Detected we also set the target (where can the image of the button be dragged to) of the drag and drop,
     * which is the root Pane in our case
     */
    // drag the tile which is shown on the button to the board
    public void DragAndDrop(){
        //Source of the drag gesture is this Button
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

                for(Position pos : everyImageView.keySet()){

                    if(everyImageView.get(pos).equals(event.getTarget())){
                        targetPos = pos;
                    }
                }

                if(targetPos!=null ){
                    currentTile.setRelX(targetPos.getRelX());
                    currentTile.setRelY(targetPos.getRelY());

                    if(model.tryPlaceTile(targetPos.getRelX(), targetPos.getRelY(), currentTile)){
                        updateBoard(model.getBoard());
                        //TODO: addpointsplayer
                    }

                }
            }
            event.setDropCompleted(true);
            event.consume();
        });
    }

    public void computer(){
        view.computerTurn.setOnAction(actionEvent -> model.computerTurn());
    }
    public void rotateLeft(){

        view.rotateLeft.setOnAction(event-> {
           view.rotateLeft();
           rotation = view.getButtonImageView().getRotate();
           System.out.println(rotation);
           currentTile.rotateLeft();
        });
    }
    public void rotateRight(){
        view.rotateRight.setOnAction(event-> {
            view.rotateRight();
            rotation = view.getButtonImageView().getRotate();
            System.out.println(rotation);
            currentTile.rotateRight();
        });
    }


    //set the event handler on the rotateRight button
    public Double getRotation(){
        return rotation;
    }
    public void fillDeck(){
        //Filling the deck with all possible cards (entries) (right amount of each card included)
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