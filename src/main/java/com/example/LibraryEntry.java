package  com.example;

import javafx.scene.image.Image;

/** the class LibraryEntry describes tile objects */
public class LibraryEntry {

    /** declaration of attributes of the class LibraryEntry.
     */

    //(a)attribute imagePath describes the path of each stored image of each tile.
    String imagePath;

    //(b)array sockets store sockets of the tile object
    Socket[] sockets;


    //feature class describes the features of the tile object.
    static class Feature {
        Component type;
        int[] socketindices;

        public Feature(Component type, int ... socketindices) {
            this.type = type;
            this.socketindices = socketindices;
        }
    }

    //(c)an instance of the image as a JavaFX Node (Image).
    Image img;

    /**constructor of LibraryEntry:
     * parameters of the constructor: imagepath and Feature. The values of them will be set in
     * the TileLibrary class when these objects are created by calling this constructor.
     * Image instance of the class initialised directly within the constructor with passing the value of
     * parameter path to the attribute "path" of Class Image..
     * array sockets initialised within the constructor by reading out data stored in features */

    public LibraryEntry( String path, Feature ... features){
        this.imagePath = path;
        this.img = new Image(path);
        this.sockets = new Socket[12];
        int counterROAD = 0;
        int counterFIELD = 0;
        int counterCITY = 0;


        // initialise Socket Array
            for (Feature feature: features){
                switch (feature.type){
                    case FIELD :
                    {
                        for (int socket: feature.socketindices){
                            this.sockets[socket] = new Socket(Component.FIELD, counterFIELD);
                        }
                    }

                    case ROAD  :
                    {
                        for (int socket: feature.socketindices){
                            this.sockets[socket] = new Socket(Component.ROAD, counterROAD);
                        }
                    }

                    case CITY  :
                    {
                        for (int socket: feature.socketindices){
                            this.sockets[socket] = new Socket(Component.CITY, counterCITY);
                        }
                    }

                if(feature.type == Component.FIELD){
                    counterFIELD++;
                }
                else if(feature.type == Component.ROAD){
                    counterROAD++;
                }
                else if(feature.type == Component.CITY){
                    counterCITY++;
                }


                }


            }


    }
    }


