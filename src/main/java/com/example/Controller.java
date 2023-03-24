package com.example;

public class Controller {
    View view= new View();
    Tile originalTile= new Tile(0,0,0,"OG", false);

    Board board= new Board(originalTile);

    //check whether the field fits on the side of another
    public Boolean tilesMatch(int x, int y){
        Tile tile= board.matrix[x][y];
        Tile north = board.matrix[x][y-1];
        Tile east= board.matrix[x+1][y];
        Tile south= board.matrix[x][y+1];
        Tile west= board.matrix[x-1][y];

        /*if (tile.sockets[0].getComponent()!=north.sockets[8].getComponent())
            return false;
        if (tile.sockets[1].getComponent()!=north.sockets[7].getComponent())
            return false;
        if (tile.sockets[2].getComponent()!=north.sockets[6].getComponent())
            return false;

        if (tile.sockets[3].getComponent()!=east.sockets[11].getComponent())
            return false;
        if (tile.sockets[4].getComponent()!=east.sockets[10].getComponent())
            return false;
        if (tile.sockets[5].getComponent()!=east.sockets[9].getComponent())
            return false;

        if (tile.sockets[6].getComponent()!=south.sockets[2].getComponent())
            return false;
        if (tile.sockets[7].getComponent()!=south.sockets[1].getComponent())
            return false;
        if (tile.sockets[8].getComponent()!=south.sockets[0].getComponent())
            return false;

        if (tile.sockets[11].getComponent()!=west.sockets[3].getComponent())
            return false;
        if (tile.sockets[10].getComponent()!=west.sockets[4].getComponent())
            return false;
        if (tile.sockets[9].getComponent()!=west.sockets[5].getComponent())
            return false;*/
        return true;
    }

    public void karteZiehen(){
       // view.getkarteZiehenButton().setOnAction(event -> {
          //  model.neueKarte();
        }


}