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
    private String  entry;


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

    public String getEntry() {
        return entry;
    }



    public Tile(int relX, int relY, int rotation, String entry, boolean gamePiece){
        this.library = new TileLibrary();
        this.relX= relX;
        this.relY= relY;
        this.rotation= rotation;
        this.entry= entry;
        this.gamePiece = gamePiece;
        // initialise the edge arrays of the tile.
        for(int i = 0; i<3; i++){
            northEdge = new Socket[3];
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
        Socket[] north = this.northEdge;
        this.northEdge = this.westEdge;
        this.westEdge = this.southEdge;
        this.southEdge = this.eastEdge;
        this.eastEdge = north;


    }

    /** rotate the whole tile 90 degrees to the left. Each edge of socket array will be rotated
     * For example the sockets in north will now be in west */
    public void rotateLeft( ) {
        Socket[] north = this.northEdge;
        this.northEdge = this.eastEdge;
        this.eastEdge = this.southEdge;
        this.southEdge = this.westEdge;
        this.westEdge = north;


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
            if(    ! this.northEdge[0].equals(this.southEdge[2])||
                   ! this.northEdge[1].equals(this.southEdge[1])||
                   ! this.northEdge[2].equals(this.southEdge[0]) ){
                return false;
            }
        }
        if(eastTile!=null){
            if(    ! this.eastEdge[0].equals(this.westEdge[2])||
                   ! this.eastEdge[1].equals(this.westEdge[1])||
                   ! this.eastEdge[2].equals(this.westEdge[0])){
                return false;
            }
        }
        if(westTile!=null){
            if(     ! this.westEdge[0].equals(this.eastEdge[2])||
                    ! this.westEdge[1].equals(this.eastEdge[1])||
                    ! this.westEdge[2].equals(this.eastEdge[0])){
                return false;
            }
        }
        if(southTile!=null){
            if(     ! this.southEdge[0].equals(this.westEdge[2])||
                    ! this.southEdge[1].equals(this.westEdge[1])||
                    ! this.southEdge[2].equals(this.westEdge[0])){
                return false;
            }
        }
        return true;
    }

}

