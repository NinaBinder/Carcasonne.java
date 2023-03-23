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
    LibraryEntry entry;

    public Image getImage(){
        return entry.img;
    }

    public Socket[] getSockets(){
        return entry.sockets;
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
        this.entry= library.map.get(entry);
        this.gamePiece = gamePiece;
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

}

