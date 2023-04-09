package com.example;
import javafx.scene.image.Image;

import java.util.HashMap;


/**The TileLibrary serves as a reference work for all possible tiles.
 * This Class has one attribute HashMap, where all objects of LibraryEntry are stored. */
public class TileLibrary {

    /** map containg all possible type of tiles */
    HashMap <String, LibraryEntry> map;

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

    /** creating entries for all possible tiles */
    LibraryEntry a = new LibraryEntry("file:src/fields/A.png",
            new LibraryEntry.Component[]{LibraryEntry.Component.FIELD, LibraryEntry.Component.FIELD, LibraryEntry.Component.ROAD, LibraryEntry.Component.FIELD} ){
    };

    LibraryEntry b = new LibraryEntry("file:src/fields/B.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.FIELD}
    ){};

    LibraryEntry c= new LibraryEntry("file:src/fields/C.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.CITY
    }){};

    // for tile of image d
    LibraryEntry d= new LibraryEntry("file:src/fields/D.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.FIELD
    }){};


    LibraryEntry originalTile= new LibraryEntry("file:src/fields/D.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.FIELD
            }){};


    LibraryEntry e= new LibraryEntry("file:src/fields/E.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.FIELD
            }){};


    LibraryEntry f= new LibraryEntry("file:src/fields/F.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY
            }){};


    LibraryEntry g= new LibraryEntry("file:src/fields/G.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD
            }){};


    // for tile of image h
    LibraryEntry h= new LibraryEntry("file:src/fields/H.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY
            }){};


    LibraryEntry i= new LibraryEntry("file:src/fields/I.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD
            }){};


    // for tile of image j
    LibraryEntry j = new LibraryEntry("file:src/fields/J.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.FIELD
            }){};


    // for tile of image k
    LibraryEntry k = new LibraryEntry("file:src/fields/K.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.ROAD
            }){};


    LibraryEntry l = new LibraryEntry("file:src/fields/L.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD
            }){};

    LibraryEntry m = new LibraryEntry("file:src/fields/M.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY
            }){};


    LibraryEntry n = new LibraryEntry("file:src/fields/N.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY
            }){};


    LibraryEntry o = new LibraryEntry("file:src/fields/O.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.CITY
            }){};

    LibraryEntry p = new LibraryEntry("file:src/fields/P.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.CITY
            }){};

    LibraryEntry q = new LibraryEntry("file:src/fields/Q.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY
            }){};


    LibraryEntry r = new LibraryEntry("file:src/fields/R.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.CITY
            }){};


    LibraryEntry s = new LibraryEntry("file:src/fields/S.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.CITY
            }){};


    LibraryEntry t = new LibraryEntry("file:src/fields/T.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.CITY,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.CITY
            }){};


    LibraryEntry u = new LibraryEntry("file:src/fields/U.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.FIELD
            }){};


    LibraryEntry v = new LibraryEntry("file:src/fields/V.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD
            }){};


    LibraryEntry w =new LibraryEntry("file:src/fields/W.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.FIELD,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD
            }){};


    LibraryEntry x =new LibraryEntry("file:src/fields/X.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD,
                    LibraryEntry.Component.ROAD
            }){};


    LibraryEntry empty= new LibraryEntry("file:src/fields/Empty.png",
            new LibraryEntry.Component[]{
                    LibraryEntry.Component.UNIVERSAL,
                    LibraryEntry.Component.UNIVERSAL,
                    LibraryEntry.Component.UNIVERSAL,
                    LibraryEntry.Component.UNIVERSAL
            }){};

    public String getNameOfEntry(LibraryEntry libraryEntry){
        for(String entry : map.keySet()){
            if(map.get(entry).equals(libraryEntry)){
                return entry;
            }
        }
        return null;
    }
    public Image getImage(String entry){
         return map.get(entry).image;
    }


}