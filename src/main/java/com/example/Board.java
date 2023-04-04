package com.example;


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
    private int width;
    private int height;
    Tile originalTile = new Tile(0,0,0,"OG",false);


    public Board(Model model) {
        this.model= model;
        // relative position of the starting Tile is always 0/0 at the beginning
        //absolut position

        originalTileX = 1;
        originalTileY = 1;
    }
    public void initBoard() {
        // initialize the board of size 3x3 (each row 3 fields, each column 3 fields)
        width = 3;
        height = 3;
        matrix = new Tile[width][height];
        setAbsoluteTile(originalTileX, originalTileY, "OG");
        model.addEmptyTiles(matrix[originalTileX][originalTileY]);
        for (int absX = 0; absX < width; absX++) {
            for (int absY = 0; absY < height; absY++) {
                if(matrix[absX][absY]!=null) {
                   // System.out.println(matrix[absX][absY].getRelX()+" ," + matrix[absX][absY].getRelY());
                }
            }
            }

    }
        //matrix[originalTileX][originalTileY] = originalTile;

    //check if a given cell is within the bounds of the board array via it's relative position
    public boolean isWithinBoard(int relX, int relY) {
        //Adding the relative coordinates of the given tile
        // to the coordinates of the origin tile to get the absolute position of the tile
        int absX = convertToAbsoluteX(relX);
        int absY = convertToAbsoluteY(relY);
        return absX>= 0 && absX< width && absY>= 0 && absY< height;
    }

    /**method to expand the array in any of the four directions */
    public void extendsBoardNorth() {
        final String direction = "north";
        extendsBoard(width, height+1,direction);
    }

    public void extendsBoardSouth() {
        final String direction = "south";
        extendsBoard(width, height+1,direction);
    }

    public void extendsBoardWest() {
        final String direction = "west";
        extendsBoard(width+1, height,direction);
    }

    public void extendsBoardEast() {
        final String direction = "east";
        extendsBoard(width+1, height,direction);
    }

    private void extendsBoard(int width, int height, String extend_direction) {
        // create new board with new size
        Tile[][] newBoard = new Tile[width][height];
        // store tiles into the new board
        for(int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++){
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

        }
        // update the position of the originalTile
        switch (extend_direction){
            case "north" -> originalTileY ++;
            case "west" ->   originalTileX ++;
            // case "south" or "east" the position remains the same
        }
        // set the new board as the current board
        this.matrix = newBoard;
        //update width and height
        this.width= width;
        this.height= height;
    }
    public void placeFigure(int x, int y, Player player){
    }

    // Methode zur Bewertung der Punkte für alle Spieler basierend auf dem aktuellen Stand des Boards
    public void evaluatePoints() {
        //for (Player player : players) {
        //player.setScore(board.evaluatePoints(player));
        //}
    }
    /**method to convert from absolute to relative positions */
    public int convertToRelativeX(int absX) {
        return absX - originalTileX;
    }
    public int convertToRelativeY(int absY) {
        return absY - originalTileY;

    }
    /** setter */
    //set the tile with via absolute references
    public void setAbsoluteTile(int absX, int absY, String entry) {
        // check if the coordinates of the selected position are inside the board.
        if (isWithinBoard(convertToRelativeX(absX), convertToRelativeY(absY)) ){
            //TODO: roation beachtet??
            this.matrix[absX][absY] = new Tile(convertToRelativeX(absX),convertToRelativeY(absY),0,entry, false);
        }
        // if the selected position is outside the board, the board has to be extended
        //TODO:wird board an dieser stelle nötig?
        else {
            // in case the selected position is on the right of current board
            if(absX>= this.width){
                extendsBoardEast();
                this.matrix[this.width-1][absY] = new Tile(convertToRelativeX(this.width-1),convertToRelativeY(absY),0,entry, false);
                //extendsBoardEast();
            }
            // in case the selected position is west of (on the left of) current board.
            if(absX<0){
                extendsBoardWest();
                this.matrix[0][absY] = new Tile(convertToRelativeX(0),convertToRelativeY(absY),0,entry, false);
                //extendsBoardWest();
            }
            // in case the selected position is south of (below)  current board.
            if(absY>= this.height){
                extendsBoardSouth();
                this.matrix[absX][this.height-1] = new Tile(convertToRelativeX(absX),convertToRelativeY(this.height-1),0,entry, false);
                //extendsBoardSouth();
            }
            // in case the selected position is north of (on top of) current board.
            if(absY<0){
                extendsBoardNorth();
                this.matrix[absX][0] = new Tile(convertToRelativeX(absX),convertToRelativeY(0),0,entry, false);
                //extendsBoardNorth();

            }



        }
    }

    //set the tile with via relative references
    public void setRelativeTile(int relX, int relY, String entry) {
        setAbsoluteTile(convertToAbsoluteX(relX),convertToAbsoluteY(relY), entry);
    }


    // get the tile via absolute references
    public Tile getAbsoluteTile(int absX, int absY) {
        int relX = convertToRelativeX(absX);
        int relY = convertToRelativeY(absY);
        if (isWithinBoard(relX, relY)) {
            return matrix[absX][absY];
        } else {
            return null;
        }
    }

    // get the tile via relative references
    public Tile getRelativeTile(int relX, int relY) {
        if (isWithinBoard(relX, relY)) {
            int absX = convertToAbsoluteX(relX);
            int absY = convertToAbsoluteY(relY);
            return matrix[absX][absY];
        } else {
           // System.out.println(relX + ","+ relY);
            return null;
        }
    }
    /**method to convert to relative to absolute positions */
    public int convertToAbsoluteX(int relX) {
        return relX + originalTileX;
    }
    public int convertToAbsoluteY(int relY) {
        return relY + originalTileY;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getOriginalTileX() {
        return originalTileX;
    }

    public int getOriginalTileY() {
        return originalTileY;
    }
}