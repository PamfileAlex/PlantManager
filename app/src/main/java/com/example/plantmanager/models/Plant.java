package com.example.plantmanager.models;

import android.graphics.Bitmap;

import java.util.Date;

public class Plant {
    private int id;
    private String name;
    private Bitmap image;
    private Date lastWatered;
    private int idCategory;

    public Plant(String name, Bitmap image, Date lastWatered, int idCategory) {
        this.name = name;
        this.image = image;
        this.lastWatered = lastWatered;
        this.idCategory = idCategory;
    }

    public Plant(int id, String name, Bitmap image, Date lastWatered, int idCategory) {
        this(name, image, lastWatered, idCategory);
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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
