package com.javaoo.store;

public class Artist {

    //constructors
    public Artist(String name) {
        this.name = name;
    }

    //variables
    private String name;
    private String[] memberNames = new String[20];
    private String[] memberInstruments = new String[20];

    //getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
    
}
