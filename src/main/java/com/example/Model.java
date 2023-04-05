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
    private Tile next;
    private List<Player> players;
    private int currentPlayerIndex;
    private Controller controller;
    private TileLibrary library;
    //für Road und City Array Lists, visitedroad, visitedCity, einfügen wo ich schon war
    ArrayList<Tile> visitedRoad;
    ArrayList<Tile> visitedCity;

    public Model (){

        board = new Board(this);
        this.library = new TileLibrary();
    }

    public void initView(Image tileimage, int x, int y){
        controller.updateView(tileimage,x,y);

    }
    public void initBoard(){
        board.initBoard();
    }
    public void setController(Controller controller){
        this.controller = controller;
    }

    public boolean isRoadClosed(Tile lastPlacedTile, int relX, int relY) {

        boolean isClosed = true;
        for (int i=0; i<4; i++){
            Component c = lastPlacedTile.library.map.get(entry).getComponent()[0];
            //gibt es an dem Tile überhaupt ein Socket mit Component Road?
            if (lastPlacedTile.getSockets()[i].getComponent()==Component.ROAD){
                //wenn ja an welchen Sockets ist Road? TODO: zwei Prüfrichtungen! müssen beide erfüllt sein
                switch (lastPlacedTile.getRotation()%360){
                    case 0: //break;
                        switch (i) {
                            case 0:
                                getNorthTile(relX, relY);
                            case 1:
                                getEastTile(relX, relY);
                            case 2:
                                getSouthTile(relX, relY);
                            case 3:
                                getWestTile(relX, relY);
                        }
                    case 90: //break;
                        switch (i){
                            case 0: controller.getEastTile(relX, relY);
                            case 1: controller.getEastTile(relX, relY);
                            case 2: controller.getEastTile(relX, relY);
                            case 3: controller.getSouthTile(relX, relY);
                            case 4: controller.getSouthTile(relX, relY);
                            case 5: controller.getSouthTile(relX, relY);
                            case 6: controller.getWestTile(relX, relY);
                            case 7: controller.getWestTile(relX, relY);
                            case 8: controller.getWestTile(relX, relY);
                            case 9: controller.getNorthTile(relX, relY);
                            case 10: controller.getNorthTile(relX, relY);
                            case 11: controller.getNorthTile(relX, relY);
                        }
                    case 180: //break;
                        switch (i){
                            case 0: controller.getSouthTile(relX, relY);
                            case 1: controller.getSouthTile(relX, relY);
                            case 2: controller.getSouthTile(relX, relY);
                            case 3: controller.getWestTile(relX, relY);
                            case 4: controller.getWestTile(relX, relY);
                            case 5: controller.getWestTile(relX, relY);
                            case 6: controller.getNorthTile(relX, relY);
                            case 7: controller.getNorthTile(relX, relY);
                            case 8: controller.getNorthTile(relX, relY);
                            case 9: controller.getEastTile(relX, relY);
                            case 10: controller.getEastTile(relX, relY);
                            case 11: controller.getEastTile(relX, relY);
                        }
                    case 270: //break;
                        switch (i){
                            case 0: controller.getWestTile(relX, relY);
                            case 1: controller.getWestTile(relX, relY);
                            case 2: controller.getWestTile(relX, relY);
                            case 3: controller.getNorthTile(relX, relY);
                            case 4: controller.getNorthTile(relX, relY);
                            case 5: controller.getNorthTile(relX, relY);
                            case 6: controller.getEastTile(relX, relY);
                            case 7: controller.getEastTile(relX, relY);
                            case 8: controller.getEastTile(relX, relY);
                            case 9: controller.getSouthTile(relX, relY);
                            case 10: controller.getSouthTile(relX, relY);
                            case 11: controller.getSouthTile(relX, relY);
                        }
                        //wiederhole den prozess bis an Ending Tile angekommen
                        if (controller.getNorthTile(relX, relY).equals(library.x) ||
                                controller.getNorthTile(relX, relY).equals(library.u)){return isClosed;}
                }
            }
        }
        return isClosed;
    }
    //isCityClosedTop und isCityClosedRec draus machen, lastPlacedTile ist das currentTile?
    //Abbruchbedingung nach jedem Case!?
    private void recurse(Tile currentTile){visitedCity.add(currentTile);}
    public boolean isCityClosed(Tile lastPlacedTile, int relX, int relY) {
        boolean isClosed = true;
        Tile northTile, eastTile, southTile, westTile;
        for (int i=0; i<12; i++){
            Component c = lastPlacedTile.getSockets()[i].getComponent();
            if (lastPlacedTile.getSockets()[i].getComponent()==Component.CITY){
                switch (lastPlacedTile.getRotation()%360){
                    case 0:
                        switch (i) {
                            case 0,1,2:
                                northTile=controller.getNorthTile(relX, relY);
                                break;
                            case 3,4,5:
                                eastTile=controller.getEastTile(relX, relY);
                                break;
                            case 6,7,8:
                                southTile=controller.getSouthTile(relX, relY);
                                break;
                            case 9, 10, 11:
                                westTile=controller.getWestTile(relX, relY);
                                break;
                        }
                        break;
                    case 90:
                        switch (i){
                            case 0,1,2:
                                eastTile=controller.getEastTile(relX, relY);
                                break;
                            case 3,4,5:
                                southTile=controller.getSouthTile(relX, relY);
                                break;
                            case 6,7,8:
                                westTile=controller.getWestTile(relX, relY);
                                break;
                            case 9,10,11:
                                northTile=controller.getNorthTile(relX, relY);
                                break;
                        }
                    case 180:
                        switch (i){
                            case 0,1,2:
                                southTile=controller.getSouthTile(relX, relY);
                                break;
                            case 3,4,5:
                                westTile=controller.getWestTile(relX, relY);
                                break;
                            case 6,7,8:
                                northTile=controller.getNorthTile(relX, relY);
                                break;
                            case 9,10,11:
                                eastTile=controller.getEastTile(relX, relY);
                                break;
                        }
                    case 270:
                        switch (i){
                            case 0,1,2:
                                westTile=controller.getWestTile(relX, relY);
                                break;
                            case 3,4,5:
                                northTile=controller.getNorthTile(relX, relY);
                                break;
                            case 6,7,8:
                                eastTile=controller.getEastTile(relX, relY);
                                break;
                            case 9,10,11:
                                southTile=controller.getSouthTile(relX, relY);
                                break;
                        }
                }
                recurse();

                {return isClosed;}
            }
        }
        return isClosed;
    }
    boolean KlosterFertig (Tile lastPlacedTile){
        boolean isClosed = true;
        if (lastPlacedTile.equals(library.a) || lastPlacedTile.equals(library.b)){
            //prüfe, ob 8 umliegende Karten != empty
        }return isClosed;
    }
    public void addPoints (int score, Tile lastPlacedTile, int relX, int relY){
        for (int i=0; i<board.matrix.length; i++){
            if (isRoadClosed(lastPlacedTile, relX, relY) == true){
                score ++;
            }
            if (isCityClosed(lastPlacedTile, relX, relY) == true){
                score++;
            }
            if (KlosterFertig(lastPlacedTile) == true){
                score++;
            }
        }
    }

    public Board getBoard(){
        return this.board;
    }

    public Tile getNextTile(){
        return this.next;
    }

    public void setNextTile(Tile next){
        this.next = next;
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

    //nextile im board setzen, true muss zurückgegeben werden wenn es geklappt hat
    public void tryPlaceTile(Position position){


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

