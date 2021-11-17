package com.example.plantmanager.models;

public class Plant {
    private final int ID;
    private String name;

    public Plant() {
        ID = -1;
    }

    public Plant(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
