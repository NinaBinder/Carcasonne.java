package com.example;
import java.util.ArrayList;

/**
 * the model contains the logic  of the game
 */

public class Model {
    //Das Model enthält die Daten des Spiels:
    // (a) eine Board Instanz;
    // (b) ein Tile Objekt für das nächste Feld;
    // (c) Informationen zu den Spielern;
    // (d) Methoden zum Lesen und Setzen der Daten und zum Ausführen (bzw. Prüfen) von Spielaktionen

    private Board board;
    private LibraryEntry nextEntry;

    private int currentPlayerIndex;
    private Controller controller;
    Tile currentTile;

    //für Road und City Array Lists, visitedroad, visitedCity, einfügen wo ich schon war
    ArrayList<Tile> visitedRoad;
    ArrayList<Tile> visitedCity;

    public Model (){
        board = new Board(this);
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
//test
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

        board = getBoard();
        //did player do turn?

        //pick a card from deck
        LibraryEntry entry = controller.pickACardAnyCard();
        String nextEntry = controller.getLibrary().getNameOfEntry(entry);

        System.out.println("card picked: " + nextEntry);

        //iterate through board, where can computer place new Card?
        for( int x = 0; x< board.getWidth(); x++ ){
            for(int y = 0; y < board.getHeight(); y++){

                Tile currentTile = board.getRelativeTile(board.convertToRelativeX(x), board.convertToRelativeY(y));

                //is Tile null? if null skip
                if(currentTile != null){
                    System.out.println(currentTile.getEntry());

                    if(currentTile.getEntry().equals("EMPTY")){

                        int currentX= currentTile.getRelX();
                        int currentY= currentTile.getRelY();

                        Tile nextTile = new Tile(currentX,currentY, currentTile.getRotation(), nextEntry,false);
                        //System.out.println("current tile created");

                        if(tileMatch(nextTile, getNorthTile(currentX,currentY),getSouthTile(currentX,currentY),getEastTile(currentX,currentY),getWestTile(currentX,currentY))){
                            //System.out.println("tiles match");

                            board.setRelativeTile(currentX, currentY,nextTile.getEntry(),0.0);
                            addEmptyTiles(nextTile);
                            //System.out.println("place tile");
                            controller.updateBoard(board);

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

    public boolean isEmpty(Tile tile){
        // what if tile is empty or null
        if(tile == null || tile.getEntry().equals("EMPTY")){
            return  true;
        }
        return false;
    }

    public boolean matches(Tile toPlace, Tile target, String direction) {
        boolean match = false;
        if(target != null) {
            switch (direction.toLowerCase()) {
                case "north" -> {
                    match = isEmpty(target) || (toPlace.getNorthEdge().equals(target.getSouthEdge()));
                    System.out.println("oben next tile " + toPlace.getNorthEdge());
                    //System.out.println("oben: " + target.getSouthEdge());
                    //System.out.println("matches "+ toPlace.getNorthEdge().equals(target.getSouthEdge()));
                }
                case "east" -> {
                    match = isEmpty(target) || (toPlace.getEastEdge() == target.getWestEdge());
                    //System.out.println("rechts: " + target.getWestEdge());
                    System.out.println("recht next tile " + toPlace.getEastEdge());
                }
                case "south" -> {
                    match = isEmpty(target) || (toPlace.getSouthEdge() == target.getNorthEdge());
                    //System.out.println("unten: " + target.getNorthEdge());
                    System.out.println("unten next tile " + toPlace.getSouthEdge());
                }
                case "west" -> {
                    match = isEmpty(target) || (toPlace.getWestEdge() == target.getEastEdge());
                    //System.out.println("links: " + target.getEastEdge());
                    System.out.println("links next tile " + toPlace.getWestEdge());
                }
            }
        }else{
            return true;
        }
        //System.out.println(match);
        return match;
    }

    /** check if the field fits on the side of another neighbours*/
    public boolean tileMatch (Tile nextTile, Tile northTile, Tile southTile, Tile eastTile, Tile westTile) {

        LibraryEntry.Component nextOben = nextTile.getNorthEdge();
        LibraryEntry.Component nextRechts = nextTile.getEastEdge();
        LibraryEntry.Component nextUnten = nextTile.getSouthEdge();
        LibraryEntry.Component nextLinks= nextTile.getWestEdge();

        //System.out.println(nextTile.getEntry());

        if (isEmpty(northTile)&& isEmpty(eastTile)  && isEmpty(southTile) && isEmpty(westTile)){
            return false;
        }
        System.out.println(nextOben);
        System.out.println(nextRechts);
        System.out.println(nextUnten);
        System.out.println(nextLinks);

        //if(northTile != null){System.out.println("north" + northTile.getSouthEdge());}
        //if(eastTile != null){System.out.println("east" + eastTile.getWestEdge());}
        //if(southTile != null){System.out.println("south" + southTile.getNorthEdge());}
        //if(westTile != null){System.out.println("west" + westTile.getEastEdge());}

        return (matches(nextTile, northTile, "north") &&
                matches(nextTile, eastTile, "east") &&
                matches(nextTile, southTile,"south") &&
                matches(nextTile, westTile, "west"));
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

    public void setNextEntry(LibraryEntry next){
        this.nextEntry = next;
    }
    public void setCurrentTile (Tile currentTile) {
        String nextEntry = currentTile.getEntry();
        this.currentTile = new Tile(currentTile.getRelX(), currentTile.getRelY(), currentTile.getRotation(), nextEntry, false);

    }
    public boolean isRoadClosed(Tile lastPlacedTile, int relX, int relY) {

        boolean isClosed = true;

        LibraryEntry.Component road = LibraryEntry.Component.ROAD;
        String entryKey = lastPlacedTile.getEntry();

        //gibt es an dem Tile überhaupt ein Socket mit Component Road?
        for (int i=0; i<4; i++){

            if (Tile.library.map.get(entryKey).getComponent()[i] == road){
                //wenn ja an welchen Sockets ist Road?
                // TODO: zwei Prüfrichtungen! müssen beide erfüllt sein

                switch ((int) (lastPlacedTile.getRotation()% 360)){
                    case 0: //break;
                        switch (i) {
                            case 0 -> getNorthTile(relX, relY);
                            case 1 -> getEastTile(relX, relY);
                            case 2 -> getSouthTile(relX, relY);
                            case 3 -> getWestTile(relX, relY);

                        }
                    case 90: //break;
                        switch (i) {
                            case 0 -> getEastTile(relX, relY);
                            case 1 -> getSouthTile(relX, relY);
                            case 2 -> getWestTile(relX, relY);
                            case 3 -> getNorthTile(relX, relY);
                        }
                    case 180:
                        switch (i) {
                            case 0 -> getSouthTile(relX, relY);
                            case 1 -> getWestTile(relX, relY);
                            case 2 -> getNorthTile(relX, relY);
                            case 3 -> getEastTile(relX, relY);

                        }
                    case 270: //break;
                        switch (i) {
                            case 0 -> getWestTile(relX, relY);
                            case 1 -> getNorthTile(relX, relY);
                            case 2 -> getEastTile(relX, relY);
                            case 3 -> getSouthTile(relX, relY);
                        }

                        //wiederhole den prozess bis an Ending Tile angekommen
                        if (getNorthTile(relX, relY).getEntry().equals("X") ||
                                getNorthTile(relX, relY).getEntry().equals("Y") ){

                            return isClosed;
                        }
                }
            }
        }
        return isClosed;
    }

    //Abbruchbedingung nach jedem Case?
    private void recurse(Tile currentTile){visitedCity.add(currentTile);}

    //isCityClosedTop und isCityClosedRec draus machen, lastPlacedTile ist das currentTile?
    public boolean isCityClosed(Tile lastPlacedTile, int relX, int relY) {
        boolean isClosed = true;

        LibraryEntry.Component city = LibraryEntry.Component.CITY;
        String entryKey = lastPlacedTile.getEntry();

        Tile northTile, eastTile, southTile, westTile;

        for (int i=0; i<12; i++){

            if (Tile.library.map.get(entryKey).getComponent()[i] == city){
                switch ((int) (lastPlacedTile.getRotation()%360)){
                    case 0:
                        switch (i) {
                            case 0 -> northTile = getNorthTile(relX, relY);
                            case 1 -> eastTile = getEastTile(relX, relY);
                            case 2 -> southTile = getSouthTile(relX, relY);
                            case 3 -> westTile = getWestTile(relX, relY);
                        }
                        break;
                    case 90:
                        switch (i) {
                            case 0 -> eastTile = getEastTile(relX, relY);
                            case 1 -> southTile = getSouthTile(relX, relY);
                            case 2 -> westTile = getWestTile(relX, relY);
                            case 3 -> northTile = getNorthTile(relX, relY);
                        }
                    case 180:
                        switch (i) {
                            case 0 -> southTile = getSouthTile(relX, relY);
                            case 1 -> westTile = getWestTile(relX, relY);
                            case 2 -> northTile = getNorthTile(relX, relY);
                            case 3 -> eastTile = getEastTile(relX, relY);
                        }
                    case 270:
                        switch (i) {
                            case 0 -> westTile = getWestTile(relX, relY);
                            case 1 -> northTile = getNorthTile(relX, relY);
                            case 2 -> eastTile = getEastTile(relX, relY);
                            case 3 -> southTile = getSouthTile(relX, relY);
                        }
                }
                recurse(lastPlacedTile);
                {return isClosed;}
            }
        }
        return isClosed;
    }

    boolean KlosterFertig (Tile lastPlacedTile){
        boolean isClosed = true;

        int relX = lastPlacedTile.getRelX();
        int relY = lastPlacedTile.getRelY();

        Tile north = getNorthTile(relX, relY);
        Tile east = getEastTile(relX, relY);
        Tile south = getSouthTile(relX, relY);
        Tile west = getWestTile(relX,relY);


        //is last placed tile ein kloster
        if (lastPlacedTile.getEntry().equals("A") || lastPlacedTile.getEntry().equals("B")){

            //prüfe, ob 8 umliegende Karten != empty
            if(!isEmpty(north) &&
                    !isEmpty(east) &&
                    !isEmpty(south) &&
                    !isEmpty(west) &&
                    !isEmpty(getWestTile(south.getRelX(),south.getRelY())) &&
                    !isEmpty(getWestTile(north.getRelX(),north.getRelY())) &&
                    !isEmpty(getNorthTile (east.getRelX(), east.getRelY())) &&
                    !isEmpty(getSouthTile (east.getRelX(), east.getRelY()))
            ){
                //sind alle umliegenden 8 Felder empty?
                return isClosed;
            }
        }
        return isClosed;
    }
    public void addPoints (int score, Tile lastPlacedTile, int relX, int relY){
        for (int i=0; i<board.matrix.length; i++){
            if (isRoadClosed(lastPlacedTile, relX, relY)){
                score ++;
            }
            if (isCityClosed(lastPlacedTile, relX, relY)){
                score++;
            }
            if (KlosterFertig(lastPlacedTile)){
                score++;
            }
        }
    }
}