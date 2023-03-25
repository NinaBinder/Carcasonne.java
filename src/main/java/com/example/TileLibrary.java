package com.example;
import javafx.scene.image.Image;
import java.util.HashMap;


/**The TileLibrary serves as a reference work for all possible tiles.
 * This Class has one attribute HashMap, where all objects of LibraryEntry are stored. */
public class TileLibrary {

    //Die TileLibrary dient als Klasse als Nachschlagewerk aller möglichen Kacheln.
    //Sie enthält eine Hashmap, die die Buchstaben Benennung der Felder in der Anleitung
    //(siehe Seite B1) auf einen LibraryEntry mapt. Sie enthält Methoden um auf die
    //Einträge über die Buchstaben Benennung oder zufällig zuzugreifen.

    /** map containg all possible type of tiles */
    HashMap <String, LibraryEntry> map;
    String path;

    /** creating entries for all possible tiles */
    LibraryEntry a= new LibraryEntry("file:src/fields/A.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11),
            new LibraryEntry.Feature(Component.ROAD, 7)
    ){};

    LibraryEntry b= new LibraryEntry("file:src/fields/B.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    ){};

    LibraryEntry c= new LibraryEntry("file:src/fields/C.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    ){};

    // for tile of image d
    LibraryEntry d= new LibraryEntry("file:src/fields/D.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 8, 9, 10, 11),
            new LibraryEntry.Feature(Component.ROAD, 1, 7),
            new LibraryEntry.Feature(Component.FIELD, 2, 6),
            new LibraryEntry.Feature(Component.CITY, 3, 4, 5)
    ){};

    LibraryEntry originalTile= new LibraryEntry("file:src/fields/D.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 8, 9, 10, 11),
            new LibraryEntry.Feature(Component.ROAD, 1, 7),
            new LibraryEntry.Feature(Component.FIELD, 2, 6),
            new LibraryEntry.Feature(Component.CITY, 3, 4, 5)
    ){};

    LibraryEntry e= new LibraryEntry("file:src/fields/E.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2),
            new LibraryEntry.Feature(Component.FIELD, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    ){};

    LibraryEntry f= new LibraryEntry("file:src/fields/F.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 1, 2),
            new LibraryEntry.Feature(Component.CITY, 3, 4, 5, 9, 10, 11),
            new LibraryEntry.Feature(Component.FIELD, 6, 7, 8)
    ){};

    LibraryEntry g= new LibraryEntry("file:src/fields/G.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 6, 7, 8),
            new LibraryEntry.Feature(Component.FIELD, 3, 4, 5),
            new LibraryEntry.Feature(Component.FIELD, 9, 10, 11)
    ){};

    // for tile of image h
    LibraryEntry h= new LibraryEntry("file:src/fields/H.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 1, 2, 6, 7, 8),
            new LibraryEntry.Feature(Component.CITY, 3, 4, 5),
            new LibraryEntry.Feature(Component.CITY, 9, 10, 11)
    ){};

    LibraryEntry i= new LibraryEntry("file:src/fields/I.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 1, 2, 6, 7, 8),
            new LibraryEntry.Feature(Component.CITY, 3, 4, 5),
            new LibraryEntry.Feature(Component.CITY, 9, 10, 11)
    ){};

    // for tile of image j
    LibraryEntry j = new LibraryEntry("file:src/fields/J.png",
            new LibraryEntry.Feature(Component.FIELD, 3, 8, 9, 10, 11),
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2),
            new LibraryEntry.Feature(Component.ROAD, 4, 7),
            new LibraryEntry.Feature(Component.FIELD, 5, 6)){};


    // for tile of image k
    LibraryEntry k = new LibraryEntry("file:src/fields/K.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 11),
            new LibraryEntry.Feature(Component.ROAD, 1, 10),
            new LibraryEntry.Feature(Component.FIELD, 2, 6, 7, 8, 9),
            new LibraryEntry.Feature(Component.CITY, 3, 4, 5)){};

    LibraryEntry l = new LibraryEntry("file:src/fields/L.png",
            new LibraryEntry.Feature(Component.ROAD, 1, 7, 10),
            new LibraryEntry.Feature(Component.FIELD, 0, 11),
            new LibraryEntry.Feature(Component.FIELD, 2, 6),
            new LibraryEntry.Feature(Component.CITY, 3, 4, 5)
    ){};

    LibraryEntry m = new LibraryEntry("file:src/fields/M.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 9, 10, 11),
            new LibraryEntry.Feature(Component.FIELD, 3, 4, 5, 6, 7, 8)
    ){};

    LibraryEntry n = new LibraryEntry("file:src/fields/N.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 9, 10, 11),
            new LibraryEntry.Feature(Component.FIELD, 3, 4, 5, 6, 7, 8)
    ){};

    LibraryEntry o = new LibraryEntry("file:src/fields/O.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 9, 10, 11),
            new LibraryEntry.Feature(Component.FIELD, 3, 8),
            new LibraryEntry.Feature(Component.ROAD, 4, 7),
            new LibraryEntry.Feature(Component.FIELD, 5, 6)
    ){};

    LibraryEntry p = new LibraryEntry("file:src/fields/P.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 9, 10, 11),
            new LibraryEntry.Feature(Component.FIELD, 3, 8),
            new LibraryEntry.Feature(Component.ROAD, 4, 7),
            new LibraryEntry.Feature(Component.FIELD, 5, 6)
    ){};

    LibraryEntry q = new LibraryEntry("file:src/fields/Q.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 3,4,5 ,9, 10, 11),
            new LibraryEntry.Feature(Component.FIELD, 6, 7, 8)
    ){};

    LibraryEntry r = new LibraryEntry("file:src/fields/R.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 3,4,5 ,9, 10, 11),
            new LibraryEntry.Feature(Component.FIELD, 6, 7, 8)
    ){};

    LibraryEntry s = new LibraryEntry("file:src/fields/S.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 3,4,5 ,9, 10, 11),
            new LibraryEntry.Feature(Component.FIELD, 8),
            new LibraryEntry.Feature(Component.ROAD, 7),
            new LibraryEntry.Feature(Component.FIELD, 6)

    ){};

    LibraryEntry t = new LibraryEntry("file:src/fields/T.png",
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 3,4,5 ,9, 10, 11),
            new LibraryEntry.Feature(Component.FIELD, 8),
            new LibraryEntry.Feature(Component.ROAD, 7),
            new LibraryEntry.Feature(Component.FIELD, 6)

    ){};

    LibraryEntry u = new LibraryEntry("file:src/fields/U.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 8, 9, 10, 11),
            new LibraryEntry.Feature(Component.ROAD, 1, 7),
            new LibraryEntry.Feature(Component.FIELD, 2, 3, 4, 5, 6)

    ){};

    LibraryEntry v = new LibraryEntry("file:src/fields/V.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 9, 10, 11),
            new LibraryEntry.Feature(Component.ROAD, 1, 7),
            new LibraryEntry.Feature(Component.FIELD, 2, 3, 4, 5, 6)
    ){};

    LibraryEntry w =new LibraryEntry("file:src/fields/W.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 1, 2, 3, 4, 5, 6),
            new LibraryEntry.Feature(Component.ROAD, 7, 10),
            new LibraryEntry.Feature(Component.FIELD, 8, 9)
    ){};

    LibraryEntry x =new LibraryEntry("file:src/fields/X.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 10),
            new LibraryEntry.Feature(Component.FIELD, 2, 3),
            new LibraryEntry.Feature(Component.FIELD, 5, 6),
            new LibraryEntry.Feature(Component.FIELD, 8, 9),
            new LibraryEntry.Feature(Component.ROAD, 1, 4, 7, 10)
    ){};

    LibraryEntry empty= new LibraryEntry("file:src/fields/Empty.png",
            new LibraryEntry.Feature(Component.FIELD, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            new LibraryEntry.Feature(Component.CITY, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            new LibraryEntry.Feature(Component.ROAD, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    ){};

    //put  all the tile entries into the map
    public TileLibrary( ){
        this.map= new HashMap<String, LibraryEntry>();
        map.put("A",a);
        map.put("B",b);
        map.put("C",c);
        map.put("D",d);
        map.put("E",e);
        map.put("F",f);
        map.put("G",g);
        map.put("H",h);
        map.put("I",i);
        map.put("J",j);
        map.put("K",k);
        map.put("L",l);
        map.put("M",m);
        map.put("N",n);
        map.put("O",o);
        map.put("P",p);
        map.put("Q",q);
        map.put("R",r);
        map.put("S",s);
        map.put("T",t);
        map.put("U",u);
        map.put("V",v);
        map.put("W",w);
        map.put("X",x);
        map.put("EMPTY",empty);
        map.put("OG",originalTile);

    }
    public Image getImage(LibraryEntry entry){
        return entry.img;
    }
}