package com.example;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;


public class Model {
    //Das Model enthält die Daten des Spiels:
    // (a) eine Board Instanz;
    // (b) ein Tile Objekt für das nächste Feld;
    // (c) Informationen zu den Spielern;
    // (d) Methoden zum Lesen und Setzen der Daten und zum Ausführen (bzw. Prüfen) von Spielaktionen

    //test
    private Board board;
    TileLibrary library;
    private LibraryEntry nextEntry;
    private List<Player> players;
    private int currentPlayerIndex;
    private Controller controller= new Controller(new View(),this);
    View view = new View();


    public Model (){
        board = new Board(this);
        System.out.println(controller.getRotation());


    }
    public void addEmptyTiles(Tile tile){

        if(isEmpty(getNorthTile(tile.getRelX(),tile.getRelY()))){
            board.setRelativeTile(tile.getRelX(),tile.getRelY()-1,"EMPTY");
        }
        if(isEmpty(getSouthTile(tile.getRelX(),tile.getRelY()))){
            board.setRelativeTile(tile.getRelX(),tile.getRelY()+1,"EMPTY");

        }
        if(isEmpty(getEastTile(tile.getRelX(),tile.getRelY()))){
            board.setRelativeTile(tile.getRelX()+1,tile.getRelY(),"EMPTY");
        }

        if(isEmpty(getWestTile(tile.getRelX(),tile.getRelY()))){
            board.setRelativeTile(tile.getRelX()-1,tile.getRelY(),"EMPTY");
        }

    }
    public boolean isEmpty(Tile tile){
        // what if tile is empty or null
        if(tile == null || tile.getEntry().equals("EMPTY")){
            return  true;
        }
        return false;
    }

    public boolean tryPlaceTile(int relX, int relY){

        //System.out.println("Rel Position of next Tile: " + relX + "," +relY);
        Tile targetTile = getBoard().getRelativeTile(relX, relY);
        //System.out.println("target Tile Entry: " + targetTile.getEntry());

        Tile northTile= getNorthTile(targetTile.getRelX(), targetTile.getRelY());
        Tile southTile= getSouthTile(targetTile.getRelX(), targetTile.getRelY());
        Tile eastTile= getEastTile(targetTile.getRelX(), targetTile.getRelY());
        Tile westTile= getWestTile(targetTile.getRelX(), targetTile.getRelY());

        //System.out.println(westTile);

        String nextEntry = controller.getLibrary().getNameOfEntry(this.nextEntry);
        System.out.println("next Tile Entry: " + nextEntry);
        //System.out.println("rotatation" + controller.getRotation());

        Tile nextTile= new Tile(relX, relY, targetTile==null ? 0 : 0, nextEntry, targetTile==null ? false : targetTile.gamePiece);


        //can setTile? is empty & is matching with neighbors?
        if(tileMatch(nextTile,northTile,southTile,eastTile,westTile)){

            board.setRelativeTile(relX, relY,nextTile.getEntry());
            addEmptyTiles(nextTile);
            System.out.println("place tile");
            return true;
        }
        else {
            System.out.println("tile not placeable");
        }
        return false;
    }
    /** check if the field fits on the side of another neighbours*/
    public boolean tileMatch (Tile nextTile, Tile northTile, Tile southTile, Tile eastTile, Tile westTile) {

        LibraryEntry.Component nextOben = nextTile.getNorthEdge();
        LibraryEntry.Component nextRechts = nextTile.getEastEdge();
        LibraryEntry.Component nextUnten = nextTile.getSouthEdge();
        LibraryEntry.Component nextLinks= nextTile.getWestEdge();

        if (northTile == null && eastTile == null && southTile == null && westTile == null) {
            return false;

        } else if (!isEmpty(northTile)) {
            LibraryEntry.Component oben = northTile.getSouthEdge();
            System.out.println("oben " + oben);

            if (oben == nextOben) {
                System.out.println("next oben " + nextOben);
                return true;
            }

        } else if (!isEmpty(eastTile)) {
            LibraryEntry.Component rechts = eastTile.getWestEdge();
            System.out.println("rechts " + rechts);

            if (rechts == nextRechts) {
                System.out.println("next rechts " + nextRechts);
                return true;
            }

        }else if (!isEmpty(southTile)) {
            LibraryEntry.Component unten = southTile.getNorthEdge();
            System.out.println("unten " + unten);

            if (unten == nextUnten) {
                System.out.println("next unten " + nextUnten);
                return true;
            }

        }else if (!isEmpty(westTile)) {
            LibraryEntry.Component links= westTile.getEastEdge();
            System.out.println("links " + links);

            if (links == nextLinks) {
                System.out.println("next links " + nextLinks);
                return true;
            }
        }


        /*


        if(!isEmpty(northTile)){

            if(isEmpty(westTile)){
                return true;
            } else if (westTile.getEastEdge() == nextTile.getEastEdge() || westTile.getEastEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
            if(isEmpty(eastTile)){
                return true;
            } else if (eastTile.getWestEdge() == nextTile.getWestEdge() || eastTile.getWestEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
            if(isEmpty(southTile)){
                return true;
            } else if (southTile.getNorthEdge()== nextTile.getSouthEdge() || southTile.getNorthEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
        }
        if(!isEmpty(eastTile)){
            System.out.println(eastTile.getWestEdge());

            if(isEmpty(westTile)){
                return true;
            } else if (westTile.getEastEdge() == nextTile.getEastEdge() || westTile.getEastEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
            if(isEmpty(northTile)){
                return true;
            } else if (northTile.getSouthEdge() == nextTile.getNorthEdge() || northTile.getSouthEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
            if(isEmpty(southTile)){
                return true;
            } else if (southTile.getNorthEdge()== nextTile.getSouthEdge() || southTile.getNorthEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
        }
        if(!isEmpty(southTile)){
            System.out.println(southTile.getNorthEdge());

            if(isEmpty(westTile)){
                return true;
            } else if (westTile.getEastEdge() == nextTile.getEastEdge() || westTile.getEastEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
            if(isEmpty(eastTile)){
                return true;
            } else if (eastTile.getWestEdge() == nextTile.getWestEdge() || eastTile.getWestEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
            if(isEmpty(northTile)){
                return true;
            } else if (northTile.getSouthEdge()== nextTile.getNorthEdge() || northTile.getSouthEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
        }
        if(!isEmpty(westTile)){
            System.out.println(westTile.getEastEdge());

            if(!isEmpty(northTile)){
                return true;
            } else if (northTile.getSouthEdge() == nextTile.getNorthEdge() || northTile.getSouthEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
            if(isEmpty(eastTile)){
                return true;
            } else if (eastTile.getWestEdge() == nextTile.getWestEdge() || eastTile.getWestEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
            if(isEmpty(southTile)){
                return true;
            } else if (southTile.getNorthEdge()== nextTile.getSouthEdge() || southTile.getNorthEdge() == LibraryEntry.Component.UNIVERSAL) {
                return true;
            }
        }*/
        /*

        //is there a tile north?
        if (northTile != null) {
            System.out.println("new Tile north:" + tile.getNorthEdge() + "=?" + northTile.getSouthEdge());

            //is northtile at connecting part mathcing or universal?
            if (northTile.getSouthEdge() == LibraryEntry.Component.UNIVERSAL || tile.getNorthEdge() == northTile.getSouthEdge()) {
                return true;
            }
        }

        if (eastTile != null) {
            System.out.println("new Tile eastEdge:" + tile.getEastEdge() + "=?" + eastTile.getWestEdge());

            if (eastTile.getWestEdge() == LibraryEntry.Component.UNIVERSAL || tile.getEastEdge() == eastTile.getWestEdge()) {
                return true;
            }
        }


        if (westTile != null) {
            System.out.println("new Tile westEdge:" + tile.getWestEdge() + "=?" + westTile.getEastEdge());

            if (westTile.getEastEdge() == LibraryEntry.Component.UNIVERSAL || tile.getWestEdge() == westTile.getEastEdge()) {
                return true;
            }
        }


        if (southTile != null) {
            System.out.println("new Tile southEdge:" + tile.getSouthEdge() + "=?" + southTile.getNorthEdge());

            if ( southTile.getNorthEdge() == LibraryEntry.Component.UNIVERSAL || tile.getSouthEdge() == southTile.getNorthEdge()) {
                 return true;
                }
            }*/

            return false;
        }


    public Tile getNorthTile(int relX, int relY){
        Tile north = getBoard().getRelativeTile(relX,relY-1);
        return north;
    }
    public Tile getEastTile(int relX, int relY){
        Tile east = getBoard().getRelativeTile(relX+1,relY);
        return east;
    }
    public Tile getSouthTile(int relX, int relY){
        Tile south = getBoard().getRelativeTile(relX,relY+1);
        return south;
    }
    public Tile getWestTile(int relX, int relY){
        Tile west = getBoard().getRelativeTile(relX-1,relY);
        return west;
    }
    public void initView(Image tileimage, int absX, int absY){
        controller.updateView(tileimage,absX,absY);

    }
    public void initBoard(){
        board.initBoard();
    }
    public void setController(Controller controller){
        this.controller = controller;
    }

    public boolean isPatternClosed(Tile lastPlacedTile) {

        boolean isClosed = false;
        int x;
        int y;
        for (int i=0; i<12; i++){
           // Component c = lastPlacedTile.getSockets()[i].getComponent();

            //gibt es an dem Tile überhaupt ein Socket mit Component Road?

        }
       return isClosed;
    }

    public Board getBoard(){
        return this.board;
    }

    public LibraryEntry getNextEntry(){
        return this.nextEntry;
    }

    public void setNextEntry(LibraryEntry next){
        this.nextEntry = next;
    }



    //Getter und Setter für die Spieler
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    //Aktuellen Spieler bekommen
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    //Zum nächsten Spieler wechseln
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    //Spielfigur auf dem Brett platzieren
    public boolean placeFigure(int x, int y) {
        // Überprüfen, ob die Position für den aktuellen Spieler gültig ist
        if (!getCurrentPlayer().canPlaceFigure(x, y)) {
            return false;
        }
        // Stellt die Figur auf das Brett
        board.placeFigure(x, y, getCurrentPlayer());
        // Aktualisiere die Punktzahl des Spielers
        board.evaluatePoints();
        return true;
    }

    public void drawTile(){

    }

    //Spielbrett initialisieren, Spieler erstellen
    public void startNewGame(int numPlayers) {
        board = new Board(this);
        players = FXCollections.observableArrayList(Player.createPlayers(numPlayers));
        //aktuellen Spieler als ersten Spieler in der Reihe festlegen
        currentPlayerIndex = 0;
        drawTile();
    }
}



class Player {
    private int score;

    //Liste von Spielern erstellen
    public static List<Player> createPlayers(int numPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
        }
        return players;
    }

    //Getter und Setter für den Punktestand
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    //prüft, ob eine Figur an Koordinate x und y platziert werden kann
    public boolean canPlaceFigure(int x, int y) {
        //implementation
        return false;
    }

    //Punktestand aktualisieren
    public void updateScore(int points) {
        // implementation
    }
}

