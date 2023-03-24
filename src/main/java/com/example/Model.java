package com.example;


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


    // Getter und Setter für die Spieler
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    // Methode, um den aktuellen Spieler zu bekommen
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }



    public void neueKarte (){
    //gib random Karte aus
    }

}
