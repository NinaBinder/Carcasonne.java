package  com.example;

        import javafx.scene.image.Image;

public class LibraryEntry {

    String imagePath;
    Image image;

    public LibraryEntry( String path, Image image ,Feature ... features){
        this.imagePath = path;
        image= path;

    }

    //for now saving all the image names paths in an array
    Image imgA= A.png";

    /*"B.png","C.png",
            "D.png",
            "E.png",
            "F.png",
            "G.png",
            "H.png",
            "I.png",
            "J.png",
            "K.png",
            "L.png",
            "M.png",
            "N.png",
            "O.png",
            "P.png",
            "Q.png",
            "S.png",
            "T.png",
            "U.png",
            "V.png",
            "W.png",
            "X.png",
            "Y.png"};*/


    // arrtributes ryentry
     //Enthält die Beschreibung eines Spielfelds, dies wird zur Erzeugen der Tile Objekte genutzt.
     //ein LibraryEntry enthält:
     // (a) String mit url zur Ablage des Bilds;



     //a des""R.png!,ription of the sockets in the Feature class e.g. new Feature("city", 3,4,5)
     // new Feature("Fields",1.7)
     class Feature {
     String type;
     int[] sockets;

     public Feature(String type, int ... sockets) {
     this.type = type;
     this.sockets = sockets;
     }


     //eine Instanz des Bildes als JavaFX Node (Image).




     /**constructor of LibraryEntry:Der Konstruktor nimmt (mindestens) einen String mit der Bild-url und ein Arbitrary
     Number Argument, das die Features eines Feldes beschreibt, daraus werden im
     Konstruktor die Sockets erzeugt..*/





}}

