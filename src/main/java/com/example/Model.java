package com.example;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * the model contains the logic  of the game
 */

public class Model {
    //Das Model enthält die Daten des Spiels:
    // (a) eine Board Instanz;
    // (b) ein Tile Objekt für das nächste Feld;
    // (c) Informationen zu den Spielern;
    // (d) Methoden zum Lesen und Setzen der Daten und zum Ausführen (bzw. Prüfen) von Spielaktionen

    private final Board board;
    final private TileLibrary library;
    private int currentPlayerIndex;
    private Controller controller;
    Tile currentTile;
    int scoreHumanPlayer = 0;
    int scoreComputerPlayer = 0;

    //für Road und City Array Lists, visitedroad, visitedCity, einfügen wo ich schon war
    ArrayList<Tile> visitedRoad;
    ArrayList<Tile> visitedCity;

    public Model(TileLibrary library) {
        this.library = library;
        board = new Board(this);
        //System.out.println(controller.getRotation());
    }

    /**
     * tryPlaceTile checks if it is possible to place a new Tile in the board at position relX/relY
     * @param relX the relative X cooridnate of new Tile in board
     * @param relY the relative Y coordinate of new Tile in board
     * @return true or false wherever it is possible to place the current Tile in board
     */
    public boolean tryPlaceTile(int relX, int relY, Tile currentTile){

        //we are trying to replace a new Tile at the position of the target Tile at relX / relY in our board
        Tile targetTile = getBoard().getRelativeTile(relX, relY);

        setCurrentTile(currentTile);
        Tile northTile= getNorthTile(targetTile.getRelX(), targetTile.getRelY());
        Tile southTile= getSouthTile(targetTile.getRelX(), targetTile.getRelY());
        Tile eastTile= getEastTile(targetTile.getRelX(), targetTile.getRelY());
        Tile westTile= getWestTile(targetTile.getRelX(), targetTile.getRelY());

        //can setTile? is empty & is matching with neighbors?
        if(tileMatch(currentTile,northTile,southTile,eastTile,westTile)){
            board.setRelativeTile(relX, relY, currentTile.getEntry(), currentTile.getRotation());
            addEmptyTiles(currentTile);
            System.out.println("place tile");
            return true;
        }
        else {
            System.out.println("tile not placeable");
        }
        return false;
    }

    public void computerTurn(){

        //pick a card from deck
        LibraryEntry entry = controller.pickACardAnyCard();
        String nextEntry = controller.getLibrary().getNameOfEntry(entry);

        System.out.println("card picked: " + nextEntry);

        //iterate through board, where can computer place new Card?
        for( int x = 0; x< board.getWidth(); x++ ){
            for(int y = 0; y < board.getHeight(); y++){

                Tile currentTile = board.getRelativeTile(board.convertToRelativeX(x), board.convertToRelativeY(y));

                //if currentTile is null skip it
                if(currentTile != null){

                    //if the computer comes across an EmptyTile
                    if(currentTile.getEntry().equals("EMPTY")){

                        int currentX= currentTile.getRelX();
                        int currentY= currentTile.getRelY();

                        //create a new Tile with the Position of the EmptyTile and the random Entry which was picked before
                        Tile nextTile = new Tile(currentX,currentY, currentTile.getRotation(), nextEntry,false);
                        //System.out.println("current tile created");

                        //then it checks if the createdTile can matches the neighbourTiles
                        if(tileMatch(nextTile, getNorthTile(currentX,currentY),getSouthTile(currentX,currentY),getEastTile(currentX,currentY),getWestTile(currentX,currentY))){
                            //System.out.println("tiles match");

                            //if it does match the new tile can be placed in the board and emptyTiles can be added
                            getBoard().setRelativeTile(currentX, currentY,nextTile.getEntry(),0.0);
                            addEmptyTiles(nextTile);
                            //System.out.println("place tile");

                            //finally update the View and add possible Points to the Computers Score
                            controller.updateBoard(getBoard());
                            addPointsComputer();

                            //return ends method / break ends for loop
                            return;

                        }else{
                            System.out.println("not placable");
                        }

                    }

                }
            }
        }
    }

    /**
     * adds EmptyTiles around the tile given to the method, if the nneighbour Tiles (north, east, south, west) either have the entry "EMPTY" or are null
     * @param tile
     */
    public void addEmptyTiles(Tile tile){

        if(isEmpty(getNorthTile(tile.getRelX(),tile.getRelY()))){
            board.setRelativeTile(tile.getRelX(),tile.getRelY()-1,"EMPTY",0.0);
            System.out.println("empty placed " + tile.getRelX()+ "," + (tile.getRelY()-1));
        }
        if(isEmpty(getSouthTile(tile.getRelX(),tile.getRelY()))){
            board.setRelativeTile(tile.getRelX(),tile.getRelY()+1,"EMPTY",0.0);
            System.out.println("empty placed " + tile.getRelX()+ "," + (tile.getRelY()+1));
        }
        if(isEmpty(getEastTile(tile.getRelX(),tile.getRelY()))){
            board.setRelativeTile(tile.getRelX()+1,tile.getRelY(),"EMPTY",0.0);
            System.out.println("empty placed " + (tile.getRelX()+1)+ "," + tile.getRelY());
        }

        if(isEmpty(getWestTile(tile.getRelX(),tile.getRelY()))){
            board.setRelativeTile(tile.getRelX()-1,tile.getRelY(),"EMPTY",0.0);
            System.out.println("empty placed " + (tile.getRelX()-1)+ "," + tile.getRelY());

        }
    }

    /**
     * @param tile
     * @return true if the parameter Tile is either an emptyTile or null
     */
    public boolean isEmpty(Tile tile){
        // what if tile is empty or null
        if(tile == null || tile.getEntry().equals("EMPTY")){
            return  true;
        }
        return false;
    }

    /**
     * checks if nextTile matches the surrounding Tiles, using the match() function
     * @param nextTile the Tile we want to place and is compared to the neighbour tiles
     * @param northTile lays above nextTile
     * @param southTile lays underneath nextTile
     * @param eastTile lays right of nextTile
     * @param westTile lays left nextTile
     * @return true is all Edges match
     */
    public boolean tileMatch (Tile nextTile, Tile northTile, Tile southTile, Tile eastTile, Tile westTile) {

        //first checks the case if all neighbout Tiles are empty, in that case it is not possible to put the next Place here
        if (isEmpty(northTile)&& isEmpty(eastTile)  && isEmpty(southTile) && isEmpty(westTile)){
            return false;
        }

        return (matches(nextTile, northTile, "north") &&
                matches(nextTile, eastTile, "east") &&
                matches(nextTile, southTile,"south") &&
                matches(nextTile, westTile, "west"));
    }

    /**
     * using switch case, checks if a newTile can be placed next to another tile
     * @param toPlace a new Tile we want to place in the board
     * @param target a Tile that lays next to the toPlace Tile
     * @param direction states the direction in which the target lays relative to the toPlace Tile
     * @return
     */
    public boolean matches(Tile toPlace, Tile target, String direction) {
        boolean match = false;
        if(target != null) {
            switch (direction.toLowerCase()) {
                case "north" -> {
                    match = isEmpty(target) || (toPlace.getNorthEdge().equals(target.getSouthEdge()));
                    //System.out.println("oben: " + target.getSouthEdge());
                }
                case "east" -> {
                    match = isEmpty(target) || (toPlace.getEastEdge() == target.getWestEdge());
                    //System.out.println("rechts: " + target.getWestEdge());
                }
                case "south" -> {
                    match = isEmpty(target) || (toPlace.getSouthEdge() == target.getNorthEdge());
                    //System.out.println("unten: " + target.getNorthEdge());
                }
                case "west" -> {
                    match = isEmpty(target) || (toPlace.getWestEdge() == target.getEastEdge());
                    //System.out.println("links: " + target.getEastEdge());
                }
            }
        }else{
            return true;
        }
        //System.out.println(match);
        return match;
    }

    public Tile getNorthTile(int relX, int relY){
        return getBoard().getRelativeTile(relX,relY-1);
    }
    public Tile getEastTile(int relX, int relY){
        return getBoard().getRelativeTile(relX+1,relY);
    }
    public Tile getSouthTile(int relX, int relY){
        return getBoard().getRelativeTile(relX,relY+1);
    }
    public Tile getWestTile(int relX, int relY){
        return getBoard().getRelativeTile(relX-1,relY);
    }
    public void initBoard(){
        board.initBoard();
    }
    public void setController(Controller controller){
        this.controller = controller;
    }
    public Board getBoard(){
        return this.board;
    }
    public void setCurrentTile (Tile currentTile) {
        String nextEntry = currentTile.getEntry();
        this.currentTile = new Tile(currentTile.getRelX(), currentTile.getRelY(), currentTile.getRotation(), nextEntry, false);
    }
    private boolean isRoadClosed(Tile lastPlacedTile) {
        if (lastPlacedTile.getEntry().equals("X") || lastPlacedTile.getEntry().equals("Y")) {
            //wenn eines von den Tiles, wo unterschiedliche roads drauf sind
            visitedRoad.add(lastPlacedTile);

            boolean isClosed = true;
            LibraryEntry.Component road = LibraryEntry.Component.ROAD;
            String entryKey = lastPlacedTile.getEntry();
            ArrayList<Tile> nextTiles = new ArrayList<Tile>();
            LibraryEntry[] endingTiles = new LibraryEntry[]{library.map.get("X"), library.map.get("Y"), library.map.get("U"),
                    library.map.get("T"), library.map.get("L"), library.map.get("A")};

            for (int i = 0; i < 4; i++) {

                if (Tile.library.map.get(entryKey).getComponent()[i] == road) {
                    int relX = lastPlacedTile.getRelX();
                    int relY = lastPlacedTile.getRelY();
                    switch ((int) (lastPlacedTile.getRotation() % 360)) {
                        case 0 -> {
                            switch (i) {
                                case 0 -> nextTiles.add(getNorthTile(relX, relY));
                                case 1 -> nextTiles.add(getEastTile(relX, relY));
                                case 2 -> nextTiles.add(getSouthTile(relX, relY));
                                case 3 -> nextTiles.add(getWestTile(relX, relY));
                            }
                        }
                        case 90 -> {
                            switch (i) {
                                case 0 -> nextTiles.add(getEastTile(relX, relY));
                                case 1 -> nextTiles.add(getSouthTile(relX, relY));
                                case 2 -> nextTiles.add(getWestTile(relX, relY));
                                case 3 -> nextTiles.add(getNorthTile(relX, relY));
                            }
                        }
                        case 180 -> {
                            switch (i) {
                                case 0 -> nextTiles.add(getSouthTile(relX, relY));
                                case 1 -> nextTiles.add(getWestTile(relX, relY));
                                case 2 -> nextTiles.add(getNorthTile(relX, relY));
                                case 3 -> nextTiles.add(getEastTile(relX, relY));
                            }
                        }
                        case 270 -> {
                            switch (i) {
                                case 0 -> nextTiles.add(getWestTile(relX, relY));
                                case 1 -> nextTiles.add(getNorthTile(relX, relY));
                                case 2 -> nextTiles.add(getEastTile(relX, relY));
                                case 3 -> nextTiles.add(getSouthTile(relX, relY));
                            }
                        }
                    }
                }
            }
            //visitedRoad.remove(lastPlacedTile); //sonst kann Straße keinen Kreis bilden
            int counter = 0;
            for (Tile t : nextTiles) {
                if (!visitedRoad.contains(t) && Arrays.asList(endingTiles).contains(t))
                    isClosed |= isRoadClosedRec(t, t.getRelX(), t.getRelY()); //isClosed = isClosed | isRoadClosedRec()
                if (isEmpty(t))
                    counter++;
            }
            if (counter == nextTiles.size())
                return false; //weil dann empty Tile da ist
            else
                return isClosed;

        } else {
            return isRoadClosedRec(lastPlacedTile, lastPlacedTile.getRelX(), lastPlacedTile.getRelY());
        }
    }
    public boolean isRoadClosedRec(Tile lastPlacedTile, int relX, int relY) {
        visitedRoad.add(lastPlacedTile);
        if (lastPlacedTile.getEntry().equals("X") || lastPlacedTile.getEntry().equals("Y"))
            //wenn eines von den Tiles, wo unterschiedliche roads drauf sind
            return true; //denn den Fall haben wir ja schon in isRoadClosed behandelt

        boolean isClosed = true;

        LibraryEntry.Component road = LibraryEntry.Component.ROAD;
        String entryKey = lastPlacedTile.getEntry();
        ArrayList<Tile> nextTiles = new ArrayList<Tile>();
        LibraryEntry[] endingTiles = new LibraryEntry[]{library.map.get("X"), library.map.get("Y"),
                library.map.get("U"), library.map.get("T"), library.map.get("L"), library.map.get("A")};

        for (int i = 0; i < 4; i++) {

            if (library.map.get(entryKey).getComponent()[i] == road) {
                switch ((int) (lastPlacedTile.getRotation() % 360)) {
                    case 0:
                        switch (i) {
                            case 0 -> nextTiles.add(getNorthTile(relX, relY));
                            case 1 -> nextTiles.add(getEastTile(relX, relY));
                            case 2 -> nextTiles.add(getSouthTile(relX, relY));
                            case 3 -> nextTiles.add(getWestTile(relX, relY));
                        }
                        break;
                    case 90:
                        switch (i) {
                            case 0 -> nextTiles.add(getEastTile(relX, relY));
                            case 1 -> nextTiles.add(getSouthTile(relX, relY));
                            case 2 -> nextTiles.add(getWestTile(relX, relY));
                            case 3 -> nextTiles.add(getNorthTile(relX, relY));
                        }
                        break;
                    case 180:
                        switch (i) {
                            case 0 -> nextTiles.add(getSouthTile(relX, relY));
                            case 1 -> nextTiles.add(getWestTile(relX, relY));
                            case 2 -> nextTiles.add(getNorthTile(relX, relY));
                            case 3 -> nextTiles.add(getEastTile(relX, relY));
                        }
                        break;
                    case 270:
                        switch (i) {
                            case 0 -> nextTiles.add(getWestTile(relX, relY));
                            case 1 -> nextTiles.add(getNorthTile(relX, relY));
                            case 2 -> nextTiles.add(getEastTile(relX, relY));
                            case 3 -> nextTiles.add(getSouthTile(relX, relY));
                        }
                        break;
                }
            }
        }
        for (Tile t : nextTiles) {
            if (isEmpty(t))
                return false;
            if (!visitedRoad.contains(t) && Arrays.asList(endingTiles).contains(t))
                isClosed &= isRoadClosedRec(t, t.getRelX(), t.getRelY()); //dieses mal "und" weil nicht unterschiedliche
            // roads auf einem Tile, müssen in alle Richtungen abschließen
        }
        return isClosed;
    }

    //Abbruchbedingung nach jedem Case?
    private void recurse(Tile currentTile){visitedCity.add(currentTile);}

    //Es gibt 2 unterschiedl. Fälle:
    // 1. Wir schauen erst, ob das zuletzt gelegte Tile mögl.weise 2 unterschiedl. citys hat
    public boolean isCityClosed(Tile lastPlacedTile) {
        if (lastPlacedTile.getEntry().equals("H") || lastPlacedTile.getEntry().equals("I")) {//wenn eines von den Tiles, wo unterschiedliche Citys drauf sind
            visitedCity.add(lastPlacedTile);

            boolean isClosed = true;
            LibraryEntry.Component city = LibraryEntry.Component.CITY;
            String entryKey = lastPlacedTile.getEntry();
            ArrayList<Tile> nextTiles = new ArrayList<Tile>();

            for (int i = 0; i < 4; i++) {

                if (Tile.library.map.get(entryKey).getComponent()[i] == city) {

                    final int relX = lastPlacedTile.getRelX();
                    final int relY = lastPlacedTile.getRelY();
                    switch ((int) (lastPlacedTile.getRotation() % 360)) {
                        case 0 -> {
                            switch (i) {
                                case 0 -> nextTiles.add(getNorthTile(relX, relY));
                                case 1 -> nextTiles.add(getEastTile(relX, relY));
                                case 2 -> nextTiles.add(getSouthTile(relX, relY));
                                case 3 -> nextTiles.add(getWestTile(relX, relY));
                            }
                        }
                        case 90 -> {
                            switch (i) {
                                case 0 -> nextTiles.add(getEastTile(relX, relY));
                                case 1 -> nextTiles.add(getSouthTile(relX, relY));
                                case 2 -> nextTiles.add(getWestTile(relX, relY));
                                case 3 -> nextTiles.add(getNorthTile(relX, relY));
                            }
                        }
                        case 180 -> {
                            switch (i) {
                                case 0 -> nextTiles.add(getSouthTile(relX, relY));
                                case 1 -> nextTiles.add(getWestTile(relX, relY));
                                case 2 -> nextTiles.add(getNorthTile(relX, relY));
                                case 3 -> nextTiles.add(getEastTile(relX, relY));
                            }
                        }
                        case 270 -> {
                            switch (i) {
                                case 0 -> nextTiles.add(getWestTile(relX, relY));
                                case 1 -> nextTiles.add(getNorthTile(relX, relY));
                                case 2 -> nextTiles.add(getEastTile(relX, relY));
                                case 3 -> nextTiles.add(getSouthTile(relX, relY));
                            }
                        }
                    }
                }
            }
            int counter = 0;
            for (Tile t : nextTiles) {
                if (!visitedCity.contains(t))
                    isClosed |= isCityClosedRec(t, t.getRelX(), t.getRelY()); //isClosed = isClosed | isCityClosedRec()
                if (isEmpty(t))
                    counter++;
            }
            if (counter == nextTiles.size()) {
                return false;
            } //weil dann empty Tile da ist
            else {
                return true;
            }
        } else {
            return isCityClosedRec(lastPlacedTile, lastPlacedTile.getRelX(), lastPlacedTile.getRelY());
        }
    }

    //isCityClosedTop und isCityClosedRec draus machen, lastPlacedTile ist das lastPlacedTile?
    private boolean isCityClosedRec(Tile lastPlacedTile, int relX, int relY) {
        visitedCity.add(lastPlacedTile);
        if (lastPlacedTile.getEntry().equals("H") || lastPlacedTile.getEntry().equals("I"))
            //wenn eines von den Tiles, wo unterschiedllaiche Citys drauf sind
            return true; //denn den Fall haben wir ja schon in isCityClosed behandelt

        boolean isClosed = true;
        LibraryEntry.Component city = LibraryEntry.Component.CITY;
        String entryKey = lastPlacedTile.getEntry();
        ArrayList<Tile> nextTiles = new ArrayList<Tile>();

        for (int i = 0; i < 4; i++) {

            if (Tile.library.map.get(entryKey).getComponent()[i] == city) {
                switch ((int) (lastPlacedTile.getRotation() % 360)) {
                    case 0 -> {
                        switch (i) {
                            case 0 -> nextTiles.add(getNorthTile(relX, relY));
                            case 1 -> nextTiles.add(getEastTile(relX, relY));
                            case 2 -> nextTiles.add(getSouthTile(relX, relY));
                            case 3 -> nextTiles.add(getWestTile(relX, relY));
                        }
                    }
                    case 90 -> {
                        switch (i) {
                            case 0 -> nextTiles.add(getEastTile(relX, relY));
                            case 1 -> nextTiles.add(getSouthTile(relX, relY));
                            case 2 -> nextTiles.add(getWestTile(relX, relY));
                            case 3 -> nextTiles.add(getNorthTile(relX, relY));
                        }
                    }
                    case 180 -> {
                        switch (i) {
                            case 0 -> nextTiles.add(getSouthTile(relX, relY));
                            case 1 -> nextTiles.add(getWestTile(relX, relY));
                            case 2 -> nextTiles.add(getNorthTile(relX, relY));
                            case 3 -> nextTiles.add(getEastTile(relX, relY));
                        }
                    }
                    case 270 -> {
                        switch (i) {
                            case 0 -> nextTiles.add(getWestTile(relX, relY));
                            case 1 -> nextTiles.add(getNorthTile(relX, relY));
                            case 2 -> nextTiles.add(getEastTile(relX, relY));
                            case 3 -> nextTiles.add(getSouthTile(relX, relY));
                        }
                    }
                }
            }
        }
        for (Tile t : nextTiles) {
            if (isEmpty(t))
                return false;
            if (!visitedCity.contains(t))
                isClosed &= isCityClosedRec(t, t.getRelX(), t.getRelY()); //dieses mal "und" weil nicht unterschiedliche
            // citys auf einem Tile, müssen in alle Richtungen abschließen
        }
        return isClosed;
    }
    boolean isMonasteryClosed(Tile lastPlacedTile) {
        boolean isClosed = true;

        int relX = lastPlacedTile.getRelX();
        int relY = lastPlacedTile.getRelY();

        Tile north = getNorthTile(relX, relY);
        Tile east = getEastTile(relX, relY);
        Tile south = getSouthTile(relX, relY);
        Tile west = getWestTile(relX, relY);

        //is last placed tile ein kloster
        if (lastPlacedTile.getEntry().equals("A") || lastPlacedTile.getEntry().equals("B")) {

            //prüfe, ob 8 umliegende Karten != empty
            if (!isEmpty(north) &&
                    !isEmpty(east) &&
                    !isEmpty(south) &&
                    !isEmpty(west) &&
                    !isEmpty(getWestTile(south.getRelX(), south.getRelY())) &&
                    !isEmpty(getWestTile(north.getRelX(), north.getRelY())) &&
                    !isEmpty(getNorthTile(east.getRelX(), east.getRelY())) &&
                    !isEmpty(getSouthTile(east.getRelX(), east.getRelY()))
            ) {
                //sind alle umliegenden 8 Felder empty?
                return isClosed;
            }
        }
        return isClosed;
    }
    public void addPointsPlayer() { //score übergeben? definieren?

        if (isRoadClosed(currentTile)) { //&& meeple placed
            scoreHumanPlayer += visitedRoad.size();
        }
        if (isCityClosed(currentTile)) { //&& meeple placed
            scoreHumanPlayer += visitedCity.size();
        }
        if (isMonasteryClosed(currentTile)) { //&& meeple placed
            scoreHumanPlayer += 9;
        }

        controller.updateHumanScore();

    }

    public int getScoreComputerPlayer() {
        return scoreComputerPlayer;
    }

    public int getScoreHumanPlayer() {
        return scoreHumanPlayer;
    }

    public void addPointsComputer() { //score übergeben? definieren?
        //if computerTurn()
        if (isRoadClosed(currentTile)) { //&& meeple placed
            scoreComputerPlayer += visitedRoad.size();
        }
        if (isCityClosed(currentTile)) { //&& meeple placed
            scoreComputerPlayer += visitedCity.size();
        }
        if (isMonasteryClosed(currentTile)) { //&& meeple placed
            scoreComputerPlayer += 9;
        }
       controller.updateComputerScore();

    }


}