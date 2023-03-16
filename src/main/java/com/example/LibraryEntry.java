package  com.example;

import javafx.scene.image.Image;

/** the class LibraryEntry describes tile objects */
public class LibraryEntry {

    /** declaration of attributes of the class LibraryEntry.
     */

    //(a)attribute imagePath describes the path of each stored image of each tile.
    String imagePath;


    //(b)a description of the sockets in the Feature class e.g. new Feature("city", 3,4,5)
    // new Feature("Fields",1.7).
    class Feature {
        String type;
        int[] sockets;

        public Feature(String type, int ... sockets) {
            this.type = type;
            this.sockets = sockets;
        }
    }

    //(c)an instance of the image as a JavaFX Node (Image).
    Image img;

    /**constructor of LibraryEntry:
     * parameters of the constructor: imagepath and Feature. The values of them will be set in
     * the TileLibrary class when these objects are created by calling this constructor.
     * Image instance of the class initialised directly within the constructor with passing the value of
     * parameter path to the attribute "path" of Class Image..*/

    public LibraryEntry( String path, Feature ... features){
        this.imagePath = path;
        this.img = new Image(path);
    }

    }
