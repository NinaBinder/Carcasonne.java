package com.example;

public class Position {
    private final int relX;
    private final int relY;

    public Position(int relX, int y){
        this.relX = relX;
        this.relY =y;
    }

    public int getRelX() {
        return relX;
    }

    public int getRelY() {
        return relY;
    }
}
