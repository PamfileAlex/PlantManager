package com.example.plantmanager.view_models;

import androidx.lifecycle.ViewModel;

import com.example.plantmanager.models.Plant;

public class PlantDetailsViewModel extends ViewModel {
    private Plant selectedPlant;

    public Plant getSelectedPlant() {
        return selectedPlant;
    }

    public void setSelectedPlant(Plant selectedPlant) {
        this.selectedPlant = selectedPlant;
    }
}
