package com.example.plantmanager;

import androidx.lifecycle.ViewModel;

import com.example.plantmanager.models.Plant;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final ArrayList<Plant> plants;

    public HomeViewModel() {
        this.plants = new ArrayList<>();
        this.plants.add(new Plant(1, "Plant1"));
        this.plants.add(new Plant(2, "Plant2"));
        this.plants.add(new Plant(3, "Plant3"));
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }
}