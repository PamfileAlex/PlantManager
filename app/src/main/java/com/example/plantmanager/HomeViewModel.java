package com.example.plantmanager;

import androidx.lifecycle.ViewModel;

import com.example.plantmanager.database.DataAccessHelper;
import com.example.plantmanager.database.PlantDataAccess;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.models.ResourceProvider;
import com.example.plantmanager.utils.SqlConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final ArrayList<Plant> plants;

    private ResourceProvider resourceProvider;

    public HomeViewModel() {
        this.resourceProvider = MainActivity.resourceProvider;
        this.plants = PlantDataAccess.getPlants();
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }
}