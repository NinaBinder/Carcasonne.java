package com.example;

import com.example.Tile;
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
        board = new Tile[3][3];
        board[1][1] = originalTile;
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
    //????
    public void setTile(int x, int y) {
        int relX = x - originalTileX;
        int relY = y - originalTileY;
        if (isWithinBoard(relX, relY)) {
            this.board[x][y] = new Tile();
        }
        else {
            if(relX>board.length){
                extendsBoardEast();
            }
            if(relX<0){
                extendsBoardWest();
            }
            if(relY>board[0].length){
                extendsBoardSouth();
            }
            if(relY<0){
                extendsBoardNorth();
            }

        }
    }

    //berechnet die absoluten Koordinaten der Kachel durch
    //Addieren der relativen Koordinaten zu den Koordinaten der Ursprungskachel (originTileX bzw. originTileY)
    public Tile get_withRelativeReference(int relX, int relY) {
        int absX = originalTileX + relX;
        int absY = originalTileY + relY;
        return getTile(absX, absY);
    }

    public void set_withRelativeReference(int relX, int relY) {
        int absX = originalTileX + relX;
        int absY = originalTileY + relY;
        //setTile(absX, absY);
    }

    public int getOriginalTileX() {
        return originalTileX;
    }

    public int getOriginalTileY() {
        return originalTileY;
    }
    //prüfe, ob eine gegebene Zelle mit den relativen Koordinaten relX und relY
    //innerhalb der Grenzen des Arrays liegt
    public boolean isWithinBoard(int relX, int relY) {
        return relX >= 0 && relX < board.length && relY >= 0 && relY < board[0].length;
    }

    //Konvertieren von absoluten zu relativen Positionen
    public int[] convertToRelative(int absX, int absY) {
        int[] relative_coordinates = { absX - originalTileX, absY - originalTileY };
        return relative_coordinates;
    }

    public void extendsBoardNorth() {
        int xSize = board.length;
        int ySize = board[0].length;
        extendsBoard(xSize, ySize+1,"north");
    }

    public void extendsBoardSouth() {
        int xSize = board.length;
        int ySize = board[0].length;
        extendsBoard(xSize, ySize+1,"south");
    }

    public void extendsBoardWest() {
        int xSize = board.length;
        int ySize = board[0].length;
        extendsBoard(xSize+1, ySize,"west");
    }

    public void extendsBoardEast() {
        int xSize = board.length;
        int ySize = board[0].length;
        extendsBoard(xSize+1, ySize,"east");
    }
    private void extendsBoard(int width, int height, String extend_direction) {

        Tile[][] newBoard = new Tile[width][height];
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++){
                switch (extend_direction) {
                    case "north":
                        newBoard[x][y+1] = board[x][y];
                        break;
                    case "south":
                        newBoard[x][y] = board[x][y];
                        break;
                    case "west":
                        newBoard[x+1][y] = board[x][y];
                        break;
                    case "east":
                        newBoard[x][y] = board[x][y];
                        break;
                }
            }

        }
        this.board = newBoard;
    }

    //Die Klasse enthält Methoden: (a) zum Setzen und Lesen von Feldern über absolute
    //und relative Verweise; (b) Zum Überprüfen, ob ein relativer Verweis innerhalb des
    //Felds liegt; (c) Methoden zum konvertieren von absoluten zu relativen

}