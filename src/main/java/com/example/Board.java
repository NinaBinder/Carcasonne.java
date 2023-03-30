package com.example;


import javafx.scene.image.Image;

/**Class representing the game field.
 This grows with the creation of new fields.*/
public class Board {
    Model model;

    //The attribute board stores the map in a two-dimensional array.
    Tile[][] matrix;

    // The relative coordinate of the starting Tile in the map.
    private int originalTileX;
    // The relative coordinate of the starting Tile in the map.
    private int originalTileY;
    Tile originalTile = new Tile(0,0,0,"OG",false);


    /**constructor*/
    public Board(Model model) {
        this.model= model;
        // relative position of the starting Tile is always 0/0 at the beginning
        originalTileX = 1;
        originalTileY = 1;
    }
    public void initBoard(){
        // initialize the board of size 3x3 (each row 3 fields, each column 3 fields)
        matrix = new Tile[3][3];
        matrix[originalTileX][originalTileY] = originalTile;

        for(int x= 0; x< matrix.length; x++){
            for(int y= 0; y< matrix.length; y++){

            //first set where which tile should go
            //use setTile function?

                if(!(x== 1 && y==1)) {
                    matrix[x][y] = new Tile(x, y, 0, "EMPTY", false);

                }

                Image tileImage = getTile(x,y).getImage();
                //System.out.println(getTile(x,y));
            //similiar like in update Board function get the images of added tiles and add them to the view
                model.initView(tileImage, x,y);
            }
        }
    }

    public boolean tryPlaceTile(Tile tile, int x, int y) {
        // Prüfen, ob das aktuelle Tile an der gegebenen Position platziert werden kann
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[y][x] != null) {
            return false; // Kachel kann nicht platziert werden
        }

        // Prüfen, ob das aktuelle Tile an eine benachbarte Kachel angrenzt
        boolean isAdjacent = false;
        if (x > 0 && matrix[y][x - 1] != null) { // Überprüfen, ob es eine benachbarte Kachel links gibt
            isAdjacent = true;
        }
        if (x < matrix.length - 1 && matrix[y][x + 1] != null) { // Überprüfen, ob es eine benachbarte Kachel rechts gibt
            isAdjacent = true;
        }
        if (y > 0 && matrix[y - 1][x] != null) { // Überprüfen, ob es eine benachbarte Kachel oben gibt
            isAdjacent = true;
        }
        if (y < matrix[0].length - 1 && matrix[y + 1][x] != null) { // Überprüfen, ob es eine benachbarte Kachel unten gibt
            isAdjacent = true;
        }
        if (!isAdjacent) {
            return false; // Kachel kann nicht platziert werden
        }
        // Platzieren des aktuellen Tiles an der gegebenen Position auf dem Board
        matrix[y][x] = tile;
        return true; // Kachel wurde erfolgreich platziert
    }


    public void placeFigure(int x, int y, Player player){
    }


    // Methode zur Bewertung der Punkte für alle Spieler basierend auf dem aktuellen Stand des Boards
    public void evaluatePoints() {
        //for (Player player : players) {
            //player.setScore(board.evaluatePoints(player));
        //}
    }

    //check if a given cell is within the bounds of the board array via it's relative position
    public boolean isWithinBoard(int relX, int relY) {
        //Adding the relative coordinates of the given tile
        // to the coordinates of the origin tile to get the absolute position of the tile
        int absX = convertToAbsolute(relX,relY)[0];
        int absY = convertToAbsolute(relX,relY)[1];
        return absX>= 0 && absX< matrix.length && absY>= 0 && absY< matrix[0].length;
    }

    /** getter */
    public int getOriginalTileX() {
        return originalTileX;
    }

    public int getOriginalTileY() {
        return originalTileY;
    }


    // get the tile via absolute references
    public Tile getTile(int x, int y) {
        int relX = convertToRelative(x,y)[0];
        int relY = convertToRelative(x,y)[1];
        if (isWithinBoard(relX, relY)) {
            return matrix[x][y];
        } else {
            return null;
        }
    }

    // get the tile via relative references
    public Tile getTile_viaRelative(int relX, int relY) {
        if (isWithinBoard(relX, relY)) {
            int absX = convertToAbsolute(relX,relY)[0];
            int absY = convertToAbsolute(relX,relY)[1];
            return matrix[absX][absY];
        } else {
            return null;
        }
    }

    /** setter */
    //set the tile with via absolute references
    public void setTile(int x, int y, String entry) {
        // check if the coordinates of the selected position are inside the board.
        if (isWithinBoard(convertToRelative(x,y)[0], convertToRelative(x,y)[1])) {
            this.matrix[x][y] = new Tile(x,y,0,entry, false);
        }
        // if the selected position is outside the board, the board has to be extended
        else {
            // in case the selected position is east of (on the right of) current board
            if(x> matrix.length){
                extendsBoardEast();
            }
            // in case the selected position is west of (on the left of) current board.
            if(x<0){
                extendsBoardWest();
            }
            // in case the selected position is south of (below)  current board.
            if(y> matrix[0].length){
                extendsBoardSouth();
            }
            // in case the selected position is north of (on top of) current board.
            if(y<0){
                extendsBoardNorth();
            }

        }
    }



    //set the tile with via relative references
    public void set_withRelativeReference(int relX, int relY, String entry) {
        setTile(convertToAbsolute(relX, relY )[0],convertToAbsolute(relX, relY)[1], entry);
    }


    /**method to convert from absolute to relative positions */
    public int[] convertToRelative(int absX, int absY) {
        int[] relative_coordinates = { absX - originalTileX, absY - originalTileY };
        return relative_coordinates;
    }

    /**method to convert to relative to absolute positions */
    public int[] convertToAbsolute(int relX, int relY) {
        int[] absolute_coordinates = { relX + originalTileX, relY + originalTileY };
        return absolute_coordinates;
    }

    /**method to expand the array in any of the four directions */
    public void extendsBoardNorth() {
        int xSize = matrix.length;
        int ySize = matrix[0].length;
        final String direction = "north";
        extendsBoard(xSize, ySize+1,direction);
    }

    public void extendsBoardSouth() {
        int xSize = matrix.length;
        int ySize = matrix[0].length;
        final String direction = "south";
        extendsBoard(xSize, ySize+1,direction);
    }

    public void extendsBoardWest() {
        int xSize = matrix.length;
        int ySize = matrix[0].length;
        final String direction = "west";
        extendsBoard(xSize+1, ySize,direction);
    }

    public void extendsBoardEast() {
        int xSize = matrix.length;
        int ySize = matrix[0].length;
        final String direction = "east";
        extendsBoard(xSize+1, ySize,direction);
    }

    private void extendsBoard(int width, int height, String extend_direction) {
        // create new board with new size
        Tile[][] newBoard = new Tile[width][height];
        // store tiles into the new board
        for(int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++){
                switch (extend_direction) {
                    case "north":
                        newBoard[x][y+1] = matrix[x][y];
                        break;
                    case "south":
                        newBoard[x][y] = matrix[x][y];
                        break;
                    case "west":
                        newBoard[x+1][y] = matrix[x][y];
                        break;
                    case "east":
                        newBoard[x][y] = matrix[x][y];
                        break;
                }
            }

            // update the position of the originalTile
            switch (extend_direction){
                case "north" -> originalTileY ++;
                case "west" ->   originalTileX ++;
                // case "south" or "east" the position remains the same
            }


        }
        // set the new board as the current board
        this.matrix = newBoard;
    }


}