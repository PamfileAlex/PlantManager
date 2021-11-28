package com.example.plantmanager.models;

import java.util.Date;

public class Plant {
//    private final int ID;
//    private String title;
//
//    public Plant() {
//        ID = -1;
//    }
//
//    public Plant(int ID, String title) {
//        this.ID = ID;
//        this.title = title;
//    }
//
//    public int getID() {
//        return ID;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

    private String name;
    private String imagePath;
    private Date lastWatered;

    public Plant(String name, String imagePath, Date lastWatered) {
        this.name = name;
        this.imagePath = imagePath;
        this.lastWatered = lastWatered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(Date lastWatered) {
        this.lastWatered = lastWatered;
    }
}
