package com.example;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class TileLibrary {
    //Die TileLibrary dient als Klasse als Nachschlagewerk aller möglichen Kacheln.
    //Sie enthält eine Hashmap, die die Buchstaben Benennung der Felder in der Anleitung
    //(siehe Seite B1) auf einen LibraryEntry mapt. Sie enthält Methoden um auf die
    //Einträge über die Buchstaben Benennung oder zufällig zuzugreifen.

    /** map containg all possible type of tiles */
    HashMap <String, LibraryEntry> map = new HashMap<String, LibraryEntry>();



    /** creating entries for all possible tiles */

    // for tile of image d
    LibraryEntry d= new LibraryEntry("file:src/field/D.png", new Image("file:src/field/D.png"),
            new LibraryEntry.Feature("field",0,8,9,10,11),
            new LibraryEntry.Feature("road",1,7),
            new LibraryEntry.Feature("field",2,6),
            new LibraryEntry.Feature("city",3,4,5)
    ){};



    // for tile of image h
    LibraryEntry h= new LibraryEntry("file:src/field/H.png", new Image("file:src/field/H.png"),
            new LibraryEntry.Feature("field",0,1,2,6,7,8),
            new LibraryEntry.Feature("city",3,4,5),
            new LibraryEntry.Feature("city",9,10,11)
    ){};

    // for tile of image j
    LibraryEntry j = new LibraryEntry("file:src/field/J.png", new Image("file:src/field/J.png"),
            new LibraryEntry.Feature("field",3,8,9,10,11),
            new LibraryEntry.Feature("city",0,1,2),
            new LibraryEntry.Feature("road",4,7),
            new LibraryEntry.Feature("field",5,6) ){};


    // for tile of image k
    LibraryEntry k = new LibraryEntry("file:src/field/K.png", new Image("file:src/field/K.png"),
            new LibraryEntry.Feature("field",0,11),
            new LibraryEntry.Feature("road",1,10),
            new LibraryEntry.Feature("field",2,6,7,8,9),
            new LibraryEntry.Feature("city",3,4,5) ){};


    // for tile of image v
    LibraryEntry v = new LibraryEntry("file:src/field/V.png", new Image("file:src/field/V.png"),
            new LibraryEntry.Feature("field",0,9,
    10,11), new LibraryEntry.Feature("road",1,7),
            new LibraryEntry.Feature("field",2,3,4,5,6)){};


    // for tile of image w
    LibraryEntry w =new LibraryEntry("file:src/field/W.png", new Image("file:src/field/W.png"),
                    new LibraryEntry.Feature("field",0,1,2,3,4,5,6),
                    new LibraryEntry.Feature("road",7,10),
                    new LibraryEntry.Feature("field",8,9)){};







    //put  all the tile entries into the map
    public TileLibrary(HashMap <String, LibraryEntry> map){
        map.put("D",d);
        map.put("H",h);
        map.put("J",j);
        map.put("K",k);
        map.put("V",v);
        map.put("W",w);

    }






}
