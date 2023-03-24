package com.example;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Controller {
    View view= new View();

    Tile originalTile= new Tile(0,0,0,"OG", false);
    Board board= new Board(originalTile);
    TileLibrary library= new TileLibrary();


    /** deck: An Array List filled with all possible Entries
     in order to get the images of that entry: deck(index).img
     */
    ArrayList<LibraryEntry> deck= new ArrayList<LibraryEntry>();

    public Controller(View view){
        this.view= view;
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


    //check whether the field fits on the side of another
    //TODO: model
    public Boolean tilesMatch(int x, int y){
        Tile tile= board.matrix[x][y];
        Tile north = board.matrix[x][y-1];
        Tile east= board.matrix[x+1][y];
        Tile south= board.matrix[x][y+1];
        Tile west= board.matrix[x-1][y];

        if (tile.getSockets()[0].getComponent()!=north.getSockets()[8].getComponent())
            return false;
        if (tile.getSockets()[1].getComponent()!=north.getSockets()[7].getComponent())
            return false;
        if (tile.getSockets()[2].getComponent()!=north.getSockets()[6].getComponent())
            return false;

        if (tile.getSockets()[3].getComponent()!=east.getSockets()[11].getComponent())
            return false;
        if (tile.getSockets()[4].getComponent()!=east.getSockets()[10].getComponent())
            return false;
        if (tile.getSockets()[5].getComponent()!=east.getSockets()[9].getComponent())
            return false;

        if (tile.getSockets()[6].getComponent()!=south.getSockets()[2].getComponent())
            return false;
        if (tile.getSockets()[7].getComponent()!=south.getSockets()[1].getComponent())
            return false;
        if (tile.getSockets()[8].getComponent()!=south.getSockets()[0].getComponent())
            return false;

        if (tile.getSockets()[11].getComponent()!=west.getSockets()[3].getComponent())
            return false;
        if (tile.getSockets()[10].getComponent()!=west.getSockets()[4].getComponent())
            return false;
        if (tile.getSockets()[9].getComponent()!=west.getSockets()[5].getComponent())
            return false;
        return true;
    }

    public void karteZiehen(){
       // view.getkarteZiehenButton().setOnAction(event -> {
          //  model.neueKarte();
        }


}