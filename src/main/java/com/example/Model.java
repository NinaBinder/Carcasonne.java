package com.example;
import java.util.List;


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

    public boolean canPlaceFigure(int x, int y) {
        // implementation
    }

    public void updateScore(int points) {
        // implementation
    }
}


    public void neueKarte (){
    //gib random Karte aus
    }

}
