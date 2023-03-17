package com.example.demo_carcassonne;

import javafx.scene.shape.Rectangle;

public class Board {

    //Klasse, die das Spielfeld repräsentiert. Dieses wächst mit dem Anlegen neuer Felder.
    //Das Board speichert die Feldkarten in einem zweidimensionalen Array.
    private Tile[][] board;
    // Die Position des Startfelds (OriginTile) innerhalb des Arrays wird in zwei Variablen originTileX und
    //originTileY gespeichert. So kann auf Positionen innerhalb des Arrays mit einem
    //relativen Verweis operiert werden, wobei die relative Position 0/0 immer das Startfeld
    //beschreibt.
    private int originalTileX;
    private int originalTileY;

    //Konstruktor, der ein Tile-Objekt als Argument verwendet,
    //um es als Ursprungskachel des Spielbretts festzulegen
    public Board(Tile originalTile) {
        board = new Tile[1][1];
        board[0][0] = originalTile;
        originalTileX = 0;
        originalTileY = 0;
    }

    //berechnet die relativen Koordinaten der Kachel durch
    //Subtrahieren der Koordinaten der Ursprungskachel (originTileX und originTileY) von den Eingabekoordinaten x bzw. y
    public Tile getTile(int x, int y) {
        int relX = x - originalTileX;
        int relY = y - originalTileY;
        if (isWithinBoard(relX, relY)) {
            return board[relY][relX];
        } else {
            return null;
        }
    }

    //berechnet die absoluten Koordinaten der Kachel durch
    //Addieren der relativen Koordinaten zu den Koordinaten der Ursprungskachel (originTileX bzw. originTileY)
    public Tile getRelativeTile(int relX, int relY) {
        int absX = originalTileX + relX;
        int absY = originalTileY + relY;
        return getTile(absX, absY);
    }

    public int getOriginalTileX() {
        return originalTileX;
    }

    public int getOriginialTileY() {
        return originalTileY;
    }
    //prüfe, ob eine gegebene Zelle mit den relativen Koordinaten relX und relY
    //innerhalb der Grenzen des Arrays liegt
    public boolean isWithinBoard(int relX, int relY) {
        return relX >= 0 && relX < board[0].length && relY >= 0 && relY < board.length;
    }
    //Konvertieren von absoluten zu relativen Positionen
    public int[] convertToRelative(int absX, int absY) {
        return new int[] { absX - originalTileX, absY - originalTileY };
    }

    //Konvertieren von relativen zu absoluten Positionen
    public int[] convertToAbsolute(int relX, int relY) {
        return new int[] { relX + originalTileX, relY + originalTileY };
    }

}

    //Die Klasse enthält Methoden: (a) zum Setzen und Lesen von Feldern über absolute
    //und relative Verweise; (b) Zum Überprüfen, ob ein relativer Verweis innerhalb des
    //Felds liegt; (c) Methoden zum konvertieren von absoluten zu relativen

}
