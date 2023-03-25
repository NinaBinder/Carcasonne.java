package com.example;

import javafx.scene.image.Image;


/**
 The Tile class describes the individual playing cards, which together make up the playing field
 result*/
public class Tile {
    TileLibrary library;
    private int relX;
    private int relY;
    private int rotation;
    boolean gamePiece;
    private Socket[] northEdge;
    private Socket[] eastEdge;
    private Socket[] southEdge;
    private Socket[] westEdge;
    private LibraryEntry entry;


    /** getter */
    // get the associated image of the tile
    public Image getImage(){
        return library.getImage(entry);
    }

    // get the socket array of the tile
    public Socket[] getSockets(){
        return library.map.get(entry).sockets;
    }

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

    public LibraryEntry getEntry(String string) {
        entry = library.map.get(string);
        return entry;
    }



    public Tile(int relX, int relY, int rotation, String entry, boolean gamePiece){
        this.library = new TileLibrary();
        this.relX= relX;
        this.relY= relY;
        this.rotation= rotation;
        this.entry= getEntry(entry);
        this.gamePiece = gamePiece;
        // initialise the edge arrays of the tile.

        for(int i = 0; i<3; i++){
            northEdge = new Socket[3];
            System.out.println(northEdge);
            this.northEdge[i] = getSockets()[i];
        }
        for(int i = 0; i<3; i++){
            this.eastEdge = new Socket[3];
            this.eastEdge[i] = getSockets()[i+3];
        }
        for(int i = 0; i<3; i++){
            this.southEdge = new Socket[3];
            this.southEdge[i] = getSockets()[i+6];
        }
        for(int i = 0; i<3; i++){
            this.westEdge = new Socket[3];
            this.westEdge[i] = getSockets()[i+9];
        }
    }



    /** rotate the whole tile 90 degrees to the right. Each edge of socket array will be rotated
     * For example the sockets in north will now be in east */
    public void rotateRight( ) {
        this.northEdge = this.westEdge;
        this.eastEdge = this.northEdge;
        this.southEdge = this.eastEdge;
        this.westEdge = this.southEdge;
        this.rotation+=90;

    }

    /** rotate the whole tile 90 degrees to the left. Each edge of socket array will be rotated
     * For example the sockets in north will now be in west */
    public void rotateLeft( ) {
        this.northEdge = this.eastEdge;
        this.eastEdge = this.southEdge;
        this.southEdge = this.westEdge;
        this.westEdge = this.northEdge;
        this.rotation+=270;

    }


    /** check if the field fits on the side of another neighbours*/
    public boolean TileMatch (Tile northTile, Tile southTile, Tile eastTile, Tile westTile){
        // check if at least one of neighbouring tiles exists. Because tiles can not be set in empty space without
        //any neighbouring
        if (northTile == null && southTile == null && eastTile == null && westTile == null){
            return false;
        }
        // check if neighbouring tiles match the given tile in case they exist
        if(northTile!=null){
            if(this.northEdge != northTile.southEdge){
                return false;
            }
        }
        if(eastTile!=null){
            if(this.eastEdge != eastTile.westEdge){
                return false;
            }
        }
        if(westTile!=null){
            if(this.westEdge != westTile.eastEdge){
                return false;
            }
        }
        if(southTile!=null){
            if(this.southEdge != southTile.northEdge){
                return false;
            }
        }
        return true;
    }

}

