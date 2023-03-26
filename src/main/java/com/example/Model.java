package com.example;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;



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

    public Model (){
        board = new Board();
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
        board = new Board();
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

