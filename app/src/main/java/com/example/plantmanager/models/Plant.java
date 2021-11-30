package com.example.plantmanager.models;

import java.util.Date;

public class Plant {
    private int id;
    private String name;
    private String imagePath;
    private Date lastWatered;
    private int idCategory;

    public Plant(String name, String imagePath, Date lastWatered, int idCategory) {
        this.name = name;
        this.imagePath = imagePath;
        this.lastWatered = lastWatered;
        this.idCategory = idCategory;
    }

    public Plant(int id, String name, String imagePath, Date lastWatered, int idCategory) {
        this(name, imagePath, lastWatered, idCategory);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
