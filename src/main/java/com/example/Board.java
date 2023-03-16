package com.example.demo_carcassonne;

import javafx.scene.shape.Rectangle;

public class Board {

    //Klasse, die das Spielfeld repräsentiert. Dieses wächst mit dem Anlegen neuer Felder.
    //Das Board speichert die Feldkarten in einem zweidimensionalen Array.
    Rectangle[][]cards;
    // Die Position des Startfelds (OriginTile) innerhalb des Arrays wird in zwei Variablen originTileX und
    //originTileY gespeichert. So kann auf Positionen innerhalb des Arrays mit einem
    //relativen Verweis operiert werden, wobei die relative Position 0/0 immer das Startfeld
    //beschreibt.
    int originalTileX=0;
    int originialTileY=0;
    public int getOriginalTileX() {
        return originalTileX;
    }

    public int getOriginialTileY() {
        return originialTileY;
    }

    //Die Klasse enthält Methoden: (a) zum Setzen und Lesen von Feldern über absolute
    //und relative Verweise; (b) Zum Überprüfen, ob ein relativer Verweis innerhalb des
    //Felds liegt; (c) Methoden zum konvertieren von absoluten zu relativen



}
