package com.example;

import javafx.scene.image.Image;


//Die Klasse Tile beschreibt die einzelnen Spielkarten, welche in Summe das Spielfeld
//ergeben.

// (e) Platzierung einer Spielfigur.Mittels Methoden kann:
// (a) das Feld nach links und rechts rotiert werden;
// (b) kann ein ImageView Node erzeugt werden mit spezifizieter Dimension und richtiger Rotation;
// (d) kann überprüft werden ob das Feld an die Seite eines anderen passt

public class Tile {

    int relX;
    int relY;
    int rotation;
    Image image;
    Socket[] sockets;

    // Jede Tile speichert:
    // (a) seine relative Position innerhalb des Spielfelds als x und y ab;
    // (b) die eigene Rotation;
    // (c) das assoziierte Bild;
    // (d) und ein Array, das die Konfiguration der Seiten beschreibt (Socket Array),
   /* public Tile(int relX,int relY, int rotation, Image image, Socket[] sockets){
        this.relX= relX;
        this.relY= relY;
        this.rotation= rotation;
        this.image=image;
        this.sockets= sockets;
    }*/


    //placement of a character on this tile


    //Setter
    /** Set the current rotation of the tile, e.g. "links" */
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


    //kann ein
    //ImageView Node erzeugt werden mit spezifizieter Dimension und richtiger Rotation;


    //check whether the field fits on the side of another
    public Boolean fit(Tile another){
        //  [C0, C0, C0] fit [C1, C1, C1] -> tiles showing a city
        return true;
    }

}

