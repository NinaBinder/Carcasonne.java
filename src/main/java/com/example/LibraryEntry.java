package  com.example;

import javafx.scene.image.Image;

public class LibraryEntry {

    /**constructor of LibraryEntry:Der Konstruktor nimmt (mindestens) einen String mit der Bild-url und ein Arbitrary
     Number Argument, das die Features eines Feldes beschreibt, daraus werden im
     Konstruktor die Sockets erzeugt..*/

    public LibraryEntry( String path, Feature ... features){
        this.imagePath = path;


    }

    /** declaration of arrtributes for one Libraryentry
     */
    // (a) String mit url zur Ablage des Bilds;
    String imagePath;


    //(b) a description of the sockets in the Feature class e.g. new Feature("city", 3,4,5)
    // new Feature("Fields",1.7)
    class Feature {
        String type;
        int[] sockets;

        public Feature(String type, int ... sockets) {
            this.type = type;
            this.sockets = sockets;
        }

    }

    //eine Instanz des Bildes als JavaFX Node (Image).
    Image img;





    }
