package com.example;

import java.lang.reflect.Field;

enum Component{CITY, ROAD, FIELD};

public class Socket {

    /**Attributes of Socket*/
    private Component component;
    private int id;

    /**getter*/
    public Component getComponent() {
        return component;
    }

    public int getId() {
        return id;
    }

    /**setter*/
    public void setComponent(Component component) {
        this.component = component;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**constructor*/
    public Socket(Component component, int id){
        this.component = component;
        this.id = id;

    }

    /**creating instances of the object socket*/
    // field socket
    Socket f0 = new Socket(Component.FIELD,0);

    // city socket
    // 7 cities in the map -> 7 different types of cities
    Socket c0 = new Socket(Component.CITY,0);
    Socket c1 = new Socket(Component.CITY,1);
    Socket c2 = new Socket(Component.CITY,2);
    Socket c3 = new Socket(Component.CITY,3);
    Socket c4 = new Socket(Component.CITY,4);
    Socket c5 = new Socket(Component.CITY,5);
    Socket c6 = new Socket(Component.CITY,6);

    // road socket
    Socket r0 = new Socket(Component.ROAD,0);



}
