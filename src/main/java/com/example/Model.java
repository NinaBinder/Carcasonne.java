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

    private Board board;
    TileLibrary library;
    private LibraryEntry nextEntry;
    private List<Player> players;
    private int currentPlayerIndex;
    private Controller controller;



    public Model (){

        board = new Board(this);
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

        System.out.println("Rel Position of next Tile: " + relX + "," +relY);
        Tile targetTile = getBoard().getRelativeTile(relX, relY);
        System.out.println("target Tile Entry: " + targetTile.getEntry());

        Tile northTile= getNorthTile(targetTile.getRelX(), targetTile.getRelY());
        Tile southTile= getSouthTile(targetTile.getRelX(), targetTile.getRelY());
        Tile eastTile= getEastTile(targetTile.getRelX(), targetTile.getRelY());
        Tile westTile= getWestTile(targetTile.getRelX(), targetTile.getRelY());
        //System.out.println(westTile);

        String nextEntry = controller.getLibrary().getNameOfEntry(this.nextEntry);
        System.out.println("next Tile Entry: " + nextEntry);

        Tile nextTile= new Tile(relX, relY, targetTile==null ? 0 : targetTile.getRotation(), nextEntry, targetTile==null ? false : targetTile.gamePiece);

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
    public boolean tileMatch (Tile tile, Tile northTile, Tile southTile, Tile eastTile, Tile westTile){

        if(isEmpty(northTile)&& isEmpty(eastTile) && isEmpty(southTile) && isEmpty(westTile) ){
            return false;
        }

        if(northTile!=null ){
            if( tile.getNorthEdge() != northTile.getSouthEdge() || northTile.getSouthEdge() == LibraryEntry.Component.UNIVERSAL ){
                System.out.println("new Tile northEdge:" + tile.getNorthEdge() + "=?" + northTile.getSouthEdge());
                return false;
            }
        }
        if(eastTile!=null){
            if(tile.getEastEdge() !=  eastTile.getWestEdge() || eastTile.getWestEdge() == LibraryEntry.Component.UNIVERSAL){
                System.out.println("new Tile eastEdge:" + tile.getEastEdge() + "=?" + eastTile.getWestEdge() );
                return false;
            }
        }

        if(westTile!=null){
            if(tile.getWestEdge() != westTile.getEastEdge() || westTile.getEastEdge() == LibraryEntry.Component.UNIVERSAL){
                System.out.println("new Tile westEdge:" + tile.getWestEdge() + "=?" + westTile.getEastEdge() );
                return false;
            }
        }
        if(southTile!=null){
            if(tile.getSouthEdge() != southTile.getNorthEdge() || southTile.getNorthEdge() == LibraryEntry.Component.UNIVERSAL){
                System.out.println("new Tile southEdge:" + tile.getSouthEdge() + "=?" + southTile.getNorthEdge() );
                return false;
            }
        }
        return true;
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

