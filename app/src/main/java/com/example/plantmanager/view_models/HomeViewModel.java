package com.example.plantmanager.view_models;

import androidx.lifecycle.ViewModel;

import com.example.plantmanager.MainActivity;
import com.example.plantmanager.database.PlantDataAccess;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.models.ResourceProvider;
import com.example.plantmanager.utils.CurrentUser;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final ArrayList<Plant> plants;

    private ResourceProvider resourceProvider;

    public HomeViewModel() {
        this.resourceProvider = MainActivity.resourceProvider;
        this.plants = PlantDataAccess.getPlants(CurrentUser.INSTANCE.getUser().getId());
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }
}