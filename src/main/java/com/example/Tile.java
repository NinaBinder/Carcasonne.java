package com.example;

import javafx.scene.image.Image;


/**
 The Tile class describes the individual playing cards, which together make up the playing field
 result*/
public class Tile {
    static TileLibrary library = new TileLibrary();
    private int relX;
    private int relY;
    private double rotation;
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

    //get the current rotation of the tile
    public Double getRotation() {
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

    public void setEntry(String newEntry){
        entry = newEntry;
    }

    public Tile(int relX, int relY, double rotation, String entry, boolean gamePiece){
        this.relX = relX;
        this.relY = relY;
        this.rotation= rotation;
        this.entry = entry;
        this.gamePiece = gamePiece;

        this.northEdge = library.map.get(entry).getComponent()[0];
        this.eastEdge = library.map.get(entry).getComponent()[1];
        this.southEdge = library.map.get(entry).getComponent()[2];
        this.westEdge = library.map.get(entry).getComponent()[3];
    }

    /** rotate the whole tile 90 degrees to the right. Each edge of socket array will be rotated
     * For example the sockets in north will now be in east */
    public void rotateRight() {

        LibraryEntry.Component tempEdge = this.northEdge;
        northEdge = this.westEdge;
        westEdge = this.southEdge;
        southEdge = this.eastEdge;
        eastEdge = tempEdge;
        this.rotation += 90;

        System.out.println("new North:" + northEdge);
        System.out.println("new East:" + eastEdge);
        System.out.println("new South:" + southEdge);
        System.out.println("new West:" + westEdge);
    }
    /** rotate the whole tile 90 degrees to the left. Each edge of socket array will be rotated
     * For example the sockets in north will now be in west */
    public void rotateLeft() {
        LibraryEntry.Component tempEdge = this.northEdge;
        northEdge = this.eastEdge;
        eastEdge = this.southEdge;
        southEdge = this.westEdge;
        westEdge = tempEdge;
        this.rotation -= 90;

        System.out.println("new North:" + northEdge);
        System.out.println("new East:" + eastEdge);
        System.out.println("new South:" + southEdge);
        System.out.println("new West:" + westEdge);
    }
   public void updateEdges(Tile currentTile){
        this.northEdge = library.map.get(currentTile.getEntry()).getComponent()[0];
        this.eastEdge = library.map.get(currentTile.getEntry()).getComponent()[1];
        this.southEdge = library.map.get(currentTile.getEntry()).getComponent()[2];
        this.westEdge = library.map.get(currentTile.getEntry()).getComponent()[3];
   }



    public LibraryEntry.Component getNorthEdge() {
        return northEdge;
    }
    public void setRelX(int x){
        relX = x;
    }
    public void setRelY(int y){
        relY = y;
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