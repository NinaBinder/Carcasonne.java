package com.example;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**The TileLibrary serves as a reference work for all possible tiles.
 * This Class has one attribute HashMap, where all objects of LibraryEntry are stored. */
public class TileLibrary {

    //Die TileLibrary dient als Klasse als Nachschlagewerk aller möglichen Kacheln.
    //Sie enthält eine Hashmap, die die Buchstaben Benennung der Felder in der Anleitung
    //(siehe Seite B1) auf einen LibraryEntry mapt. Sie enthält Methoden um auf die
    //Einträge über die Buchstaben Benennung oder zufällig zuzugreifen.

    /**The */
    HashMap <String, LibraryEntry> map = new HashMap<String, LibraryEntry>();


    /** creating all possible tile objects. */

    // for tile of image d
    LibraryEntry d= new LibraryEntry("file:src/field/D.png",
            new LibraryEntry.Feature(Component.FIELD,0,8,9,10,11),
            new LibraryEntry.Feature(Component.ROAD,1,7),
            new LibraryEntry.Feature(Component.FIELD,2,6),
            new LibraryEntry.Feature(Component.CITY,3,4,5)
    ){};



    // for tile of image h
    LibraryEntry h= new LibraryEntry("file:src/field/H.png",
            new LibraryEntry.Feature(Component.FIELD,0,1,2,6,7,8),
            new LibraryEntry.Feature(Component.CITY,3,4,5),
            new LibraryEntry.Feature(Component.CITY,9,10,11)
    ){};

    // for tile of image j
    LibraryEntry j = new LibraryEntry("file:src/field/J.png",
            new LibraryEntry.Feature(Component.FIELD,3,8,9,10,11),
            new LibraryEntry.Feature(Component.CITY,0,1,2),
            new LibraryEntry.Feature(Component.ROAD,4,7),
            new LibraryEntry.Feature(Component.FIELD,5,6) ){};


    // for tile of image k
    LibraryEntry k = new LibraryEntry("file:src/field/K.png",
            new LibraryEntry.Feature(Component.FIELD,0,11),
            new LibraryEntry.Feature(Component.ROAD,1,10),
            new LibraryEntry.Feature(Component.FIELD,2,6,7,8,9),
            new LibraryEntry.Feature(Component.CITY,3,4,5) ){};


    // for tile of image v
    LibraryEntry v = new LibraryEntry("file:src/field/V.png",
            new LibraryEntry.Feature(Component.FIELD,0,9,
    10,11), new LibraryEntry.Feature(Component.ROAD,1,7),
            new LibraryEntry.Feature(Component.FIELD,2,3,4,5,6)){};


    // for tile of image w
    LibraryEntry w =new LibraryEntry("file:src/field/W.png",
                    new LibraryEntry.Feature(Component.FIELD,0,1,2,3,4,5,6),
                    new LibraryEntry.Feature(Component.ROAD,7,10),
                    new LibraryEntry.Feature(Component.FIELD,8,9)){};



    //put  all the tile entries into the map
    public TileLibrary( ){
        map.put("D",d);
        map.put("H",h);
        map.put("J",j);
        map.put("K",k);
        map.put("V",v);
        map.put("W",w);


    }


}
