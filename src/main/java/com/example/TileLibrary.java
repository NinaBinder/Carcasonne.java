package com.example;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.HashMap;

public class TileLibrary{
    HashMap<String, LibraryEntry> map;

    public TileLibrary(HashMap<String, LibraryEntry> map){
        this.map= map;

        map.put("K",k);
        map.put("D",d);
        map.put("V",v);
        map.put("W",w);
        map.put("H",h);
        map.put("J",j);

    }

    LibraryEntry k = new LibraryEntry("file:src/field/A.png",

            new LibraryEntry.Feature("field",0,11),
            new LibraryEntry.Feature("road",1,10),
            new LibraryEntry.Feature("field",2,6,7,8,9),
            new LibraryEntry.Feature("city",3,4,5)
    ) {};
    LibraryEntry d= new LibraryEntry("file:src/field/D.png",
            new LibraryEntry.Feature("field",0,8,9,10,11),
            new LibraryEntry.Feature("road",1,7),
            new LibraryEntry.Feature("field",2,6),
            new LibraryEntry.Feature("city",3,4,5)
    ){};
    LibraryEntry h= new LibraryEntry("file:src/field/D.png",
            new LibraryEntry.Feature("field",0,1,2,6,7,8),
            new LibraryEntry.Feature("city",3,4,5),
            new LibraryEntry.Feature("city",9,10,11)
    ){};

    // for tile of image v
    LibraryEntry v = new LibraryEntry("file:src/field/V.png",
            new LibraryEntry.Feature("field",0,9, 10,11),
            new LibraryEntry.Feature("road",1,7),
            new LibraryEntry.Feature("field",2,3,4,5,6)
    ){};


    // for tile of image w
    LibraryEntry w = new LibraryEntry("file:src/field/W.png",
            new LibraryEntry.Feature("field",0,1,2,3,4,5,6),
            new LibraryEntry.Feature("road",7,10),
            new LibraryEntry.Feature("field",8,9)
    ){};


    // for tile of image j
    LibraryEntry j = new LibraryEntry("file:src/field/J.png",
            new LibraryEntry.Feature("field",3,8,9,10,11),
            new LibraryEntry.Feature("city",0,1,2),
            new LibraryEntry.Feature("road",4,7),
            new LibraryEntry.Feature("field",5,6)
    ){};
}