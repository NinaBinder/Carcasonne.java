package com.example;

import javafx.scene.image.Image;


//Die Klasse Tile beschreibt die einzelnen Spielkarten, welche in Summe das Spielfeld
//ergeben.

// (e) Platzierung einer Spielfigur.Mittels Methoden kann:
// (a) das Feld nach links und rechts rotiert werden;
// (b) kann ein ImageView Node erzeugt werden mit spezifizieter Dimension und richtiger Rotation;
// (d) kann überprüft werden ob das Feld an die Seite eines anderen passt

public class Tile {
    TileLibrary library= new TileLibrary();
    int relX;
    int relY;
    int rotation;
    boolean gamePiece;
    Socket[] northEdge;
    Socket[] eastEdge;
    Socket[] southEdge;
    Socket[] westEdge;
    String  entry;

    public Image getImage(){
        return library.getImage(entry);
    }

    public Socket[] getSockets(){
        return library.map.get(entry).sockets;
    }

    // Jede Tile speichert:
    // (a) seine relative Position innerhalb des Spielfelds als x und y ab;
    // (b) die eigene Rotation;
    // (c) das assoziierte Bild;
    // (d) und ein Array, das die Konfiguration der Seiten beschreibt (Socket Array),
    public Tile(int relX,int relY, int rotation, String entry, boolean gamePiece){
        this.relX= relX;
        this.relY= relY;
        this.rotation= rotation;
        this.entry= entry;
        this.gamePiece = gamePiece;
        // initialise the edge arrays.
        for(int i = 0; i<3; i++){
            this.northEdge[i] = getSockets()[i];
        }
        for(int i = 0; i<3; i++){
            this.eastEdge[i] = getSockets()[i+3];
        }
        for(int i = 0; i<3; i++){
            this.southEdge[i] = getSockets()[i+6];
        }
        for(int i = 0; i<3; i++){
            this.westEdge[i] = getSockets()[i+9];
        }
    }

    //placement of a character on this tile


    //Setter
    /** Set the current rotation of the tile, e.g. "links" */
    //TODO: rotate sockets as well!!!
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    //Getter
    /** Get the current rotation of the tile, e.g. "links" */
    public int getRotation() {
        return rotation;
    }

    // method to rotate the file
    public void rotate(){
        //setRotation((getRotation()=="links")?"rechts":"links");
    }


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

