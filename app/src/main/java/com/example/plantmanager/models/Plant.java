package com.example.plantmanager.models;

import java.util.Date;

public class Plant {
    private int id;
    private String name;
    private String imagePath;
    private Date lastWatered;

    public Plant(String name, String imagePath, Date lastWatered) {
        this.name = name;
        this.imagePath = imagePath;
        this.lastWatered = lastWatered;
    }

    public Plant(int id, String name, String imagePath, Date lastWatered) {
        this(name, imagePath, lastWatered);
        this.id = id;
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
