package com.example.plantmanager.models;

public class Plant {
    private final int ID;
    private String title;

    public Plant() {
        ID = -1;
    }

    public Plant(int ID, String title) {
        this.ID = ID;
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
