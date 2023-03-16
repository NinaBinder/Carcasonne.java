package com.example.demo_carcassonne;

//Die Klasse Tile beschreibt die einzelnen Spielkarten, welche in Summe das Spielfeld
//ergeben.
//Jede Tile speichert:
// (a) seine relative Position innerhalb des Spielfelds als x und y ab;
//(b) die eigene Rotation;
// (c) das assoziierte Bild; (d) und ein Array, das die
//Konfiguration der Seiten beschreibt (Socket Array), (e) Platzierung einer Spielfigur.
//Mittels Methoden kann: (a) das Feld nach links und rechts rotiert werden; (b) kann ein
//ImageView Node erzeugt werden mit spezifizieter Dimension und richtiger Rotation;
//(d) kann überprüft werden ob das Feld an die Seite eines anderen passt

import javafx.scene.shape.Rectangle;


import java.util.ArrayList;


public class Tile {
    // relative position as (x,y) inside the game field
    private int x;
    private int y;
    // rotation of the tile, e.g. links, rechts etcs (since they are just relative positions, will they change dynamically?
    // arrayList not necessary here?)
    private String rotation;

    //the associated image


    // array of the socket array
    Socket[] sockets;



    //placement of a character on this tile


    //Setter
    /** Set the current rotation of the tile, e.g. "links" */
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    //Getter
    /** Get the current rotation of the tile, e.g. "links" */
    public String getRotation() {
        return rotation;
    }




    // constructor
    public Tile(){
        rotation = "links";

    }

    // method to rotate the file
    public void rotate(){
        setRotation((getRotation()=="links")?"rechts":"links");
    }


    //kann ein
    //ImageView Node erzeugt werden mit spezifizieter Dimension und richtiger Rotation;


    //check whether the field fits on the side of another
    public Boolean fit(Tile another){
        //  [C0, C0, C0] fit [C1, C1, C1] -> tiles showing a city
    }

}



}
