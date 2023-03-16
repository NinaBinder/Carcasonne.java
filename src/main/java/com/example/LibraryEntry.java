package com.example;

import javafx.scene.image.Image;

public class LibraryEntry {
    //Enthält die Beschreibung eines Spielfelds, dies wird zur Erzeugen der Tile Objekte genutzt.
    //ein LibraryEntry enthält:
    // (a) String mit url zur Ablage des Bilds;
    String imagePath = new String();
    //imagePath = "file:src/fields/A.png";
    // (b) eine Beschreibung der Sockets;

    // (c) eine Instanz des Bildes als JavaFX Node (Image).
    Image img = new Image("file:src/fields/A.png");


    //Der Konstruktor nimmt (mindestens) einen String mit der Bild-url und ein Arbitrary
    //Number Argument, das die Features eines Feldes beschreibt, daraus werden im
    //Konstruktor die Sockets erzeugt.

    public LibraryEntry(){

    }
}
