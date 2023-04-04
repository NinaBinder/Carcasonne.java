package com.example;

import javafx.scene.image.Image;


/**
 The Tile class describes the individual playing cards, which together make up the playing field
 result*/
public class Tile {
    TileLibrary library;
    View view;
    private int relX;
    private int relY;
    private int rotation;
    boolean gamePiece;
    private LibraryEntry.Component northEdge;
    private LibraryEntry.Component eastEdge;
    private LibraryEntry.Component southEdge;
    private LibraryEntry.Component westEdge;
    private String entry;


    /** getter */
    // get the associated image of the tile
    public Image getImage(){
        return library.getImage(entry);
    }

    // get the socket array of the tile
    /*public Component getComponent(){
        return library.map.get(entry);
    }*/

    //get the current rotation of the tile
    public int getRotation() {
        return rotation;
    }

    public int getRelX() {
        return relX;
    }

    public int getRelY() {
        return relY;
    }

    public String getEntry() {
        return entry;
    }



    public Tile(int relX, int relY, int rotation, String entry, boolean gamePiece){
        this.view = new View();
        this.library = new TileLibrary();
        this.relX= relX;
        this.relY= relY;
        this.rotation= rotation;
        this.entry= entry;
        this.gamePiece = gamePiece;

        this.northEdge = library.map.get(entry).getComponent()[0];
        this.eastEdge = library.map.get(entry).getComponent()[1];
        this.southEdge = library.map.get(entry).getComponent()[2];
        this. westEdge = library.map.get(entry).getComponent()[3];

       // rotateRight();
    }



    /** rotate the whole tile 90 degrees to the right. Each edge of socket array will be rotated
     * For example the sockets in north will now be in east */
   /* public void rotateRight() {
        view.rotateRight.setOnAction(event-> {
                    LibraryEntry.Component tempEdge= this.northEdge;
                    northEdge= this.westEdge;
                    westEdge = this.southEdge;
                    southEdge = this.eastEdge;
                    eastEdge = tempEdge;
                    this.rotation+=90;
                    view.rotateRight();)}

    }*/



    /** rotate the whole tile 90 degrees to the left. Each edge of socket array will be rotated
     * For example the sockets in north will now be in west */
    public void rotateLeft( ) {
        this.northEdge = this.eastEdge;
        this.eastEdge = this.southEdge;
        this.southEdge = this.westEdge;
        this.westEdge = this.northEdge;
        this.rotation+=270;

    }

    public LibraryEntry.Component getNorthEdge() {
        return northEdge;
    }

    public LibraryEntry.Component getEastEdge() {
        return eastEdge;
    }

    public LibraryEntry.Component getSouthEdge() {
        return southEdge;
    }

    public LibraryEntry.Component getWestEdge() {
        return westEdge;
    }
}