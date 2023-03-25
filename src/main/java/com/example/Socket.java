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

}
