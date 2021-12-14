package com.example.plantmanager.models;

import android.graphics.Bitmap;

import java.time.LocalDate;
import java.time.LocalTime;

public class Plant {
    private int id;
    private int idCategory;
    private String name;
    private Bitmap image;
    private LocalDate lastWatered;
    private LocalDate nextWater;
    private LocalTime time;
    private boolean allowNotifications;

    public Plant(int id, int idCategory, String name, Bitmap image, LocalDate lastWatered, LocalDate nextWater, LocalTime time, boolean sendNotifications) {
        this(idCategory, name, image, lastWatered, nextWater, time, sendNotifications);
        this.id = id;
    }

    public Plant(int idCategory, String name, Bitmap image, LocalDate lastWatered, LocalDate nextWater, LocalTime time, boolean sendNotifications) {
        this.idCategory = idCategory;
        this.name = name;
        this.image = image;
        this.lastWatered = lastWatered;
        this.nextWater = nextWater;
        this.time = time;
        this.allowNotifications = sendNotifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
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

    public LocalDate getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(LocalDate lastWatered) {
        this.lastWatered = lastWatered;
    }

    public LocalDate getNextWater() {
        return nextWater;
    }

    public void setNextWater(LocalDate nextWater) {
        this.nextWater = nextWater;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean getAllowNotifications() {
        return allowNotifications;
    }

    public void setAllowNotifications(boolean allowNotifications) {
        this.allowNotifications = allowNotifications;
    }
}
