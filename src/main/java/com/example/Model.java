package com.example;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;

//Das Model enthält die Daten des Spiels

public class Model {
    private Board board;
    private Tile next;
    private Controller controller;


    public void setController(Controller controller){
        this.controller = controller;
    }
