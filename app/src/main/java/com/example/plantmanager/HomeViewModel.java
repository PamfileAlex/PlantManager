package com.example.plantmanager;

import androidx.lifecycle.ViewModel;

import com.example.plantmanager.models.Plant;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final ArrayList<Plant> plants;

    public HomeViewModel() {
        this.plants = new ArrayList<>();
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }
}