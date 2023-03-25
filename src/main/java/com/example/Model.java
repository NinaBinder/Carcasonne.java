package com.example;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import your.package.Player;


public class Model {
    //Das Model enthält die Daten des Spiels:
    // (a) eine Board Instanz;
    // (b) ein Tile Objekt für das nächste Feld;
    // (c) Informationen zu den Spielern;
    // (d) Methoden zum Lesen und Setzen der Daten und zum Ausführen (bzw. Prüfen) von Spielaktionen


    private Board board;
    private Tile next = new Tile();
    private List<Player> players;
    private int currentPlayerIndex;

    public Model (){
        Tile tile = new Tile();
        board = new Board(tile);

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
        getCurrentPlayer().updateScore(board.evaluatePoints(x, y));
        return true;
    }

    //neue Kachel ziehen
    public void drawTile() {
        setNextTile(Tile.getRandomTile());
    }

    // Methode zur Bewertung der Punkte für alle Spieler basierend auf dem aktuellen Stand des Boards
    public void evaluatePoints() {
        for (Player player : players) {
            player.setScore(board.evaluatePoints(player));
        }
    }


    //Spielbrett initialisieren, Spieler erstellen.
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
        // implementation
    }

    //Punktestand aktualisieren
    public void updateScore(int points) {
        // implementation
    }
}

class Tile {
    // implementation
}

class Board {
    // implementation
}


    public void setLocation(int x, int y) {
        this.location = board.matrix[x][y];
    }
    public Integer getLocation() {return location;} //get the location of the tile where the meeple is placed

    public Integer getOwner() {
        return owner;
    }
    public boolean hasFreeMeeples() {
        return freeMeeples > 0;
    }
    public boolean isPlaced() {
        if (location != null){ return true;}}

    private boolean isPatternClosed() {
        boolean isClosed = true;
        for (//every side, get direct neighbours)
        {
            if (//sockets match and are connected)) {
            //get neighbour;
            if (get.Neighbor() == null) { // if it has no neighbor
                isClosed = false;
            } else { // continue on neighbors
                isClosed &= checkNeighbor(); //to do: check neighbour methode schreiben
            }
        }return isClosed;
    }

    public Component getType(Tile tile) {
        if (tile.gamePiece=true) {return type);} //man braucht von dem Entry das Feature und davon die Componente type
    }
    /*public Image getMeeple() {
            freeMeeples=10;
        if (hasFreeMeeples()) {
            freeMeeples--;
            Image meeple = new Image();
            placedMeeples.add(meeple);
            assert placedMeeples.size() <= 10; //= max Meeples
            return meeple;
        }
        throw new IllegalStateException("No unused meeples are left.");
    }
    */
    public void addPoints () {
        for (placedMeeples) {
            score = 0;
            //points.setText("Score: " + score); Label in View
            if (isPatternClosed())
                switch (feature.type){
                    case FIELD -> score + 3
                    case CITY -> score + 3
                    case ROAD -> score + 3
                }
        }
    }
    public void removeMeeple () {
        if (isPatternClosed=true && isPlaced()) { //
            //return meeple,
            freeMeeples++;
            location = null; // mark as unplaced.
        }
    }

    public String tellScore() {
        return "Player[number: " + playerNumber[x] + ", score: " + score + ", free meeples: " + freeMeeples + "]";
    }// Label text!

    public int getPlayer() {
        return playerNumber[x];
    }
}
