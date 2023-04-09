package  com.example;

import javafx.scene.image.Image;

/** the class LibraryEntry describes tile objects */
public class LibraryEntry {
    enum Component{ROAD, CITY, FIELD, UNIVERSAL}
    Component[] components;
    Image image;

    /**
     * parameters of the constructor: imagepath and Feature. The values of them will be set in
     * the TileLibrary class when these objects are created by calling this constructor.
     * Image instance of the class initialised directly within the constructor with passing the value of
     * parameter path to the attribute "path" of Class Image..
     * array sockets initialised within the constructor by reading out data stored in features */

    public LibraryEntry(String path, Component[] components) {
        this.image = new Image(path);
        this.components = components;
    }
    public Component[] getComponent() {
        return components;
    }
}